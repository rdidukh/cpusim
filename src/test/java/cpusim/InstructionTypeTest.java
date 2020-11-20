package cpusim;

import static cpusim.InstructionType.HLT;
import static cpusim.InstructionType.STA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class InstructionTypeTest {
  @Test
  void fromOpcode() {
    assertTrue(InstructionType.fromOpcode(0xff).isEmpty());
    assertEquals(HLT, InstructionType.fromOpcode(0x00).orElseThrow());
    assertEquals(STA, InstructionType.fromOpcode(0x01).orElseThrow());
    // Don't forget to cover newly added instructions.
    assertEquals(2, InstructionType.values().length);
  }
}
