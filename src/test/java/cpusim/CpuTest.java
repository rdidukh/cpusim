package cpusim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CpuTest {

  @Test
  void constructor() {
    Memory memory = new Memory(new int[]{0x00});
    Cpu cpu = new Cpu(memory);

    assertEquals(0, cpu.getIp());
    assertEquals(0, cpu.getRegA());
    assertFalse(cpu.getFlagZero());
    assertFalse(cpu.getFlagNegative());
    assertFalse(cpu.isHalted());
  }

  @Test
  void reset() {
    Memory memory = new Memory(new int[]{0x01, 0x75});
    Cpu cpu = new Cpu(memory);
    cpu.executeNextInstruction();
    cpu.reset();
    assertEquals(0, cpu.getIp());
    assertEquals(0, cpu.getRegA());
    assertFalse(cpu.getFlagZero());
    assertFalse(cpu.getFlagNegative());
    assertFalse(cpu.isHalted());

    memory.write(0, 0x01);
    memory.write(1, -10);
    cpu.executeNextInstruction();
    cpu.reset();
    assertFalse(cpu.getFlagNegative());

    memory.write(0, 0);
    cpu.executeNextInstruction();
    cpu.reset();
    assertFalse(cpu.isHalted());
  }

  @Test
  void haltInstruction() {
    Memory memory = new Memory(new int[]{0x00});
    Cpu cpu = new Cpu(memory);

    cpu.executeNextInstruction();
    assertTrue(cpu.isHalted());
  }

  @Test
  void staInstruction() {
    Memory memory = new Memory(new int[]{0x01, 0x75, 0x01, 0x00, 0x01, -1});
    Cpu cpu = new Cpu(memory);

    cpu.executeNextInstruction();
    assertEquals(2, cpu.getIp());
    assertEquals(0x75, cpu.getRegA());
    assertFalse(cpu.getFlagZero());
    assertFalse(cpu.getFlagNegative());
    assertFalse(cpu.isHalted());

    cpu.executeNextInstruction();
    assertEquals(4, cpu.getIp());
    assertEquals(0x0, cpu.getRegA());
    assertTrue(cpu.getFlagZero());
    assertFalse(cpu.getFlagNegative());
    assertFalse(cpu.isHalted());

    cpu.executeNextInstruction();
    assertEquals(6, cpu.getIp());
    assertEquals(-1, cpu.getRegA());
    assertFalse(cpu.getFlagZero());
    assertTrue(cpu.getFlagNegative());
    assertFalse(cpu.isHalted());
  }
}
