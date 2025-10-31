import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Problem p = new Problem();
        p.solve();
        p.printResult();
    }

    static class Problem {
        private final ArrayList<Integer> ls = new ArrayList<>();
        private int average, median, mode, gap;

        Problem() {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) ls.add(sc.nextInt());
            sc.close();
        }

        public void solve() {
            int len = ls.remove(0);
            if (len == 1) {
                average = median = mode = ls.get(0);
                gap = 0;
                return;
            }

            ls.sort(Integer::compare);
            int min = ls.get(0), max = ls.get(len - 1);
            gap = max - min;
            median = ls.get(len / 2);

            // 최빈값
            // 1. for(n:ls) ++count[ls]
            // 2. if count[ls] >  maxMode: maxMode = count[ls]
            // 3. if count[ls] == maxMode: modeHistory[2] = n
            // 4. modeHistory.sort

            ArrayList<Integer> modeHistory = new ArrayList<>();
            double sum = 0;
            int[] count = new int[gap + 1];
            int maxCount = 0;

            for (int n : ls) {
                sum += n;
                int idx = n - min;
                ++count[idx];

                if (maxCount < count[idx]) {
                    maxCount = count[idx];
                    modeHistory.clear();
                    modeHistory.add(n);
                } else if (maxCount == count[idx]) {
                    if (modeHistory.size() >= 3) modeHistory.set(2, n);
                    else modeHistory.add(n);
                }
                modeHistory.sort(Integer::compare);
            }

            average = (int) Math.round(sum / len);
            mode = modeHistory.get(modeHistory.size() >= 2 ? 1 : 0);
        }

        public void printResult() {
            System.out.printf("%d %d %d %d\n",
                    average, median, mode, gap);
        }
    }
}