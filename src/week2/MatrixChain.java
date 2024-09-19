package week2;

public class MatrixChain {

    int nOfMatrix;
    int[] p;
    int count;
    int[][] memo;

    public MatrixChain(int[] dimension) {
        p = dimension;
        nOfMatrix = dimension.length - 1;
        memo = new int[nOfMatrix + 1][nOfMatrix + 1];
    }

    void reset() {
        count = 0;
        for (int i = 0; i <= nOfMatrix; i++) {
            for (int j = 0; j <= nOfMatrix; j++) {
                memo[i][j] = -1;
            }
        }
    }

    int getCount() {
        return count;
    }

    int matrixChainIter(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    count++;
                    int q = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < dp[i][j]) {
                        dp[i][j] = q;
                    }
                }
            }
        }

        return dp[1][n];
    }

    int matrixChain(int i, int j) {
        if (i == j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            count++;
            int q = matrixChain(i, k) + matrixChain(k + 1, j) + p[i - 1] * p[k] * p[j];
            if (q < min) {
                min = q;
            }
        }
        return min;
    }

    int matrixChainDP(int i, int j) {
        if (i == j) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            count++;
            int q = matrixChainDP(i, k) + matrixChainDP(k + 1, j) + p[i - 1] * p[k] * p[j];
            if (q < min) {
                min = q;
            }
        }

        memo[i][j] = min;
        return min;
    }

    public void showMemoTable() {
        System.out.println("Memoization Table:");
        for (int i = 1; i <= nOfMatrix; i++) {
            for (int j = 1; j <= nOfMatrix; j++) {
                if (memo[i][j] == -1) {
                    System.out.print("0\t");
                } else {
                    System.out.print(memo[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] dimension = {9, 5, 15, 4, 20, 7, 15, 8};  // dimension.length=numOfMatrix+1
        int numOfMatrix = dimension.length - 1;

        MatrixChain mc = new MatrixChain(dimension);
        for (int i = 1; i <= numOfMatrix; i++) {
            mc.reset();
            System.out.print("Iteration : " + mc.matrixChainIter(i) + "   Count=" + mc.getCount());
            mc.reset();
            System.out.print(" => Recursion : " + mc.matrixChain(1, i) + "   Count=" + mc.getCount());
            mc.reset();
            System.out.println(" => DP : " + mc.matrixChainDP(1, i) + "   Count=" + mc.getCount());
            mc.showMemoTable();
        }
    }
}
