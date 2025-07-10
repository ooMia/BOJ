import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

interface ISupplier extends IStructModifier, IStructState {
    QInput next();

    boolean hasNext();
}

interface IConsumer {
    void consume(QInput input) throws IOException;
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
                bw.flush();
                bw.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// ====== Problem Domain ======

interface IStructModifier {
    void C1_pushFront(int x); // 정수 X를 덱의 앞에 넣는다. (1 ≤ X ≤ 100,000)

    void C2_pushBack(int x); // 정수 X를 덱의 뒤에 넣는다. (1 ≤ X ≤ 100,000)

    int C3_popFront(); // 덱에 정수가 있다면 맨 앞의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.

    int C4_popBack(); // 덱에 정수가 있다면 맨 뒤의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
}

interface IStructState {
    int C5_size(); // 덱에 들어있는 정수의 개수를 출력한다

    int C6_isEmpty(); // 덱이 비어있으면 1, 아니면 0을 출력한다.

    int C7_peekFront(); // 덱에 정수가 있다면 맨 앞의 정수를 출력한다. 없다면 -1을 대신 출력한다.

    int C8_peekBack(); // 덱에 정수가 있다면 맨 뒤의 정수를 출력한다. 없다면 -1을 대신 출력한다.
}

class Supplier implements ISupplier {
    final BufferedReader reader;
    final QInput _cache = new QInput();

    final Deque<Integer> deque = new ArrayDeque<>();

    public Supplier(BufferedReader br) {
        this.reader = br;
        try {
            reader.readLine(); // 첫 줄은 무시
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public QInput next() {
        int[] parts;
        try {
            parts = Stream.of(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            _cache.set(parts[0], parts.length > 1 ? parts[1] : null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return _cache;
    }

    @Override
    public boolean hasNext() {
        try {
            return reader.ready();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void C1_pushFront(int x) {
        deque.addFirst(x);
    }

    @Override
    public void C2_pushBack(int x) {
        deque.addLast(x);
    }

    @Override
    public int C3_popFront() {
        return deque.isEmpty() ? -1 : deque.removeFirst();
    }

    @Override
    public int C4_popBack() {
        return deque.isEmpty() ? -1 : deque.removeLast();
    }

    @Override
    public int C5_size() {
        return deque.size();
    }

    @Override
    public int C6_isEmpty() {
        return deque.isEmpty() ? 1 : 0;
    }

    @Override
    public int C7_peekFront() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }

    @Override
    public int C8_peekBack() {
        return deque.isEmpty() ? -1 : deque.peekLast();
    }
}

// record pattern
class QInput {
    int operator;
    Integer operand;

    public void set(int operator, Integer operand) {
        this.operator = operator;
        this.operand = operand;
    }
}

class Consumer implements IConsumer {
    final BufferedWriter writer;
    final ISupplier supplier;

    public Consumer(BufferedWriter bw, ISupplier supplier) {
        this.writer = bw;
        this.supplier = supplier;
    }

    @Override
    public void consume(QInput input) throws IOException {
        switch (input.operator) {
            case 1:
                supplier.C1_pushFront(input.operand);
                break;
            case 2:
                supplier.C2_pushBack(input.operand);
                break;
            case 3:
                writer.write(supplier.C3_popFront() + "\n");
                break;
            case 4:
                writer.write(supplier.C4_popBack() + "\n");
                break;
            case 5:
                writer.write(supplier.C5_size() + "\n");
                break;
            case 6:
                writer.write(supplier.C6_isEmpty() + "\n");
                break;
            case 7:
                writer.write(supplier.C7_peekFront() + "\n");
                break;
            case 8:
                writer.write(supplier.C8_peekBack() + "\n");
                break;
        }
    }
}
