package com.example.cardview.Pg5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import com.example.cardview.R;

public class QuizActivity extends AppCompatActivity {
    private TextView questionText;
    private Button option1, option2, option3, option4;
    private QuestionData questionData;
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

        questionData = new QuestionData();
        loadNewQuestion();

        View.OnClickListener answerListener = view -> {
            Button clickedButton = (Button) view;
            checkAnswer(clickedButton.getText().toString());
        };

        option1.setOnClickListener(answerListener);
        option2.setOnClickListener(answerListener);
        option3.setOnClickListener(answerListener);
        option4.setOnClickListener(answerListener);
    }

    private void loadNewQuestion() {
        Random random = new Random();
        currentQuestionIndex = random.nextInt(questionData.questions.length);
        String[] questionSet = questionData.questions[currentQuestionIndex];

        questionText.setText(questionSet[0]);
        option1.setText(questionSet[1]);
        option2.setText(questionSet[2]);
        option3.setText(questionSet[3]);
        option4.setText(questionSet[4]);

        correctAnswer = questionSet[5]; // Store correct answer
    }

    private void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(correctAnswer)) {
            showConfetti();
        } else {
            showWrongAnimation();
        }
        loadNewQuestion();
    }

    private void showConfetti() {
        findViewById(R.id.confettiAnimation).setVisibility(View.VISIBLE);
        Toast.makeText(this, "🎉 Correct Answer!", Toast.LENGTH_SHORT).show();
    }

    private void showWrongAnimation() {
        findViewById(R.id.wrongAnimation).setVisibility(View.VISIBLE);
        Toast.makeText(this, "❌ Wrong Answer!", Toast.LENGTH_SHORT).show();
    }
}
