package cpusim;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
  private final Memory memory = new Memory(256);
  private final Cpu cpu = new Cpu(memory);

  private final JButton nextButton = new JButton("Next");
  private final CpuTableModel cpuTableModel = new CpuTableModel(cpu);

  private static class CpuTableModel extends AbstractTableModel {
    private static final int ROWS = 5;
    private static final int COLUMNS = 2;

    private final Cpu cpu;
    private final String[][] values = new String[ROWS][COLUMNS];

    CpuTableModel(Cpu cpu) {
      this.cpu = cpu;
      values[0][0] = "IP";
      values[1][0] = "Reg A";
      values[2][0] = "Flag Z";
      values[3][0] = "Flag N";
      values[4][0] = "Halted";
    }

    @Override
    public int getRowCount() {
      return ROWS;
    }

    @Override
    public int getColumnCount() {
      return COLUMNS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      updateValues();
      return values[rowIndex][columnIndex];
    }

    private void updateValues() {
      values[0][1] = String.format("%02x", cpu.getIp());
      values[1][1] = String.format("%02x", cpu.getRegA());
      values[2][1] = cpu.getFlagZero() ? "1" : "0";
      values[3][1] = cpu.getFlagNegative() ? "1" : "0";
      values[4][1] = cpu.isHalted() ? "1" : "0";
    }
  }

  Main() {
    setName("cpusim");
    setTitle("cpusim");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new GridLayout(1, 1));

    add(nextButton);
    add(new JTable(cpuTableModel));

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
    cpuTableModel.fireTableDataChanged();
    nextButton.setEnabled(!cpu.isHalted());
  }

  public static void main(String[] args) {
    new Main();
  }
}
