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
    final String sk = "SK", cy = "CY";
    final boolean[] isSkWin = new boolean[]{true, true, false};

    public String solution(long N) {
        // N 최댓값이 1_000_000_000_000이라
        // dp로는 해결하지 못함

        // N이 작았을 때의 경우를 생각해보자
        // 돌이 1개이면 처음 시작하는 사람이 이긴다
        // 2개면 지고, 3개면 또 이긴다

        // 4개면 처음에 3개를 가져가든 1개를 가져가든 진다
        // f(1), f(3)의 반전인 결과이다.
        // 5개면 이기고, 6개면 진다.

        // 7개면 처음에 1개를 가져가면 f(6)
        // 3개를 가져가면 f(4)가 되어 이길 수 있다.

        // 즉, div 3의 몫과 mod 3의 나머지 결과가 중요하다.
        // mod 3의 나머지에 따라 isSkWin 결과를 참조하고,
        // div 3의 몫이 0, 2 ... 짝수이면 isSkWin 결과를 그대로 반환하고,
        // 홀수이면 결과를 반전시켜 반환한다.

        // 결과를 정리하면
        // 1, 2, 3은 sk, cy, sk
        // 4, 5, 6은 cy, sk, cy
        // 이걸 mod로 표현하려면 (N - 1) % 3 해야
        // 각각 [0, 1, 2] 인덱스에 매핑된다

        boolean isDivEven = ((N - 1) / 3) % 2 == 0;
        int mod3 = (int) (N % 3);
        boolean isSk = isDivEven ? isSkWin[mod3] : !isSkWin[mod3];
        return isSk ? sk : cy;
    }
}

class Runner {
    final BufferedWriter bw;

    final long N;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        // var reader = new Reader(br);
        try {
            N = Long.parseLong(br.readLine());

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
        var res = sol.solution(N);
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
}