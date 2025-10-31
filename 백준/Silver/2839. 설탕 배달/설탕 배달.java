import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Integer> ls = new ArrayList<>();
        while (sc.hasNext()) ls.add(sc.nextInt());
        sc.close();

        int n = ls.get(0);
        int nBags = -1;
        for (int nBag_5 = n / 5; nBag_5 >= 0; --nBag_5) {
            if ((n - nBag_5 * 5) % 3 == 0) {
                nBags = (n - nBag_5 * 5) / 3 + nBag_5;
                break;
            }
        }
        System.out.println(nBags);
    }
}
