import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        try {
            IRunner runner = new Runner(br, bw);
            runner.run();
            runner.flush();

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

    final int nLimit = 9;
    final Stack<History> history = new Stack<>();

    int[][] grid = new int[nLimit][nLimit];
    PriorityQueue<Data> tasks = new PriorityQueue<>();

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        final int[][] cols = new int[nLimit][nLimit];
        final int[][][] blocks = new int[nLimit / 3][nLimit / 3][nLimit];
        try {
            for (int y = 0; y < nLimit; ++y) {
                grid[y] = reader.readInts();
                for (int x = 0; x < nLimit; ++x) {
                    int value = grid[y][x];
                    cols[x][y] = value;
                    blocks[y / 3][x / 3][(y % 3) * 3 + (x % 3)] = value;
                }
            }

            for (int y = 0; y < nLimit; ++y) {
                for (int x = 0; x < nLimit; ++x) {
                    if (grid[y][x] == 0) {
                        var data = new Data(y, x, grid[y], cols[x], blocks[y / 3][x / 3]);
                        tasks.offer(data);
                    }
                }
            }
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
    public void run() {
        while (!tasks.isEmpty()) {
            Data task = tasks.poll();
            int y = task.y, x = task.x;

            int possibilities = task.possibles.size();
            if (possibilities < 1) {
                var s = history.pop();
                this.grid = s.grid;
                this.tasks = s.tasks;
                continue;
            }

            if (task.possibles.size() > 1) {
                history.push(new History(grid, tasks, task));
            }

            int value = task.possibles.iterator().next();
            grid[y][x] = value;

            for (Data affected : task.update(value, tasks)) {
                tasks.remove(affected);
                tasks.add(affected);
            }
        }

        for (int y = 0; y < nLimit; ++y) {
            for (int x = 0; x < nLimit; ++x) {
                sb.append(grid[y][x]).append(' ');
            }
            sb.append('\n');
        }
    }
}

interface IRunner {
    void run();

    void flush();
}

class History {
    final int[][] grid = new int[9][9];
    final PriorityQueue<Data> tasks = new PriorityQueue<>();

    History(final int[][] _grid, final PriorityQueue<Data> _tasks, final Data _chosen) {
        for (int y = 0; y < 9; ++y) {
            System.arraycopy(_grid[y], 0, grid[y], 0, 9);
        }
        Data data = _chosen.clone();
        data.possibles.remove(data.possibles.iterator().next());
        tasks.add(data);
        for (Data task : _tasks) {
            tasks.add(task.clone());
        }
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
        return Arrays.stream(readInts()).boxed().collect(Collectors.toList());
    }

}

interface IReader {
    void skipLine() throws IOException;

    List<Integer> readIntegers() throws IOException;

    int[] readInts() throws IOException;
}

class Data implements IData, Comparable<Data> {
    final int y, x;
    final Set<Integer> possibles = new HashSet<>();

    private Data(int y, int x) {
        this.y = y;
        this.x = x;
    }

    Data(int y, int x, int[] rows, int[] cols, int[] block) {
        this(y, x);

        int nLimit = rows.length;
        boolean[] used = new boolean[nLimit + 1];
        for (int i = 0; i < nLimit; ++i) {
            used[rows[i]] = true;
            used[cols[i]] = true;
            used[block[i]] = true;
        }
        for (int i = 1; i < used.length; ++i) {
            if (!used[i]) {
                possibles.add(i);
            }
        }
    }

    @Override
    public int priority() {
        return possibles.size();
    }

    @Override
    public int compareTo(Data o) {
        return Integer.compare(this.priority(), o.priority());
    }

    public boolean isRelated(Data other) {
        // Check if the other Data object has the same row or column
        if (this.y == other.y || this.x == other.x) {
            return true;
        }
        // Check if the other Data object is in the same 3x3 block
        return (this.y / 3 == other.y / 3 && this.x / 3 == other.x / 3);
    }

    public List<Data> update(int value, Queue<Data> all) {
        List<Data> affected = relatives(all);
        for (Data data : affected) {
            data.possibles.remove(value);
        }
        return affected;
    }

    public List<Data> relatives(Queue<Data> all) {
        List<Data> related = new ArrayList<>();
        for (Data data : all) {
            if (this.isRelated(data)) {
                related.add(data);
            }
        }
        return related;
    }

    public Data clone() {
        Data copy = new Data(this.y, this.x);
        copy.possibles.addAll(this.possibles);
        return copy;
    }
}

interface IData {
    int priority();

    boolean isRelated(Data other);

    List<Data> update(int value, Queue<Data> all);

    List<Data> relatives(Queue<Data> all);

    Data clone();
}
