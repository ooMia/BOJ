class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int minX,maxX,minY,maxY;
        maxX = board[0]/2;
        minX = -maxX;
        maxY = board[1]/2;
        minY = -maxY;
        
        int curX=0, curY=0;
        for (var s: keyinput) {
            if ("right".equals(s)) curX = Math.min(maxX, curX+1);
            if ("left".equals(s)) curX = Math.max(minX, curX-1);
            if ("up".equals(s)) curY = Math.min(maxY, curY+1);
            if ("down".equals(s)) curY = Math.max(minY, curY-1);
        }
        return new int[]{curX, curY};
    }
}