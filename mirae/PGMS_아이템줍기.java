package Programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGMS_아이템줍기 {
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int[][] map;
	
	static class Point {
		int r, c, len;
		public Point(int r, int c, int len) {
			this.r = r;
			this.c = c;
			this.len = len;
		}
	}
	
	public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		map = new int[101][101];
		int x1, y1, x2, y2;
		for(int i=0; i<rectangle.length; i++) {
			x1 = rectangle[i][0]*2;
			y1 = rectangle[i][1]*2;
			x2 = rectangle[i][2]*2;
			y2 = rectangle[i][3]*2;
			for(int r=y1; r<=y2; r++) {
				for(int c=x1; c<=x2; c++) {
					if(map[r][c] == 2) continue;
					map[r][c] = 2;
					if(r == y1 || r==y2 || c == x1 || c == x2) map[r][c] =1;
				}
			}
		}
		
		Queue<Point> queue = new ArrayDeque<Point>();
        queue.add(new Point(characterY*2, characterX*2, 0));
        map[characterY*2][characterX*2] = 0;
        
        while(!queue.isEmpty()) {
        	Point cur = queue.poll();
        	
        	if(cur.r == itemY*2 && cur.c == itemX*2) return cur.len/2;
        	
        	for(int d=0; d<4; d++) {
        		int nr = cur.r + dr[d];
        		int nc = cur.c + dc[d];
        		if(nr < 0 || nr >= 101 || nc < 0 || nc >= 101 || map[nr][nc] != 1) continue;
        		map[nr][nc] = 0;
        		queue.add(new Point(nr, nc, cur.len+1));
        	}
        }
        return -1;
    }
	
	private static void print() {
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println(
				solution(
						new int[][]{
								{1,1,7,4},
								{3,2,5,5},
								{4,3,6,9},
								{2,6,8,8}
						},
						1, 3, 7, 8
				));
		System.out.println(
				solution(
						new int[][]{
								{1,1,8,4},
								{2,2,4,9},
								{3,6,9,8},
								{6,3,7,7}
						},
						9, 7, 6, 1
				));
	}

}
