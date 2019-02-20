/**
 * 
 */
package com.preps.practice.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author chella
 *
 */
public class BacktrackingPractice {
	
	public static void main(String[] args) {
		String a = "(a)())()";
		List<String> removeInvalidParentheses = removeInvalidParentheses(a);
		System.out.println(removeInvalidParentheses);
		char[][] island = {
				{'1','1','0','0','0'},
				{'1','1','0','0','0'},
				{'0','0','1','0','0'},
				{'0','0','0','1','1'},
				{'0','0','0','1','1'},
			};
		System.out.println(Islands.numIslands(island));
		System.out.println(Islands.numIslands2(3,3,new int[][]{
			{0,1},
			{1,2},
			{2,1},
			{1,0},
			{0,2},
			{0,0},
			{1,1},
		}));
		
		char[][] board = {
				{'X','X','X','X'},
				{'X','O','O','X'},
				{'X','O','O','X'},
				{'X','O','X','X'},
			};
		solve(board);
		System.out.println(Arrays.toString(board));
	}
	
	/**
	 * https://leetcode.com/problems/surrounded-regions/
	 * @param board
	 */
	static void solve(char[][] board){
		if(board==null || board.length<2 || board[0].length<2)
			return;
		
		int row = board.length, col = board[0].length;
		
		for(int i=0; i<row; i++){
			if(board[i][0]=='O'){
				dfs(board,i,0);
			}
			if(board[i][col-1]=='O'){
				dfs(board,i,col-1);
			}
		}
		
		for(int j=0; j<col; j++){
			if(board[0][j]=='O'){
				dfs(board,0,j);
			}
			if(board[row-1][j]=='O'){
				dfs(board,row-1,j);
			}
		}
		
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(board[i][j]=='O')
					board[i][j]='X';
				if(board[i][j]=='1')
					board[i][j]='O';
			}
		}
	}
	
	
	static void dfs(char[][] board, int row, int col){
		board[row][col]='1';
		int[][] dir = new int[][]{ {0,1}, {0,-1}, {1,0}, {-1,0} };
		for(int i=0;i<dir.length;i++){
			int r = row+dir[i][0];
			int c = col+dir[i][1];
			if(r<0 || r>board.length-1 || c<0 || c>board[0].length-1 || board[r][c]!='O')
				continue;
			if(board[r][c]=='O')
				dfs(board,r,c);
		}
	}

	static class Islands{
		static int numIslands(char[][] grid) {
		    int count = 0;
		    int n = grid.length;
		    if (n == 0) return 0;
		    int m = grid[0].length;
		    for (int i = 0; i < n; i++){
		        for (int j = 0; j < m; j++)
		            if (grid[i][j] == '1') {
		                DFSMarking(grid, i, j ,n ,m);
		                ++count;
		            }
		    }    
		    return count;
		}

		static void DFSMarking(char[][] grid, int i, int j, int n, int m) {
		    if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
		    grid[i][j] = '0';
		    DFSMarking(grid, i + 1, j, n, m);
		    DFSMarking(grid, i - 1, j, n, m);
		    DFSMarking(grid, i, j + 1, n, m);
		    DFSMarking(grid, i, j - 1, n, m);
		}
		
		static int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

		static List<Integer> numIslands2(int m, int n, int[][] positions) {
		    List<Integer> result = new ArrayList<>();
		    if(m <= 0 || n <= 0) return result;

		    int count = 0;                      // number of islands
		    int[] roots = new int[m * n];       // one island = one tree
		    Arrays.fill(roots, -1);            

		    for(int[] p : positions) {
		        int root = n * p[0] + p[1];     // assume new point is isolated island
		        roots[root] = root;             // add new island
		        count++;

		        for(int[] dir : dirs) {
		            int x = p[0] + dir[0]; 
		            int y = p[1] + dir[1];
		            int nb = n * x + y;
		            if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) continue;
		            
		            int rootNb = findIsland(roots, nb);
		            if(root != rootNb) {        // if neighbor is in another island
		                roots[root] = rootNb;   // union two islands 
		                root = rootNb;          // current tree root = joined tree root
		                count--;               
		            }
		        }

		        result.add(count);
		    }
		    return result;
		}

		static int findIsland(int[] roots, int id) {
		    while(id != roots[id]) id = roots[id];
		    return id;
		}
	}
	
	
	static List<String> removeInvalidParentheses(String s) {
	    List<String> ans = new ArrayList<>();
	    remove(s, ans, 0, 0, new char[]{'(', ')'});
	    return ans;
	}

	static void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
	    for (int stack = 0, i = last_i; i < s.length(); ++i) {
	        if (s.charAt(i) == par[0]) stack++;
	        if (s.charAt(i) == par[1]) stack--;
	        if (stack >= 0) continue;
	        for (int j = last_j; j <= i; ++j)
	            if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
	                remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
	        return;
	    }
	    String reversed = new StringBuilder(s).reverse().toString();
	    if (par[0] == '(') // finished left to right
	        remove(reversed, ans, 0, 0, new char[]{')', '('});
	    else // finished right to left
	        ans.add(reversed);
	}
	
	static int trapRainWater(int[][] heights) {
		if (heights == null || heights.length == 0 || heights[0].length == 0)
            return 0;

        PriorityQueue<Cell> queue = new PriorityQueue<>(1, new Comparator<Cell>(){
            public int compare(Cell a, Cell b) {
                return a.height - b.height;
            }
        });
        
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];

        // Initially, add all the Cells which are on borders to the queue.
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, n - 1, heights[i][n - 1]));
        }

        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            queue.offer(new Cell(0, i, heights[0][i]));
            queue.offer(new Cell(m - 1, i, heights[m - 1][i]));
        }

        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
       // add all its neighbors to the queue.
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] dir : dirs) {
                int row = cell.row + dir[0];
                int col = cell.col + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    res += Math.max(0, cell.height - heights[row][col]);
                    queue.offer(new Cell(row, col, Math.max(heights[row][col], cell.height)));
                }
            }
        }
        
        return res;
	}
	
	static class Cell {
        @Override
		public String toString() {
			return "Cell [row=" + row + ", col=" + col + ", height=" + height + "]";
		}
		int row;
        int col;
        int height;
        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
        
    }
    
    static int getDiff(int curr, int up, int down, int right, int left){
        int diff = Math.min( Math.min(up, down), Math.min(right,left));
        return diff-curr;
    }
	
	interface Robot{
		void clean();
		boolean move();
		void turnRight();
		void turnLeft();
	}
	
	static class RobotCleaner {
	    int[] dx = {-1, 0, 1, 0};
	    int[] dy = {0, 1, 0, -1};
	    public void cleanRoom(Robot robot) {
	        // use 'x@y' mark visited nodes, where x,y are integers tracking the coordinates
	        dfs(robot, new HashSet<>(), 0, 0, 0); // 0: up, 90: right, 180: down, 270: left
	    }
	 
	    public void dfs(Robot robot, Set<String> visited, int x, int y, int curDir) {
	        String key = x + "@" + y;
	        if (visited.contains(key)) return;
	        visited.add(key);
	        robot.clean();
	 
	        for (int i = 0; i < 4; i++) { // 4 directions
	            if(robot.move()) { // can go directly. Find the (x, y) for the next cell based on current direction
	                dfs(robot, visited, x + dx[curDir], y + dy[curDir], curDir);
	                backtrack(robot);
	            }
	 
	            // turn to next direction
	            robot.turnRight();
	            curDir += 1;
	            curDir %= 4;
	        }
	    }
	 
	    // go back to the starting position
	    private void backtrack(Robot robot) {
	        robot.turnLeft();
	        robot.turnLeft();
	        robot.move();
	        robot.turnRight();
	        robot.turnRight();
	    }
	}

	
	static List<List<String>> nQueens(int N){
		char[][] chess = new char[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				chess[i][j] = '.';
		
		List<List<String>> results = new ArrayList<>();
		solveQueen(chess, 0, results);
		
		return results;
	}
	
	private static List<String> printQueen(char[][] chess) {
		List<String> rowAns = new ArrayList<>();
		for(int i=0;i<chess.length;i++){
			rowAns.add(new String(chess[i]));
		}
		return rowAns;
	}

	static void solveQueen(char[][] chess, int row, List<List<String>> results){
		if(row==chess.length){
			results.add(printQueen(chess));
			return;
		}
		
		int N = chess.length;
		for(int col=0; col<N; col++){
			if(validateQueen(chess, row, col, N)){
				chess[row][col]='Q';
				solveQueen(chess,row+1,results);
				chess[row][col]='.';
			}
		}
	}
	
	static boolean validateQueen(char[][] pos, int row, int col, int N) {
		for(int i=0; i<row;i++){
			if(pos[i][col]=='Q')
				return false;
		}
		
		for(int i=row-1, j=col-1; i>=0 && j>=0 ;i--, j--){
			if(pos[i][j]=='Q')
				return false;
		}
		
		for(int i=row-1, j=col+1; i>=0&&j<N;i--, j++){
			if(pos[i][j]=='Q')
				return false;
		}
			
		return true;
	}
	
	
	
	static class Sudoku {
		static void solveSudoku(char[][] board) {
			if (board == null || board.length == 0)
				return;

			solve(board);
		}

		static boolean solve(char[][] board) {

			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board.length; col++) {
					if (board[row][col] == '.') {
						for (int i = 1; i <= 9; i++) {
							if (isValid(board, row, col, (char) i)) {
								board[row][col] = (char) i;

								if (solve(board))
									return true;
								else
									board[row][col] = '.';

							}
						}
						return false;
					}
				}
			}
			return true;
		}

		static boolean isValid(char[][] board, int row, int col, char val) {
			for (int i = 0; i < board.length; i++) {
				if (board[row][i] != '.' && board[row][i] == val)
					return false;
				if (board[i][col] != '.' && board[i][col] == val)
					return false;
				if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
						&& board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == val)
					return false;
			}
			return true;
		}
	}

	static class NQueens{
		static String[][] solveNQueens(int N){
			String[][] pos = new String[N][N];
			
			if(position(pos,0,N)){
				return pos;
			}
			return null;
		}

		static boolean position(String[][] pos, int row, int N){
			if(row>=N)
				return true;
			for(int j=0;j<N;j++){
				if(check(pos,row,j,N)){
					pos[row][j]="Q";
					if(position(pos,row+1,N)){
						return true;
					}else{
						pos[row][j]=null;
					}
				}
			}
			return false;
		}

		static boolean check(String[][] pos, int row, int col, int N) {
			for(int i=0; i<row;i++){
				if(pos[i][col]!=null)
					return false;
			}
			
			for(int i=row-1, j=col-1; i>=0 && j>=0 ;i--, j--){
				if(pos[i][j]!=null)
					return false;
			}
			
			for(int i=row-1, j=col+1; i>=0&&j<N;i--, j++){
				if(pos[i][j]!=null)
					return false;
			}
				
			return true;
		}
	}
	
	static class Minesweeper{
		public char[][] updateBoard(char[][] board, int[] click) {
			//base case, if the click is a mine, just change it and return the board.
	        if(board[click[0]][click[1]] == 'M'){
	            board[click[0]][click[1]] = 'X';
	            return board;
	        }
			//Depth first search on the board, starting on the click position.
	        dfs(board, click[0], click[1]);
	        return board;
	    }
	    void dfs(char[][] board, int i, int j){
	        if(i < 0 || i > board.length-1) return;
	        if(j < 0 || j > board[i].length-1) return;
	        //update the counts first, check on all 8 directions
	        int count = countMine(board, i-1, j-1) +
	                    countMine(board, i-1, j) +
	                    countMine(board, i-1, j+1) +
	                    countMine(board, i, j-1) +
	                    countMine(board, i, j+1) +
	                    countMine(board, i+1, j-1) +
	                    countMine(board, i+1, j) +
	                    countMine(board, i+1, j+1);
	        if(count > 0){
	            board[i][j] = (char)(count + '0'); // if has a count, meaning it has a number to be associated and not a B.
	        }else if(board[i][j] == 'E'){
	            board[i][j] = 'B';
	            //DFS on all 8 directions.
	            dfs(board, i-1, j-1);
	            dfs(board, i-1, j);
	            dfs(board, i-1, j+1);
	            dfs(board, i, j-1);
				//don't run on the same position as you are already --> dfs(board, i, j) 
	            dfs(board, i, j+1);
	            dfs(board, i+1, j-1);
	            dfs(board, i+1, j);
	            dfs(board, i+1, j+1);
	        }
	    }
		
		//simple helper method to check boundaries and return 1 or 0 if it is a mine
	    int countMine(char[][] board, int i, int j){
	        if(i < 0 || i > board.length-1) return 0; //boundaries check, just return zero for the count.
	        if(j < 0 || j > board[i].length-1) return 0;
	        return (board[i][j] == 'M' || board[i][j] == 'X')?1:0;  //remember to check X as well, as it counts as mine.
	    }
	}
}
