class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int mod = 1_000_000_007;

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int[] row : score) {
            Arrays.fill(row, -1);
        }

        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        int[][] directions = {{1, 0}, {0, 1}, {1, 1}};

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char ch = board.get(i).charAt(j);

                if (ch == 'X' || ch == 'S') {
                    continue;
                }

                int maxScore = -1;
                long pathCount = 0;

                for (int[] dir : directions) {
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if (x < n && y < n && score[x][y] != -1) {
                        if (score[x][y] > maxScore) {
                            maxScore = score[x][y];
                            pathCount = ways[x][y];
                        } else if (score[x][y] == maxScore) {
                            pathCount = (pathCount + ways[x][y]) % mod;
                        }
                    }
                }

                if (maxScore != -1) {
                    int value = ch == 'E' ? 0 : ch - '0';
                    score[i][j] = maxScore + value;
                    ways[i][j] = (int) pathCount;
                }
            }
        }

        if (score[0][0] == -1) {
            return new int[]{0, 0};
        }

        return new int[]{score[0][0], ways[0][0]};
    }
}