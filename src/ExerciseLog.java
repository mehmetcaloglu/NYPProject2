// ExerciseLog.java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseLog implements Serializable {
    private List<ExerciseEntry> exerciseEntries;

    public ExerciseLog() {
        exerciseEntries = new ArrayList<>();
    }

    public void addExerciseEntry(ExerciseEntry exerciseEntry) {
        exerciseEntries.add(exerciseEntry);
    }

    public List<ExerciseEntry> getExerciseEntries() {
        return exerciseEntries;
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExerciseLog loadFromFile(String fileName) {
        ExerciseLog exerciseLog = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            exerciseLog = (ExerciseLog) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return exerciseLog;
    }
}
