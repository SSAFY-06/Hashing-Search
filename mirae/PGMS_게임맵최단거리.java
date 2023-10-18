package Programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGMS_게임맵최단거리 {
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	static class Point {
		int r, c, len;
		public Point(int r, int c, int len) {
			this.r = r;
			this.c = c;
			this.len = len;
		}
	}
	public static int solution(int[][] maps) {
        int R = maps.length;
        int C = maps[0].length;
        Queue<Point> queue = new ArrayDeque<Point>();
        queue.add(new Point(0, 0, 1));
        maps[0][0] += 1;
        
        while(!queue.isEmpty()) {
        	Point cur = queue.poll();
        	
        	if(cur.r == R-1 && cur.c == C-1) return cur.len;
        	
        	for(int d=0; d<4; d++) {
        		int nr = cur.r + dr[d];
        		int nc = cur.c + dc[d];
        		if(nr < 0 || nr >= R || nc < 0 || nc >= C || maps[nr][nc] != 1) continue;
        		maps[nr][nc] += 1;
        		queue.add(new Point(nr, nc, cur.len + 1));
        	}
        }
        
        return -1;
    }
	
	public static void main(String[] args) {
		System.out.println(
				solution(
						new int[][]{
								{1,0,1,1,1},
								{1,0,1,0,1},
								{1,0,1,1,1},
								{1,1,1,0,1},
								{0,0,0,0,1}
						}
				));
		System.out.println(
				solution(
						new int[][]{
								{1,0,1,1,1},
								{1,0,1,0,1},
								{1,0,1,1,1},
								{1,1,1,0,0},
								{0,0,0,0,1}
						}
				));
	}

}
