import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            int T = 1;
            for (int i = 0; i < T; ++i) {
                IRunner runner = new Runner(br, bw);
                runner.run();
                runner.flush();
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {

    final int baseTime = 100;
    final int baseFee = 10;
    final int unitTime = 50;
    final int unitFee = 3;

    public String solution(Map<String, Integer> usage) {
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> feeMap = new HashMap<>();
        for (var entry : usage.entrySet()) {
            String user = entry.getKey();
            int time = entry.getValue();
            int fee = calcFee(time);
            feeMap.put(user, fee);
        }

        var l = feeMap.entrySet().stream()
                .sorted(
                        (o1, o2) -> {
                            int cmp = Integer.compare(o2.getValue(), o1.getValue());
                            return cmp != 0 ? cmp : o1.getKey().compareTo(o2.getKey());
                        })
                .collect(java.util.stream.Collectors.toList());

        for (var entry : l) {
            String user = entry.getKey();
            int fee = entry.getValue();
            sb.append(user).append(' ').append(fee).append('\n');
        }
        return sb.toString();
    }

    int calcFee(int _time) {
        if (_time <= baseTime)
            return baseFee;
        int extra = _time - baseTime;
        int units = (extra + unitTime - 1) / unitTime; // 올림 처리
        return baseFee + units * unitFee;
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N; // N: 통화 기록의 개수
    final Map<String, Integer> usage = new HashMap<>(); // 사용자별 통화 시간

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = reader.readInts()[0];
            for (int i = 0; i < N; ++i) {
                String[] line = reader.line().split(" ");
                String h = line[0].split(":")[0];
                String m = line[0].split(":")[1];
                String user = line[1];

                int time = Integer.parseInt(h) * 60 + Integer.parseInt(m);
                usage.put(user, usage.getOrDefault(user, 0) + time);
            }

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() throws IOException {
        var res = new Solution().solution(usage);
        sb.append(res);
    }
}

class Reader implements IReader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void skipLine() throws IOException {
        br.readLine();
    }

    @Override
    public Stream<String> lines() throws IOException {
        return br.lines();
    }

    @Override
    public String line() throws IOException {
        return br.readLine();
    }

    @Override
    public int[] readInts() throws IOException {
        String[] tokens = br.readLine().split(" ");
        int[] ints = new int[tokens.length];
        for (int i = 0; i < tokens.length; ++i) {
            ints[i] = Integer.parseInt(tokens[i]);
        }
        return ints;
    }

    @Override
    public List<Integer> readIntegers() throws IOException {
        String[] tokens = br.readLine().split(" ");
        var res = new ArrayList<Integer>(tokens.length);
        for (String token : tokens) {
            res.add(Integer.valueOf(token));
        }
        return res;
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}

interface IReader {
    void skipLine() throws IOException;

    Stream<String> lines() throws IOException;

    String line() throws IOException;

    List<Integer> readIntegers() throws IOException;

    int[] readInts() throws IOException;
}
