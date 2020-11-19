package cpusim;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class Instruction {
  enum Type {
    HLT(0);

    final int opcode;

    Type(int opcode) {
      this.opcode = opcode;
    }

    static Optional<Type> fromOpcode(int opcode) {
      if (opcodeToType.containsKey(opcode)) {
        return Optional.of(opcodeToType.get(opcode));
      }
      return Optional.empty();
    }

    private static final Map<Integer, Type> opcodeToType = new HashMap<>();

    static {
      for (Type type : Type.values()) {
        int opcode = type.opcode;
        if (opcodeToType.containsKey(opcode)) {
          throw new IllegalStateException(String.format("Multiple instruction types with the same opcode %d: %s and %s.",
              type.opcode, opcodeToType.get(opcode).name(), type.name()));
        }
        opcodeToType.put(opcode, type);
      }
    }
  }
}
