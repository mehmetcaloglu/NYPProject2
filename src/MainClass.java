import javax.swing.JOptionPane;
import java.io.*;

public class MainClass {

    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Kullanıcı Adı:");
        String password = JOptionPane.showInputDialog("Şifre:");

        if (username.equals("admin") && password.equals("admin123")) {
            // Admin kullanıcısı, ParentUserScreen'i aç
            ExerciseSettings settings = ExerciseSettings.loadSettings();
            ParentUserScreen parentUserScreen = new ParentUserScreen(settings);
            parentUserScreen.setVisible(true);
        } else {
            // Diğer kullanıcılar için kontrol yap
            ChildUserScreen childUserScreen = loadChildUserScreen(username, password);
            if (childUserScreen != null) {
                childUserScreen.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Geçersiz kullanıcı adı veya şifre!");
            }
        }
    }


    private static ChildUserScreen loadChildUserScreen(String username, String password) {
        try {
            FileInputStream fileInputStream = new FileInputStream("users.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            ChildUser childUser;
            while ((childUser = (ChildUser) objectInputStream.readObject()) != null) {
                if (childUser.getUsername().equals(username) && childUser.getPassword().equals(password)) {
                    objectInputStream.close();
                    ExerciseSettings settings = ExerciseSettings.loadSettings();
                    return new ChildUserScreen(settings.getA(), settings.getB(), settings.getN());
                }
            }

            objectInputStream.close();
        } catch (EOFException e) {
            // Dosya sonuna ulaşıldığında EOFException fırlatılır, bu durumu kontrol etmek gerekir
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
