import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
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

    public ChildUserScreen(int a, int b, int N) {
        this.a = a;
        this.b = b;
        this.N = N;

        exerciseLog = new ExerciseLog();
        currentExerciseEntry = new ExerciseEntry("name");

        setTitle("Child User Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));

        questionLabel = new JLabel();
        answerTextField = new JTextField();
        startExerciseButton = new JButton("Alıştırmaya Başla");
        submitButton = new JButton("Gönder");

        startExerciseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startExercise();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int userAnswer = Integer.parseInt(answerTextField.getText());
                int responseTime = calculateResponseTime();
                boolean isCorrect = checkAnswer(userAnswer);

                QuestionResponse questionResponse = new QuestionResponse(questionLabel.getText(), responseTime, isCorrect);
                currentExerciseEntry.addQuestionResponse(questionResponse);

                if (currentExerciseEntry.getQuestionResponses().size() == N) {
                    endExercise();
                } else {
                    generateQuestion();
                    answerTextField.setText("");
                }
            }
        });

        add(questionLabel);
        add(answerTextField);
        add(startExerciseButton);
        add(submitButton);

        setLocationRelativeTo(null);
    }

    private void startExercise() {
        generateQuestion();
        startExerciseButton.setEnabled(false);
    }

    private void generateQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(a) + 1;
        int num2 = random.nextInt(b) + 1;
        int result = num1 * num2;

        questionLabel.setText(num1 + " * " + num2 + " = ");
        exerciseStartTime = LocalDateTime.now();
        isAnswerCorrect = false;
        answerTextField.setEnabled(true);
        answerTextField.requestFocus();
    }

    private boolean checkAnswer(int userAnswer) {
        int num1 = Integer.parseInt(questionLabel.getText().split(" ")[0]);
        int num2 = Integer.parseInt(questionLabel.getText().split(" ")[2]);
        int result = num1 * num2;

        if (userAnswer == result) {
            isAnswerCorrect = true;
            return true;
        } else {
            isAnswerCorrect = false;
            return false;
        }
    }

    private int calculateResponseTime() {
        LocalDateTime now = LocalDateTime.now();
        return (int) java.time.Duration.between(exerciseStartTime, now).getSeconds();
    }

    private void endExercise() {
        answerTextField.setEnabled(false);
        startExerciseButton.setEnabled(true);
        exerciseLog.addExerciseEntry(currentExerciseEntry);
        currentExerciseEntry = new ExerciseEntry("name"); // currentExerciseEntry'nin yeniden oluşturulması

        int correctCount = 0;
        int incorrectCount = 0;

        for (QuestionResponse questionResponse : exerciseLog.getExerciseEntries().get(0).getQuestionResponses()) {
            if (questionResponse.isCorrect()) {
                correctCount++;
            } else {
                incorrectCount++;
            }
        }

        String message = "Doğru Sayısı: " + correctCount + "\nYanlış Sayısı: " + incorrectCount;
        JOptionPane.showMessageDialog(null, message, "Alıştırma Tamamlandı", JOptionPane.INFORMATION_MESSAGE);
    }

}
