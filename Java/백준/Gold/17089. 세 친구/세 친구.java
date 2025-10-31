import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.BitSet;
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
    final int maxId;
    final Node[] nodes;

    public Solution(int maxId, int[][] relations) {
        this.maxId = maxId;
        this.nodes = new Node[maxId + 1];
        for (int i = 1; i <= maxId; ++i) {
            nodes[i] = new Node(i);
        }
        initNodes(relations); // 주어진 관계 정보에 따라 노드 초기화
    }

    private void initNodes(int[][] relations) {
        for (int[] relation : relations) {
            int id_0 = relation[0], id_1 = relation[1];
            nodes[id_0].addRelation(id_1);
            nodes[id_1].addRelation(id_0);
        }
    }

    public int solution() {
        int min = Integer.MAX_VALUE;
        // 첫 번쨰 케이스는 대략 이런 느낌이다.
        // 1   1
        // 2 - 3
        // 4   4 - 5

        // 셋이 모두 친구라는 것은 그래프에 순환이 존재한다는 뜻이다
        // 순환을 체크할 때 방문 배열을 활용한다

        // 시작 노드를 A로 고정했을 때,
        // A와 연결된 노드와 그 노드로부터 한 노드를 거쳐
        // 다시 A로 돌아올 수 있다면
        // 그 조합에 대해 친구 수를 계산할 가치가 있다.

        // 만약 어떤 세 노드가 서로 순환되는 관계라고 가정했을 때,
        // (각 노드에서 나가는 연결의 수의 합) - 2 * 3
        // 으로 친구의 수를 구할 수 있다.

        // 조합에 대한 탐색을 시도해보았는지 임시로 기록하는 배열 대신
        // 만약 내가 현재 2번 노드를 시작 노드로 삼아 순회하고 있다면
        // 자신보다 id가 작은 노드들, 가령 1번 노드로 시작하는 경우는
        // 이미 고려했다고 판단하고 순회에서 제외한다.
        // 이 규칙에 따르면 가능한 시작노드는 1부터 maxId - 2까지 가능하다.

        for (int idStart = 1; idStart <= maxId - 2; ++idStart) {
            // 초기 노드를 방문한다.
            var startNode = nodes[idStart];

            // 중간 노드의 연결성을 확인한다.
            // 인덱스 구간은 [idStart + 1, maxId - 1] 
            for (int idMiddle = startNode.nextRelationId(idStart + 1);
                 idMiddle > 0;
                 idMiddle = startNode.nextRelationId(idMiddle + 1)
            ) {
                var middleNode = nodes[idMiddle];

                // 마지막 노드도 동일한 방식으로 탐색
                // [idMiddle + 1, maxId]
                for (int idLast = middleNode.nextRelationId(idMiddle + 1);
                     idLast > 0;
                     idLast = middleNode.nextRelationId(idLast + 1)
                ) {
                    var lastNode = nodes[idLast];

                    if (!lastNode.isConnectedTo(idStart)) continue;

                    // 만약 초기 노드와 연결되어 있다면
                    // 세 노드의 cardinality에 대한 계산식을 수행하고
                    // Math.min으로 갱신
                    min = Math.min(min, calcFriendsNumber(startNode, middleNode, lastNode));
                }

            }

        }
        if (min == Integer.MAX_VALUE) return -1;
        return min;
    }

    private int calcFriendsNumber(Solution.Node startNode, Solution.Node middleNode, Solution.Node lastNode) {
        return startNode.nTotalRelations() + middleNode.nTotalRelations() + lastNode.nTotalRelations() - 2 * 3;
    }

    class Node {
        final int id;
        final BitSet relation = new BitSet(maxId + 1);

        public Node(int id) {
            this.id = id;
        }

        void addRelation(int id) {
            relation.set(id);
        }

        int nTotalRelations() {
            return relation.cardinality();
        }

        int nextRelationId(int fromIndex) {
            return relation.nextSetBit(fromIndex);
        }

        boolean isConnectedTo(int id) {
            return relation.get(id);
        }
    }
}

class Runner {
    final BufferedWriter bw;

    final int N;
    final int[][] relations;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        var reader = new Reader(br);
        try {
            var line = reader.readInts();
            N = line[0];
            var m = line[1];
            relations = new int[m][];
            for (int i = 0; i < relations.length; ++i) {
                relations[i] = reader.readInts();
            }
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
        var sol = new Solution(N,relations);
        var res = sol.solution( );
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