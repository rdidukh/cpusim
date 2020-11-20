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

  @Test
  void write() {
    Memory memory = new Memory(new int[]{1, 2, 3, 4, 5, 6, 7, 8});

    memory.write(0, 9);
    memory.write(1, -5);
    memory.write(2, 127);
    memory.write(3, 128);
    memory.write(4, 256);

    assertEquals(9, memory.read(0));
    assertEquals(-5, memory.read(1));
    assertEquals(127, memory.read(2));
    assertEquals(-128, memory.read(3));
    assertEquals(0, memory.read(4));
    assertDoesNotThrow(() -> memory.write(-1, 5));
    assertDoesNotThrow(() -> memory.write(100000, 5));
  }
}
