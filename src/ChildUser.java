import java.io.*;

public class ChildUser implements Serializable {
    private String username;
    private String password;

    public ChildUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static void saveChildUser(ChildUser childUser) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("users.ser", true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream) {
                @Override
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            };
            objectOutputStream.writeObject(childUser);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ChildUser loadChildUser(String username, String password) {
        try {
            FileInputStream fileInputStream = new FileInputStream("users.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ChildUser childUser;

            while (fileInputStream.available() > 0) {
                childUser = (ChildUser) objectInputStream.readObject();
                if (childUser.getUsername().equals(username) && childUser.getPassword().equals(password)) {
                    objectInputStream.close();
                    return childUser;
                }
            }

            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
