package edu.harshil.solutions.LC75;

import java.util.*;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * 
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * 
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 * 
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 * [0,4]: [0,4] -> Pacific Ocean
 * [0,4] -> Atlantic Ocean
 * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 * [1,3] -> [1,4] -> Atlantic Ocean
 * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
 * [1,4] -> Atlantic Ocean
 * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
 * [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
 * [3,0]: [3,0] -> Pacific Ocean
 * [3,0] -> [4,0] -> Atlantic Ocean
 * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
 * [3,1] -> [4,1] -> Atlantic Ocean
 * [4,0]: [4,0] -> Pacific Ocean
 * [4,0] -> Atlantic Ocean
 * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 * Example 2:
 * 
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 * 
 * 
 * Constraints:
 * 
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */
public class PacificAtlanticWaterFlow {


    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * Runtime 5 ms Beats 64.50%
     * Memory 43.8 MB Beats 62.45%
     */
    public List<List<Integer>> pacificAtlanticDFS(int[][] heights) {

        List<List<Integer>> answer = new LinkedList<>();
        int m = heights.length, n = heights[0].length;

        if (heights == null || heights[0].length == 0 || heights.length == 0)
            return answer;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, Integer.MIN_VALUE, i, 0, m, n);
            dfs(heights, atlantic, Integer.MIN_VALUE, i, n - 1, m, n);
        }
        for (int i = 0; i < n; i++) {
            dfs(heights, pacific, Integer.MIN_VALUE, 0, i, m, n);
            dfs(heights, atlantic, Integer.MIN_VALUE, m - 1, i, m, n);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    LinkedList<Integer> l = new LinkedList<>();
                    l.add(i);
                    l.add(j);
                    answer.add(l);
                }
            }
        }
        return answer;
    }

    public void dfs(int[][] heights, boolean[][] visited, int prevHeight, int r, int c, int m, int n) {


        if (r < 0 || r >= m || c >= n || c < 0 || visited[r][c] || heights[r][c] < prevHeight)
            return;
        visited[r][c] = true;
        for (int[] d : dir) {
            dfs(heights, visited, heights[r][c], r + d[0], c + d[1], m, n);
        }

    }

    /**
     * Runtime 9 ms Beats 39.61%
     * Memory 44.9 MB Beats 7.78%
     */
    public List<List<Integer>> pacificAtlanticBFS(int[][] heights) {

        List<List<Integer>> answer = new LinkedList<>();
        int m = heights.length, n = heights[0].length;

        if (heights == null || heights[0].length == 0 || heights.length == 0)
            return answer;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        Queue<int[]> pq = new LinkedList<>();
        Queue<int[]> aq = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            pq.add(new int[]{i, 0});
            pacific[i][0] = true;
            aq.add(new int[]{i, n - 1});
            atlantic[i][n - 1] = true;
        }

        for (int i = 0; i < n; i++) {
            pq.add(new int[]{0, i});
            pacific[0][i] = true;
            aq.add(new int[]{m - 1, i});
            atlantic[m - 1][i] = true;
        }

        bfs(heights, pq, pacific, m, n);
        bfs(heights, aq, atlantic, m, n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    LinkedList<Integer> l = new LinkedList<>();
                    l.add(i);
                    l.add(j);
                    answer.add(l);
                }
            }
        }
        return answer;
    }

    public void bfs(int[][] heights, Queue<int[]> q, boolean[][] visited, int m, int n) {
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            for (int[] d : dir) {
                int x = r + d[0];
                int y = c + d[1];
                if (x < 0 || x >= m || y >= n || y < 0 || visited[x][y] || heights[x][y] < heights[r][c])
                    continue;
                visited[x][y] = true;
                q.add(new int[]{x, y});
            }
        }

    }


}
