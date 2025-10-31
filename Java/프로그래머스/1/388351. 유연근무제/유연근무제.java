class Solution {

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        for (int i = 0; i < schedules.length; i++) {
            int schedule = schedules[i];
            int[] timelog = timelogs[i];

            boolean isValid = true;

            for (int j = 0; j < timelog.length; j++) {
                // 1: 월요일 ... 7: 일요일
                int day = (startday + j) % 7;
                if (day == 0) {
                    day = 7;
                }
                int timelogTime = timelog[j];

                if (!isOnTime(day, schedule, timelogTime)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                answer++;
            }
        }
        return answer;
    }

    private boolean isOnTime(int day, int schedule, int timelog) {
        if (6 <= day)
            return true; // 휴일 무시
        int destTime = addMinutes(schedule, 10);
        return compareTime(timelog, destTime) <= 0;
    }

    private int addMinutes(int time, int minutes) {
        int hour = time / 100;
        int minute = time % 100;

        minute += minutes;
        if (minute >= 60) {
            hour += minute / 60;
            minute %= 60;
        }
        if (hour >= 24) {
            hour %= 24;
        }

        return hour * 100 + minute;
    }

    private int compareTime(int time1, int time2) {
        if (time1 == time2)
            return 0;
        if (time1 < time2)
            return -1;
        return 1;
    }
}