import java.io.*;

public class ExerciseSettings implements Serializable {
    private int a;
    private int b;
    private int N;

    public ExerciseSettings(int a, int b, int N) {
        this.a = a;
        this.b = b;
        this.N = N;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public static ExerciseSettings loadSettings() {
        ExerciseSettings settings = null;
        File file = new File("settings.ser");

        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                settings = (ExerciseSettings) objectInputStream.readObject();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (settings == null) {
            settings = new ExerciseSettings(0, 0, 0);
            ExerciseSettings.saveSettings(settings);
        }

        return settings;
    }

    public static void saveSettings(ExerciseSettings settings) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("settings.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(settings);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
