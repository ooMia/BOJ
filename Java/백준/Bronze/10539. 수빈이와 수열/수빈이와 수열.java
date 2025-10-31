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

    // 수열 A가 주어지면, 다음과 같이 수열 B를 만든다.
    // (누적 평균) = (누적 합) / 개수

    // 따라서, B가 주어지면 A에 대한 누적 합 배열을 구할 수 있다.
    // 각 요소 * (현재 인덱스 + 1) = (현재까지의 A의 누적 합)

    // 각 누적합 배열의 요소는 이전 요소와의 차이를 통해
    // 원래 수열 A의 요소를 구할 수 있다.

    public String solution(int N, long[] numbers) {
        for (int i = numbers.length - 1; i > 0; --i) {
            numbers[i] = numbers[i] * (i + 1) - numbers[i - 1] * i;
        }
        StringBuilder sb = new StringBuilder();
        for (long num : numbers) {
            sb.append(num).append(' ');
        }
        return sb.toString();
    }
}

class Runner {
    final BufferedWriter bw;

    final int N;
    final long[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        var reader = new Reader(br);
        try {
            N = Integer.parseInt(br.readLine());
            numbers = reader.readLongs();
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
        var res = sol.solution(N, numbers);
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
    
    public long[] readLongs() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int cnt = st.countTokens();
        long[] longs = new long[cnt];
        for (int i = 0; i < cnt; ++i) {
            longs[i] = Long.parseLong(st.nextToken());
        }
        return longs;
    }
}