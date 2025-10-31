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

    private boolean _toggle = true;
    private QInput _cache;

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
            int nSort = readNextIntegers().get(1);
            var numbers = readNextIntegers();
            this._cache = new QInput(nSort, numbers);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public QInput next() {
        this._toggle = false;
        return _cache;
    }

    @Override
    public boolean hasNext() {
        return this._toggle;
    }

}

class QInput {
    int nRemainingStore;
    List<Integer> numbers;

    public QInput(int nRemainingStore, List<Integer> numbers) {
        this.nRemainingStore = nRemainingStore;
        this.numbers = numbers;
    }
}

class Consumer implements IConsumer {
    final BufferedWriter writer;
    final ISupplier supplier;
    final StringBuilder sb = new StringBuilder();
    private int nStore;

    public Consumer(BufferedWriter bw, ISupplier supplier) {
        this.writer = bw;
        this.supplier = supplier;
    }


    @Override
    public void consume(QInput input) throws IOException {
        this.nStore = input.nRemainingStore;
        var numbers = input.numbers.stream().mapToInt(Integer::intValue).toArray();
        merge_sort(numbers, 0, numbers.length - 1);
        if (nStore > 0) {
            sb.append("-1\n"); // nStore가 0보다 크면 -1 출력
        }
    }

    private void merge_sort(int[] numbers, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2; // 중간 지점
            merge_sort(numbers, p, q); // 전반부 정렬
            merge_sort(numbers, q + 1, r); // 후반부 정렬
            merge(numbers, p, q, r); // 병합
        }
    }

    private void merge(int[] numbers, int p, int q, int r) {
        int i = p, j = q + 1, t = 0;
        var tmp = new int[r - p + 1];

        while (i <= q && j <= r) {
            if (numbers[i] <= numbers[j]) {
                tmp[t++] = numbers[i++];
            } else {
                tmp[t++] = numbers[j++];
            }
        }
        while (i <= q) { // 왼쪽 배열 부분이 남은 경우
            tmp[t++] = numbers[i++];
        }
        while (j <= r) { // 오른쪽 배열 부분이 남은 경우
            tmp[t++] = numbers[j++];
        }
        i = p;
        t = 0;
        while (i <= r) { // 결과를 numbers[p..r]에 저장
            nStore--;
            numbers[i++] = tmp[t++];
            if (nStore == 0) {
                sb.append(numbers[i - 1]).append("\n");
                return; // nStore가 0이 되면 결과를 출력하고 종료
            }
        }
    }

    @Override
    public void flush() throws IOException {
        writer.write(sb.toString());
        writer.flush();
    }
}
