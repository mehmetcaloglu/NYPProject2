import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ExerciseDataStorage {
    private static final String FILE_PATH = "exercisedata.txt";

    public static void saveExerciseData(int a, int b, int N) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(a + "\n");
            writer.write(b + "\n");
            writer.write(N + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] loadExerciseData() {
        int[] exerciseData = new int[3];
        try {
            FileReader reader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);
            for (int i = 0; i < exerciseData.length; i++) {
                exerciseData[i] = Integer.parseInt(bufferedReader.readLine());
            }
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exerciseData;
    }
}
