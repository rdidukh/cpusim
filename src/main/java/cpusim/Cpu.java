package cpusim;

import static cpusim.Instruction.Type.HLT;

class Cpu {
  private final Memory memory;

  private int ip = 0;
  private boolean halted = false;

  Cpu(Memory memory) {
    this.memory = memory;
  }

  void executeNextInstruction() {
    if (halted) {
      return;
    }

    int opcode = memory.read(ip);

    Instruction.Type type = Instruction.Type.fromOpcode(opcode).orElse(HLT);

    if (type == HLT) {
      halted = true;
    }
  }

  int getIp() {
    return ip;
  }

  boolean isHalted() {
    return halted;
  }
}
