package com.leetcode.simon.algo.graph;

import com.leetcode.simon.Solution;

/**
 * https://leetcode.com/problems/number-of-islands/description/
 *
 *
 */
public class IslandNum implements Solution {

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) return 0;
    if (grid[0].length == 0) return 0;
    final int m = grid.length;
    final int n = grid[0].length;

    int count = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          dfs(grid, i, j, m, n);
          count++;
        }
      }
    }

    return count;
  }

  private void dfs(char[][] grid, int sourceX, int sourceY, int m, int n) {
    if (grid[sourceX][sourceY] == '0') return;

    // mark visit
    grid[sourceX][sourceY] = '0';

    // probe up
    if (sourceX > 0) dfs(grid, sourceX - 1, sourceY, m, n);

    // probe down
    if (sourceX < m - 1) dfs(grid, sourceX + 1, sourceY, m, n);

    // probe left
    if (sourceY > 0) dfs(grid, sourceX, sourceY - 1, m, n);

    // probe right
    if (sourceY < n -1) dfs(grid, sourceX, sourceY + 1, m, n);
  }


  @Override
  public void launchTestCase() {
    char[][] island = new char[4][5];
    island[0] = new char[]{'1', '1', '1', '1', '0'};
    island[1] = new char[]{'1', '1', '0', '0', '0'};
    island[2] = new char[]{'0', '0', '1', '0', '0'};
    island[3] = new char[]{'0', '0', '0', '1', '1'};

    System.out.println(numIslands(island));
  }
}
