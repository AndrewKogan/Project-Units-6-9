import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CharacterSelectionFrame extends JFrame {
    private StatsPanel statsPanel;
    private MonsterParent selectedMonster;
    private MonsterParent randMonster;

    public CharacterSelectionFrame() {
        setTitle("Character Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(1, 2));

        statsPanel = new StatsPanel();

        JPanel leftPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Characters"));

        Random rand = new Random();
        String[] types = {"Ice", "Fairy", "Fighting", "Rock"};
        MonsterParent[][] monsterGrid = new MonsterParent[4][2];

        for (int i = 0; i < 8; i++) {
            int row = i / 2;
            int col = i % 2;

            String name = switch (i) {
                case 0 -> "Steven";
                case 1 -> "Matthew";
                case 2 -> "Kyle";
                case 3 -> "Andy";
                case 4 -> "Roy";
                case 5 -> "James";
                case 6 -> "Andrew";
                case 7 -> "Lev";
                default -> "Unknown";
            };
            Random random = new Random();


            int defense = random.nextInt(250 - 150 + 1) + 150;
            String type = types[rand.nextInt(types.length)];
            int attack = random.nextInt(30 - 15 + 1) + 15;

            MonsterParent monster = new MonsterParent(defense, type, name, attack);
            monsterGrid[row][col] = monster;

            String stats = "Type: " + type + "\nDefense: " + defense + "\nAttack: " + attack;

            ImageIcon icon = new ImageIcon("img/img_0" + (i + 1) + ".jpeg");
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

        Random random = new Random();
        randMonster = monsterGrid[random.nextInt(4)][random.nextInt(2)];
        while (randMonster.equals(selectedMonster)) {
            randMonster = monsterGrid[random.nextInt(4)][random.nextInt(2)];
        }







        statsPanel.setChooseButtonListener(e -> {
            if (selectedMonster != null) {
                new BattleScreen(selectedMonster.getFighterName(),
                        "Type: " + selectedMonster.getType() + "\nHP: " + selectedMonster.getDefenseValue() + "\nAttack: " + selectedMonster.getAttack(),
                        selectedMonster.getType(), randMonster.getFighterName(),
                        "Type: " + randMonster.getType() + "\nHP: " + randMonster.getDefenseValue() + "\nAttack: " + randMonster.getAttack(),
                        randMonster.getType());
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

        BufferedImage original = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = original.createGraphics();
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.dispose();

        BufferedImage rotated = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = rotated.createGraphics();

        g.translate(height, 0);
        g.rotate(Math.toRadians(90));
        g.drawImage(original, 0, 0, null);
        g.dispose();

        return new ImageIcon(rotated);
    }

}
