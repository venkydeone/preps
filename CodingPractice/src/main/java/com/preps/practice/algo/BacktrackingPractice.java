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

			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board.length; col++) {
					board[row][col] = '.';
				}
			}

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
		private String[][] solveNQueens(int N){
			String[][] pos = new String[N][N];
			
			if(position(pos,0,N)){
				return pos;
			}
			return null;
		}

		private boolean position(String[][] pos, int row, int N){
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

		private boolean check(String[][] pos, int row, int col, int N) {
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
}
