import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ParentUserScreen extends JFrame {

    private ExerciseSettings settings;

    private JLabel aLabel;
    private JLabel bLabel;
    private JLabel NLabel;
    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField NTextField;
    private JButton saveButton;
    private JButton createChildUserButton;

    public ParentUserScreen(ExerciseSettings settings) {
        this.settings = settings;

        setTitle("Parent User Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));

        aLabel = new JLabel("a:");
        bLabel = new JLabel("b:");
        NLabel = new JLabel("N:");
        aTextField = new JTextField(String.valueOf(settings.getA()));
        bTextField = new JTextField(String.valueOf(settings.getB()));
        NTextField = new JTextField(String.valueOf(settings.getN()));
        saveButton = new JButton("Kaydet");
        createChildUserButton = new JButton("Child User Oluştur");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(aTextField.getText());
                int b = Integer.parseInt(bTextField.getText());
                int N = Integer.parseInt(NTextField.getText());

                settings.setA(a);
                settings.setB(b);
                settings.setN(N);

                ExerciseSettings.saveSettings(settings);

                JOptionPane.showMessageDialog(null, "Ayarlar kaydedildi.");
            }
        });

        createChildUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Kullanıcı Adı:");
                String password = JOptionPane.showInputDialog("Şifre:");

                ChildUser childUser = new ChildUser(username, password);
                ChildUser.saveChildUser(childUser);

                JOptionPane.showMessageDialog(null, "Kullanıcı kaydedildi.");
            }
        });

        add(aLabel);
        add(aTextField);
        add(bLabel);
        add(bTextField);
        add(NLabel);
        add(NTextField);
        add(saveButton);
        add(createChildUserButton);

        setLocationRelativeTo(null);
    }
}
