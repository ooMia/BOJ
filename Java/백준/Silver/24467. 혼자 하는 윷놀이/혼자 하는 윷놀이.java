import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

    public String solution(String[] dices) {
        var node = Node.getStartNode();
        int turn = 1;
        int iDice = 0;

        while (turn <= 10 && iDice < dices.length) {
            int dice = toDice(dices[iDice++]);
            node = node.move(dice);

            if (node.isDestination()) return "WIN";
            if (dice < 4) turn++;
        }
        return "LOSE";
    }

    int toDice(String s) {
        if ("1111".equals(s)) return 5;
        return (int) s.chars().filter(c -> c == '0').count();
    }

    static class Node {

        // 20 14 13     12 11 10
        // 21 25           15  4
        // 22    26     16     3
        //         40 30
        // 23    31     41     2
        // 24 32           42  1  0
        // 50 51 52     53 54 55 56

        // 보드를 이런 식으로 설계한다고 했을 때,
        // 0. 0은 시작점이고, 46은 도착점이다.
        // 1. 일의 자리 수가 0이면 꼭지점을 의미
        // 2. 각 꼭지점에 대해 경로의 분기가 발생한다.
        // 3. 현재 주어진 dice를 모두 소모했을 때, 정확히 꼭지점에 위치하는 경우
        //    선택 가능한 경로 중 가장 id가 높은 쪽으로 진행한다.
        // 4. 그 외에는 항상 id가 낮은 쪽으로 진행한다.

        private static final Node[] nodes = new Node[57];
        static {
            for (int id = 0; id <= 56; ++id) {
                nodes[id] = new Node(id);
            }

            nodes[0].connect(1).connect(2).connect(3).connect(4).connect(10);
            nodes[10].connect(11).connect(12).connect(13).connect(14).connect(20);
            nodes[20].connect(21).connect(22).connect(23).connect(24).connect(50);
            nodes[50].connect(51).connect(52).connect(53).connect(54).connect(55).connect(56);

            nodes[10].connect(15).connect(16).connect(30).connect(31).connect(32).connect(50);
            nodes[20].connect(25).connect(26).connect(40).connect(41).connect(42);
            nodes[30].connect(41).connect(42).connect(55);

            nodes[46].connect(46);
        }

        static Node getStartNode() {
            return nodes[0];
        }

        final int id;
        private final TreeSet<Node> edges = new TreeSet<>(
            (a, b) -> Integer.compare(a.id, b.id)
        ); // 오름차순 정렬

        public Node(int id) {
            this.id = id;
        }

        /**
         * @param id 연결할 노드의 id
         * @return id번 노드 객체
         */
        private Node connect(int id) {
            var node = nodes[id];
            edges.add(node);
            return node;
        }

        boolean isCorner() {
            return id % 10 == 0;
        }

        boolean isDestination() {
            return id == 56;
        }

        private Node _move(int dice) {
            if (dice == 0 || edges.isEmpty()) return this;
            return edges.first()._move(dice - 1);
        }

        public Node move(int dice) {
            // 가장 id가 높은 쪽으로 이동한 이후
            // 나머지는 일반적인 이동
            if (isCorner()) {
                return edges.last()._move(dice - 1);
            }
            return _move(dice);
        }

    }
}

class Runner {
    final BufferedWriter bw;

    final String[] dices;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        dices = br.lines().toArray(String[]::new);
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
        var res = sol.solution(dices);
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
}