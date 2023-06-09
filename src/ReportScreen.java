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
            String exercise = exerciseEntry.getExerciseName(); // Alıştırma adını alma
            String date = exerciseEntry.getStartTime().toString(); // Başlangıç zamanını alma
            String userAnswer = "";
            for (QuestionResponse questionResponse : exerciseEntry.getQuestionResponses()) {
                userAnswer += questionResponse.getQuestion() + ": " + questionResponse.getResponseTime() + " sn";
                if (!questionResponse.isCorrect()) {
                    userAnswer += " (Yanlış)\n";
                } else {
                    userAnswer += "\n";
                }
            }
            logsBuilder.append("Alıştırma: ").append(exercise).append("\n");
            logsBuilder.append("Başlangıç tarihi: ").append(date).append("\n");
            logsBuilder.append("Cevaplar:\n").append(userAnswer).append("\n");
        }

        exerciseLogsTextArea.setText(logsBuilder.toString());
    }

    public static void main(String[] args) {
        ChildUserScreen childUserScreen = new ChildUserScreen(10, 10, 5);
        new ReportScreen(childUserScreen.getExerciseLog());
    }
}
