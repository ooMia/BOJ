import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static List<Integer> readIntegers() throws IOException {
        String[] tokens = br.readLine().split(" ");
        List<Integer> integers = new ArrayList<>();
        for (String token : tokens) {
            integers.add(Integer.parseInt(token));
        }
        return integers;
    }

    public static void main(String[] args) {
        try {
            br.readLine(); // Skip the first line

            List<QInput> people;
            people = br.lines().map(line -> {
                String[] parts = line.split(" ");
                return new QInput(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            }).collect(Collectors.toList());
            Runner runner = new Runner(bw, people);
            runner.run();
            runner.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Runner {
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();
    List<QInput> people = new ArrayList<>();

    Runner(BufferedWriter bw, List<QInput> people) {
        this.bw = bw;
        this.people = List.copyOf(people);
    }

    void run() {
        for (QInput input : people) {
            input.compareWithOthers(people);
            sb.append(input.nBiggerThanMe + 1).append(" ");
        }
    }

    void flush() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }
}

class QInput {
    final int weight, height;
    boolean didCompare = false;
    int nBiggerThanMe = 0;

    QInput(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    void compareWithOthers(List<QInput> others) {
        if (didCompare) {
            return;
        }
        for (QInput other : others) {
            if (this.weight < other.weight && this.height < other.height) {
                ++nBiggerThanMe;
            }
        }
        didCompare = true;
    }
}
