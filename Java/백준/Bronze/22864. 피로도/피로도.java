import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
    /**
     * @param A 피로도 증가량
     * @param B 처리량
     * @param C 피로도 감소량
     * @param M 최대 피로도
     * @return 피로도 M을 넘지 않고 24시간 내에 처리할 수 있는 최대 처리량
     */
    public int solution(int A, int B, int C, int M) {
        int time, fatigue, workDone;
        time = fatigue = workDone = 0;
        while (time < 24) {
            if (fatigue + A > M) {
                fatigue = Math.max(0, fatigue - C);
            } else {
                fatigue += A;
                workDone += B;
            }
            ++time;
        }
        return workDone;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int A, B, C, M; // A: 피로도 증가량, B: 처리량, C: 피로도 감소량, M: 최대 피로도

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.readInts();
            this.A = input[0];
            this.B = input[1];
            this.C = input[2];
            this.M = input[3];
        } catch (IOException e) {
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
        var res = new Solution().solution(A, B, C, M);
        bw.write(String.valueOf(res));
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