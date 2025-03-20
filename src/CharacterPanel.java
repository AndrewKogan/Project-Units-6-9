import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CharacterPanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CharacterSelectionFrame::new);
    }

    public CharacterPanel() {
        JFrame frame = new JFrame("Character Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(1, 2)); // Split the frame into two halves

        // Left panel for character selection
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 3, 5, 5)); // 5x3 grid for characters
        leftPanel.setBorder(BorderFactory.createTitledBorder("Characters"));

        // Right panel for character stats
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Character Stats"));

        // Label to display character stats
        JLabel statsLabel = new JLabel("Select a character to view stats", JLabel.CENTER);
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        rightPanel.add(statsLabel, BorderLayout.CENTER);

        // Data for characters
        HashMap<String, String> characterStats = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            characterStats.put("Character " + i, "Stats for Character " + i + ": \nHealth: " + (50 + i) + "\nStrength: " + (10 + i));
        }

        // Adding buttons to the left panel
        for (int i = 1; i <= 15; i++) {
            String characterName = "Character " + i;
            JButton button = new JButton(characterName);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    statsLabel.setText("<html>" + characterStats.get(characterName).replace("\n", "<br>") + "</html>");
                }
            });
            leftPanel.add(button);
        }

        // Add panels to the frame
        frame.add(leftPanel);
        frame.add(rightPanel);

        frame.setVisible(true);
    }
}
