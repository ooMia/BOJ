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
            // int T = Integer.parseInt(br.readLine().trim());
            // for (int i = 0; i < T; ++i)
            {
                // System.err.println("\ncase " + (i + 1));
                // long start = System.currentTimeMillis();
                Runner runner = new Runner(br, bw);
                runner.run();
                runner.flush();
                // long end = System.currentTimeMillis();
                // System.err.println("took " + (end - start) + " ms");
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {
    public String solution(int[][] pairs) {
        var sb = new StringBuilder();
        for (var pair : pairs) {
            sb.append(pair[1] / (pair[0] + 1)).append('\n');
        }
        return sb.toString().trim();
    }
}

class Runner {
    final BufferedWriter bw;

    final int[][] pairs;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;
        // var reader = new Reader(br);
        try {
            this.pairs = br.lines().map(line -> {
                var nums = line.split(" ");
                return new int[]{Integer.parseInt(nums[0]), Integer.parseInt(nums[1])};
            }).toArray(int[][]::new);
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
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
        var res = sol.solution(pairs);
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

    public int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public int[] readInts() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, " ");
        int cnt = st.countTokens();
        int[] ints = new int[cnt];
        for (int i = 0; i < cnt; ++i) {
            ints[i] = Integer.parseInt(st.nextToken());
        }
        return ints;
    }

    public long[] readLongs() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, " ");
        int cnt = st.countTokens();
        long[] longs = new long[cnt];
        for (int i = 0; i < cnt; ++i) {
            longs[i] = Long.parseLong(st.nextToken());
        }
        return longs;
    }
}