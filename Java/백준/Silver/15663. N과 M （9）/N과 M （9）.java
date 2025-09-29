import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            int T = 1;
            for (int i = 0; i < T; ++i) {
                IRunner runner = new Runner(br, bw);
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

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    int N, M; // N: number of elements, M: length of sub-sequences
    int[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var nm = reader.readInts();
            N = nm[0];
            M = nm[1];
            numbers = reader.readInts();
            Arrays.sort(numbers);
            sb.ensureCapacity(M * N * N);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() throws IOException {
        Set<String> history = new HashSet<>();
        Itertools.permutations(numbers, M).forEach(chosen -> {
            String key = Arrays.toString(chosen);
            if (!history.contains(key)) {
                history.add(key);
                for (int i = 0; i < M; i++) {
                    sb.append(chosen[i]).append(' ');
                }
                sb.append('\n');
            }
        });
    }
}

class Reader implements IReader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void skipLine() throws IOException {
        br.readLine();
    }

    @Override
    public Stream<String> lines() throws IOException {
        return br.lines();
    }

    @Override
    public String line() throws IOException {
        return br.readLine();
    }

    @Override
    public int[] readInts() throws IOException {
        String[] tokens = br.readLine().split(" ");
        int[] ints = new int[tokens.length];
        for (int i = 0; i < tokens.length; ++i) {
            ints[i] = Integer.parseInt(tokens[i]);
        }
        return ints;
    }

    @Override
    public List<Integer> readIntegers() throws IOException {
        String[] tokens = br.readLine().split(" ");
        var res = new ArrayList<Integer>(tokens.length);
        for (String token : tokens) {
            res.add(Integer.valueOf(token));
        }
        return res;
    }
}

class Itertools {
    public static List<int[]> permutations(int[] numbers, int r) {
        List<int[]> resultList = new ArrayList<>();
        int n = numbers.length;

        if (r > n || r < 0) {
            return resultList;
        }

        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        int[] cycles = new int[r];
        for (int i = 0; i < r; i++) {
            cycles[i] = n - i;
        }

        // 첫 번째 순열 추가
        int[] firstPermutation = new int[r];
        for (int i = 0; i < r; i++) {
            firstPermutation[i] = numbers[indices[i]];
        }
        resultList.add(firstPermutation);

        while (true) {
            boolean broken = false;
            for (int i = r - 1; i >= 0; i--) {
                cycles[i]--;
                if (cycles[i] == 0) {
                    // 인덱스 배열을 오른쪽으로 한 칸 회전
                    int temp = indices[i];
                    for (int j = i; j < n - 1; j++) {
                        indices[j] = indices[j + 1];
                    }
                    indices[n - 1] = temp;
                    cycles[i] = n - i;
                } else {
                    int j = cycles[i];
                    int swapIndex = n - j;

                    // 인덱스 교환
                    int temp = indices[i];
                    indices[i] = indices[swapIndex];
                    indices[swapIndex] = temp;

                    // 새 순열 추가
                    int[] newPermutation = new int[r];
                    for (int k = 0; k < r; k++) {
                        newPermutation[k] = numbers[indices[k]];
                    }
                    resultList.add(newPermutation);
                    broken = true;
                    break; // 안쪽 for 루프 탈출
                }
            }

            if (!broken) {
                // 안쪽 for 루프가 break 없이 완료되면 모든 순열을 찾은 것
                return resultList;
            }
        }
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}

interface IReader {
    void skipLine() throws IOException;

    Stream<String> lines() throws IOException;

    String line() throws IOException;

    List<Integer> readIntegers() throws IOException;

    int[] readInts() throws IOException;
}
