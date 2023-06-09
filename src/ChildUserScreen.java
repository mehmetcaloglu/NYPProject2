// ChildUserScreen.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ChildUserScreen extends JFrame {

    private int a;
    private int b;
    private int N;

    private JLabel questionLabel;
    private JTextField answerTextField;
    private JButton submitButton;

    private ExerciseLog exerciseLog;

    private LocalDateTime exerciseStartTime;

    public ChildUserScreen(int a, int b, int N) {
        this.a = a;
        this.b = b;
        this.N = N;

        exerciseLog = new ExerciseLog();

        setTitle("Child User Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));

        questionLabel = new JLabel();
        generateQuestion();

        answerTextField = new JTextField();

        submitButton = new JButton("Gönder");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int userAnswer = Integer.parseInt(answerTextField.getText());
                int responseTime = calculateResponseTime();
                boolean isCorrect = checkAnswer(userAnswer);

                QuestionResponse questionResponse = new QuestionResponse(questionLabel.getText(), responseTime, isCorrect);
                exerciseLog.getCurrentExerciseEntry().addQuestionResponse(questionResponse);

                generateQuestion();
                answerTextField.setText("");
            }
        });

        JButton showReportButton = new JButton("Raporu Göster");
        showReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReportScreen reportScreen = new ReportScreen(exerciseLog);
                reportScreen.setVisible(true);
            }
        });

        add(questionLabel);
        add(answerTextField);
        add(submitButton);
        add(showReportButton);

        setLocationRelativeTo(null);
    }

    private void generateQuestion() {
        Random random = new Random();
        int number1 = random.nextInt(a) + 1;
        int number2 = random.nextInt(b) + 1;
        int result = number1 + number2;
        questionLabel.setText(number1 + " + " + number2 + " = ?");
        exerciseStartTime = LocalDateTime.now();
    }

    private int calculateResponseTime() {
        LocalDateTime endTime = LocalDateTime.now();
        return endTime.getSecond() - exerciseStartTime.getSecond();
    }

    private boolean checkAnswer(int userAnswer) {
        int number1 = Integer.parseInt(questionLabel.getText().split("\\+")[0].trim());
        int number2 = Integer.parseInt(questionLabel.getText().split("\\+")[1].trim());
        int correctAnswer = number1 + number2;
        return userAnswer == correctAnswer;
    }

    public static void main(String[] args) {
        ChildUserScreen childUserScreen = new ChildUserScreen(10, 10, 5);
        childUserScreen.setVisible(true);
    }
}
