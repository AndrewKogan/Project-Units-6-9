import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CharacterSelectionFrame extends JFrame {
    private StatsPanel statsPanel;
    private MonsterParent selectedMonster;

    public CharacterSelectionFrame() {
        setTitle("Character Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(1, 2));

        statsPanel = new StatsPanel();

        JPanel leftPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Characters"));

        Random rand = new Random();
        String[] types = {"Fire", "Water", "Earth", "Air"};

        for (int i = 1; i <= 8; i++) {
            String name = "Character " + i;
            int defense = rand.nextInt(51) + 70; // Random defense stat
            String type = types[rand.nextInt(types.length)];
            int attack = rand.nextInt(21) + 15;

            MonsterParent monster = new MonsterParent(defense, type, name, attack);

            String stats = "Type: " + type + "\nDefense: " + defense + "\nAttack: " + attack;

            ImageIcon icon = new ImageIcon("img/img_0" + i + ".jpeg");
            icon = rotateImageIcon(icon);
            Image scaledImage = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            JButton button = new JButton(new ImageIcon(scaledImage));

            button.setToolTipText(name);

            button.addActionListener(e -> {
                selectedMonster = monster;
                statsPanel.updateStats(name, stats);
            });

            leftPanel.add(button);
        }


        statsPanel.setChooseButtonListener(e -> {
            if (selectedMonster != null) {
                new BattleScreen(selectedMonster.getFighterName(),
                        "Type: " + selectedMonster.getType() + "\nHP: " + selectedMonster.getDefenseValue() + "\nAttack: " + selectedMonster.getAttack(),
                        selectedMonster.getType());
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a character first!", "No Character Selected", JOptionPane.WARNING_MESSAGE);
            }
        });


        add(leftPanel);
        add(statsPanel);

        setVisible(true);
    }

    public static ImageIcon rotateImageIcon(ImageIcon icon) {
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();

        // Create a new image with flipped dimensions
        BufferedImage original = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = original.createGraphics();
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.dispose();

        BufferedImage rotated = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = rotated.createGraphics();

        // Rotate 90 degrees clockwise
        g.translate(height, 0);
        g.rotate(Math.toRadians(90));
        g.drawImage(original, 0, 0, null);
        g.dispose();

        return new ImageIcon(rotated);
    }

}
