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
    assertFalse(cpu.isHalted());
  }

  @Test
  void haltInstruction() {
    Memory memory = new Memory(new int[]{0x00});
    Cpu cpu = new Cpu(memory);

    cpu.executeNextInstruction();
    assertTrue(cpu.isHalted());
  }
}
