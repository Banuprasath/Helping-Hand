package com.example.cardview.Pg5;

public class QuestionBank {
    public static String[][] getQuestions() {
        return new String[][]{
                // Format: {Question, Option1, Option2, Option3, Option4, CorrectAnswer}
                {"What is the size of int in Java?", "2 bytes", "4 bytes", "8 bytes", "Depends on OS", "4 bytes"},
                {"Which data structure uses LIFO?", "Queue", "Stack", "Linked List", "Tree", "Stack"},
                {"What is the parent class of all Java classes?", "Object", "Main", "Class", "Root", "Object"},
                {"Which collection allows unique elements in Java?", "List", "Queue", "Set", "Array", "Set"},
                {"Which Android component is used for background services?", "Activity", "Fragment", "Service", "BroadcastReceiver", "Service"},
                {"Which method is used to start a new Activity in Android?", "startActivity()", "launch()", "openActivity()", "navigate()", "startActivity()"},
                {"Which database is used in Android for local storage?", "MySQL", "MongoDB", "SQLite", "Firebase", "SQLite"},
                {"Which class is used for JSON parsing in Android?", "JSONParser", "JSONObject", "Gson", "JsonReader", "JSONObject"},
                {"Which Android UI component is used for swipe gestures?", "ScrollView", "ViewPager", "RecyclerView", "ListView", "ViewPager"},
                {"Which keyword is used to handle exceptions in Java?", "catch", "error", "try", "throw", "try"}
        };
    }
}
