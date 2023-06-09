import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportScreen extends JFrame {

    private ExerciseLog exerciseLog;

    private JTextArea exerciseLogsTextArea;

    public ReportScreen(ExerciseLog exerciseLog) {
        this.exerciseLog = exerciseLog;

        setTitle("Egzersiz Günlükleri");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        exerciseLogsTextArea = new JTextArea();
        exerciseLogsTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(exerciseLogsTextArea);

        JButton showLogsButton = new JButton("Günlükleri Göster");
        showLogsButton.addActionListener(e -> showExerciseLogs());

        add(scrollPane, BorderLayout.CENTER);
        add(showLogsButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void showExerciseLogs() {
        List<ExerciseEntry> exerciseEntries = exerciseLog.getExerciseEntries();

        StringBuilder logsBuilder = new StringBuilder();
        for (ExerciseEntry exerciseEntry : exerciseEntries) {
            logsBuilder.append("Egzersiz: ").append(exerciseEntry.getExercise())
                    .append(", Tarih: ").append(exerciseEntry.getDate())
                    .append(", Cevap: ").append(exerciseEntry.getUserAnswer())
                    .append("\n");
        }

        exerciseLogsTextArea.setText(logsBuilder.toString());
    }

    public static void main(String[] args) {
        ChildUserScreen childUserScreen = new ChildUserScreen(10, 10, 5);
        new ReportScreen(childUserScreen.getExerciseLog());
    }
}
