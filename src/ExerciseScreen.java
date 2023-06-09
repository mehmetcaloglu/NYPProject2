import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExerciseScreen {
    private List<Exercise> exercises;
    private int currentExerciseIndex;

    public ExerciseScreen(List<Exercise> exercises) {
        this.exercises = exercises;
        this.currentExerciseIndex = 0;
    }

    public void showExerciseScreen() {
        JFrame frame = new JFrame("Exercise Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Exercise " + (currentExerciseIndex + 1));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);

        Exercise currentExercise = exercises.get(currentExerciseIndex);
        JLabel questionLabel = new JLabel(currentExercise.getQuestion());
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(questionLabel, BorderLayout.CENTER);

        JTextField answerField = new JTextField();
        frame.add(answerField, BorderLayout.SOUTH);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userAnswer = Integer.parseInt(answerField.getText());
                currentExercise.setUserAnswer(userAnswer);
                nextExercise(frame);
            }
        });
        frame.add(submitButton, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void nextExercise(JFrame frame) {
        currentExerciseIndex++;
        if (currentExerciseIndex < exercises.size()) {
            frame.dispose();
            showExerciseScreen();
        } else {
            frame.dispose();
            showExerciseCompleteScreen();
        }
    }

    private void showExerciseCompleteScreen() {
        JFrame completeFrame = new JFrame("Exercise Complete");
        completeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        completeFrame.setSize(300, 200);
        completeFrame.setLayout(new BorderLayout());

        JLabel completeLabel = new JLabel("Exercise completed!");
        completeLabel.setHorizontalAlignment(JLabel.CENTER);
        completeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        completeFrame.add(completeLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeFrame.dispose();
            }
        });
        completeFrame.add(closeButton, BorderLayout.SOUTH);

        completeFrame.setVisible(true);
    }
}
