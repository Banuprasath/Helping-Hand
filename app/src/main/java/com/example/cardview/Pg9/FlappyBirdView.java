package com.example.cardview.Pg9;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class FlappyBirdView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private SurfaceHolder holder;
    private Paint paint;
    private Paint backgroundPaint;
    private Paint cloudPaint;
    private Paint scorePaint;
    private Paint gameOverPaint;
    private Paint buttonPaint;
    private Paint buttonTextPaint;

    private float birdY = 500;
    private float birdRotation = 0;
    private float velocity = 0;
    private float gravity = 0.6f;
    private int score = 0;
    private int highScore = 0;

    private ArrayList<Pipe> pipes;
    private ArrayList<Cloud> clouds;
    private int pipeSpeed = 8;
    private int gap = 420;
    private Random random = new Random();

    private boolean gameOver = false;
    private boolean gameStarted = false;

    // Bird animation
    private float wingPosition = 0;
    private float wingDirection = 1;

    // UI elements
    private RectF playButton;

    // Colors
    private final int SKY_TOP = Color.parseColor("#64B5F6");
    private final int SKY_BOTTOM = Color.parseColor("#1976D2");
    private final int BIRD_COLOR = Color.parseColor("#FFC107");
    private final int BIRD_WING_COLOR = Color.parseColor("#FF6F00");
    private final int BIRD_EYE_COLOR = Color.BLACK;
    private final int PIPE_TOP_COLOR = Color.parseColor("#388E3C");
    private final int PIPE_BODY_COLOR = Color.parseColor("#4CAF50");
    private final int GROUND_COLOR = Color.parseColor("#795548");
    private final int CLOUD_COLOR = Color.argb(150, 255, 255, 255);

    public FlappyBirdView(Context context) {
        super(context);
        init();
    }

    public FlappyBirdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        holder = getHolder();
        paint = new Paint();

        // Background gradient paint
        backgroundPaint = new Paint();

        // Cloud paint
        cloudPaint = new Paint();
        cloudPaint.setColor(CLOUD_COLOR);
        cloudPaint.setAntiAlias(true);

        // Score paint
        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(80);
        scorePaint.setTextAlign(Paint.Align.CENTER);
        scorePaint.setShadowLayer(5, 2, 2, Color.BLACK);
        scorePaint.setAntiAlias(true);
        scorePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        // Game over paint
        gameOverPaint = new Paint();
        gameOverPaint.setColor(Color.WHITE);
        gameOverPaint.setTextSize(120);
        gameOverPaint.setTextAlign(Paint.Align.CENTER);
        gameOverPaint.setShadowLayer(10, 3, 3, Color.BLACK);
        gameOverPaint.setAntiAlias(true);
        gameOverPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        // Button paint
        buttonPaint = new Paint();
        buttonPaint.setColor(Color.parseColor("#4CAF50"));
        buttonPaint.setAntiAlias(true);

        // Button text paint
        buttonTextPaint = new Paint();
        buttonTextPaint.setColor(Color.WHITE);
        buttonTextPaint.setTextSize(60);
        buttonTextPaint.setTextAlign(Paint.Align.CENTER);
        buttonTextPaint.setAntiAlias(true);
        buttonTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        pipes = new ArrayList<>();
        clouds = new ArrayList<>();

        // Add initial clouds
        for (int i = 0; i < 5; i++) {
            addCloud();
        }

        playButton = new RectF();
    }

    private void addPipe() {
        int canvasHeight = getHeight() > 0 ? getHeight() - 200 : 1800; // Leave space for ground
        int minGapPosition = 300;
        int maxGapPosition = canvasHeight - gap - 300;
        int offset = minGapPosition + random.nextInt(maxGapPosition - minGapPosition);
        pipes.add(new Pipe(getWidth(), offset));
    }

    private void addCloud() {
        int y = 100 + random.nextInt(400);
        int size = 70 + random.nextInt(100);
        int speed = 1 + random.nextInt(3);
        clouds.add(new Cloud(getWidth() + random.nextInt(500), y, size, speed));
    }

    @Override
    public void run() {
        while (isPlaying) {
            if (!holder.getSurface().isValid()) continue;

            update();
            draw();
            sleep();
        }
    }

    private void update() {
        // Update clouds
        for (int i = 0; i < clouds.size(); i++) {
            Cloud c = clouds.get(i);
            c.x -= c.speed;

            if (c.x + c.size < 0) {
                clouds.remove(i);
                i--;
                addCloud();
            }
        }

        if (!gameStarted) {
            // Update wing animation for menu state
            updateWingAnimation();
            return;
        }

        if (!gameOver) {
            // Bird physics
            velocity += gravity;
            birdY += velocity;

            // Set bird rotation based on velocity
            birdRotation = Math.max(-30, Math.min(90, velocity * 2));

            // Update wing animation
            updateWingAnimation();

            // Add new pipe when needed
            if (pipes.size() == 0 || pipes.get(pipes.size() - 1).x < getWidth() - 400) {
                addPipe();
            }

            // Update pipes
            for (int i = 0; i < pipes.size(); i++) {
                Pipe p = pipes.get(i);
                p.x -= pipeSpeed;

                if (!p.passed && p.x < 200) {
                    score++;
                    highScore = Math.max(score, highScore);
                    p.passed = true;
                }

                // Collision detection with more accurate hitbox
                if (200 + 30 > p.x && 200 - 30 < p.x + 80) {
                    if (birdY - 30 < p.gapTop || birdY + 30 > p.gapTop + gap) {
                        gameOver = true;
                    }
                }

                if (p.x + 80 < 0) {
                    pipes.remove(i);
                    i--;
                }
            }

            // Ground or ceiling collision
            if (birdY > getHeight() - 150 || birdY < 50) {
                gameOver = true;
                if (birdY > getHeight() - 150) {
                    birdY = getHeight() - 150;
                }
            }
        } else {
            // Game over state
            if (birdY < getHeight() - 150) {
                velocity += gravity;
                birdY += velocity;
                birdRotation = Math.min(90, birdRotation + 5);
            } else {
                birdY = getHeight() - 150;
                velocity = 0;
            }
        }
    }

    private void updateWingAnimation() {
        wingPosition += wingDirection * 0.2f;
        if (wingPosition > 1.0f || wingPosition < -1.0f) {
            wingDirection *= -1;
        }
    }

    private void draw() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if (canvas != null) {
                drawGame(canvas);
            }
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void drawGame(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        // Draw sky gradient background
        LinearGradient skyGradient = new LinearGradient(0, 0, 0, height - 150,
                SKY_TOP, SKY_BOTTOM, Shader.TileMode.CLAMP);
        backgroundPaint.setShader(skyGradient);
        canvas.drawRect(0, 0, width, height - 150, backgroundPaint);

        // Draw clouds
        for (Cloud c : clouds) {
            drawCloud(canvas, c.x, c.y, c.size);
        }

        // Draw pipes
        for (Pipe p : pipes) {
            drawPipe(canvas, p);
        }

        // Draw ground
        paint.setColor(GROUND_COLOR);
        canvas.drawRect(0, height - 150, width, height, paint);

        // Draw ground detail lines
        paint.setColor(Color.parseColor("#5D4037"));
        for (int i = 0; i < width; i += 30) {
            canvas.drawLine(i, height - 150, i + 15, height - 130, paint);
        }

        // Draw bird
        drawBird(canvas, 200, birdY, birdRotation);

        // Draw score
        if (gameStarted) {
            canvas.drawText(String.valueOf(score), width / 2f, 150, scorePaint);
        }

        // Draw game over UI
        if (gameOver) {
            // Semi-transparent overlay
            paint.setColor(Color.argb(120, 0, 0, 0));
            canvas.drawRect(0, 0, width, height, paint);

            // Game over text
            canvas.drawText("GAME OVER", width / 2f, height / 3f, gameOverPaint);

            // Score display
            canvas.drawText("Score: " + score, width / 2f, height / 3f + 120, scorePaint);
            canvas.drawText("Best: " + highScore, width / 2f, height / 3f + 220, scorePaint);

            // Play again button
            int buttonWidth = 400;
            int buttonHeight = 120;
            playButton.set(width / 2f - buttonWidth / 2f, height * 2 / 3f - buttonHeight / 2f,
                    width / 2f + buttonWidth / 2f, height * 2 / 3f + buttonHeight / 2f);

            // Button with shadow
            paint.setColor(Color.argb(50, 0, 0, 0));
            canvas.drawRoundRect(playButton.left + 5, playButton.top + 5,
                    playButton.right + 5, playButton.bottom + 5, 20, 20, paint);

            canvas.drawRoundRect(playButton, 20, 20, buttonPaint);
            canvas.drawText("PLAY AGAIN", playButton.centerX(),
                    playButton.centerY() + buttonTextPaint.getTextSize() / 3, buttonTextPaint);
        } else if (!gameStarted) {
            // Title and tap to play
            canvas.drawText("FLAPPY BIRD", width / 2f, height / 3f, gameOverPaint);
            canvas.drawText("Tap to Start", width / 2f, height / 2f, scorePaint);
        }
    }

    private void drawBird(Canvas canvas, float x, float y, float rotation) {
        canvas.save();
        canvas.rotate(rotation, x, y);

        // Bird body
        paint.setColor(BIRD_COLOR);
        RectF birdBody = new RectF(x - 30, y - 30, x + 40, y + 30);
        canvas.drawOval(birdBody, paint);

        // Wing
        paint.setColor(BIRD_WING_COLOR);
        float wingOffset = 10 * Math.abs(wingPosition);
        RectF wing = new RectF(x - 10, y - 5 - wingOffset, x + 20, y + 15 - wingOffset);
        canvas.drawOval(wing, paint);

        // Face details
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x + 25, y - 10, 12, paint);

        paint.setColor(BIRD_EYE_COLOR);
        canvas.drawCircle(x + 25, y - 10, 6, paint);

        paint.setColor(Color.parseColor("#FF5722")); // Orange beak
        paint.setStrokeWidth(3);
        canvas.drawLine(x + 35, y, x + 50, y, paint);
        canvas.drawLine(x + 50, y, x + 40, y + 10, paint);
        canvas.drawLine(x + 40, y + 10, x + 35, y, paint);

        canvas.restore();
    }

    private void drawPipe(Canvas canvas, Pipe p) {
        int pipeWidth = 80;

        // Top pipe
        paint.setColor(PIPE_BODY_COLOR);
        canvas.drawRect(p.x, 0, p.x + pipeWidth, p.gapTop, paint);

        // Bottom pipe
        canvas.drawRect(p.x, p.gapTop + gap, p.x + pipeWidth, getHeight() - 150, paint);

        // Pipe edges (darker green)
        paint.setColor(PIPE_TOP_COLOR);

        // Top pipe edge
        RectF topEdge = new RectF(p.x - 10, p.gapTop - 30, p.x + pipeWidth + 10, p.gapTop);
        canvas.drawRect(topEdge, paint);

        // Bottom pipe edge
        RectF bottomEdge = new RectF(p.x - 10, p.gapTop + gap, p.x + pipeWidth + 10, p.gapTop + gap + 30);
        canvas.drawRect(bottomEdge, paint);
    }

    private void drawCloud(Canvas canvas, float x, float y, float size) {
        canvas.drawCircle(x, y, size * 0.5f, cloudPaint);
        canvas.drawCircle(x + size * 0.4f, y - size * 0.2f, size * 0.4f, cloudPaint);
        canvas.drawCircle(x + size * 0.8f, y, size * 0.5f, cloudPaint);
        canvas.drawCircle(x + size * 0.4f, y + size * 0.2f, size * 0.4f, cloudPaint);
    }

    private void sleep() {
        try {
            Thread.sleep(16); // ~60 fps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            float touchY = event.getY();

            if (!gameStarted) {
                gameStarted = true;
                return true;
            }

            if (!gameOver) {
                velocity = -12;
            } else if (playButton.contains(touchX, touchY)) {
                restartGame();
            }
        }
        return true;
    }

    private void restartGame() {
        birdY = 500;
        velocity = 0;
        birdRotation = 0;
        score = 0;
        pipes.clear();
        gameOver = false;
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Inner class for Pipe
    static class Pipe {
        int x;
        int gapTop;
        boolean passed = false;

        public Pipe(int x, int gapTop) {
            this.x = x;
            this.gapTop = gapTop;
        }
    }

    // Inner class for Cloud
    static class Cloud {
        float x;
        float y;
        float size;
        float speed;

        public Cloud(float x, float y, float size, float speed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
        }
    }
}