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
    private int hour = 1; // [1, 12]
    private int modifier = +1; // +1 or -1

    final StringBuilder sb = new StringBuilder();

    public String solution(int N, Card[] cards) {
        sb.ensureCapacity(20 * N);

        for (int i = 0; i < N; ++i) {
            var c = cards[i];
            applyRules(c);
            turnOver();
        }

        return sb.toString();
    }

    void applyRules(Card c) {
        sb.append(hour).append(' ');

        boolean needSync = false;
        if (c.isHourglass && c.hour == hour) {
            // 과부하의 원칙
        } else if (c.isHourglass) {
            // 시간 역행의 법칙
            modifier *= -1;
        } else if (c.hour == hour) {
            // 동기화의 원칙
            needSync = true;
        }
        sb.append(needSync ? "YES" : "NO").append('\n');
    }

    void turnOver() {
        hour += modifier;
        if (hour == 0) hour = 12;
        else if (hour == 13) hour = 1;
    }
}

class Card {
    final boolean isHourglass;
    final int hour;

    Card(String type, int hour) {
        this.isHourglass = "HOURGLASS".equals(type);
        this.hour = hour;
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N; // N: 펼쳐질 카드의 개수
    final Card[] cards;
    
    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = reader.readInts()[0];
            this.cards = new Card[N];
            for (int i = 0; i < N; ++i) {
                var _card = reader.line().split(" ");
                this.cards[i] = new Card(_card[0], Integer.parseInt(_card[1]));
            }

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
        var res = new Solution().solution(N, cards);
        sb.append(res);
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
