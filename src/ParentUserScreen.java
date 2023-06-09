import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParentUserScreen extends JFrame {

    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField nTextField;

    public ParentUserScreen() {
        setTitle("Parent User Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        JLabel aLabel = new JLabel("a:");
        aTextField = new JTextField();
        JLabel bLabel = new JLabel("b:");
        bTextField = new JTextField();
        JLabel nLabel = new JLabel("N:");
        nTextField = new JTextField();

        JButton saveButton = new JButton("Kaydet");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(aTextField.getText());
                int b = Integer.parseInt(bTextField.getText());
                int N = Integer.parseInt(nTextField.getText());

                ExerciseDataStorage.saveExerciseData(a, b, N);

                JOptionPane.showMessageDialog(null, "Değerler kaydedildi.");
            }
        });

        add(aLabel);
        add(aTextField);
        add(bLabel);
        add(bTextField);
        add(nLabel);
        add(nTextField);
        add(new JLabel());
        add(saveButton);

        setLocationRelativeTo(null);
    }
}
