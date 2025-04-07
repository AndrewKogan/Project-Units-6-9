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

        MonsterParent[][] monsterGrid = new MonsterParent[4][2];

        for (int i = 0; i < 8; i++) {
            int row = i / 2;
            int col = i % 2;

            MonsterParent monster = switch (i) {
                case 0 -> new Steven();
                case 1 -> new Matthew();
                case 2 -> new Kyle();
                case 3 -> new Andy();
                case 4 -> new Benjamin();
                case 5 -> new James();
                case 6 -> new Andrew();
                case 7 -> new Lev();
                default -> new MonsterParent(0,"none","Unknown",0);
            };



            monsterGrid[row][col] = monster;


            String stats = "Type: " + monster.getType() + "\nDefense: " + monster.getDefenseValue() + "\nSpeed: " + monster.getSpeed();

            ImageIcon icon = new ImageIcon("img/img_0" + (i + 1) + ".jpeg");
            icon = rotateImageIcon(icon);
            Image scaledImage = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            JButton button = new JButton(new ImageIcon(scaledImage));

            button.setToolTipText(monster.getFighterName());

            button.addActionListener(e -> {
                selectedMonster = monster;
                statsPanel.updateStats(monster.getFighterName(), stats);
            });


            leftPanel.add(button);

        }

        Random random = new Random();
        randMonster = monsterGrid[random.nextInt(4)][random.nextInt(2)];
        if(selectedMonster!=null) {
            while (randMonster.getFighterName().equals(selectedMonster.getFighterName())) {
                randMonster = monsterGrid[random.nextInt(4)][random.nextInt(2)];
            }
        }







        statsPanel.setChooseButtonListener(e -> {
            if (selectedMonster != null) {
                new BattleScreen(selectedMonster.getFighterName(),
                        "Type: " + selectedMonster.getType() + "\nHP: " + selectedMonster.getDefenseValue() + "\nSpeed: " + selectedMonster.getSpeed(),
                        selectedMonster.getType(), selectedMonster,randMonster.getFighterName(),
                        "Type: " + randMonster.getType() + "\nHP: " + randMonster.getDefenseValue() + "\nSpeed: " + randMonster.getSpeed(),
                        randMonster.getType(), randMonster);
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
