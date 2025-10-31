class Solution {
    public int solution(int n, int w, int num) {
        int height = (n % w == 0) ? n / w : n / w + 1;
        int[][] boxes = new int[height][w];

        // fill boxes in zigzag manner
        // start from the bottom left corner
        int iFromX = 0, iFromY = height - 1;
        boolean isDirRight = true;
        int nBox = 1;
        while (nBox <= n) {
            boxes[iFromY][iFromX] = nBox++;

            iFromX += isDirRight ? 1 : -1;
            if (iFromX < 0 || iFromX >= w) {
                isDirRight = !isDirRight;
                --iFromY;
                iFromX += isDirRight ? 1 : -1;
            }
        }

        // find the box with the given number
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < w; ++x) {
                if (boxes[y][x] == num) {
                    int cnt = 0;
                    while (y >= 0 && boxes[y][x] > 0) {
                        ++cnt;
                        --y;
                    }
                    return cnt;
                }
            }
        }

        // if not found, return -1
        return -1;
    }
}