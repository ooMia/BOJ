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
    String solution(int N, int[] numbers) {
        // 조화평균을 계산하기 위한 분자와 분모 초기화
        long numerator = 0; // 역수의 합을 위한 분자
        long denominator = 1; // 공통 분모 초기화

        for (int i = 0; i < N; i++) {
            long number = numbers[i];
            // 현재 분모와 새로운 분모의 최소공배수 계산
            long lcm = lcm(denominator, number);
            // 분자를 업데이트
            numerator = numerator * (lcm / denominator) + (lcm / number);
            denominator = lcm; // 분모 업데이트
        }

        // 조화 평균의 결과는 denominator/numerator
        long gcd = gcd(numerator, denominator); // 기약분수로 만들기
        numerator /= gcd;
        denominator /= gcd;

        // 결과 출력
        return String.format("%s/%s", denominator, numerator);
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final int[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = Integer.parseInt(br.readLine());
            numbers = reader.readInts();
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