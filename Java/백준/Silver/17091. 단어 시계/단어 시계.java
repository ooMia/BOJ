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

    static String oClock = "o' clock";
    static String m_1_30 = "past";
    static String m_31_59 = "to";

    public String solution(int h, int m) {
        if (m == 0)
            return String.format("%s o' clock", hourToString(h));

        var n_hour = m <= 30 ? h : (h + 1 == 13 ? 1 : h + 1);
        var hour = hourToString(n_hour);
        var middle = m <= 30 ? m_1_30 : m_31_59;
        var n_minute = m <= 30 ? m : 60 - m;
        var minute = minuteToString(n_minute);
        return String.format("%s %s %s", minute, middle, hour);
    }

    String hourToString(int h) {
        return StringNumber.of(h);
    }

    String minuteToString(int m) {
        // 내부 entryPoint
        return switch (m) {
            // 0, 15, 30, 45 케이스
            // 그 다음 [11, 19] 케이스
            // 나머지는 따로 처리
            case 0:
                yield "o' clock";
            case 30:
                yield "half";
            case 15: // 45는 사전에 변환시킨 상태에서 호출해야 함
                yield "quarter";
            default:
                yield StringNumber.of(m) + (m == 1 ? " minute": " minutes");
        };
    }
}

class Runner {
    final BufferedWriter bw;

    final int h, m;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        // var reader = new Reader(br);
        try {
            h = Integer.parseInt(br.readLine());
            m = Integer.parseInt(br.readLine());

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
        var res = sol.solution(h, m);
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

enum StringNumber {
    zero, one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen,
    sixteen, seventeen, eighteen, nineteen, twenty, twenty_one, twenty_two, twenty_three, twenty_four, twenty_five,
    twenty_six, twenty_seven, twenty_eight, twenty_nine, thirty;

    static String of(int n) {
        for (var e : StringNumber.values())
            if (e.ordinal() == n)
                return e.name().replace('_', ' ');
        return null;
    }
}
