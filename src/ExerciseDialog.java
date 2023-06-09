import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciseDialog extends JDialog {
    private Exercise exercise;
    private JTextField answerField;
    private JButton submitButton;
    private boolean exerciseCompleted;
    private boolean isCorrectAnswer;

    public ExerciseDialog(JFrame parent, Exercise exercise) {
        super(parent, "Exercise Dialog", true);
        this.exercise = exercise;
        this.exerciseCompleted = false;

        initComponents();
        setupLayout();
        setupListeners();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        answerField = new JTextField();
        submitButton = new JButton("Submit");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(new JLabel("Exercise: " + exercise.toString()), BorderLayout.NORTH);
        add(answerField, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = answerField.getText();
                isCorrectAnswer = checkAnswer(answer);
                exerciseCompleted = true;
                dispose();
            }
        });
    }

    private boolean checkAnswer(String answer) {
        // Burada cevabın doğruluğunu kontrol etmek için gerekli kodu ekleyin
        // Doğru veya yanlış olduğuna dair bir değer döndürün
        // Örnek olarak sadece "dogru" cevabını doğru olarak kabul edelim
        return answer.equalsIgnoreCase("dogru");
    }

    public boolean isExerciseCompleted() {
        return exerciseCompleted;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public String getAnswer() {
        return answerField.getText();
    }
}
