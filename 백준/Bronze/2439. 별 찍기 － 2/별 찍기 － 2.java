//
// #2439 - 별 찍기 2
// 작성자: 김현학
// 작성일자: 2023-03-26
//
//    문제 - https://www.acmicpc.net/problem/2439
//    첫째 줄에는 별 1개, 둘째 줄에는 별 2개, N번째 줄에는 별 N개를 찍는 문제
//
//    하지만, 오른쪽을 기준으로 정렬한 별(예제 참고)을 출력하시오.
//
//    입력
//    첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.
//
//    출력
//    첫째 줄부터 N번째 줄까지 차례대로 별을 출력한다.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<Integer>();
        while (sc.hasNext())
            for (int n : Arrays.stream(sc.nextLine().split(System.lineSeparator().toString() + "| ")).mapToInt(Integer::parseInt).toArray()
            )
                numbers.add(n);
        sc.close();

        int N = numbers.get(0);
        for (int line = 1; line <= N; line++)
        {
            for (int blank = N-line; blank > 0; blank--) System.out.printf(" ");
            for (int star = 0; star < line; star++) System.out.printf("*");
            System.out.printf("\n");
        }
    }
}
