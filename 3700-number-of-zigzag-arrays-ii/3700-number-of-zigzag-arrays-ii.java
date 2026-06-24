class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        int size = 2 * m;

        long[][] T = new long[size][size];

        for (int v = 0; v < m; v++) {
            for (int u = 0; u < v; u++) {
                T[v][m + u] = 1;
            }
        }

        for (int v = 0; v < m; v++) {
            for (int u = v + 1; u < m; u++) {
                T[m + v][u] = 1;
            }
        }

        long[] base = new long[size];

        for (int v = 0; v < m; v++) {
            base[v] = v;
            base[m + v] = m - 1 - v;
        }

        long[][] P = matrixPower(T, n - 2);

        long[] res = multiply(P, base);

        long ans = 0;
        for (long x : res) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[][] matrixPower(long[][] mat, long exp) {
        int n = mat.length;

        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, mat);
            }
            mat = multiply(mat, mat);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;

        long[][] c = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                long val = a[i][k];

                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;

                    c[i][j] = (c[i][j] + val * b[k][j]) % MOD;
                }
            }
        }

        return c;
    }

    private long[] multiply(long[][] mat, long[] vec) {
        int n = mat.length;

        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long cur = 0;

            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) continue;

                cur = (cur + mat[i][j] * vec[j]) % MOD;
            }

            res[i] = cur;
        }

        return res;
    }
}