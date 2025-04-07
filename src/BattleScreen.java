import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BattleScreen extends JFrame {
    private int playerHP, aiHP;
    private int playerAttack, aiAttack;
    private final int MAX_PLAYER_HP, MAX_AI_HP;
    private String playerType, aiType;
    private int waterStrongCooldown = 0;
    private int fireStrongCooldown = 0;
    private int earthStrongCooldown = 0;
    private int airStrongCooldown = 0;
    private ArrayList<Move> playerMoveSet;
    private ArrayList<Move> aiMoveSet;
    private int attackBonus;
    private int healAmount;

    private JLabel playerStatsLabel, aiStatsLabel, logLabel;
    private JButton healButton, strongAttackButton, normalAttackButton, typeSpecialButton, attackButton;
    private String playerName, aiName;

    private JProgressBar playerHealthBar, aiHealthBar;
    private JPanel playerPanel, aiPanel;

    public BattleScreen(String playerName, String playerStats, String playerType, MonsterParent playerFighter, String aiName, String aiStats, String aiType, MonsterParent aiFighter) {
        this.playerType = playerType;
        this.playerName = playerName;
        setTitle("Battle Screen");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        attackBonus = 0;
        healAmount = 70;
        aiFighter.createMoveSet();
        aiMoveSet = aiFighter.getMoveSet();
        String[] stats = playerStats.split("\n");
        playerHP = Integer.parseInt(stats[1].split(": ")[1]);
        playerAttack = Integer.parseInt(stats[2].split(": ")[1]);
        MAX_PLAYER_HP = playerHP;

        this.aiName = aiName;
        this.aiType = aiType;
        String[] stats1 = aiStats.split("\n");
        aiHP =  Integer.parseInt(stats1[1].split(": ")[1]);;
        aiAttack = Integer.parseInt(stats1[2].split(": ")[1]);
        MAX_AI_HP = aiHP;

        playerPanel = createCharacterPanel(playerName, playerHP, playerAttack);
        aiPanel = createCharacterPanel(aiName, aiHP, aiAttack);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(playerPanel);
        topPanel.add(aiPanel);


        logLabel = new JLabel("Click a move to begin!", SwingConstants.CENTER);
        logLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel playerSpriteLabel = new JLabel();
        JLabel aiSpriteLabel = new JLabel();

        playerSpriteLabel.setHorizontalAlignment(SwingConstants.LEFT);
        aiSpriteLabel.setHorizontalAlignment(SwingConstants.RIGHT);



        JPanel spritePanel = new JPanel(null);
        spritePanel.setPreferredSize(new Dimension(600, 300));
        spritePanel.setBackground(Color.BLACK);

        playerSpriteLabel.setBounds(50, 50, 200, 200);
        aiSpriteLabel.setBounds(350, 70, 200, 200);

        spritePanel.add(playerSpriteLabel);
        spritePanel.add(aiSpriteLabel);
        add(spritePanel, BorderLayout.CENTER);
        slideSprite(playerSpriteLabel, true); // For player
        slideSprite(aiSpriteLabel, false);   // For AI


        playerFighter.createMoveSet();
        playerMoveSet = playerFighter.getMoveSet();
        JPanel movePanel = new JPanel(new GridLayout(2, 2));


        add(playerSpriteLabel);
        add(aiSpriteLabel);

        healButton = new JButton(playerMoveSet.get(0).getName());
        strongAttackButton = new JButton(playerMoveSet.get(1).getName());
        normalAttackButton = new JButton(playerMoveSet.get(2).getName());
        typeSpecialButton = new JButton(playerMoveSet.get(3).getName());
        for(int i = 0; i < 4; i++){
            if(playerMoveSet.get(i).getStatus().equals("hp+")){
                switch(i){
                    case 0:
                        healButton.addActionListener(e -> performMove("hp+",0));
                        break;
                    case 1:
                        strongAttackButton.addActionListener(e -> performMove("hp+",1));
                        break;
                    case 2:
                        normalAttackButton.addActionListener(e -> performMove("hp+",2));
                        break;
                    case 3:
                        typeSpecialButton.addActionListener(e -> performMove("hp+",3));
                        break;
                }
            }
            else if(playerMoveSet.get(i).getStatus().equals("attack+")){
                switch(i){
                    case 0:
                        healButton.addActionListener(e -> performMove("attack+",0));
                        break;
                    case 1:
                        strongAttackButton.addActionListener(e -> performMove("attack+",1));
                        break;
                    case 2:
                        normalAttackButton.addActionListener(e -> performMove("attack+",2));
                        break;
                    case 3:
                        typeSpecialButton.addActionListener(e -> performMove("attack+",3));
                        break;
                }
            }
            else{
                switch(i){
                    case 0:
                        healButton.addActionListener(e -> performMove("normal",0));
                        break;
                    case 1:
                        strongAttackButton.addActionListener(e -> performMove("normal",1));
                        break;
                    case 2:
                        normalAttackButton.addActionListener(e -> performMove("normal",2));
                        break;
                    case 3:
                        typeSpecialButton.addActionListener(e -> performMove("normal",3));
                        break;
                }
            }
        }

        movePanel.add(healButton);
        movePanel.add(strongAttackButton);
        movePanel.add(normalAttackButton);
        movePanel.add(typeSpecialButton);

        add(movePanel, BorderLayout.SOUTH);

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



    private void performMove(String moveType, int buttonPressed) {
        setMoveButtonsEnabled(false);
        boolean aiDodged = Math.random() > playerMoveSet.get(buttonPressed).getAccuracy()/100.;
        if (playerHP <= 0 || aiHP <= 0) return;
        int playerDamage = playerMoveSet.get(buttonPressed).getAttackValue() + attackBonus;
        String actionText = "";
        if(!aiDodged) {
            switch (moveType) {
                case "hp+":
                    if (playerHP >= MAX_PLAYER_HP) {
                        logLabel.setText("You are already at full health!");
                        disableHeal();
                        setMoveButtonsEnabled(true); // Re-enable buttons since heal wasn't used
                        return;
                    } else {
                        playerHP = Math.min(MAX_PLAYER_HP, playerHP + healAmount);
                        updatePanel(playerStatsLabel, playerHealthBar, playerHP, MAX_PLAYER_HP, playerAttack + attackBonus);
                        logLabel.setText("You healed for " + healAmount + " HP!");
                        healAmount -= 10;
                        // Skip attack animation, go straight to AI move after delay
                        Timer delay = new Timer(1000, e -> doAIAttack());
                        delay.setRepeats(false);
                        delay.start();
                        return;
                    }
                case "attack+":
                    if (attackBonus < 80) {
                        attackBonus += 20;
                        updatePanel(playerStatsLabel, playerHealthBar, playerHP, MAX_PLAYER_HP, playerAttack + attackBonus);
                    }
                    break;
            }
            aiHP -= playerDamage;
            actionText = "Attack dealt " + playerDamage + " damage!";
            if (aiHP <= 0) {
                actionText = playerName + " Wins!";
            }

            // Prevent negative HP
            aiHP = Math.max(0, aiHP);
        }
        else{
            showDodgeEffect("AI Dodged!");
            actionText = "AI Dodged!";
        }
        // Handle attack animation and AI response
        String finalActionText = actionText;
            slideForwardAndBack(playerPanel, true, () -> {
                if(!aiDodged) {
                    animateHit(aiPanel);
                    animateHealthBarLoss(aiPanel, aiHealthBar, playerDamage);
                }
                logLabel.setText(finalActionText);

                // Show effectiveness message
                if (moveType.equals("special")) {
                    double effectiveness = getEffectiveness("Ice", aiType);
                    if (effectiveness > 1.0) showFloatingMessage("It's super effective!", playerPanel);
                    else if (effectiveness < 1.0) showFloatingMessage("Not very effective...", playerPanel);
                }

                updatePanel(aiStatsLabel, aiHealthBar, aiHP, MAX_AI_HP, aiAttack);

                Timer delay = new Timer(1000, e -> doAIAttack());
                delay.setRepeats(false);
                delay.start();
            });

        if (playerHP <= 0) {
            logLabel.setText(aiName + " wins!");
            disableAllButtons();
        } else if (aiHP <= 0) {
            logLabel.setText(playerName + " wins!");
            disableAllButtons();
        }

        if (waterStrongCooldown > 0) waterStrongCooldown--;
    }


    private void doAIAttack() {
        if (playerHP <= 0 || aiHP <= 0) return;

        Move aiMove = aiMoveSet.get((int) (Math.random()*4));
        boolean playerDodged = Math.random() > aiMove.getAccuracy()/100.;

        int damage = aiMove.getAttackValue();
        if (hasAdvantage(aiMove.getType(), playerType)) {
            damage *= 1.2;
        }

        if (!playerDodged) {
            playerHP -= damage;
            if (playerHP < 0) playerHP = 0;
            animateHit(playerPanel);
            animateHealthBarLoss(playerPanel, playerHealthBar, damage);
            updatePanel(playerStatsLabel, playerHealthBar, playerHP, MAX_PLAYER_HP, playerAttack + attackBonus);
            logLabel.setText(aiName + " dealt " + damage + " damage!");
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
        setMoveButtonsEnabled(true);
    }

    private void animateHit(JPanel panel) {
        Color originalColor = panel.getBackground();
        panel.setBackground(Color.RED);
        Timer timer = new Timer(200, e -> panel.setBackground(originalColor));
        timer.setRepeats(false);
        timer.start();

        shakePanel(panel); // add shake
    }


    private void showDodgeEffect(String message) {
        logLabel.setText(message);
        Timer timer = new Timer(500, e -> logLabel.setText(""));
        timer.setRepeats(false);
        timer.start();
    }

    private void updatePanel(JLabel label, JProgressBar bar, int hp, int maxHp, int atk) {
        label.setText("HP: " + hp + " | ATK: " + atk);
        bar.setMaximum(maxHp);
        bar.setValue(hp);


        if (hp < maxHp * 0.3) bar.setForeground(Color.RED);
        else if (hp < maxHp * 0.6) bar.setForeground(Color.ORANGE);
        else bar.setForeground(Color.GREEN);

    }

    private boolean hasAdvantage(String attackerType, String defenderType) {
        return (attackerType.equals("Ice") && defenderType.equals("Rock")) ||
                (attackerType.equals("Rock") && defenderType.equals("Fairy")) ||
                (attackerType.equals("Fairy") && defenderType.equals("Fighting")) ||
                (attackerType.equals("Fighting") && defenderType.equals("Ice"));
    }

    private void disableAllButtons() {
        healButton.setEnabled(false);
        strongAttackButton.setEnabled(false);
        normalAttackButton.setEnabled(false);
        typeSpecialButton.setEnabled(false);
    }

    private void disableHeal(){
        healButton.setEnabled(false);
    }

    private void setMoveButtonsEnabled(boolean enabled) {
        healButton.setEnabled(enabled);
        strongAttackButton.setEnabled(enabled);
        normalAttackButton.setEnabled(enabled);
        typeSpecialButton.setEnabled(enabled);
    }

    private void shakePanel(JPanel panel) {
        Point originalLocation = panel.getLocation();
        Timer timer = new Timer(10, null);
        final int[] count = {0};

        timer.addActionListener(e -> {
            int offset = (count[0] % 2 == 0) ? 5 : -5;
            panel.setLocation(originalLocation.x + offset, originalLocation.y);
            count[0]++;

            if (count[0] >= 10) {
                timer.stop();
                panel.setLocation(originalLocation); // reset position
            }
        });

        timer.start();
    }

    private void slideForwardAndBack(JPanel panel, boolean isLeftSide, Runnable onComplete) {
        Point original = panel.getLocation();
        int direction = isLeftSide ? 1 : -1;
        Timer timer = new Timer(15, null);
        final int[] step = {0};

        timer.addActionListener(e -> {
            if (step[0] < 10) {
                panel.setLocation(original.x + direction * (step[0] + 1), original.y);
            } else if (step[0] < 20) {
                panel.setLocation(original.x + direction * (20 - step[0]), original.y);
            } else {
                panel.setLocation(original); // reset
                timer.stop();
                if (onComplete != null) onComplete.run();
            }
            step[0]++;
        });

        timer.start();
    }

    private void animateHealthBarLoss(JPanel character, JProgressBar bar, int damage) {
        if(bar==null) return;
        int start = bar.getValue();
        int end = Math.max(0, start - damage);
        Timer timer = new Timer(20, null);
        final int[] current = {start};

        timer.addActionListener(e -> {
            if (current[0] > end) {
                current[0]--;
                bar.setValue(current[0]);
            } else {
                timer.stop();
            }
        });
        timer.start();

    }

    private double getEffectiveness(String attackerType, String defenderType) {
        if (attackerType.equals(defenderType)) return 1.0; // Neutral

        switch (attackerType) {
            case "Ice":
                return defenderType.equals("Rock") ? 2.0 :
                        defenderType.equals("Fairy") ? 1.5 :
                                defenderType.equals("Fighting") ? 0.5 : 1.0;

            case "Rock":
                return defenderType.equals("Fairy") ? 2.0 :
                        defenderType.equals("Fighting") ? 1.5 :
                                defenderType.equals("Ice") ? 0.5 : 1.0;

            case "Fairy":
                return defenderType.equals("Fighting") ? 2.0 :
                        defenderType.equals("Ice") ? 1.5 :
                                defenderType.equals("Rock") ? 0.5 : 1.0;

            case "FightingFighting":
                return defenderType.equals("Ice") ? 2.0 :
                        defenderType.equals("Rock") ? 1.5 :
                                defenderType.equals("FairyFairy") ? 0.5 : 1.0;
        }
        return 1.0;
    }
    private void showFloatingMessage(String message, JPanel targetPanel) {
        JLabel floatingMessage = new JLabel(message);
        floatingMessage.setFont(new Font("Arial", Font.BOLD, 16));
        floatingMessage.setForeground(Color.WHITE);
        floatingMessage.setOpaque(true);
        floatingMessage.setBackground(new Color(0, 0, 0, 100));
        floatingMessage.setHorizontalAlignment(SwingConstants.CENTER);
        floatingMessage.setVerticalAlignment(SwingConstants.CENTER);

        Point panelLocation = targetPanel.getLocationOnScreen();
        int x = panelLocation.x + targetPanel.getWidth() / 2 - 50;
        int y = panelLocation.y - 20;
        floatingMessage.setBounds(x, y, 100, 30);

        add(floatingMessage);
        revalidate();
        repaint();

        Timer timer = new Timer(100, new ActionListener() {
            int deltaY = 1;
            int alpha = 255;
            @Override
            public void actionPerformed(ActionEvent e) {
                floatingMessage.setLocation(floatingMessage.getX(), floatingMessage.getY() - deltaY);

                if (alpha > 0) {
                    alpha -= 5;
                    floatingMessage.setForeground(new Color(255, 255, 255, alpha));
                } else {
                    // Remove the message once it’s fully faded out
                    remove(floatingMessage);
                    revalidate();
                    repaint();
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    private void slideSprite(JLabel label, boolean isPlayer) {
        Point original = label.getLocation();
        int direction = isPlayer ? 1 : -1;
        Timer timer = new Timer(15, null);
        final int[] step = {0};

        timer.addActionListener(e -> {
            if (step[0] < 10) {
                label.setLocation(original.x + direction * (step[0] + 2), original.y);
            } else if (step[0] < 20) {
                label.setLocation(original.x + direction * (20 - step[0]), original.y);
            } else {
                label.setLocation(original); // reset
                timer.stop();
            }
            step[0]++;
        });

        timer.start();
    }

}
