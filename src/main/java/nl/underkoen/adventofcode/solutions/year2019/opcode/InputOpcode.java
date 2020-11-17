package nl.underkoen.adventofcode.solutions.year2019.opcode;

import java.util.Map;
import java.util.function.LongSupplier;
import java.util.function.LongUnaryOperator;

/**
 * Created by Under_Koen on 07/12/2019.
 */
public class InputOpcode implements Opcode {
    private LongSupplier input;

    public InputOpcode(LongSupplier input) {
        this.input = input;
    }

    @Override
    public int execute(LongUnaryOperator getArg, LongUnaryOperator getArgPos, int i, long[] program, long[] result, Map<Long, Long> storage) {
        long val = input.getAsLong();
        long pos = getArgPos.applyAsLong(1);

        if (pos >= program.length) {
            storage.put(pos, val);
        } else {
            program[(int) pos] = val;
        }
        return i + 2;
    }
}