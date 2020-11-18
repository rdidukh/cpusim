package cpusim;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("cpusim");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);

    JLabel label = new JLabel("Hello World");
    frame.getContentPane().add(label);

    frame.pack();
    frame.setVisible(true);
  }
}
