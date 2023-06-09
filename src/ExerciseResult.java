import java.io.Serializable;

public class ExerciseResult implements Serializable {
    private Exercise exercise;
    private boolean isCorrect;
    private int elapsedTime;

    public ExerciseResult(Exercise exercise, boolean isCorrect, int elapsedTime) {
        this.exercise = exercise;
        this.isCorrect = isCorrect;
        this.elapsedTime = elapsedTime;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }
}
