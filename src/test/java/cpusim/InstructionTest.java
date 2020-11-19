package cpusim;

import static cpusim.Instruction.Type.HLT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class InstructionTest {
  @Test
  void fromOpcode() {
    assertTrue(Instruction.Type.fromOpcode(0xff).isEmpty());
    assertEquals(HLT, Instruction.Type.fromOpcode(0x00).orElseThrow());
  }
}
