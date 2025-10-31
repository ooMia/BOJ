import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            int T = 1;
            for (int i = 0; i < T; ++i) {
                Runner runner = new Runner(br, bw);
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
    // M: 터널 안에 들어있는 차량의 수
    // datas: 특정 시간대에 입구를 통과한 차량의 수와 출구를 통과한 차량의 수
    public int solution(int M, List<Record> datas) {
        int maxCount = M;
        int currentCount = M;
        for (var data : datas) {
            currentCount = data.getTunnelCount(currentCount);
            if (currentCount < 0) return 0;
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N, M; // N: 조사한 시간, M: 터널 안에 들어있는 차량의 수
    final List<Record> datas; // 특정 시간대에 입구를 통과한 차량의 수와 출구를 통과한 차량의 수

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            datas = new java.util.ArrayList<>(N);
            for (int i = 0; i < N; ++i) {
                var line = reader.readInts();
                datas.add(new Record(line[0], line[1]));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write('\n');
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution();
        var res = sol.solution(M, datas);
        _write(res);
    }

    private void _write(Object o) {
        try {
            bw.write(String.valueOf(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    public int[] readInts() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int cnt = st.countTokens();
        int[] ints = new int[cnt];
        for (int i = 0; i < cnt; ++i) {
            ints[i] = Integer.parseInt(st.nextToken());
        }
        return ints;
    }

    public long[] readLongs() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int cnt = st.countTokens();
        long[] longs = new long[cnt];
        for (int i = 0; i < cnt; ++i) {
            longs[i] = Long.parseLong(st.nextToken());
        }
        return longs;
    }

    public int[][] readIntArrays() throws IOException {
        return br.lines().map(s -> Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray())
                .toArray(int[][]::new);
    }
}

class Record {
    int entranceCount;
    int exitCount;

    public Record(int entranceCount, int exitCount) {
        this.entranceCount = entranceCount;
        this.exitCount = exitCount;
    }

    int getTunnelCount(int initialTunnelCount) {
        return initialTunnelCount + entranceCount - exitCount;
    }
}