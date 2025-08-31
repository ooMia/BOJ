import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

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

    public String solution(Student[] students) {
        Arrays.sort(students);
        StringBuilder sb = new StringBuilder();
        sb.append(students[students.length - 1].name).append('\n');
        sb.append(students[0].name);
        return sb.toString();
    }
}

class Student implements Comparable<Student> {
    final String name;
    final Calendar birthDate;

    Student(String name, int day, int month, int year) {
        this.name = name;
        this.birthDate = Calendar.getInstance();
        this.birthDate.set(year, month - 1, day);
    }

    @Override
    public int compareTo(Student other) {
        return this.birthDate.compareTo(other.birthDate);
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final Student[] students;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var N = reader.readInts()[0];
            students = new Student[N];
            for (int i = 0; i < N; i++) {
                var input = reader.line().split(" ");
                var name = input[0];
                var day = Integer.parseInt(input[1]);
                var month = Integer.parseInt(input[2]);
                var year = Integer.parseInt(input[3]);
                students[i] = new Student(name, day, month, year);
            }

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution();
        var res = sol.solution(students);
        sb.append(res).append('\n');
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    public void skipLine() throws IOException {
        br.readLine();
    }

    public Stream<String> lines() throws IOException {
        return br.lines();
    }

    public String line() throws IOException {
        return br.readLine();
    }

    public int[] readInts() throws IOException {
        String[] tokens = br.readLine().split(" ");
        int[] ints = new int[tokens.length];
        for (int i = 0; i < tokens.length; ++i) {
            ints[i] = Integer.parseInt(tokens[i]);
        }
        return ints;
    }

    public List<Integer> readIntegers() throws IOException {
        String[] tokens = br.readLine().split(" ");
        var res = new ArrayList<Integer>(tokens.length);
        for (String token : tokens) {
            res.add(Integer.valueOf(token));
        }
        return res;
    }
}
