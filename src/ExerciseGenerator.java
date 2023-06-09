import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExerciseGenerator {
    private int minValue;
    private int maxValue;
    private int questionCount;

    public ExerciseGenerator(int minValue, int maxValue, int questionCount) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.questionCount = questionCount;
    }

    public List<Exercise> generateExercises() {
        List<Exercise> exercises = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < questionCount; i++) {
            int number1 = random.nextInt(maxValue - minValue + 1) + minValue;
            int number2 = random.nextInt(maxValue - minValue + 1) + minValue;

            exercises.add(new Exercise(number1, number2));
        }

        return exercises;
    }
}
