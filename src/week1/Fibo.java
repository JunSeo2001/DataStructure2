package week1;

public class Fibo {
    int count ;
    int[] dptable = new int[30];

    public void reset() {
        count=0;
        for (int i = 0; i < dptable.length; i++) {
            dptable[i] = 0;
        }
    }
    public int getCount() {
        return count;
    }

    public int fiboRec(int n) {
        count++;
        if (n<=2)
            return 1;
        else
            return fiboRec(n-1)+fiboRec(n-2);
    }

    public int fiboDP(int n) {
        count++;
        if (n <= 2) {
            return 1;
        }

        if (dptable[n] != 0) {
            return dptable[n];
        } else {
            dptable[n] = fiboDP(n - 1) + fiboDP(n - 2);
            return dptable[n];
        }
    }

    public static void main(String[] args) {
        Fibo f = new Fibo();
        int  c1, c2;
        for (int i=3;i<30;i++) {
            f.reset();
            f.fiboRec(i);
            c1=f.getCount();
            f.reset();
            f.fiboDP(i);
            c2=f.getCount();

            System.out.printf(" Recursion : %-10d    =>    DP : %-10d\n", c1, c2);
        }
    }
}
