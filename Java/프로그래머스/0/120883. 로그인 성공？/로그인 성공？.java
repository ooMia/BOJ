class Solution {
    public String solution(String[] id_pw, String[][] db) {
        for (var e: db) {
            if (e[0].equals(id_pw[0])) {
                if (e[1].equals(id_pw[1])) return "login";
                else return "wrong pw";
            }
        }
        return "fail";
    }
}