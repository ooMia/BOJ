import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface ISupplier {
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

// ====== Problem Domain ======

class Supplier implements ISupplier {
    final BufferedReader reader;

    final Deque<QInput> deque = new ArrayDeque<>();

    public Supplier(BufferedReader br) {
        this.reader = br;
        try {
            reader.readLine(); // 첫 줄은 무시
            List<Integer> parts = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (int i = 0; i < parts.size(); i++) {
                QInput input = new QInput();
                input.set(i + 1, parts.get(i));
                deque.add(input);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public QInput next() {
        var res = deque.poll();
        if (deque.isEmpty()) {
            return res;
        }

        int move = res.value;
        if (move > 0) {
            move -= 1;
        }
        move %= deque.size();
        while (move != 0) {
            if (move > 0) {
                deque.addLast(deque.pollFirst());
                move--;
            } else {
                deque.addFirst(deque.pollLast());
                move++;
            }
        }
        return res;
    }

    @Override
    public boolean hasNext() {
        return !deque.isEmpty();
    }

}

// record pattern
class QInput {
    int id; // 풍선의 번호
    int value; // 풍선 안에 적힌 값

    public void set(int id, int value) {
        this.id = id;
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
        sb.append(input.id).append(" ");
    }

    @Override
    public void flush() throws IOException {
        writer.write(sb.toString());
        writer.flush();
    }
}
