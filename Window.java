
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Window {

    private static final JPanel square = new JPanel();
    private static int x = 20;
    private static int y = 200;

    public static void createAndShowGUI(){
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(square);
        square.setBounds(20,200,10,10);
        square.setBackground(Color.RED);

        Timer timer = new Timer(1000/60,new MyActionListener());
        timer.start();
        frame.setVisible(true);
    }

    public static class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            square.setLocation(x++, y--);

        }

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                createAndShowGUI();
                JFrame frame = new JFrame("Button Example");
                JButton button = new JButton("Click Me");

                // Implement ActionListener using an anonymous inner class
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // This code will be executed when the button is clicked
                        System.out.println("Button Clicked!");
                        // Add more actions here, e.g., modify other components
                    }
                });

                frame.add(button);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }

        });


    }

}