import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class BattleScreen extends JFrame {
    private int playerHP, aiHP;
    private int playerAttack, aiAttack;
    private final int MAX_PLAYER_HP, MAX_AI_HP;
    private String playerType, aiType;
    private int waterStrongCooldown = 0;
    private int strongAttackCooldown = 0;
    private int fireballCooldown = 0;
    private int earthquakeCooldown = 0;
    private double playerDefense = 1.0;

    private JLabel playerStatsLabel, aiStatsLabel, logLabel;
    private JButton healButton, strongAttackButton, normalAttackButton, typeSpecialButton, attackButton;
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

        String[] stats = playerStats.split("\n");
        playerHP = Integer.parseInt(stats[1].split(": ")[1]);
        playerAttack = Integer.parseInt(stats[2].split(": ")[1]);
        MAX_PLAYER_HP = playerHP;

        Random rand = new Random();
        aiName = "AI Character";
        String[] types = {"Fire", "Water", "Earth"};
        aiType = types[rand.nextInt(types.length)];
        aiHP = rand.nextInt(51) + 70;
        aiAttack = rand.nextInt(21) + 15;
        MAX_AI_HP = aiHP;

        playerPanel = createCharacterPanel(playerName, playerHP, playerAttack);
        aiPanel = createCharacterPanel(aiName, aiHP, aiAttack);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(playerPanel);
        topPanel.add(aiPanel);

        logLabel = new JLabel("Click a move to begin!", SwingConstants.CENTER);
        logLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        if (playerType.equals("Water")) {
            JPanel movePanel = new JPanel(new GridLayout(2, 2));

            healButton = new JButton("Heal");
            strongAttackButton = new JButton("Strong Attack");
            normalAttackButton = new JButton("Normal Attack");
            typeSpecialButton = new JButton("Water Surge");

            healButton.addActionListener(e -> performWaterMove("heal"));
            strongAttackButton.addActionListener(e -> performWaterMove("strong"));
            normalAttackButton.addActionListener(e -> performWaterMove("normal"));
            typeSpecialButton.addActionListener(e -> performWaterMove("special"));

            movePanel.add(healButton);
            movePanel.add(strongAttackButton);
            movePanel.add(normalAttackButton);
            movePanel.add(typeSpecialButton);

            add(movePanel, BorderLayout.SOUTH);
        }
        if (playerType.equals("Earth")) {
            JPanel movePanel = new JPanel(new GridLayout(2, 2));

            healButton = new JButton("Heal");
            strongAttackButton = new JButton("Strong Attack");
            normalAttackButton = new JButton("Normal Attack");
            typeSpecialButton = new JButton("Earth Surge");

            healButton.addActionListener(e -> performEarthMove("heal"));
            strongAttackButton.addActionListener(e -> performEarthMove("strong"));
            normalAttackButton.addActionListener(e -> performEarthMove("normal"));
            typeSpecialButton.addActionListener(e -> performEarthMove("special"));

            movePanel.add(healButton);
            movePanel.add(strongAttackButton);
            movePanel.add(normalAttackButton);
            movePanel.add(typeSpecialButton);

            add(movePanel, BorderLayout.SOUTH);
        } if (playerType.equals("Fire")) {
            JPanel movePanel = new JPanel(new GridLayout(2, 2));

            healButton = new JButton("Heal");
            strongAttackButton = new JButton("Strong Attack");
            normalAttackButton = new JButton("Normal Attack");
            typeSpecialButton = new JButton("Fire Surge");

            healButton.addActionListener(e -> performFireMove("heal"));
            strongAttackButton.addActionListener(e -> performFireMove("strong"));
            normalAttackButton.addActionListener(e -> performFireMove("normal"));
            typeSpecialButton.addActionListener(e -> performFireMove("special"));

            movePanel.add(healButton);
            movePanel.add(strongAttackButton);
            movePanel.add(normalAttackButton);
            movePanel.add(typeSpecialButton);

            add(movePanel, BorderLayout.SOUTH);
        }
        if (playerType.equals("Air")) {
            JPanel movePanel = new JPanel(new GridLayout(2, 2));

            healButton = new JButton("Heal");
            strongAttackButton = new JButton("Strong Attack");
            normalAttackButton = new JButton("Normal Attack");
            typeSpecialButton = new JButton("Air Surge");

            healButton.addActionListener(e -> performAirMove("heal"));
            strongAttackButton.addActionListener(e -> performAirMove("strong"));
            normalAttackButton.addActionListener(e -> performAirMove("normal"));
            typeSpecialButton.addActionListener(e -> performAirMove("special"));

            movePanel.add(healButton);
            movePanel.add(strongAttackButton);
            movePanel.add(normalAttackButton);
            movePanel.add(typeSpecialButton);

            add(movePanel, BorderLayout.SOUTH);
        }

        add(topPanel, BorderLayout.NORTH);
        add(logLabel, BorderLayout.CENTER);
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

    private void performWaterMove(String moveType) {
        if (playerHP <= 0 || aiHP <= 0) return;

        Random rand = new Random();
        int playerDamage = 0;
        String actionText = "";

        switch (moveType) {
            case "heal":
                int healAmount = 20;
                playerHP = Math.min(MAX_PLAYER_HP, playerHP + healAmount);
                updatePanel(playerPanel, playerStatsLabel, playerHealthBar, playerName, playerHP, MAX_PLAYER_HP);
                actionText = "You healed for " + healAmount + " HP!";
                break;

            case "strong":
                if (waterStrongCooldown > 0) {
                    logLabel.setText("Strong Attack is on cooldown!");
                    return;
                }
                playerDamage = playerAttack + 20;
                aiHP -= playerDamage;
                waterStrongCooldown = 2;
                actionText = "Strong Attack dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;

            case "normal":
                playerDamage = playerAttack;
                aiHP -= playerDamage;
                actionText = "Normal Attack dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;

            case "special":
                playerDamage = hasAdvantage("Water", aiType) ? playerAttack + 15 : playerAttack;
                aiHP -= playerDamage;
                actionText = "Water Surge dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;
        }

        if (aiHP < 0) aiHP = 0;
        updatePanel(aiPanel, aiStatsLabel, aiHealthBar, aiName, aiHP, MAX_AI_HP);
        logLabel.setText(actionText);

        doAIAttack();

        if (waterStrongCooldown > 0) waterStrongCooldown--;
    }

    private void doBattle() {
        if (playerHP <= 0 || aiHP <= 0) return;

        Random rand = new Random();
        int playerDamage = playerAttack;
        aiHP -= playerDamage;
        animateHit(aiPanel);
        logLabel.setText(playerName + " dealt " + playerDamage + " damage!");

        if (aiHP <= 0) {
            aiHP = 0;
            updatePanel(aiPanel, aiStatsLabel, aiHealthBar, aiName, aiHP, MAX_AI_HP);
            logLabel.setText(playerName + " wins!");
            disableAllButtons();
            return;
        }

        updatePanel(aiPanel, aiStatsLabel, aiHealthBar, aiName, aiHP, MAX_AI_HP);

        Timer timer = new Timer(1000, e -> doAIAttack());
        timer.setRepeats(false);
        timer.start();
    }


    private void doAIAttack() {
        if (playerHP <= 0 || aiHP <= 0) return;

        Random rand = new Random();
        boolean playerDodged = rand.nextInt(100) < 10;

        int damage = aiAttack;
        if (hasAdvantage(aiType, playerType)) {
            damage *= 1.2;
        }

        if (!playerDodged) {
            playerHP -= damage;
            if (playerHP < 0) playerHP = 0;
            updatePanel(playerPanel, playerStatsLabel, playerHealthBar, playerName, playerHP, MAX_PLAYER_HP);
            animateHit(playerPanel);
        } else {
            showDodgeEffect("You Dodged!");
        }

        if (playerHP <= 0) {
            logLabel.setText(aiName + " wins!");
            disableAllButtons();
        } else if (aiHP <= 0) {
            logLabel.setText(playerName + " wins!");
            disableAllButtons();
        }
    }

    private void animateHit(JPanel panel) {
        Color originalColor = panel.getBackground();
        panel.setBackground(Color.RED);
        Timer timer = new Timer(200, e -> panel.setBackground(originalColor));
        timer.setRepeats(false);
        timer.start();
    }

    private void showDodgeEffect(String message) {
        logLabel.setText(message);
        Timer timer = new Timer(500, e -> logLabel.setText(""));
        timer.setRepeats(false);
        timer.start();
    }

    private void updatePanel(JPanel panel, JLabel label, JProgressBar bar, String name, int hp, int maxHp) {
        label.setText("HP: " + hp);
        bar.setMaximum(maxHp);
        bar.setValue(hp);

        if (hp < maxHp * 0.3) bar.setForeground(Color.RED);
        else if (hp < maxHp * 0.6) bar.setForeground(Color.ORANGE);
        else bar.setForeground(Color.GREEN);
    }

    private boolean hasAdvantage(String attackerType, String defenderType) {
        return (attackerType.equals("Water") && defenderType.equals("Fire")) ||
                (attackerType.equals("Fire") && defenderType.equals("Earth")) ||
                (attackerType.equals("Earth") && defenderType.equals("Water"));
    }

    private void disableAllButtons() {
        healButton.setEnabled(false);
        strongAttackButton.setEnabled(false);
        normalAttackButton.setEnabled(false);
        typeSpecialButton.setEnabled(false);
    }

    private void performFireMove(String moveType) {
        if (playerHP <= 0 || aiHP <= 0) return;

        Random rand = new Random();
        int playerDamage = 0;
        String actionText = "";

        switch (moveType) {
            case "heal":
                int healAmount = 20;
                playerHP = Math.min(MAX_PLAYER_HP, playerHP + healAmount);
                updatePanel(playerPanel, playerStatsLabel, playerHealthBar, playerName, playerHP, MAX_PLAYER_HP);
                actionText = "You healed for " + healAmount + " HP!";
                break;

            case "strong":
                if (waterStrongCooldown > 0) {
                    logLabel.setText("Strong Attack is on cooldown!");
                    return;
                }
                playerDamage = playerAttack + 20;
                aiHP -= playerDamage;
                waterStrongCooldown = 2;
                actionText = "Strong Attack dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;

            case "normal":
                playerDamage = playerAttack;
                aiHP -= playerDamage;
                actionText = "Normal Attack dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;

            case "special":
                playerDamage = hasAdvantage("Fire", aiType) ? playerAttack + 15 : playerAttack;
                aiHP -= playerDamage;
                actionText = "Fire Surge dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;
        }

        if (aiHP < 0) aiHP = 0;
        updatePanel(aiPanel, aiStatsLabel, aiHealthBar, aiName, aiHP, MAX_AI_HP);
        logLabel.setText(actionText);

        doAIAttack();

        if (waterStrongCooldown > 0) waterStrongCooldown--;
    }

    private void performAirMove(String moveType) {
        if (playerHP <= 0 || aiHP <= 0) return;

        Random rand = new Random();
        int playerDamage = 0;
        String actionText = "";

        switch (moveType) {
            case "heal":
                int healAmount = 20;
                playerHP = Math.min(MAX_PLAYER_HP, playerHP + healAmount);
                updatePanel(playerPanel, playerStatsLabel, playerHealthBar, playerName, playerHP, MAX_PLAYER_HP);
                actionText = "You healed for " + healAmount + " HP!";
                break;

            case "strong":
                if (waterStrongCooldown > 0) {
                    logLabel.setText("Strong Attack is on cooldown!");
                    return;
                }
                playerDamage = playerAttack + 20;
                aiHP -= playerDamage;
                waterStrongCooldown = 2;
                actionText = "Strong Attack dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;

            case "normal":
                playerDamage = playerAttack;
                aiHP -= playerDamage;
                actionText = "Normal Attack dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;

            case "special":
                playerDamage = hasAdvantage("Fire", aiType) ? playerAttack + 15 : playerAttack;
                aiHP -= playerDamage;
                actionText = "Air Surge dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;
        }

        if (aiHP < 0) aiHP = 0;
        updatePanel(aiPanel, aiStatsLabel, aiHealthBar, aiName, aiHP, MAX_AI_HP);
        logLabel.setText(actionText);

        doAIAttack();

        if (waterStrongCooldown > 0) waterStrongCooldown--;
    }

    private void performEarthMove(String moveType) {
        if (playerHP <= 0 || aiHP <= 0) return;

        Random rand = new Random();
        int playerDamage = 0;
        String actionText = "";

        switch (moveType) {
            case "heal":
                int healAmount = 20;
                playerHP = Math.min(MAX_PLAYER_HP, playerHP + healAmount);
                updatePanel(playerPanel, playerStatsLabel, playerHealthBar, playerName, playerHP, MAX_PLAYER_HP);
                actionText = "You healed for " + healAmount + " HP!";
                break;

            case "strong":
                if (waterStrongCooldown > 0) {
                    logLabel.setText("Strong Attack is on cooldown!");
                    return;
                }
                playerDamage = playerAttack + 20;
                aiHP -= playerDamage;
                waterStrongCooldown = 2;
                actionText = "Strong Attack dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;

            case "normal":
                playerDamage = playerAttack;
                aiHP -= playerDamage;
                actionText = "Normal Attack dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;

            case "special":
                playerDamage = hasAdvantage("Fire", aiType) ? playerAttack + 15 : playerAttack;
                aiHP -= playerDamage;
                actionText = "Earth Surge dealt " + playerDamage + " damage!";
                animateHit(aiPanel);
                break;
        }
        if (aiHP < 0) aiHP = 0;
        updatePanel(aiPanel, aiStatsLabel, aiHealthBar, aiName, aiHP, MAX_AI_HP);
        logLabel.setText(actionText);

        doAIAttack();

        if (waterStrongCooldown > 0) waterStrongCooldown--;
    }
}
