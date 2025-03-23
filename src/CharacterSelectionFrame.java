import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CharacterSelectionFrame extends JFrame {
    private StatsPanel statsPanel;
    private String selectedCharacterName;
    private String selectedCharacterStats;

    public CharacterSelectionFrame() {
        setTitle("Character Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(1, 2));

        statsPanel = new StatsPanel();

        JPanel leftPanel = new JPanel(new GridLayout(5, 3, 5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Characters"));

        Random rand = new Random();
        String[] types = {"Fire", "Water", "Earth", "Wind", "Light", "Dark"};

        for (int i = 1; i <= 15; i++) {
            String name = "Character " + i;
            int hp = rand.nextInt(51) + 70;
            int attack = rand.nextInt(21) + 15;
            String type = types[rand.nextInt(types.length)];

            String stats = "Type: " + type + "\nHP: " + hp + "\nAttack: " + attack;

            ImageIcon icon = new ImageIcon("img/img_" + i + ".jpg");
            Image scaledImage = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            JButton button = new JButton(new ImageIcon(scaledImage));

            button.setToolTipText(name);

            button.addActionListener(e -> {
                selectedCharacterName = name;
                selectedCharacterStats = stats;
                statsPanel.updateStats(name, stats);
            });

            leftPanel.add(button);
        }


        statsPanel.setChooseButtonListener(e -> {
            if (selectedCharacterName != null) {
                openCharacterScreen(selectedCharacterName);
                this.setVisible(false);
            }
        });

        add(leftPanel);
        add(statsPanel);

        setVisible(true);
    }

    private void openCharacterScreen(String characterName) {
        JFrame characterFrame = new JFrame(characterName + "'s Screen");
        characterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        characterFrame.setSize(400, 300);
        characterFrame.setLocationRelativeTo(this);

        JLabel label = new JLabel("Welcome, " + characterName + "!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        characterFrame.add(label, BorderLayout.CENTER);
        characterFrame.setVisible(true);
    }
}
