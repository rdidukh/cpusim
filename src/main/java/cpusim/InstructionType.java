package cpusim;

import static cpusim.OperandType.IMM_8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

enum InstructionType {
  HLT(0, List.of()),
  STA(0x01, List.of(IMM_8));

  final int opcode;
  final List<OperandType> operandTypes;

  InstructionType(int opcode, List<OperandType> operandTypes) {
    this.opcode = opcode;
    this.operandTypes = operandTypes;
  }

  static Optional<InstructionType> fromOpcode(int opcode) {
    if (opcodeToType.containsKey(opcode)) {
      return Optional.of(opcodeToType.get(opcode));
    }
    return Optional.empty();
  }

  private static final Map<Integer, InstructionType> opcodeToType = new HashMap<>();

  static {
    for (InstructionType instructionType : InstructionType.values()) {
      int opcode = instructionType.opcode;
      if (opcodeToType.containsKey(opcode)) {
        throw new IllegalStateException(String.format("Multiple instruction types with the same opcode %d: %s and %s.",
            instructionType.opcode, opcodeToType.get(opcode).name(), instructionType.name()));
      }
      opcodeToType.put(opcode, instructionType);
    }
  }
}
