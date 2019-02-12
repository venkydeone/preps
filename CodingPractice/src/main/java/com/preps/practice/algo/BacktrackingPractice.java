/**
 * 
 */
package com.preps.practice.algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chella
 *
 */
public class BacktrackingPractice {
	
	public static void main(String[] args) {
		int[][] board = {
				{1,2,3},
				{4,5,6},
				{7,8,9}
		};
		System.out.println(PhoneCombinations.phoneCombinations(board, 1, 0));
	}
	
	static class PhoneCombinations {

	    private static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	    private static final int PHONE_LENGTH = 7;

	    public static List<String> phoneCombinations(int[][] board, int sr, int sc) {
	        if (isNullOrEmpty(board)) return new ArrayList<>();
	        List<String> combinations = new ArrayList<>();
	        StringBuilder phone = new StringBuilder(PHONE_LENGTH);
	        phone.append(board[sr][sc]);
	        phoneCombinations(board, combinations, phone, sr, sc);
	        return combinations;
	    }

	    private static void phoneCombinations(int[][] board, List<String> phones, StringBuilder phone, int r, int c) {
	        if (phone.length() == PHONE_LENGTH) {
	            phones.add(phone.toString());
	            return;
	        }

	        Set<Integer> used = new HashSet<>();
	        for (int[] dir : DIRS) {
	            int newRow = r + dir[0];
	            int newCol = c + dir[1];
	            if (isSafe(board, newRow, newCol) && !used.contains(board[newRow][newCol])) {
	                used.add(board[newRow][newCol]);
	                phone.append(board[newRow][newCol]);
	                phoneCombinations(board, phones, phone, newRow, newCol);
	                phone.setLength(phone.length() - 1);
	            }
	        }
	    }

	    private static boolean isSafe(int[][] board, int r, int c) {
	        return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
	    }

	    private static boolean isNullOrEmpty(int[][] board) {
	        return board == null || board.length == 0 || board[0].length == 0;
	    }
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
