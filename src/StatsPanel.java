package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatsPanel extends JPanel {
    private JLabel nameLabel;
    private JTextArea statsArea;
    private JButton chooseButton;

    public StatsPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Character Stats"));

        nameLabel = new JLabel("Select a Character", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        statsArea = new JTextArea();
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        statsArea.setEditable(false);
        statsArea.setLineWrap(true);
        statsArea.setWrapStyleWord(true);

        chooseButton = new JButton("Choose Character");
        chooseButton.setVisible(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(chooseButton);

        add(nameLabel, BorderLayout.NORTH);
        add(new JScrollPane(statsArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updateStats(String name, String stats) {
        nameLabel.setText(name);
        statsArea.setText(stats);
        chooseButton.setVisible(true);
    }

    public void setChooseButtonListener(ActionListener listener) {
        for (ActionListener al : chooseButton.getActionListeners()) {
            chooseButton.removeActionListener(al);
        }
        chooseButton.addActionListener(listener);
    }


}
