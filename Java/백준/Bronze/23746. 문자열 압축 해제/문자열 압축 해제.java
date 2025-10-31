import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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

    private final Map<Character, String> compressionMap;

    Solution(Map<Character, String> compressionMap){
        this.compressionMap = compressionMap;
    }

    public String solution(String compressed, int l, int r) {
        StringBuilder sb = new StringBuilder();
        for (char ch : compressed.toCharArray()) {
            sb.append(compressionMap.get(ch));
        }
        // l과 r은 1-based 인덱스이므로, 0-based 인덱스로 변환
        return sb.substring(l - 1, r);
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N; // 압축 방법의 개수
    final Map<Character, String> compressionMap;
    final String compressed; // 압축된 문자열
    final int l, r; // 압축 이전의 문자열 반환 범위

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(reader.line());
            this.compressionMap = new HashMap<>(N, 1.0f);
            for (int i = 0; i < N; i++) {
                String[] parts = reader.line().split(" ");
                this.compressionMap.put(parts[1].charAt(0), parts[0]);
            }
            this.compressed = reader.line();
            var _lr = reader.readInts();
            this.l = _lr[0];
            this.r = _lr[1];
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
        var sol = new Solution(compressionMap);
        var res = sol.solution(compressed, l, r);
        bw.write(String.valueOf(res));
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    public String line() throws IOException {
        return br.readLine();
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