import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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

    final static int maxClassNumber = 1_000_000;
    final static int[] have = new int[maxClassNumber + 1];  // have[i] = 수업 i를 가진 학생 수
    final static int[] want = new int[maxClassNumber + 1];  // want[i] = 수업 i를 원하는 학생 수

    // N: 학생 수
    // cur: i번 학생의 현재 수업 배열
    // seek: i번 학생의 원하는 수업 배열
    public int solution(int N, int[] cur, int[] seek) {
        // 각 수업별로 현재 가진 학생 수와 원하는 학생 수 계산
        for (int i = 0; i < N; ++i) {
            ++have[cur[i]];
            ++want[seek[i]];
        }

        // 각 수업별로 교환 가능한 학생 수
        int canExchange = 0;
        for (int i = 1; i <= maxClassNumber; ++i) {
            canExchange += Math.min(have[i], want[i]);
        }

        return N - canExchange;
    }
}

class Runner {
    final BufferedWriter bw;

    final int N;
    final int[] As, Bs;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        var reader = new Reader(br);
        try {
            N = Integer.parseInt(br.readLine());
            As = reader.readInts();
            Bs = reader.readInts();

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
        var res = sol.solution(N, As, Bs);
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
}