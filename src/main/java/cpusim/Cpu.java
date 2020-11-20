package cpusim;

import static cpusim.InstructionType.HLT;

class Cpu {
  private final Memory memory;

  private int regA = 0;

  private boolean flagZero = false;
  private boolean flagNegative = false;

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
    ip++;

    InstructionType instructionType = InstructionType.fromOpcode(opcode).orElse(HLT);

    int numberOfOperands = instructionType.operandTypes.size();

    int[] operands = new int[numberOfOperands];

    for (int i = 0; i < numberOfOperands; i++) {
      OperandType operandType = instructionType.operandTypes.get(i);
      if (operandType == OperandType.IMM_8) {
        operands[i] = memory.read(ip);
        ip++;
      } else {
        throw new IllegalStateException(String.format("Illegal operand type: %s", operandType.name()));
      }
    }

    switch (instructionType) {
      case HLT -> halted = true;
      case STA -> {
        regA = operands[0];
        flagZero = regA == 0;
        flagNegative = regA < 0;
      }
      default -> throw new IllegalStateException(String.format("Illegal instruction: %s", instructionType.name()));
    }
  }

  int getIp() {
    return ip;
  }

  int getRegA() {
    return regA;
  }

  boolean getFlagZero() {
    return flagZero;
  }

  boolean getFlagNegative() {
    return flagNegative;
  }

  boolean isHalted() {
    return halted;
  }
}
