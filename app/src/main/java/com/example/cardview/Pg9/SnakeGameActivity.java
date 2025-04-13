package com.example.cardview.Pg9;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SnakeView snakeView = new SnakeView(this);
        setContentView(snakeView);
    }
}

class SnakeView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying = true;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder holder;

    private ArrayList<Rect> snakeParts;
    private Rect food;

    private int direction = 0; // 0=Right, 1=Down, 2=Left, 3=Up
    private int blockSize = 60;
    private int screenWidth, screenHeight;

    private long moveDelay = 200;
    private long lastMove;

    private Random random = new Random();

    public SnakeView(Context context) {
        super(context);
        holder = getHolder();
        paint = new Paint();
        snakeParts = new ArrayList<>();

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        int startX = blockSize * 5;
        int startY = blockSize * 5;

        for (int i = 0; i < 3; i++) {
            snakeParts.add(new Rect(startX - i * blockSize, startY, startX - i * blockSize + blockSize, startY + blockSize));
        }

        spawnFood();
    }

    private void spawnFood() {
        int x = random.nextInt(screenWidth / blockSize) * blockSize;
        int y = random.nextInt(screenHeight / blockSize) * blockSize;
        food = new Rect(x, y, x + blockSize, y + blockSize);
    }

    @Override
    public void run() {
        while (isPlaying) {
            if (System.currentTimeMillis() - lastMove > moveDelay) {
                update();
                draw();
                lastMove = System.currentTimeMillis();
            }
        }
    }

    private void update() {
        Rect head = snakeParts.get(0);
        int newX = head.left;
        int newY = head.top;

        switch (direction) {
            case 0: newX += blockSize; break;
            case 1: newY += blockSize; break;
            case 2: newX -= blockSize; break;
            case 3: newY -= blockSize; break;
        }

        if (newX < 0 || newY < 0 || newX >= screenWidth || newY >= screenHeight) {
            isPlaying = false;
            return;
        }

        Rect newHead = new Rect(newX, newY, newX + blockSize, newY + blockSize);

        for (Rect part : snakeParts) {
            if (part.intersect(newHead)) {
                isPlaying = false;
                return;
            }
        }

        snakeParts.add(0, newHead);

        if (newHead.intersect(food)) {
            spawnFood();
        } else {
            snakeParts.remove(snakeParts.size() - 1);
        }
    }

    private void draw() {
        if (holder.getSurface().isValid()) {
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.GREEN);
            for (Rect part : snakeParts) {
                canvas.drawRect(part, paint);
            }

            paint.setColor(Color.RED);
            canvas.drawRect(food, paint);

            if (!isPlaying) {
                paint.setColor(Color.WHITE);
                paint.setTextSize(80);
                canvas.drawText("Game Over", screenWidth / 3f, screenHeight / 2f, paint);
            }

            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        Rect head = snakeParts.get(0);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (direction == 0 || direction == 2) {
                if (y < head.top) direction = 3;
                else direction = 1;
            } else {
                if (x < head.left) direction = 2;
                else direction = 0;
            }
        }
        return true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
