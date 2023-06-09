import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        LoginScreen loginScreen = new LoginScreen();

        loginScreen.getLoginButton().addActionListener(e -> {
            String username = loginScreen.getUsernameField().getText();
            String password = new String(loginScreen.getPasswordField().getPassword());

            if (username.equals("admin")) {
                ParentUser parentUser = new ParentUser(username, password, "", "", "");
                ParentUserScreen parentUserScreen = new ParentUserScreen();
                parentUserScreen.setVisible(true);
            } else {
                ChildUserScreen childUserScreen = new ChildUserScreen(5,10,5);
                childUserScreen.setVisible(true);
            }
            loginScreen.dispose();
        });

        loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginScreen.pack();
        loginScreen.setLocationRelativeTo(null);
        loginScreen.setVisible(true);
    }
}
