import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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

    interface Judge {
        void pullLever(); // 레버 당기기 (레버 상태 토글)

        void waitSecond(); // 1초 기다리기 (카트를 다음 구역으로 이동시킴)
    }

    interface Cart {
        void move(); // 다음 구역으로 이동 ('A', 'B', 'C')

        int crash(); // C 구역이라는 가정 하에 주어진 레버 상태를 토대로 충돌한 마네킹 수 반환
    }

    // 심판자, 레버, 트랙
    // 레버는 당기지 않은 상태, 선로는 2번 선로, 각 선로에는 각각 1, 5개의 마네킹이 있는 상태로 시작

    boolean lever = false, leverPulledOnAreaB = false;
    char cartArea = 'A';

    Cart cart;
    Judge judge;

    Solution() {
        this.cart = new Cart() {
            @Override
            public void move() {
                if (cartArea == 'C')
                    return; // C 구역에서는 이동하지 않음
                cartArea++;
            }

            @Override
            public int crash() {
                if (cartArea == 'C') {
                    if (leverPulledOnAreaB)
                        return 6; // B 구역에서 레버가 당겨진 경우
                    return lever ? 1 : 5;
                }
                return 0; // 아직 C 구역에 도달하지 않음
            }
        };

        this.judge = new Judge() {
            @Override
            public void pullLever() {
                switch (cartArea) {
                    case 'B':
                        leverPulledOnAreaB = true;
                    case 'A':
                        lever = !lever;
                    default:
                        break;
                }
            }

            @Override
            public void waitSecond() {
                if (cartArea == 'C')
                    return; // C 구역에서는 이동하지 않음
                cartArea++;
            }
        };

    }

    int solution(int nCommands, String commands) {
        for (int i = 0; i < nCommands; ++i) {
            char command = commands.charAt(i);
            switch (command) {
                case 'P':
                    judge.pullLever();
                    break;
                case 'W':
                    judge.waitSecond();
                    break;
            }
        }
        return cart.crash();
    }

}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int nCommands;
    final String commands;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            nCommands = Integer.parseInt(br.readLine());
            commands = br.readLine();
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
        var res = sol.solution(nCommands, commands);
        bw.write(String.valueOf(res));
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }
}