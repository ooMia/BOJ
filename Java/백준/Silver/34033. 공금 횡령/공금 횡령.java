import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    final private double upperBoundRate = 1.05;

    int solution(Map<String, Integer> itemPrices, List<Transaction> transactions) {
        int exceedCount = 0;
        for (var entry : transactions) {
            int originalPrice = itemPrices.get(entry.itemName);
            double limit = originalPrice * upperBoundRate;

            if (entry.tradedPrice > limit) ++exceedCount;
        }
        return exceedCount;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N, M; // N: 물품의 개수, M: 거래 내역의 개수
    final Map<String, Integer> itemPrices;
    final List<Transaction> transactions;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var line = reader.readInts();
            N = line[0];
            M = line[1];

            itemPrices = new java.util.HashMap<>(N);
            for (int n = 0; n < N; ++n) {
                var item = br.readLine().split(" ");
                itemPrices.put(item[0], Integer.parseInt(item[1]));
            }

            transactions = new java.util.ArrayList<>(M);
            for (int m = 0; m < M; ++m) {
                var transaction = br.readLine().split(" ");
                transactions.add(new Transaction(transaction[0], Integer.parseInt(transaction[1])));
            }

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
        var res = sol.solution(itemPrices, transactions);
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

class Transaction {
    String itemName;
    int tradedPrice;

    Transaction(String itemName, int tradedPrice) {
        this.itemName = itemName;
        this.tradedPrice = tradedPrice;
    }
}