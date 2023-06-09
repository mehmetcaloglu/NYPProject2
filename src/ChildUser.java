import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChildUser extends User {
    private ChildUserScreen childUserScreen;

    private List<ExerciseEntry> exerciseLog;

    public ChildUser(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
        this.exerciseLog = new ArrayList<>();
    }

    public ChildUserScreen getChildUserScreen() {
        return childUserScreen;
    }

    public void setChildUserScreen(ChildUserScreen childUserScreen) {
        this.childUserScreen = childUserScreen;
    }

    public List<ExerciseEntry> getExerciseLog() {
        return exerciseLog;
    }

    public void addToExerciseLog(ExerciseEntry exerciseEntry) {
        exerciseLog.add(exerciseEntry);
    }
}

