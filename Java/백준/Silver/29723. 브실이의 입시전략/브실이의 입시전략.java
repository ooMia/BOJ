import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

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
    public String solution(
        int N, // 수강 과목 수
        int M, // 요구 과목 수
        int K, // 공개한 과목 수
        Map<String, Integer> dict, // 수강 과목과 점수
        String[] keys // 평가에 들어가야 하는 과목들
    ) {
        int res = 0;
        // 1. keys를 돌면서 dict의 값 더하기
        for (String key : keys) {
            res += dict.getOrDefault(key, 0);
            dict.remove(key);
        }

        List<Integer> orderedScores = dict.values().stream().sorted().collect(Collectors.toList());
        int goal = M - K;
        int iLast = orderedScores.size() - 1;

        // 2. 최솟값 구하기 (M - K)개
        int min = 0;
        for (int i = 0; i < goal; ++i) {
            min += orderedScores.get(i);
        }

        // 3. 최댓값 구하기 (M - K)개
        int max = 0;
        for (int i = iLast; i > iLast - goal; --i) {
            max += orderedScores.get(i);
        }
        return String.format("%d %d", min + res, max + res);
    }
}

class Runner {
    final BufferedWriter bw;

    final int N, M, K;
    final Map<String, Integer> dict;
    final String[] keys;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        var reader = new Reader(br);
        try {
            var nmk = reader.readInts();
            N = nmk[0];
            M = nmk[1];
            K = nmk[2];

            dict = new java.util.HashMap<>();
            for (int i = 0; i < N; ++i) {
                var line = br.readLine().split(" ");
                dict.put(line[0], Integer.parseInt(line[1]));
            }
            keys = br.lines().toArray(String[]::new);

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
        var res = sol.solution(N, M, K, dict, keys);
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