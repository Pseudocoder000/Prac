class Solution {
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int area = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int height = grid[i][j];

                if (height > 0) {
                    area += 4 * height + 2;

                    if (i > 0) {
                        area -= 2 * Math.min(height, grid[i - 1][j]);
                    }

                    if (j > 0) {
                        area -= 2 * Math.min(height, grid[i][j - 1]);
                    }
                }
            }
        }

        return area;
    }
}