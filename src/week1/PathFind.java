package week1;

public class PathFind {

    int[][] maze;
    int[][] memo;
    int count, mSize;

    // 생성자
    public PathFind(int[][] input) {
        maze = input;
        count = 0;
        mSize = maze.length;
        memo = new int[mSize][mSize];  // 메모이제이션 배열 초기화
    }

    // count 리셋
    public void reset() {
        count = 0;
        // 메모이제이션 배열도 리셋
        for (int i = 0; i < mSize; i++) {
            for (int j = 0; j < mSize; j++) {
                memo[i][j] = 0;
            }
        }
    }

    // count 값을 리턴
    public int getCount() {
        return count;
    }

    // 재귀적으로 경로의 최대값을 찾는 함수
    public int maxPathRec(int row, int col) {
        count++;
        if (row == 0 && col == 0) {
            return maze[row][col];  // 시작점
        } else if (row == 0) {
            return maze[row][col] + maxPathRec(row, col - 1);  // 첫 번째 행
        } else if (col == 0) {
            return maze[row][col] + maxPathRec(row - 1, col);  // 첫 번째 열
        } else {
            return maze[row][col] + Math.max(maxPathRec(row, col - 1), maxPathRec(row - 1, col));
        }
    }

    // DP를 활용한 경로 최대값 함수
    public int maxPathDP(int row, int col) {
        count++;

        // 이미 계산된 값이 있으면 그것을 반환
        if (memo[row][col] != 0) {
            return memo[row][col];
        }

        // 값이 없으면 계산
        if (row == 0 && col == 0) {
            memo[row][col] = maze[row][col];  // 시작점
        } else if (row == 0) {
            memo[row][col] = maze[row][col] + maxPathDP(row, col - 1);  // 첫 번째 행
        } else if (col == 0) {
            memo[row][col] = maze[row][col] + maxPathDP(row - 1, col);  // 첫 번째 열
        } else {
            memo[row][col] = maze[row][col] + Math.max(maxPathDP(row, col - 1), maxPathDP(row - 1, col));  // 상단과 좌측 중 최댓값 선택
        }

        return memo[row][col];  // 계산된 결과 반환
    }

    // 메인 메소드
    public static void main(String[] args) {
        int[][] matrix = {
                {6, 7, 12, 5, 7},
                {5, 3, 11, 18, 4},
                {3, 4, 7, 7, 10},
                {1, 17, 3, 9, 3},
                {8, 10, 14, 9, 11}
        };
        int nDim = matrix.length;

        PathFind pf = new PathFind(matrix);

        // 재귀 방식으로 최대 경로 계산
        pf.reset();
        System.out.println("Recursion : " + pf.maxPathRec(nDim - 1, nDim - 1) + " (" + pf.getCount() + ")");

        // DP 방식으로 최대 경로 계산
        pf.reset();
        System.out.println("DP : " + pf.maxPathDP(nDim - 1, nDim - 1) + " (" + pf.getCount() + ")");
    }
}
