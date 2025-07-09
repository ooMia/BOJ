import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
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

            if (supplier.isSuccess()) {
                bw.write("Nice\n");
            } else {
                bw.write("Sad\n");
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
    void sourceToTemp();

    void sourceToDest();

    void tempToDest();
}

interface IStructState {
    boolean isSuccess();

    boolean isValidId(Integer id);
}

class Supplier implements ISupplier {
    final BufferedReader reader;
    final QInput _cache = new QInput();

    private final int goalId;
    private int needId;

    final Queue<Integer> source;
    final Stack<Integer> temp;

    public Supplier(BufferedReader br) {
        this.reader = br;
        try {
            int nPeople = Integer.parseInt(reader.readLine());
            this.goalId = nPeople;
            this.needId = 1;

            List<Integer> ids = Stream.of(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            this.source = new ArrayDeque<>(ids);
            this.temp = new Stack<>();

            this._cache.set(source.peek(), null);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public QInput next() {
        _cache.set(source.peek(), temp.isEmpty() ? null : temp.peek());
        return _cache;
    }

    @Override
    public boolean hasNext() {
        boolean hasSource = !source.isEmpty();
        boolean hasTemp = !temp.isEmpty();
        boolean hasTempAlive = !hasSource && hasTemp && temp.peek() == needId;
        return hasSource || hasTempAlive;
    }

    @Override
    public boolean isSuccess() {
        return !hasNext() && needId > goalId;
    }

    @Override
    public boolean isValidId(Integer id) {
        return id != null && id == needId;
    }

    @Override
    public void sourceToTemp() {
        Integer id = source.poll();
        if (id == null) {
            return;
        }
        temp.push(id);
        // System.err.println("source -> temp: " + id);
    }

    @Override
    public void sourceToDest() {
        Integer id = source.poll();
        if (id == null) {
            return;
        }
        // System.err.println("source -> dest: " + id);
        needId++;
    }

    @Override
    public void tempToDest() {
        try {
            Integer id = temp.pop();
            if (id == null) {
                return;
            }
            // System.err.println("temp -> dest: " + id);
            needId++;
        } catch (EmptyStackException e) {
        }
    }
}

// record pattern
class QInput {
    private Integer sourceId;
    private Integer tempId;

    public void set(Integer sourceId, Integer tempId) {
        this.sourceId = sourceId;
        this.tempId = tempId;
    }

    public Integer sourceId() {
        return sourceId;
    }

    public Integer tempId() {
        return tempId;
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
        if (supplier.isValidId(input.sourceId())) {
            supplier.sourceToDest();
            return;
        }

        if (supplier.isValidId(input.tempId())) {
            supplier.tempToDest();
            return;
        }

        supplier.sourceToTemp();
    }
}
