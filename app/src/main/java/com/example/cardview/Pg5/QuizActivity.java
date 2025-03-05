package com.example.cardview.Pg5;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.cardview.R;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    private TextView questionText;
    private Button option1, option2, option3, option4;
    private LottieAnimationView confettiView;

    private String[][] questions = QuestionBank.getQuestions();
    private int currentQuestionIndex;
    private String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        confettiView = findViewById(R.id.confettiView);

        loadNewQuestion();

        View.OnClickListener answerClickListener = view -> {
            Button clickedButton = (Button) view;
            if (clickedButton.getText().toString().equals(correctAnswer)) {
                showConfetti();  // 🎉 Show confetti animation
            } else {
                shakeButton(clickedButton); // 🔴 Wrong answer effect
            }
            loadNewQuestion();
        };

        option1.setOnClickListener(answerClickListener);
        option2.setOnClickListener(answerClickListener);
        option3.setOnClickListener(answerClickListener);
        option4.setOnClickListener(answerClickListener);
    }

    private void loadNewQuestion() {
        Random random = new Random();
        currentQuestionIndex = random.nextInt(questions.length);

        questionText.setText(questions[currentQuestionIndex][0]);
        option1.setText(questions[currentQuestionIndex][1]);
        option2.setText(questions[currentQuestionIndex][2]);
        option3.setText(questions[currentQuestionIndex][3]);
        option4.setText(questions[currentQuestionIndex][4]);

        correctAnswer = questions[currentQuestionIndex][5];
    }

    private void showConfetti() {
        confettiView.setVisibility(View.VISIBLE);
        confettiView.playAnimation();  // Play Lottie animation 🎉
    }

    private void shakeButton(Button button) {
        ObjectAnimator shake = ObjectAnimator.ofFloat(button, "translationX", 0, 10, -10, 10, -10, 0);
        shake.setDuration(300);
        shake.start();
    }
}
