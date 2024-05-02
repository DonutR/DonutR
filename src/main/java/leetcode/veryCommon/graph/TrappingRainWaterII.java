package leetcode.veryCommon.graph;

public class TrappingRainWaterII {
    int[][] diff = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        if (m == 0) return 0;
        int n = heightMap[0].length;
        int out = 0;
        boolean[][] visited = new boolean[m][n];

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                if (visited[i][j] = false) {
                    boolean[][] currVisited = new boolean[m][n];
                    int[] heights = {heightMap[i][j], Integer.MAX_VALUE, 1};
                    if (!helper(heightMap, i, j, m, n, currVisited, visited, heights))
                        out += (heights[1] - heights[0]) * heights[2];
                }
            }
        return out;
    }

    public boolean helper(int[][] heightMap, int i, int j, int m, int n, boolean[][] currVisited, boolean[][] visited, int[] heights) {
        currVisited[i][j] = true;
        if (heights[0] == heightMap[i][j]) visited[i][j] = true;
        for (int[] xy : diff) {
            int nX = i + xy[0], nY = j + xy[1];
            if (nX >= 0 && nX < m && nY >= 0 && nY < n) {
                if (currVisited[nX][nY] == false) {
                    if (heights[0] >= heightMap[nX][nY]) {
                        ++heights[2];
                        if (helper(heightMap, nX, nY, m, n, currVisited, visited, heights))
                            return true;
                    } else if (heights[0] < heightMap[nX][nY])
                        heights[1] = Math.min(heights[1], heightMap[nX][nY]);
                }
            } else return true;
        }
        return false;
    }
}
