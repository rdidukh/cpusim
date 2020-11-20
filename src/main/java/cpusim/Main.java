package cpusim;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
  private final Memory memory = new Memory(256);
  private final Cpu cpu = new Cpu(memory);

  private final JButton nextButton = new JButton("Next");
  private final JTable cpuTable = new JTable(5, 2);

  Main() {
    setName("cpusim");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new GridLayout(2, 2));

    add(nextButton);
    add(cpuTable);

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
    cpuTable.setValueAt("ip", 0, 0);
    cpuTable.setValueAt(cpu.getIp(), 0, 1);
    cpuTable.setValueAt("Reg A", 1, 0);
    cpuTable.setValueAt(cpu.getRegA(), 1, 1);
    cpuTable.setValueAt("Flag Zero", 2, 0);
    cpuTable.setValueAt(cpu.getFlagZero(), 2, 1);
    cpuTable.setValueAt("Flag Negative", 3, 0);
    cpuTable.setValueAt(cpu.getFlagNegative(), 3, 1);
    cpuTable.setValueAt("Halted", 4, 0);
    cpuTable.setValueAt(cpu.isHalted(), 4, 1);
    nextButton.setEnabled(!cpu.isHalted());
  }

  public static void main(String[] args) {
    new Main();
  }
}
