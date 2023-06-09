// ExerciseEntry.java
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExerciseEntry implements Serializable {
    private String exerciseName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<QuestionResponse> questionResponses;

    public ExerciseEntry(String exerciseName) {
        this.exerciseName = exerciseName;
        this.startTime = LocalDateTime.now();
        this.endTime = null;
        this.questionResponses = new ArrayList<>();
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<QuestionResponse> getQuestionResponses() {
        return questionResponses;
    }

    public void addQuestionResponse(QuestionResponse questionResponse) {
        questionResponses.add(questionResponse);
    }

    public void setEndTime() {
        endTime = LocalDateTime.now();
    }
}
