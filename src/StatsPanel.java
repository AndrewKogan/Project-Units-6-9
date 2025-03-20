import javax.swing.*;
import java.awt.*;

class StatsPanel extends JPanel {
    private JLabel nameLabel;
    private JTextArea statsArea;

    public StatsPanel() {
        setLayout(new BorderLayout());
        nameLabel = new JLabel("Select a Character", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Arial", Font.PLAIN, 16));

        add(nameLabel, BorderLayout.NORTH);
        add(statsArea, BorderLayout.CENTER);
    }

    public void updateStats(String name, String stats) {
        nameLabel.setText(name);
        statsArea.setText(stats);
    }
}
