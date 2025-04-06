import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
        String[] types = {"Rock", "Ice", "Fairy", "Fighting"};
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

        JLabel playerSpriteLabel = new JLabel();
        JLabel aiSpriteLabel = new JLabel();

        playerSpriteLabel.setHorizontalAlignment(SwingConstants.LEFT);
        aiSpriteLabel.setHorizontalAlignment(SwingConstants.RIGHT);

// Scale images to sprite size (e.g. 200x200)


        JPanel spritePanel = new JPanel(null); // null layout for positioning manually
        spritePanel.setPreferredSize(new Dimension(600, 300));
        spritePanel.setBackground(Color.BLACK); // Or something thematic

        playerSpriteLabel.setBounds(50, 50, 200, 200);
        aiSpriteLabel.setBounds(350, 70, 200, 200);

        spritePanel.add(playerSpriteLabel);
        spritePanel.add(aiSpriteLabel);
        add(spritePanel, BorderLayout.CENTER);
        slideSprite(playerSpriteLabel, true); // For player
        slideSprite(aiSpriteLabel, false);   // For AI



        if (playerType.equals("Ice")) {
            JPanel movePanel = new JPanel(new GridLayout(2, 2));


            add(playerSpriteLabel);
            add(aiSpriteLabel);

            healButton = new JButton("Heal");
            strongAttackButton = new JButton("Strong Attack");
            normalAttackButton = new JButton("Normal Attack");
            typeSpecialButton = new JButton("Ice Surge");

            healButton.addActionListener(e -> performIceMove("heal"));
            strongAttackButton.addActionListener(e -> performIceMove("strong"));
            normalAttackButton.addActionListener(e -> performIceMove("normal"));
            typeSpecialButton.addActionListener(e -> performIceMove("special"));

            movePanel.add(healButton);
            movePanel.add(strongAttackButton);
            movePanel.add(normalAttackButton);
            movePanel.add(typeSpecialButton);

            add(movePanel, BorderLayout.SOUTH);
        }
        if (playerType.equals("Fairy")) {
            JPanel movePanel = new JPanel(new GridLayout(2, 2));

            add(playerSpriteLabel);
            add(aiSpriteLabel);

            healButton = new JButton("Heal");
            strongAttackButton = new JButton("Strong Attack");
            normalAttackButton = new JButton("Normal Attack");
            typeSpecialButton = new JButton("Fairy Surge");

            healButton.addActionListener(e -> performFairyMove("heal"));
            strongAttackButton.addActionListener(e -> performFairyMove("strong"));
            normalAttackButton.addActionListener(e -> performFairyMove("normal"));
            typeSpecialButton.addActionListener(e -> performFairyMove("special"));

            movePanel.add(healButton);
            movePanel.add(strongAttackButton);
            movePanel.add(normalAttackButton);
            movePanel.add(typeSpecialButton);

            add(movePanel, BorderLayout.SOUTH);
        } if (playerType.equals("Rock")) {
            JPanel movePanel = new JPanel(new GridLayout(2, 2));
            add(playerSpriteLabel);
            add(aiSpriteLabel);

            healButton = new JButton("Heal");
            strongAttackButton = new JButton("Strong Attack");
            normalAttackButton = new JButton("Normal Attack");
            typeSpecialButton = new JButton("Rock Surge");

            healButton.addActionListener(e -> performRockMove("heal"));
            strongAttackButton.addActionListener(e -> performRockMove("strong"));
            normalAttackButton.addActionListener(e -> performRockMove("normal"));
            typeSpecialButton.addActionListener(e -> performRockMove("special"));

            movePanel.add(healButton);
            movePanel.add(strongAttackButton);
            movePanel.add(normalAttackButton);
            movePanel.add(typeSpecialButton);

            add(movePanel, BorderLayout.SOUTH);
        }
        if (playerType.equals("Fighting")) {
            JPanel movePanel = new JPanel(new GridLayout(2, 2));

            add(playerSpriteLabel);
            add(aiSpriteLabel);

            healButton = new JButton("Heal");
            strongAttackButton = new JButton("Strong Attack");
            normalAttackButton = new JButton("Normal Attack");
            typeSpecialButton = new JButton("Fighting Surge");

            healButton.addActionListener(e -> performFightingMove("heal"));
            strongAttackButton.addActionListener(e -> performFightingMove("strong"));
            normalAttackButton.addActionListener(e -> performFightingMove("normal"));
            typeSpecialButton.addActionListener(e -> performFightingMove("special"));

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



    private void performIceMove(String moveType) {
        setMoveButtonsEnabled(false);

        if (playerHP <= 0 || aiHP <= 0) return;

        int playerDamage = 0;
        String actionText = "";

        switch (moveType) {
            case "heal":
                int healAmount = 20;
                playerHP = Math.min(MAX_PLAYER_HP, playerHP + healAmount);
                updatePanel(playerStatsLabel, playerHealthBar, playerHP, MAX_PLAYER_HP, playerAttack);
                logLabel.setText("You healed for " + healAmount + " HP!");

                // Skip attack animation, go straight to AI move after delay
                Timer delay = new Timer(1000, e -> doAIAttack());
                delay.setRepeats(false);
                delay.start();
                break;

            case "strong":
                if (waterStrongCooldown > 0) {
                    logLabel.setText("Strong Attack is on cooldown!");
                    setMoveButtonsEnabled(true); // Re-enable buttons since nothing happened
                    return;
                }
                playerDamage = playerAttack + 20;
                aiHP -= playerDamage;
                if(waterStrongCooldown == 0) {
                    waterStrongCooldown = 3;
                }
                actionText = "Strong Attack dealt " + playerDamage + " damage!";
                break;

            case "normal":
                playerDamage = playerAttack;
                aiHP -= playerDamage;
                actionText = "Normal Attack dealt " + playerDamage + " damage!";
                break;

            case "special":
                playerDamage = hasAdvantage("Ice", aiType) ? playerAttack + 15 : playerAttack;
                aiHP -= playerDamage;
                actionText = "Ice Surge dealt " + playerDamage + " damage!";
                break;
        }



        // Ensure HP doesn't go negative
        if (aiHP < 0) aiHP = 0;

        // Do slide-forward animation, then hit, health bar loss, and logging
        if(!moveType.equals("heal")) {
            int finalDamage = playerDamage;
            String finalActionText = actionText;
            slideForwardAndBack(playerPanel, true, () -> {
                animateHit(aiPanel);
                animateHealthBarLoss(aiPanel, aiHealthBar, finalDamage);
                logLabel.setText(finalActionText);

                // Show effectiveness floating message if it's a special move
                if (moveType.equals("special")) {
                    double effectiveness = getEffectiveness("Ice", aiType);
                    if (effectiveness > 1.0) showFloatingMessage("It's super effective!", playerPanel);
                    else if (effectiveness < 1.0) showFloatingMessage("Not very effective...", playerPanel);
                }

                // Update AI panel (HP already updated)
                updatePanel(aiStatsLabel, aiHealthBar, aiHP, MAX_AI_HP, aiAttack);

                // Delay AI's move
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
        }
        if (waterStrongCooldown > 0) waterStrongCooldown--;
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
            updatePanel(playerStatsLabel, playerHealthBar, playerHP, MAX_PLAYER_HP, playerAttack);
            animateHit(playerPanel);
            animateHealthBarLoss(playerPanel, playerHealthBar, damage);
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
        int percent = (int) ((hp / (double) maxHp) * 100);
        if (hp <= 0) {
            percent = 0;
            bar.setMaximum(1);
        }
        bar.setValue(percent);


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

    private void setMoveButtonsEnabled(boolean enabled) {
        healButton.setEnabled(enabled);
        strongAttackButton.setEnabled(enabled);
        normalAttackButton.setEnabled(enabled);
        typeSpecialButton.setEnabled(enabled);
    }


    private void performRockMove(String moveType) {
        setMoveButtonsEnabled(false);

        if (playerHP <= 0 || aiHP <= 0) return;

        int playerDamage = 0;
        String actionText = "";

        switch (moveType) {
            case "heal":
                int healAmount = 20;
                playerHP = Math.min(MAX_PLAYER_HP, playerHP + healAmount);
                updatePanel(playerStatsLabel, playerHealthBar, playerHP, MAX_PLAYER_HP, playerAttack);
                logLabel.setText("You healed for " + healAmount + " HP!");

                // Skip attack animation, go straight to AI move after delay
                Timer delay = new Timer(1000, e -> doAIAttack());
                delay.setRepeats(false);
                delay.start();
                break;

            case "strong":
                if (fireStrongCooldown > 0) {
                    logLabel.setText("Strong Attack is on cooldown!");
                    setMoveButtonsEnabled(true); // Re-enable buttons since nothing happened
                    return;
                }
                playerDamage = playerAttack + 20;
                aiHP -= playerDamage;
                if(fireStrongCooldown == 0) {
                    fireStrongCooldown = 3;
                }
                actionText = "Strong Attack dealt " + playerDamage + " damage!";
                break;

            case "normal":
                playerDamage = playerAttack;
                aiHP -= playerDamage;
                actionText = "Normal Attack dealt " + playerDamage + " damage!";
                break;

            case "special":
                playerDamage = hasAdvantage("Rock", aiType) ? playerAttack + 15 : playerAttack;
                aiHP -= playerDamage;
                actionText = "Rock Surge dealt " + playerDamage + " damage!";
                break;
        }

        // Ensure HP doesn't go negative
        if (aiHP <= 0) aiHP = 0;

        // Do slide-forward animation, then hit, health bar loss, and logging
        if(!moveType.equals("heal")){
            int finalDamage = playerDamage;
            String finalActionText = actionText;
            slideForwardAndBack(playerPanel, true, () -> {
                animateHit(aiPanel);
                animateHealthBarLoss(aiPanel, aiHealthBar, finalDamage);
                logLabel.setText(finalActionText);

                // Show effectiveness floating message if it's a special move
                if (moveType.equals("special")) {
                    double effectiveness = getEffectiveness("Rock", aiType);
                    if (effectiveness > 1.0) showFloatingMessage("It's super effective!", playerPanel);
                    else if (effectiveness < 1.0) showFloatingMessage("Not very effective...", playerPanel);
                }

                // Update AI panel (HP already updated)
                updatePanel(aiStatsLabel, aiHealthBar, aiHP, MAX_AI_HP, aiAttack);

                // Delay AI's move
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
        }
        if (fireStrongCooldown > 0) fireStrongCooldown--;
    }

    private void performFightingMove(String moveType) {
        setMoveButtonsEnabled(false);

        if (playerHP <= 0 || aiHP <= 0) return;

        Random rand = new Random();
        int playerDamage = 0;
        String actionText = "";

        switch (moveType) {
            case "heal":
                int healAmount = 20;
                playerHP = Math.min(MAX_PLAYER_HP, playerHP + healAmount);
                updatePanel(playerStatsLabel, playerHealthBar, playerHP, MAX_PLAYER_HP, playerAttack);
                logLabel.setText("You healed for " + healAmount + " HP!");

                // Skip attack animation, go straight to AI move after delay
                Timer delay = new Timer(1000, e -> doAIAttack());
                delay.setRepeats(false);
                delay.start();
                break;

            case "strong":
                if (airStrongCooldown > 0) {
                    logLabel.setText("Strong Attack is on cooldown!");
                    setMoveButtonsEnabled(true); // Re-enable buttons since nothing happened
                    return;
                }
                playerDamage = playerAttack + 20;
                aiHP -= playerDamage;
                if(airStrongCooldown == 0) {
                    airStrongCooldown = 3;
                }
                actionText = "Strong Attack dealt " + playerDamage + " damage!";
                break;

            case "normal":
                playerDamage = playerAttack;
                aiHP -= playerDamage;
                actionText = "Normal Attack dealt " + playerDamage + " damage!";
                break;

            case "special":
                playerDamage = hasAdvantage("Fighting", aiType) ? playerAttack + 15 : playerAttack;
                aiHP -= playerDamage;
                actionText = "Fighting Surge dealt " + playerDamage + " damage!";
                break;
        }

        // Ensure HP doesn't go negative
        if (aiHP < 0) aiHP = 0;

        // Do slide-forward animation, then hit, health bar loss, and logging
        if(!moveType.equals("heal")){
            int finalDamage = playerDamage;
            String finalActionText = actionText;
            slideForwardAndBack(playerPanel, true, () -> {
                animateHit(aiPanel);
                animateHealthBarLoss(aiPanel, aiHealthBar, finalDamage);
                logLabel.setText(finalActionText);

                // Show effectiveness floating message if it's a special move
                if (moveType.equals("special")) {
                    double effectiveness = getEffectiveness("Fighting", aiType);
                    if (effectiveness > 1.0) showFloatingMessage("It's super effective!", playerPanel);
                    else if (effectiveness < 1.0) showFloatingMessage("Not very effective...", playerPanel);
                }

                // Update AI panel (HP already updated)
                updatePanel(aiStatsLabel, aiHealthBar, aiHP, MAX_AI_HP, aiAttack);

                // Delay AI's move
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
        }
        if (airStrongCooldown > 0) airStrongCooldown--;
    }

    private void performFairyMove(String moveType) {
        setMoveButtonsEnabled(false);

        if (playerHP <= 0 || aiHP <= 0) return;

        Random rand = new Random();
        int playerDamage = 0;
        String actionText = "";

        switch (moveType) {
            case "heal":
                int healAmount = 20;
                playerHP = Math.min(MAX_PLAYER_HP, playerHP + healAmount);
                updatePanel(playerStatsLabel, playerHealthBar, playerHP, MAX_PLAYER_HP, playerAttack);
                logLabel.setText("You healed for " + healAmount + " HP!");

                // Skip attack animation, go straight to AI move after delay
                Timer delay = new Timer(1000, e -> doAIAttack());
                delay.setRepeats(false);
                delay.start();
                break;

            case "strong":
                if (earthStrongCooldown > 0) {
                    logLabel.setText("Strong Attack is on cooldown!");
                    setMoveButtonsEnabled(true); // Re-enable buttons since nothing happened
                    return;
                }
                playerDamage = playerAttack + 20;
                aiHP -= playerDamage;
                if(earthStrongCooldown == 0) {
                    earthStrongCooldown = 3;
                }
                actionText = "Strong Attack dealt " + playerDamage + " damage!";
                break;

            case "normal":
                playerDamage = playerAttack;
                aiHP -= playerDamage;
                actionText = "Normal Attack dealt " + playerDamage + " damage!";
                break;

            case "special":
                playerDamage = hasAdvantage("Fairy", aiType) ? playerAttack + 15 : playerAttack;
                aiHP -= playerDamage;
                actionText = "Fairy Surge dealt " + playerDamage + " damage!";
                break;
        }

        // Ensure HP doesn't go negative
        if (aiHP < 0) aiHP = 0;

        // Do slide-forward animation, then hit, health bar loss, and logging
        if(!moveType.equals("heal")){
            int finalDamage = playerDamage;
            String finalActionText = actionText;
            slideForwardAndBack(playerPanel, true, () -> {
                animateHit(aiPanel);
                animateHealthBarLoss(aiPanel, aiHealthBar, finalDamage);
                logLabel.setText(finalActionText);

                // Show effectiveness floating message if it's a special move
                if (moveType.equals("special")) {
                    double effectiveness = getEffectiveness("Fairy", aiType);
                    if (effectiveness > 1.0) showFloatingMessage("It's super effective!", playerPanel);
                    else if (effectiveness < 1.0) showFloatingMessage("Not very effective...", playerPanel);
                }

                // Update AI panel (HP already updated)
                updatePanel(aiStatsLabel, aiHealthBar, aiHP, MAX_AI_HP, aiAttack);

                // Delay AI's move
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
        }
        if (earthStrongCooldown > 0) earthStrongCooldown--;
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
        return 1.0; // Default to neutral if unknown
    }
    private void showFloatingMessage(String message, JPanel targetPanel) {
        // Create a new JLabel to display the message
        JLabel floatingMessage = new JLabel(message);
        floatingMessage.setFont(new Font("Arial", Font.BOLD, 16));
        floatingMessage.setForeground(Color.WHITE);
        floatingMessage.setOpaque(true);
        floatingMessage.setBackground(new Color(0, 0, 0, 100)); // Semi-transparent background
        floatingMessage.setHorizontalAlignment(SwingConstants.CENTER);
        floatingMessage.setVerticalAlignment(SwingConstants.CENTER);

        // Get the position of the target panel (where the message should float above)
        Point panelLocation = targetPanel.getLocationOnScreen();
        int x = panelLocation.x + targetPanel.getWidth() / 2 - 50; // Adjust for centering
        int y = panelLocation.y - 20; // Position above the panel

        // Set the floating message's location on the screen
        floatingMessage.setBounds(x, y, 100, 30);

        // Add the floating message to the JFrame
        add(floatingMessage);
        revalidate();
        repaint();

        // Animate the floating message: Move it upwards and fade out
        Timer timer = new Timer(100, new ActionListener() {
            int deltaY = 1;
            int alpha = 255;
            @Override
            public void actionPerformed(ActionEvent e) {
                // Move the message up
                floatingMessage.setLocation(floatingMessage.getX(), floatingMessage.getY() - deltaY);

                // Fade out the message
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
