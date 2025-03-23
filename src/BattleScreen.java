import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BattleScreen extends JFrame {
    private int playerHP, aiHP;
    private int playerAttack, aiAttack;
    private final int MAX_PLAYER_HP;
    private final int MAX_AI_HP;
    private String playerType, aiType;


    private JLabel playerStatsLabel, aiStatsLabel, logLabel;
    private JButton attackButton;
    private String playerName, aiName;

    private JProgressBar playerHealthBar, aiHealthBar;
    private JPanel playerPanel, aiPanel;

    public BattleScreen(String playerName, String playerStats, String playerType) {
        this.playerType = playerType;
        this.playerName = playerName;
        setTitle("Battle Screen");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Parse player stats
        String[] stats = playerStats.split("\n");
        playerHP = Integer.parseInt(stats[1].split(": ")[1]);
        playerAttack = Integer.parseInt(stats[2].split(": ")[1]);
        MAX_PLAYER_HP = playerHP;

        Random rand = new Random();
        aiName = "AI Character";
        String[] types = {"Fire", "Water", "Earth"};
        int aiTypeIndex = rand.nextInt(types.length);
        aiType = types[aiTypeIndex];
        aiHP = rand.nextInt(51) + 70;
        aiAttack = rand.nextInt(21) + 15;
        MAX_AI_HP = aiHP;

        playerPanel = createCharacterPanel(playerName, playerHP, playerAttack);
        aiPanel = createCharacterPanel(aiName, aiHP, aiAttack);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(playerPanel);
        topPanel.add(aiPanel);

        logLabel = new JLabel("Click 'Attack' to begin!", SwingConstants.CENTER);
        logLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> doBattle());

        add(topPanel, BorderLayout.NORTH);
        add(logLabel, BorderLayout.CENTER);
        add(attackButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createCharacterPanel(String name, int hp, int attack) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(name));

        JLabel stats = new JLabel("HP: " + hp + " | ATK: " + attack, SwingConstants.CENTER);
        JProgressBar healthBar = new JProgressBar(0, hp);
        healthBar.setValue(hp);
        healthBar.setForeground(Color.GREEN);
        healthBar.setStringPainted(true);

        if (name.equals(playerName)) {
            playerStatsLabel = stats;
            playerHealthBar = healthBar;
        } else {
            aiStatsLabel = stats;
            aiHealthBar = healthBar;
        }

        panel.add(stats, BorderLayout.NORTH);
        panel.add(healthBar, BorderLayout.SOUTH);

        return panel;
    }

    private void doBattle() {

        Random rand = new Random();


        if (playerHP <= 0 || aiHP <= 0) return;

        int actualPlayerDamage = playerAttack;
        int actualAIDamage = aiAttack;

        if (hasAdvantage(playerType, aiType)) {
            actualAIDamage *= 0.8;
        }

        if (hasAdvantage(aiType, playerType)) {
            actualPlayerDamage *= 0.8;
        }

        boolean playerDodged = rand.nextInt(100) < 2; // Random number from 0 to 99, 3% chance
        boolean aiDodged = rand.nextInt(100) < 2; // Same for AI

        if (!aiDodged) {
            aiHP -= actualPlayerDamage;
            if (aiHP < 0) aiHP = 0;
            updatePanel(aiPanel, aiStatsLabel, aiHealthBar, aiName, aiHP, aiAttack, MAX_AI_HP, aiType);
            animateHit(aiPanel);
        } else {
            showDodgeEffect("AI Dodged!");
        }
        if (!playerDodged) {
            int finalActualAIDamage = actualAIDamage;
                playerHP -= finalActualAIDamage;
                if (playerHP < 0) playerHP = 0;
                updatePanel(playerPanel, playerStatsLabel, playerHealthBar, playerName, playerHP, playerAttack, MAX_PLAYER_HP, playerType);
                animateHit(playerPanel);
        } else {
            showDodgeEffect("You Dodged!");
        }

        if (aiHP <= 0 && playerHP <= 0) {
            logLabel.setText("Tie");
            attackButton.setEnabled(false);
            return;
        }


        if (aiHP <= 0) {
            logLabel.setText(playerName + " wins!");
            attackButton.setEnabled(false);
            return;
        }

        if (playerHP <= 0) {
            logLabel.setText(aiName + " wins!");
            attackButton.setEnabled(false);
            return;
        }
    }

    private void updatePanel(JPanel panel, JLabel label, JProgressBar bar, String name, int hp, int atk, int maxHp, String type) {
        label.setText("HP: " + hp + " | ATK: " + atk + " | Type: " + type);
        bar.setMaximum(maxHp);
        bar.setValue(hp);


        if (hp < maxHp * 0.3) bar.setForeground(Color.RED);
        else if (hp < maxHp * 0.6) bar.setForeground(Color.ORANGE);
        else bar.setForeground(Color.GREEN);
    }

    private void animateHit(JPanel targetPanel) {
        Color original = targetPanel.getBackground();
        targetPanel.setBackground(Color.RED);
        Timer t = new Timer(150, e -> targetPanel.setBackground(original));
        t.setRepeats(false);
        t.start();
    }

    private boolean hasAdvantage(String attackerType, String defenderType) {
        return (attackerType.equals("Water") && defenderType.equals("Fire")) ||
                (attackerType.equals("Fire") && defenderType.equals("Earth")) ||
                (attackerType.equals("Earth") && defenderType.equals("Water"));
    }

    private void showDodgeEffect(String message) {
        JLabel dodgeLabel = new JLabel(message, SwingConstants.CENTER);
        dodgeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        dodgeLabel.setForeground(Color.RED);
        dodgeLabel.setOpaque(true);
        dodgeLabel.setBackground(Color.BLACK);
        dodgeLabel.setBounds(getWidth() / 2 - 150, getHeight() / 2 - 30, 300, 60);  // Center of the screen

        add(dodgeLabel);
        repaint(); // Refresh screen to show dodge label

        // Hide the dodge message after 2 seconds
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(dodgeLabel);
                repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }


}
