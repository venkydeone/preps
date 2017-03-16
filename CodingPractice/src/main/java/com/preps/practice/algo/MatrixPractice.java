package com.preps.practice.algo;

public class MatrixPractice {

	public static void main(String[] args) {
		System.err.println(islandPerimeter(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}}));
	}
	
	static int islandPerimeter(int[][] grid) {
        int island =0;
        int neighbours =0;
        for( int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j]==1){
                    island++;
                    if(i<grid.length-1 && grid[i+1][j]==1)
                        neighbours++;
                    if(j<grid[i].length-1 && grid[i][j+1]==1)
                        neighbours++;
                }
            }
        }
        return 4 * island - 2 * neighbours;
    }
}
