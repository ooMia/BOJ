import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            Runner runner = new Runner(br, bw);
            runner.run();
            runner.flush();
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {

    Map<Integer, Integer> fromOldToNewMap;

    Solution(int[] newMapping) {
        fromOldToNewMap = new HashMap<>();
        for (int i = 0; i < newMapping.length; i++) {
            fromOldToNewMap.put(newMapping[i], i + 1);
        }
    }

    public String solution(String result) {
        StringBuilder sb = new StringBuilder();
        var keyPressed = new OldPhone.KeyPress(0,0);
        for (var c : result.toCharArray()) {
            var keyWillPress = OldPhone.CHAR_TO_KEYPRESS_MAP.get(c);
            if (keyPressed.key == keyWillPress.key) {
                sb.append('#');
            }

            keyPressed = keyWillPress;
            int oldKey = keyPressed.key;
            int count = keyPressed.count;
            int newKey = fromOldToNewMap.get(oldKey);
            for (int i = 0; i < count; i++) {
                sb.append(newKey);
            }
        }
        return sb.toString();

    }

    static class OldPhone {
        // ex. 2를 세 번 누르면 'c'가 입력된다.
        static final char[][] KEYS = new char[][]{
                {},
                {},
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };

        static final Map<Character, KeyPress> CHAR_TO_KEYPRESS_MAP = new HashMap<>();
        static {
            for (int key = 2; key <= 9; key++) {
                for (int count = 1; count <= KEYS[key].length; count++) {
                    char ch = KEYS[key][count - 1];
                    CHAR_TO_KEYPRESS_MAP.put(ch, new KeyPress(key, count));
                }
            }
        }

        public static char getChar(int key, int count) {
            return KEYS[key][count - 1];
        }

        static class KeyPress {
            final int key, count;

            KeyPress(int key, int count) {
                this.key = key;
                this.count = count;
            }
        }

    }
}

class Runner {
    final BufferedWriter bw;

    final int[] newMapping;
    final String result;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;
        var reader = new Reader(br);
        try {
            newMapping = reader.readInts();
            result = br.readLine();
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
        var sol = new Solution(newMapping);
        var res = sol.solution(result);
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

    public int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public int[] readInts() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, " ");
        int cnt = st.countTokens();
        int[] ints = new int[cnt];
        for (int i = 0; i < cnt; ++i) {
            ints[i] = Integer.parseInt(st.nextToken());
        }
        return ints;
    }
}