package cpusim;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryTest {

  @Test
  void read() {
    Memory memory = new Memory(new int[]{0xff, 258, 511, 24, -10, -129, 128, -128});

    assertEquals(-1, memory.read(0));
    assertEquals(2, memory.read(1));
    assertEquals(-1, memory.read(2));
    assertEquals(24, memory.read(3));
    assertEquals(-10, memory.read(4));
    assertEquals(127, memory.read(5));
    assertEquals(-128, memory.read(6));
    assertEquals(-128, memory.read(7));
    assertEquals(0, memory.read(-1));
    assertEquals(0, memory.read(-100));
    assertEquals(0, memory.read(100));
  }
}
