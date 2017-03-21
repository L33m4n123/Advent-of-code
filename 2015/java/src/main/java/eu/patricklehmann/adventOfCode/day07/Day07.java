package eu.patricklehmann.adventOfCode.day07;

import eu.patricklehmann.adventOfCode.utils.AdventInputReader;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum Operation {
    AND, OR, LSHIFT, RSHIFT, NOT, PROVIDED;

    static Operation getOperationFromLine(String line) {
        for (Operation op : Operation.values()) {
            if (line.contains(op.toString())) {
                return op;
            }
        }
        return PROVIDED;
    }
}

/**
 * --- Day 7: Some Assembly Required ---
 * <p>
 * This year, Santa brought little Bobby Tables a set of wires and bitwise logic gates! Unfortunately, little Bobby is a little under the recommended age range, and he needs help assembling the circuit.
 * <p>
 * Each wire has an identifier (some lowercase letters) and can carry a 16-bit signal (a number from 0 to 65535). A signal is provided to each wire by a gate, another wire, or some specific value. Each wire can only get a signal from one source, but can provide its signal to multiple destinations. A gate provides no signal until all of its inputs have a signal.
 * <p>
 * The included instructions booklet describes how to connect the parts together: x AND y -> z means to connect wires x and y to an AND gate, and then connect its output to wire z.
 * <p>
 * For example:
 * <p>
 * 123 -> x means that the signal 123 is provided to wire x.
 * x AND y -> z means that the bitwise AND of wire x and wire y is provided to wire z.
 * p LSHIFT 2 -> q means that the value from wire p is left-shifted by 2 and then provided to wire q.
 * NOT e -> f means that the bitwise complement of the value from wire e is provided to wire f.
 * Other possible gates include OR (bitwise OR) and RSHIFT (right-shift). If, for some reason, you'd like to emulate the circuit instead, almost all programming languages (for example, C, JavaScript, or Python) provide operators for these gates.
 * <p>
 * For example, here is a simple circuit:
 * <p>
 * 123 -> x
 * 456 -> y
 * x AND y -> d
 * x OR y -> e
 * x LSHIFT 2 -> f
 * y RSHIFT 2 -> g
 * NOT x -> h
 * NOT y -> i
 * After it is run, these are the signals on the wires:
 * <p>
 * d: 72
 * e: 507
 * f: 492
 * g: 114
 * h: 65412
 * i: 65079
 * x: 123
 * y: 456
 * In little Bobby's kit's instructions booklet (provided as your puzzle input), what signal is ultimately provided to wire a?
 * <p>
 * Your puzzle answer was 956.
 * <p>
 * --- Part Two ---
 * <p>
 * Now, take the signal you got on wire a, override wire b to that signal, and reset the other wires (including wire a). What new signal is ultimately provided to wire a?
 * <p>
 * Your puzzle answer was 40149.
 */
public class Day07 {

    private List<String> input;
    // Have a reference to all wires
    private Map<String, Integer> wires;
    private Map<String, String> sources;

    public Day07() {
        input = AdventInputReader.getStringListInput(new InputStreamReader(getClass()
                .getResourceAsStream("/inputs/day07_input.txt")));

        wires = new HashMap<>();
        sources = new HashMap<>();
        wireThemWires();

        int signal = getSignalForWire("a");
        System.out.println("The Signal on Wire a is: " + signal);
        wires.clear();
        wires.put("b", signal);
        signal = getSignalForWire("a");
        System.out.println("The new signal on Wire a is: " + signal);
    }

    private void wireThemWires() {
        for (String line : input) {
            String[] split = line.split("->");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].trim();
            }
            sources.put(split[1], split[0]);
        }
    }

    private int getSignalForWire(String wire) {
        try {
            int val = Integer.parseInt(wire);
            return val;
        } catch (NumberFormatException nfe) {
            // I hate! silent catches but I cba to check if it is a real number or not! :P
        }
        if (!wires.containsKey(wire)) {
            Integer signal;
            String instruc = sources.get(wire);

            if (instruc.length() <= 2) {
                return getSignalForWire(instruc);
            } else {
                Operation operation = Operation.getOperationFromLine(instruc);
                signal = doInstructions(operation, instruc);
            }
            wires.put(wire, signal);
        }
        return wires.get(wire);
    }

    private int doInstructions(Operation op, String instruction) {
        String[] splitInstruction = instruction.replace(op.toString() + " ", "").split(" ");
        splitInstruction[0] = splitInstruction[0].trim();
        int left;
        try {
            left = Integer.parseInt(splitInstruction[0]);
        } catch (NumberFormatException nfe) {
            left = getSignalForWire(splitInstruction[0]);
        }
        if (splitInstruction.length > 1) {
            int right;
            splitInstruction[1] = splitInstruction[1].trim();
            try {
                right = Integer.parseInt(splitInstruction[1]);
            } catch (NumberFormatException nfe) {
                right = getSignalForWire(splitInstruction[1]);
            }
            switch (op) {
                case AND:
                    return left & right;
                case OR:
                    return left | right;
                case LSHIFT:
                    return left << right;
                case RSHIFT:
                    return left >> right;
            }
        } else if (Operation.NOT == op) {
            return Integer.MAX_VALUE - getSignalForWire(splitInstruction[0]);
        }
        return Integer.parseInt(instruction);
    }
}
