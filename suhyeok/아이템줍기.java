import java.util.*;

class Solution {
    
    static final int[] dr = {-1,0,1,0};
    static final int[] dc = {0,-1,0,1};
    
    static class Node{
        int r;
        int c;
        int len;
        public Node(int r, int c, int len){
            this.r = r;
            this.c = c;
            this.len = len;
        }
    }
    
    static int[][] map;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        map = new int[101][101];
        
        for(int i=0;i<rectangle.length;i++) {
			fill(2*rectangle[i][0],2*rectangle[i][1],2*rectangle[i][2],2*rectangle[i][3]);
		}
        
        // System.out.println(bfs(new Node(100-2*characterY, 2*characterX, 0), 2*itemY, 2*itemX)/2);
        
        answer = bfs(new Node(100-2*characterY, 2*characterX, 0), 2*itemY, 2*itemX)/2;
        return answer;
    }
    
    private static int bfs(Node node, int itemY, int itemX) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[101][101];
		
		visited[node.r][node.c]=true;
		q.offer(node);
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			// System.out.println("현재 노드 : "+cur.r+" "+cur.c);
			// System.out.println("현재 길이 : "+cur.len);
			
			if(cur.r==100-itemY && cur.c==itemX) return cur.len;
			
			for(int d=0;d<4;d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				
				if(isInRange(nr, nc) && map[nr][nc]==1 && !visited[nr][nc]) {
					visited[nr][nc]=true;
					q.offer(new Node(nr, nc, cur.len+1));
				}
			}
		}
		
		return -1;
	}
    
    private static void fill(int x1, int y1, int x2, int y2) {
		for(int i=y1;i<=y2;i++){
            for(int j=x1;j<=x2;j++){
                if(map[100-i][j]==2) continue;
                map[100-i][j]=2;
                if(i==y1 || i==y2 || j==x1 || j==x2) map[100-i][j]=1;
            }
        }
	}
    
    private static boolean isInRange(int x, int y){
        return x>=0 && y>=0 && x<101 && y<101;
    }
    
    private static void print(){
        for(int i=0;i<101;i++){
            for(int j=0;j<101;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
