import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface ISupplier extends IStructModifier, IStructState {
    QInput next();

    boolean hasNext();
}

interface IConsumer {
    void consume(QInput input) throws IOException;

    void flush() throws IOException;
}

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final ISupplier supplier = new Supplier(br);
    static final IConsumer consumer = new Consumer(bw, supplier);

    public static void main(String[] args) {
        try {
            while (supplier.hasNext()) {
                QInput input = supplier.next();
                consumer.consume(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                consumer.flush();
                bw.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

interface IStructModifier {
}

interface IStructState {
}

class Supplier implements ISupplier {
    final BufferedReader reader;
    private int nInput;

    private List<Integer> readNextIntegers() {
        try {
            return Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public Supplier(BufferedReader br) {
        this.reader = br;
        try {
            this.nInput = readNextIntegers().get(0);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public QInput next() {
        try {
            return new QInput(reader.readLine());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        } finally {
            --nInput;
        }
    }

    @Override
    public boolean hasNext() {
        return nInput > 0;
    }
}

class QInput {
    String line;

    public QInput(String line) {
        this.line = line;
    }
}

class Consumer implements IConsumer {
    final BufferedWriter writer;
    final ISupplier supplier;
    final StringBuilder sb = new StringBuilder();
    private int callTime;

    public Consumer(BufferedWriter bw, ISupplier supplier) {
        this.writer = bw;
        this.supplier = supplier;
    }

    private int recursion(String s, int l, int r) {
        callTime++;
        if (l >= r)
            return 1;
        else if (s.charAt(l) != s.charAt(r))
            return 0;
        else
            return recursion(s, l + 1, r - 1);
    }

    @Override
    public void consume(QInput input) throws IOException {
        this.callTime = 0;
        int isPalindrome = recursion(input.line, 0, input.line.length() - 1);
        sb.append(isPalindrome).append(" ").append(callTime).append("\n");
    }

    @Override
    public void flush() throws IOException {
        writer.write(sb.toString());
        writer.flush();
    }
}
