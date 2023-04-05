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

/*
* https://www.acmicpc.net/board/view/40713
*
* 이 문제는 사실상 4개의 문제를 푸는 것과 같고,
* 어디서든 실수를 할 수 있기 때문에 굉장히 많은 디버깅을 필요로 합니다.
* 예제만 돌려보고 끝내지 말고, 질문 게시판의 다른 질문들도 모두 읽어보고,
* 스스로 다양한 코너 케이스를 수십, 수백 개 만들어서 디버깅하는 연습을 해봐야 합니다.
*
* 1. "입력되는 정수의 절댓값은 4,000을 넘지 않는다." 그러면 입력되는 정수는 최대 몇 가지 종류가 있을까요? 양수 쪽에 4000개, 음수 쪽에 4000개 해서 8000개일까요? 여기에 하나 더, 0이 있습니다. 그래서 개수를 세려고 하면 최소 8001칸의 배열이 필요합니다.
* 2. 마찬가지로 -4000, 0, 4000 등의 값에 대한 처리가 항상 올바르게 이루어지는지 두 번, 세 번, 아니 열 번은 꼼꼼히 점검해 보세요. -4000, 0, 4000 등의 수에서 서로 겹치는 인덱스가 사용되고 있지는 않은지도 확인하고, -4000부터 4000까지의 수 8001개가 항상 검사 범위에 들어가고 있는지도 확인해 봅시다. 전체 질문의 약 1/3은 이 부분에서 실수가 있었다고 해도 과언이 아닙니다.
* 3. float는 너무나, 너무나 부정확하기 때문에 이 문제에 사용할 수 없습니다. float의 정확도는 겨우 6자리 정도인데, 이 문제에서는 무려 20억까지 가는 수를 저장해야 합니다. 이런 수를 float가 정확하게 표현할 수는 없습니다. 이 문제 뿐만 아니라, 거의 모든 경우에 실수형을 사용해야 한다면 double이 옳습니다. double로도 모자라서 long double을 사용해야 하는 경우가 있을 수는 있어도, float를 쓸 일은 없다고 보시면 됩니다. 이전에는 float로 나눈 코드가 통과되었지만, 이제 데이터가 추가되어 더 이상 통과되지 못합니다. (코드 약 600개가 이 데이터에 의해 틀리게 됐습니다.)
* 4. 코드 어디에서도 O(N^2)에 돌아가는 부분이 있어서는 안 됩니다. 쉽게 예를 들면, 배열 전체 혹은 부분을 이중 루프로 순회하는 코드가 있으면 그 부분은 O(N^2)일 가능성이 매우 높습니다. 마찬가지로 정렬도 O(NlogN)의 정렬을 사용해야 합니다. 이게 무슨 뜻인지 모르겠고 시간 초과가 난다면,  https://www.acmicpc.net/problem/2751 와  https://www.acmicpc.net/board/view/31887 를 먼저 보시기 바랍니다.
* 5. 입력이 느리면 시간 초과를 받을 수도 있습니다. https://www.acmicpc.net/proble...
* 6. 산술평균은 반올림한 결과를 출력해야지, 무조건 버리거나 올리면 안 됩니다.
* 7. 최빈값이 여러 개 있을 때는 두 번째로 작은 수를 출력해야 하지만, 최빈값이 하나라면 그것을 그대로 출력해야 합니다.
*
* */