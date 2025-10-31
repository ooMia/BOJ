import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(int[] answers) {
        int[] player1 = { 1, 2, 3, 4, 5 };
        int[] player2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] player3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

        Data[] count = new Data[3];
        for (int i = 0; i < count.length; i++) {
            count[i] = new Data(i + 1);
        }
        for (int i = 0; i < answers.length; i++) {
            if (player1[i % player1.length] == answers[i])
                count[0].score++;
            if (player2[i % player2.length] == answers[i])
                count[1].score++;
            if (player3[i % player3.length] == answers[i])
                count[2].score++;
        }
        Arrays.sort(count);

        int maxScore = count[count.length - 1].score;
        List<Integer> topPlayers = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i].score == maxScore) {
                topPlayers.add(count[i].id);
            }
        }
        return topPlayers.stream().mapToInt(i -> i).toArray();
    }

    class Data implements Comparable<Data> {
        int id;
        int score = 0;

        Data(int id) {
            this.id = id;
        }

        @Override
        public int compareTo(Data o) {
            if (this.score == o.score) {
                return Integer.compare(this.id, o.id);
            }
            return Integer.compare(this.score, o.score);
        }
    }
}