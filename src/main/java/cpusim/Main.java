package cpusim;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
  private final Memory memory = new Memory(256);
  private final Cpu cpu = new Cpu(memory);

  private final JLabel ipLabel = new JLabel();
  private final JLabel haltedLabel = new JLabel();
  private final JButton nextButton = new JButton("Next");

  Main() {
    setName("cpusim");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new GridLayout(2, 2));

    add(ipLabel);
    add(haltedLabel);
    add(nextButton);

    nextButton.addActionListener(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cpu.executeNextInstruction();
        updateCpuStatusUi();
      }
    });

    updateCpuStatusUi();

    setLocationRelativeTo(null);
    pack();
    setVisible(true);
  }

  private void updateCpuStatusUi() {
    ipLabel.setText(String.format("ip: %02x", cpu.getIp()));
    haltedLabel.setText(String.format("halted: %d", cpu.isHalted() ? 1 : 0));
    nextButton.setEnabled(!cpu.isHalted());
  }

  public static void main(String[] args) {
    new Main();
  }
}
