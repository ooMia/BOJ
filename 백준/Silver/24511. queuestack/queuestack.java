import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
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
    int pushAndPop(int value);
}

interface IStructState {
}

class Supplier implements ISupplier {
    final BufferedReader reader;
    final Deque<Integer> queue;
    final Iterator<QInput> nextIt;

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
            // 입력 읽기
            reader.readLine(); // 첫 줄 무시
            List<Integer> isStack = readNextIntegers();
            List<Integer> initialTempValues = readNextIntegers();
            reader.readLine(); // 넷째 줄 무시
            List<Integer> nextValues = readNextIntegers();

            // 초기화
            this.nextIt = nextValues.stream()
                    .map(QInput::new)
                    .iterator();
            this.queue = new ArrayDeque<>();

            for (int i = 0; i < isStack.size(); ++i) {
                boolean isStacked = isStack.get(i) == 1;
                int initialTemp = initialTempValues.get(i);
                if (isStacked == false) {
                    queue.offerFirst(initialTemp);
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public QInput next() {
        return nextIt.next();
    }

    @Override
    public boolean hasNext() {
        return nextIt.hasNext();
    }

    @Override
    public int pushAndPop(int value) {
        queue.offerLast(value);
        return queue.pollFirst();
    }
}

class QInput {
    int value;

    public QInput(int value) {
        this.value = value;
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
        int value = supplier.pushAndPop(input.value);
        sb.append(value).append(" ");
    }

    @Override
    public void flush() throws IOException {
        writer.write(sb.toString());
        writer.flush();
    }
}
