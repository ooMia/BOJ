class Solution {
    public int solution(String[] spell, String[] dic) {
        for (var d: dic){
            boolean isValid = true;
            for (var s: spell) {
                if (!d.contains(s)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) return 1;
        }
        return 2;
    }
}