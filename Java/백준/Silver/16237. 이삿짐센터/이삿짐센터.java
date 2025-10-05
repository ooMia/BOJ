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
    final int BASKET_CAPACITY = 5;
    final int[] counts;

    Solution(int[] counts) {
        this.counts = counts;
    }

    public int solution() {
        int iLast = weightToIdx(BASKET_CAPACITY);
        int res = counts[iLast];
        counts[iLast] = 0;

        for (int i = iLast - 1; i >= 0; --i) {
            if (counts[i] < 1) continue;

            int iWeight = idxToWeight(i);
            {
                // 이론상 최대의 케이스
                int nPerBasket = BASKET_CAPACITY / iWeight;
                int nFullBasket = counts[i] / nPerBasket;
                res += nFullBasket;
                counts[i] %= nPerBasket;

                // 최대 케이스 바구니의 나머지 빈 공간 채우기
                int capacity = BASKET_CAPACITY % iWeight;
                for (int j = capacity - 1; j >= 0 && nFullBasket > 0; --j) {
                    if (counts[j] < 1) continue;
                    // 현재 아이템으로 바구니를 채울 수 있을만큼 채움
                    int jWeight = idxToWeight(j);
                    int perBasketIdealN = capacity / jWeight;

                    int actualN = Math.min(counts[j], perBasketIdealN * nFullBasket);
                    counts[j] -= actualN;
                    nFullBasket -= actualN / perBasketIdealN;
                }
            }
            {
                // 최대로 담은 이후 남은 잉여를 한 바구니로 처리
                if (counts[i] == 0) continue;
                res += 1;
                int capacity = BASKET_CAPACITY - iWeight * counts[i];
                for (int j = i - 1; j >= 0 && capacity > 0; --j) {
                    if (j < 0 || counts[j] < 1) continue;
                    // 현재 아이템으로 바구니를 채울 수 있을만큼 채움
                    int jWeight = idxToWeight(j);
                    int idealN = capacity / jWeight;

                    int actualN = Math.min(counts[j], idealN);
                    counts[j] -= actualN;
                    capacity -= actualN * jWeight;
                }
            }
        }
        return res;
    }

    private int idxToWeight(int idx) {
        return idx + 1;
    }

    private int weightToIdx(int weight) {
        return weight - 1;
    }
}

class Runner {
    final BufferedWriter bw;

    final int[] counts;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        var reader = new Reader(br);
        try {
            counts = reader.readInts();

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
        var sol = new Solution(counts);
        var res = sol.solution();
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