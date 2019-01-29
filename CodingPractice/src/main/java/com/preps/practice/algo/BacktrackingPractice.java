/**
 * 
 */
package com.preps.practice.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chella
 *
 */
public class BacktrackingPractice {
	
	public static void main(String[] args) {
		List<List<String>> solveNQueens = new NQueens().solveNQueens(8);
		for(List<String> p : solveNQueens){
			System.out.println(p);
		}
	}

	static class NQueens{
		private List<List<String>> solveNQueens(int N){
			String[][] pos = new String[N][N];
			
            List<List<String>> results = new ArrayList<>();
			if(position(pos,0,N)){
				results.add(convert(pos));
                pos = new String[N][N];
			}
			return results;
		}
		
		private List<String> convert(String[][] pos) {
			List<String> position = new ArrayList<>();
			for(int i=0; i<pos.length;i++){
				StringBuilder sb = new StringBuilder();
				for(int j=0;j<pos[0].length; j++){
					if(pos[i][j]==null){
						sb.append(".");
					}else{
						sb.append("Q");
					}
				}
				position.add(sb.toString());
			}
			return position;
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
