import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class BattlefieldView extends JPanel {
    private JLabel playerImageLabel;
    private JLabel aiImageLabel;
    private JLabel playerNameLabel;
    private JLabel aiNameLabel;
    private JProgressBar playerHPBar;
    private JProgressBar aiHPBar;

    public BattlefieldView(String playerImgPath, String aiImgPath, String playerName, String aiName) {
        setLayout(null);
        setPreferredSize(new Dimension(800, 400));
        setBackground(new Color(200, 240, 255)); // Light sky-blue background

        // Load and flip images
        ImageIcon playerIcon = new ImageIcon(playerImgPath);
        ImageIcon aiIcon = flipImageIconHorizontally(new ImageIcon(aiImgPath));

        // Image labels
        playerImageLabel = new JLabel(scaleIcon(playerIcon, 160, 160));
        aiImageLabel = new JLabel(scaleIcon(aiIcon, 160, 160));

        playerImageLabel.setBounds(100, 200, 160, 160); // bottom-left
        aiImageLabel.setBounds(540, 80, 160, 160);      // top-right

        add(playerImageLabel);
        add(aiImageLabel);

        // Name labels
        playerNameLabel = new JLabel(playerName);
        aiNameLabel = new JLabel(aiName);

        playerNameLabel.setBounds(100, 170, 160, 20);
        aiNameLabel.setBounds(540, 50, 160, 20);

        playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aiNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        aiNameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        add(playerNameLabel);
        add(aiNameLabel);

        // HP Bars
        playerHPBar = createHPBar();
        aiHPBar = createHPBar();

        playerHPBar.setBounds(100, 190, 160, 15);
        aiHPBar.setBounds(540, 70, 160, 15);

        add(playerHPBar);
        add(aiHPBar);
    }

    // Create styled HP bar
    private JProgressBar createHPBar() {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue(100);
        bar.setStringPainted(true);
        bar.setForeground(new Color(0, 200, 0));
        bar.setBackground(Color.LIGHT_GRAY);
        bar.setFont(new Font("Arial", Font.BOLD, 12));
        return bar;
    }

    // Slide forward and back animation
    public void animateAttack(boolean isPlayer) {
        JLabel attacker = isPlayer ? playerImageLabel : aiImageLabel;
        Point original = attacker.getLocation();
        Timer timer = new Timer(20, null);
        final int[] step = {0};

        timer.addActionListener(e -> {
            if (step[0] < 5) {
                attacker.setLocation(original.x + (isPlayer ? 10 : -10), original.y);
            } else if (step[0] < 10) {
                attacker.setLocation(original.x, original.y);
            } else {
                attacker.setLocation(original);
                timer.stop();
            }
            step[0]++;
        });
        timer.start();
    }

    // Shake animation when hit
    public void animateShake(boolean isPlayer) {
        JLabel label = isPlayer ? playerImageLabel : aiImageLabel;
        Point original = label.getLocation();
        Timer timer = new Timer(20, null);
        final int[] ticks = {0};

        timer.addActionListener(e -> {
            int dx = (ticks[0] % 2 == 0) ? 5 : -5;
            label.setLocation(original.x + dx, original.y);
            ticks[0]++;
            if (ticks[0] > 5) {
                timer.stop();
                label.setLocation(original);
            }
        });
        timer.start();
    }

    // Update HP
    public void setPlayerHP(int hp) {
        playerHPBar.setValue(hp);
    }

    public void setAiHP(int hp) {
        aiHPBar.setValue(hp);
    }

    // Flip image
    private ImageIcon flipImageIconHorizontally(ImageIcon icon) {
        BufferedImage original = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = original.createGraphics();
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.dispose();

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-original.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage flipped = op.filter(original, null);

        return new ImageIcon(flipped);
    }

    // Scale image
    private ImageIcon scaleIcon(ImageIcon icon, int w, int h) {
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public JLabel getPlayerImageLabel() {
        return playerImageLabel;
    }

    public JLabel getAiImageLabel() {
        return aiImageLabel;
    }
}
