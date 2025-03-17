
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
        JButton button = new JButton("Click Me");
        button.setSize(100,20);
        button.setLocation(0,440);
        frame.add(button);
        frame.add(square);
        square.setBounds(20,200,10,10);
        square.setBackground(Color.RED);
        Timer timer = new Timer(1000/60,new MyActionListener());
        timer.start();
        frame.setVisible(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPressed();
            }
        } );
    }
    public static void buttonPressed(){
        System.out.println("button clicked!");
    }

    public static class MyActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            square.setLocation(x++, y--);

        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Window::createAndShowGUI);
    }

}