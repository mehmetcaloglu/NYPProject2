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
    private JButton startExerciseButton;
    private JButton submitButton;

    private ExerciseLog exerciseLog;
    private ExerciseEntry currentExerciseEntry;

    private LocalDateTime exerciseStartTime;
    private boolean isAnswerCorrect = false;

    private int exerciseCount = 0;

    public ChildUserScreen(int a, int b, int N) {
        this.a = a;
        this.b = b;
        this.N = N;

        exerciseLog = new ExerciseLog();

        setTitle("Child User Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(5, 1));

        questionLabel = new JLabel();
        answerTextField = new JTextField();
        startExerciseButton = new JButton("Alıştırmaya Başla");
        submitButton = new JButton("Gönder");

        startExerciseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startExerciseButton.setEnabled(false);
                generateExercise();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int userAnswer = Integer.parseInt(answerTextField.getText());
                int responseTime = calculateResponseTime();
                boolean isCorrect = checkAnswer(userAnswer);

                QuestionResponse questionResponse = new QuestionResponse(questionLabel.getText(), responseTime, isCorrect);
                currentExerciseEntry.addQuestionResponse(questionResponse);

                if (exerciseCount >= N) {
                    endExercise();
                } else {
                    generateQuestion();
                    answerTextField.setText("");
                    exerciseCount++;
                }
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
        add(startExerciseButton);
        add(submitButton);
        add(showReportButton);

        setLocationRelativeTo(null);
    }

    private void generateExercise() {
        exerciseCount = 1;
        generateQuestion();
        startExerciseButton.setEnabled(false);
        submitButton.setEnabled(true);
    }

    private void endExercise() {
        startExerciseButton.setEnabled(true);
        submitButton.setEnabled(false);
        questionLabel.setText("");
        answerTextField.setText("");
    }

    private void generateQuestion() {
        Random random = new Random();
        int number1 = random.nextInt(a - 1) + 1;
        int number2 = random.nextInt(b - 1) + 1;
        int result = number1 * number2;
        questionLabel.setText(number1 + " * " + number2 + " = ?");
        exerciseStartTime = LocalDateTime.now();
        currentExerciseEntry = new ExerciseEntry("çarpma");
    }

    private int calculateResponseTime() {
        LocalDateTime endTime = LocalDateTime.now();
        return endTime.getSecond() - exerciseStartTime.getSecond();
    }

    private boolean checkAnswer(int userAnswer) {
        String questionText = questionLabel.getText();
        if (questionText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen bir sayı girin!", "Hata", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String correctAnswer = "?";
        if (questionText.contains("=")) {
            correctAnswer = questionText.substring(questionText.indexOf("=") + 1).trim();
        }

        boolean isCorrect = Integer.toString(userAnswer).equals(correctAnswer);
        isAnswerCorrect = isCorrect;

        return isCorrect;
    }

    public ExerciseLog getExerciseLog() {
        return exerciseLog;
    }

    public static void main(String[] args) {
        ChildUserScreen childUserScreen = new ChildUserScreen(10, 10, 5);
        childUserScreen.setVisible(true);
    }
}
