import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
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
    final StringBuilder sb = new StringBuilder(), buffer = new StringBuilder();
    boolean inTag = false;

    public String solution(String line) {
        // 반복문을 순회하는 동안 2개의 상태가 존재함
        // 1. tag 안에 있는 상태 : <를 만나면 시작하고, >를 만나면 끝나며 상태 저장
        // 2. tag 밖에 있는 상태 : tag 안에 있는 상태가 아니라면 기본 상태이고, 공백 또는 태그 시작을 만나면 끝나며 상태 저장
        for (int i = 0; i < line.length(); ++i) {
            var c = line.charAt(i);

            switch (c) {
                case '<':
                    // isTag = true
                    // flush if toggled
                    if (!inTag)
                        flush();
                    inTag = true;
                    sb.append(c);
                    break;
                case '>':
                    // isTag = false
                    flush();
                    inTag = false;
                    sb.append(c);
                    break;
                case ' ':
                    // flush buffer
                    flush();
                    sb.append(c);
                    break;
                default:
                    buffer.append(c);
            }
        }
        flush();
        return sb.toString();
    }

    void flush() {
        if (inTag) {
            sb.append(buffer.toString());
        } else {
            sb.append(buffer.reverse().toString());
        }
        buffer.setLength(0);
    }

}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final String line;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.line = reader.line();

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
        var res = new Solution().solution(line);
        sb.append(res).append('\n');
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
