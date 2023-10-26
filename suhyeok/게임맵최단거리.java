import java.util.*;

/*
    0 : 벽
    1 : 빈 공간(이동가능한 공간)
    가장 빠른 루트를 찾는 문제 => bfs 이용
*/


class Solution {
    
    static final int[] dr = {-1,0,1,0};
    static final int[] dc = {0,-1,0,1};
    
    static class Node{
        int r;
        int c;
        public Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    static int N,M;
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        N = maps.length;
        M = maps[0].length;
        
        // print(maps);
        answer = bfs(new Node(0,0), maps);
        
        return answer;
    }
    
    private static int bfs(Node node, int[][] map){
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        int cnt = 1;
        
        //시작 위치 방문 처리 및 큐에 넣어주기
        visited[node.r][node.c] = true;
        q.offer(node);
            
        while(!q.isEmpty()){
            
            int size = q.size();
            // System.out.println("현재 q의 크기 : "+size);
            
            while(size-->0){
                //현재 위치
                Node cur = q.poll();
                
                // System.out.println("현재 노드 : "+cur.r+" "+cur.c);
                
                //목적지 도착 시
                if(cur.r==N-1 && cur.c==M-1)return cnt;
                
                //목적지에 도착 못했을 시
                //사방탐색하며 다음 갈 곳을 찾는다.
                for(int d=0;d<4;d++){
                    //다음 행, 열 값
                    int nr = cur.r+dr[d];
                    int nc = cur.c+dc[d];
                    
                    //맵을 벗어나지 않고 갈 수 있는 길이며 아직 방문을 하지 않았을 때
                    if(isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc]==1){
                        //방문처리 후 큐에 삽입
                        visited[nr][nc]=true;
                        q.offer(new Node(nr, nc));
                    }
                }
            }
            cnt++;
        }
        //만약 목적지에 도착하지 못하고 bfs가 종료되면 가는 길이 존재하지 않는 것이기 때문에 -1을 리턴
        return -1;
    }
    
    private static boolean isInRange(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }
    
    private static void print(int[][] maps){
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[0].length;j++){
                System.out.print(maps[i][j]+" ");
            }
            System.out.println();
        }
    }
}
