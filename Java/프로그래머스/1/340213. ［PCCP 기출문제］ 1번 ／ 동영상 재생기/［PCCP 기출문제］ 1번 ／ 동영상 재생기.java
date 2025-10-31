class Solution {

    int[] cur; // current position
    int[] videoLen; // length
    int[] opStart; // start of opening
    int[] opEnd; // end of opening

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        cur = parseTime(pos);
        videoLen = parseTime(video_len);
        opStart = parseTime(op_start);
        opEnd = parseTime(op_end);

        for (String command : commands) {
            operate(command);
        }

        return String.format("%02d:%02d", cur[0], cur[1]);
    }

    void operate(String command) {
        if (isCurrentBetween(opStart, opEnd)) {
            cur[0] = opEnd[0];
            cur[1] = opEnd[1];
        }

        switch (command) {
            case "next":
                move(10);
                break;
            case "prev":
                move(-10);
                break;
        }

        if (isCurrentBetween(opStart, opEnd)) {
            cur[0] = opEnd[0];
            cur[1] = opEnd[1];
        }
    }

    int[] parseTime(String timeStr) {
        String[] parts = timeStr.split(":");
        int min = Integer.parseInt(parts[0]);
        int sec = Integer.parseInt(parts[1]);
        return new int[] { min, sec };
    }

    void move(int sec) {
        int t = cur[0] * 60 + cur[1] + sec; // move

        int tMin = 10;
        int tMax = videoLen[0] * 60 + videoLen[1] - 10;
        if (t < tMin) {
            t = tMin - 10;
        } else if (t > tMax) {
            t = tMax + 10;
        }

        cur[0] = t / 60;
        cur[1] = t % 60;
    }

    boolean isCurrentBetween(int[] from, int[] to) {
        int tFrom = from[0] * 60 + from[1];
        int tTo = to[0] * 60 + to[1];
        int tCurrent = cur[0] * 60 + cur[1];
        if (tFrom <= tCurrent && tCurrent <= tTo) {
            return true;
        }
        return false;
    }

}