/*
 * #2588 - 곱셈
 * 작성자: 김현학
 * 작성일자: 2023-03-26
 *
 * 문제 설명
 * 그림: https://bit.ly/40DYZAe
 * (세 자리 수) × (세 자리 수)는 다음과 같은 과정을 통하여 이루어진다.
 * (1)과 (2)위치에 들어갈 세 자리 자연수가 주어질 때
 * (3), (4), (5), (6)위치에 들어갈 값을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 (1)의 위치에 들어갈 세 자리 자연수가,
 * 둘째 줄에 (2)의 위치에 들어갈 세자리 자연수가 주어진다.
 *
 * 출력
 * 첫째 줄부터 넷째 줄까지 차례대로
 * (3), (4), (5), (6)에 들어갈 값을 출력한다.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<Integer>();
        while (sc.hasNext())
            for (int n : Arrays.stream(sc.nextLine().split(System.lineSeparator().toString())).mapToInt(Integer::parseInt).toArray()
            )
                numbers.add(n);
        sc.close();

        int A = numbers.get(0), B = numbers.get(1);
        System.out.printf("%d\n%d\n%d\n%d\n",
                A * (B % 10),
                A * ((B % 100) / 10),
                A * (B / 100),
                A * B);
    }
}