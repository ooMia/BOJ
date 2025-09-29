import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
    private final Iterator<Integer> numbIterator;
    private final QInput _cache;

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
            this.numbIterator = numbers.iterator();
            int max = numbers.stream().max(Integer::compareTo).orElse(numbers.get(0));
            _cache = new QInput(max, numbers.get(0));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public QInput next() {
        int n = numbIterator.next();
        _cache.set(n);
        return _cache;
    }

    @Override
    public boolean hasNext() {
        return numbIterator.hasNext();
    }
}

class QInput {
    final int max;
    int n;

    public QInput(int max, int n) {
        this.max = max;
        this.n = n;
    }

    public void set(int n) {
        this.n = n;
    }
}

class Consumer implements IConsumer {
    final BufferedWriter writer;
    final ISupplier supplier;
    final StringBuilder sb = new StringBuilder();

    final List<String> dp = new ArrayList<>(Arrays.asList("-", "- -", "- -   - -"));

    public Consumer(BufferedWriter bw, ISupplier supplier) {
        this.writer = bw;
        this.supplier = supplier;
    }

    private void initDp(int _max) {
        int i = dp.size();
        StringBuilder sb = new StringBuilder();
        sb.append(dp.get(i - 1));
        while (i <= _max) {
            // 3 ** (3-1)
            sb.append(" ".repeat((int) Math.pow(3, i - 1)));
            sb.append(dp.get(i - 1));
            dp.add(sb.toString());
            i++;
        }
    }

    @Override
    public void consume(QInput input) throws IOException {
        initDp(input.max);
        sb.append(dp.get(input.n)).append("\n");
    }

    @Override
    public void flush() throws IOException {
        writer.write(sb.toString());
        writer.flush();
    }
}
