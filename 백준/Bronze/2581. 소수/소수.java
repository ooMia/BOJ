import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Prime {
        static boolean[] sieve;
        final int limit;

        Prime(int limit) {
            this.limit = limit;
            sieve = new boolean[limit + 1];
            Arrays.fill(sieve, true);
            sieve[0] = sieve[1] = false;
            if (limit >= 2) sieve[2] = true;
            for (int p = 3; p <= limit; p += 2)
                if (isPrime(p)) for (int i = p * 2; i <= limit; i += p)
                    sieve[i] = false;
        }

        boolean isPrime(int n) {
            return (n % 2 != 0 || n <= 2) && sieve[n];
        }

        List<Integer> getPrimesInRange(int from, int to) {
            ArrayList<Integer> res = new ArrayList<>();
            if (from <= 2 && 2 <= to) res.add(2);
            for (int n = from + (from % 2 == 0 ? 1 : 0); n <= to; n += 2)
                if (isPrime(n)) res.add(n);
            return res;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        while (sc.hasNext()) numbers.add(sc.nextInt());
        sc.close();

        int N = numbers.get(0), M = numbers.get(1);
        Prime a = new Prime(M);

        List<Integer> ls = a.getPrimesInRange(N, M);
        if (ls.size() == 0) System.out.println(-1);
        else System.out.printf("%d %d\n",
                ls.stream().reduce(Integer::sum).get().intValue(),
                ls.stream().min(Integer::compareTo).get().intValue());
    }
}
