// #2941 - 크로아티아 알파벳
// 작성자: 김현학
// 작성일자: 2023-03-26
//
// 문제 - https://www.acmicpc.net/problem/2941
// 예전에는 운영체제에서 크로아티아 알파벳을 입력할 수가 없었다.
// 따라서, 다음과 같이 크로아티아 알파벳을 변경해서 입력했다.
// 크로아티아 알파벳	변경
//      č            c=
//      ć	         c-
//      dž          dz=
//      đ	         d-
//      lj	         lj
//      nj	         nj
//      š	         s=
//      ž	         z=
// 예를 들어, ljes=njak은 크로아티아 알파벳 6개(lj, e, š, nj, a, k)로 이루어져 있다.
// 단어가 주어졌을 때, 몇 개의 크로아티아 알파벳으로 이루어져 있는지 출력한다.
// dž는 무조건 하나의 알파벳으로 쓰이고, d와 ž가 분리된 것으로 보지 않는다. lj와 nj도 마찬가지이다.
// 위 목록에 없는 알파벳은 한 글자씩 센다.
//
// 입력
// 첫째 줄에 최대 100글자의 단어가 주어진다. 알파벳 소문자와 '-', '='로만 이루어져 있다.
//
// 단어는 크로아티아 알파벳으로 이루어져 있다. 문제 설명의 표에 나와있는 알파벳은 변경된 형태로 입력된다.
//
// 출력
// 입력으로 주어진 단어가 몇 개의 크로아티아 알파벳으로 이루어져 있는지 출력한다.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] chars = sc.next().toCharArray();
        sc.close();

        // =, -, j 가 나올 때까지 계속 +1 하면서 진행
        // =: 앞 char가 z가 아니면 그냥 -1, z면 그 앞 부분이 d인지 확인하고 -1 또는 -2
        // j: 앞 char가 n 또는 l이면 -1
        // -: 앞 char 관계없이 -1
        
        int count = 0;
        for (int i = 0; i < chars.length; ++i) {
            ++count;
            if (chars[i] == '-') --count;
            else if (chars[i] == 'j' && i - 1 >= 0 && (chars[i - 1] == 'n' || chars[i - 1] == 'l')) --count;
            else if (chars[i] == '=') {
                if (chars[i - 1] == 'z' && i - 2 >= 0 && chars[i - 2] == 'd') count -= 2;
                else --count;
            }
        }
        System.out.println(count);
    }
}
