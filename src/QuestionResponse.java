// QuestionResponse.java
import java.io.Serializable;

public class QuestionResponse implements Serializable {
    private String question;
    private int responseTime;
    private boolean isCorrect;

    public QuestionResponse(String question, int responseTime, boolean isCorrect) {
        this.question = question;
        this.responseTime = responseTime;
        this.isCorrect = isCorrect;
    }

    public String getQuestion() {
        return question;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
