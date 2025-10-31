class Solution {
    public int solution(int[][] dots) {
        int minX,maxX,minY,maxY;
        minX=maxX= dots[0][0];
        minY=maxY= dots[0][1];
        
        for(int i=1; i<dots.length; ++i){
            minX=Math.min(minX, dots[i][0]);
            maxX=Math.max(maxX, dots[i][0]);
            minY=Math.min(minY, dots[i][1]);
            maxY=Math.max(maxY, dots[i][1]);
        }
        return (maxX-minX) * (maxY-minY);
    }
}