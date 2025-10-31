import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
    final List<QInput> balloons = new ArrayList<>();
    int currentIndex = 0;

    public Supplier(BufferedReader br) {
        this.reader = br;
        try {
            reader.readLine(); // 첫 줄은 무시
            List<Integer> values = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (int i = 0; i < values.size(); i++) {
                QInput balloon = new QInput();
                balloon.set(i + 1, values.get(i));
                balloons.add(balloon);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public QInput next() {
        if (balloons.isEmpty()) {
            return null;
        }
        
        QInput currentBalloon = balloons.get(currentIndex);
        int moveCount = currentBalloon.value;
        
        // 현재 풍선을 제거
        balloons.remove(currentIndex);
        
        // 모든 풍선을 터뜨렸으면 반환
        if (balloons.isEmpty()) {
            return currentBalloon;
        }
        
        // 다음 풍선 위치 계산
        if (moveCount > 0) {
            // 양수: 오른쪽으로 이동
            currentIndex = (currentIndex + moveCount - 1) % balloons.size();
        } else {
            // 음수: 왼쪽으로 이동
            currentIndex = (currentIndex + moveCount) % balloons.size();
            if (currentIndex < 0) {
                currentIndex += balloons.size();
            }
        }
        
        return currentBalloon;
    }

    @Override
    public boolean hasNext() {
        return !balloons.isEmpty();
    }
}

class QInput {
    int id;
    int value;
    boolean used;

    public void set(int id, int value) {
        this.id = id;
        this.value = value;
        this.used = false;
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
