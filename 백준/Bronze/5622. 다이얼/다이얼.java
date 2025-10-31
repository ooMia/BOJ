//
// #5622 - 다이얼
// 작성자: 김현학
// 작성일자: 2023-03-26
//
//    문제 - https://www.acmicpc.net/problem/5622
// 문제
// 상근이의 할머니는 아래 그림과 같이 오래된 다이얼 전화기를 사용한다.
// [그림] - https://bit.ly/3JNUDA0
// 전화를 걸고 싶은 번호가 있다면, 숫자를 하나를 누른 다음에 금속 핀이 있는 곳까지 시계방향으로 돌려야 한다.
// 숫자를 하나 누르면 다이얼이 처음 위치로 돌아가고, 다음 숫자를 누르려면 다이얼을 처음 위치에서 다시 돌려야 한다.
// 숫자 1을 걸려면 총 2초가 필요하다.
// 1보다 큰 수를 거는데 걸리는 시간은 이보다 더 걸리며, 한 칸 옆에 있는 숫자를 걸기 위해선 1초씩 더 걸린다.
// 상근이의 할머니는 전화 번호를 각 숫자에 해당하는 문자로 외운다.
// 즉, 어떤 단어를 걸 때, 각 알파벳에 해당하는 숫자를 걸면 된다. 예를 들어, UNUCIC는 868242와 같다.
// 할머니가 외운 단어가 주어졌을 때, 이 전화를 걸기 위해서 필요한 최소 시간을 구하는 프로그램을 작성하시오.
//
// 입력
// 첫째 줄에 알파벳 대문자로 이루어진 단어가 주어진다. 단어의 길이는 2보다 크거나 같고, 15보다 작거나 같다.
//
// 출력
// 첫째 줄에 다이얼을 걸기 위해서 필요한 최소 시간을 출력한다.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] chars = sc.next().toCharArray();
        sc.close();

        int[] timeMap = new int[1 + 'Z' - 'A'];
        int[] relation = {3, 3, 3, 3, 3, 4, 3, 4};
        int alpha = 'A';

        for (int n = 0, i = 0; i < relation.length; ) {
            timeMap[alpha++ - 'A'] = 3 + i;
            if (++n == relation[i]) {
                n = 0;
                ++i;
            }
        }
        int timeSum = 0;
        for (char c : chars) timeSum += timeMap[c - 'A'];
        System.out.println(timeSum);
    }
}
