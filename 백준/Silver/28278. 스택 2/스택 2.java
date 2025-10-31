import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

interface IParser {
    QInput next();

    boolean hasNext();
}

interface IRunner {
    void run() throws IOException;
}

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final IParser parser = new Parser(br);
    static final IRunner runner = new Runner(parser, bw);

    public static void main(String[] args) {
        try {
            runner.run();
            bw.flush();
            bw.close();
            br.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

class Parser implements IParser {
    final BufferedReader reader;
    final QInput cache = new QInput(0, null);
    boolean hasNextLine = true;

    public Parser(BufferedReader br) {
        this.reader = br;
        try {
            reader.readLine(); // Skip the first line
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public QInput next() {
        String[] parts;
        try {
            String line = reader.readLine();
            parts = line.split(" ");
        } catch (IOException | NullPointerException e) {
            hasNextLine = false;
            return null;
        }
        int a = Integer.parseInt(parts[0]);
        Integer b = parts.length > 1 ? Integer.parseInt(parts[1]) : null;
        cache.set(a, b);
        return cache;
    }

    @Override
    public boolean hasNext() {
        return hasNextLine;
    }
}

// record pattern
class QInput {
    private int opCode;
    private Integer operand;

    public QInput(int opCode, Integer operand) {
        this.opCode = opCode;
        this.operand = operand;
    }

    public int opCode() {
        return opCode;
    }

    public Integer operand() {
        return operand;
    }

    public void set(int opCode, Integer operand) {
        this.opCode = opCode;
        this.operand = operand;
    }
}

class Runner implements IRunner {
    final IParser parser;
    final BufferedWriter writer;
    final ArrayDeque<Integer> stack = new ArrayDeque<>(); // Java Stack is synchronized, which can be inefficient.

    public Runner(IParser parser, BufferedWriter bw) {
        this.parser = parser;
        this.writer = bw;
    }

    void iterate() throws IOException {
        QInput input = parser.next();
        if (input == null)
            return;

        switch (input.opCode()) {
            case 1:
                stack.push(input.operand());
                break;
            case 2:
                writer.write(String.valueOf(stack.isEmpty() ? -1 : stack.pop()));
                writer.newLine();
                break;
            case 3:
                writer.write(String.valueOf(stack.size()));
                writer.newLine();
                break;
            case 4:
                writer.write(String.valueOf(stack.isEmpty() ? 1 : 0));
                writer.newLine();
                break;
            case 5:
                writer.write(String.valueOf(stack.isEmpty() ? -1 : stack.peek()));
                writer.newLine();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void run() throws IOException {
        while (parser.hasNext()) {
            iterate();
        }
    }
}
