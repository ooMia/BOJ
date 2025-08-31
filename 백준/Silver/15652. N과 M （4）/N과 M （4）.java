import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
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
    public void push(QInput input);
}

interface IStructState {
    public int getRange();
}

class Supplier implements ISupplier {
    final BufferedReader reader;
    final int range;
    final int nLimit;
    private final Deque<QInput> deque = new ArrayDeque<>();

    private List<Integer> readMultiLineIntegers() {
        try {
            return reader.lines()
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (UncheckedIOException e) {
            throw new IllegalArgumentException();
        }
    }

    public Supplier(BufferedReader br) {
        this.reader = br;
        try {
            List<Integer> numbers = readMultiLineIntegers();
            this.range = numbers.get(0);
            this.nLimit = numbers.get(1);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        for (int initialNumber = 1; initialNumber <= range; initialNumber++) {
            if (nLimit == 1) {
                System.out.println(initialNumber);
                continue;
            }
            QInput input = new QInput(nLimit, initialNumber);
            deque.offer(input);
        }
    }

    @Override
    public QInput next() {
        return deque.poll();
    }

    @Override
    public boolean hasNext() {
        return !deque.isEmpty();
    }

    @Override
    public void push(QInput input) {
        deque.offer(input);
    }

    @Override
    public int getRange() {
        return range;
    }
}

class QInput {
    final int nLimit;
    final private List<Integer> numbers = new ArrayList<>();

    public QInput(int nLimit, int first) {
        this.nLimit = nLimit;
        this.numbers.add(first);
    }

    public void choose(int number) {
        numbers.add(number);
    }

    public boolean isComplete() {
        return numbers.size() == nLimit;
    }

    public void print(StringBuilder sb) {
        sb.append(this.numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "))).append("\n");
    }

    public QInput copyOf() {
        QInput copy = new QInput(nLimit, numbers.get(0));
        for (int i = 1; i < numbers.size(); i++) {
            copy.choose(numbers.get(i));
        }
        return copy;
    }

    public int getLast() {
        return numbers.get(numbers.size() - 1);
    }
}

class Consumer implements IConsumer {
    final BufferedWriter writer;
    final ISupplier supplier;
    final StringBuilder sb = new StringBuilder();

    public Consumer(BufferedWriter bw, ISupplier supplier) {
        this.writer = bw;
        this.supplier = supplier;
    }

    @Override
    public void consume(QInput input) throws IOException {
        for (int i = input.getLast(); i <= supplier.getRange(); i++) {
            QInput newInput = input.copyOf();
            newInput.choose(i);

            if (newInput.isComplete()) {
                newInput.print(sb);
            } else {
                supplier.push(newInput);
            }
        }
    }

    @Override
    public void flush() throws IOException {
        writer.write(sb.toString());
        writer.flush();
    }
}
