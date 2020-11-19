package cpusim;

class Memory {
  private final byte[] data;

  Memory(int maxSize) {
    data = new byte[maxSize];
  }

  Memory(int[] data) {
    this.data = new byte[data.length];
    for (int i = 0; i < data.length; i++) {
      this.data[i] = (byte)data[i];
    }
  }

  int read(int address) {
    if (address < 0 || address >= data.length) {
      return 0;
    }
    return data[address];
  }
}
