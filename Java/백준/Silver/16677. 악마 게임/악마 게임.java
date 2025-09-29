import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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
    String solution(String initialWord, Dict[] dicts) {
        String answer = "No Jam"; // default

        double maxEfficiency = 0;
        for (Dict dict : dicts) {
            if (contains(dict.word, initialWord)) {
                var eff = efficiency(initialWord, dict.word, dict.score);
                if (eff <= maxEfficiency) continue;
                maxEfficiency = eff;
                answer = dict.word;
            }
        }

        return answer;
    }

    private boolean contains(String source, String target) {
        int iSource = 0, iTarget = 0;
        while (iSource < source.length() && iTarget < target.length()) {
            if (source.charAt(iSource) == target.charAt(iTarget)) {
                iTarget++;
            }
            iSource++;
        }
        return iTarget == target.length();
    }

    private double efficiency(String from, String to, int score) {
        int nCharAdded = to.length() - from.length();
        return (double) score / nCharAdded;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final String initialWord;
    final int nDict;
    final Dict[] dicts;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.initialWord = br.readLine();
            this.nDict = Integer.parseInt(br.readLine());
            this.dicts = new Dict[nDict];
            for (int i = 0; i < nDict; ++i) {
                String[] parts = br.readLine().split(" ");
                dicts[i] = new Dict(parts[0], Integer.parseInt(parts[1]));
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
        var sol = new Solution();
        var res = sol.solution(initialWord, dicts);
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

class Dict {
    int score;
    String word;

    Dict(String word, int score) {
        this.word = word;
        this.score = score;
    }
}