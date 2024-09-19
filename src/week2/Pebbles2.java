package week2;

public class Pebbles2 {
    int [][]peb;
    int memo[][];

    int [][] nextPattern = {
            {0,0,0},
            {2,3,0},
            {1,3,4},
            {1,2,0},
            {2,0,0}
    };

    int nCols, count;

    public Pebbles2(int [][] input) {
        peb=input;
        nCols=peb[0].length;
        memo=new int [5][nCols];
    }
    public void reset() {
        count=0;
        for (int i=0;i<5;i++)
            for (int j=0;j<nCols;j++)
                memo[i][j]=-99999;
    }
    public int getCount() {
        return count;
    }

    public int maxPebble( int n ) {
        int max =-99999;
        for (int i=1;i<=4;i++)
            max=Math.max(max, pebble(n,i));
        return max;
    }

    private int pebble(int i, int p) {
        count++;
        if (i == 1) {
            return aPatternValue(i, p);
        } else {
            int max = -99999;
            int k = 0;
            while (k < 3 && nextPattern[p][k] != 0) {
                int q = nextPattern[p][k];
                max = Math.max(max, pebble(i - 1, q));
                count++;
                k++;
            }
            return aPatternValue(i, p) + max;
        }
    }


    public int maxPebbleDP( int n ) {
        int max =-99999;
        for (int i=1;i<=4;i++)
            max=Math.max(max, pebbleDP(n,i));
        return max;
    }

    private int pebbleDP(int i, int p) {
        if (memo[p][i] != -99999) {
            return memo[p][i];
        }

        count++;

        if (i == 0) {
            return aPatternValue(i, p);
        }

        int maxPebbles = -99999;
        for (int j = 0; j < 3; j++) {
            int nextP = nextPattern[p][j];
            if (nextP != 0) {
                maxPebbles = Math.max(maxPebbles, pebbleDP(i - 1, nextP));
            }
        }

        memo[p][i] = maxPebbles + aPatternValue(i, p);
        return memo[p][i];
    }


    public int maxPebbleIter(int n) {
        int[][] dp = new int[5][n + 1];

        for (int p = 1; p <= 4; p++) {
            dp[p][0] = aPatternValue(0, p);
            count++;
        }

        for (int i = 1; i <= n; i++) {
            for (int p = 1; p <= 4; p++) {
                int maxPebbles = -99999;
                for (int j = 0; j < 3; j++) {
                    int nextP = nextPattern[p][j];
                    if (nextP != 0) {
                        maxPebbles = Math.max(maxPebbles, dp[nextP][i - 1]);
                    }
                }
                dp[p][i] = maxPebbles + aPatternValue(i, p);
                count++;
            }
        }

        int max = -99999;
        for (int p = 1; p <= 4; p++) {
            max = Math.max(max, dp[p][n]);
        }

        return max;
    }




    private int aPatternValue(int i, int p) {

        switch(p){
            case 1: return peb[1][i];
            case 2: return peb[2][i];
            case 3: return peb[3][i];
            case 4: return peb[1][i]+peb[3][i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int [][] input = {
                {0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,6,7,12,-5,5,3,11,3,7,-2,5,4},
                {0,-8,10,14,9,7,13,8,5,6,1,3,9},
                {0,11,12,7,4,8,-2,9,4,-4,3,7,10} };

        Pebbles2 myPeb =new Pebbles2(input);

        for (int i=1; i<input[0].length; i++) {
            myPeb.reset();
            System.out.printf(">>> %3d : [Iteration] %3d Count = %-10d", i, myPeb.maxPebbleIter(i),myPeb.getCount());
            myPeb.reset();
            System.out.printf(" ==> [Recursion] %3d Count = %-10d", myPeb.maxPebble(i),myPeb.getCount());
            myPeb.reset();
            System.out.printf(" ==> [DynamicProg.] %3d Count = %-10d%n", myPeb.maxPebbleDP(i),myPeb.getCount());

        }
    }


}
