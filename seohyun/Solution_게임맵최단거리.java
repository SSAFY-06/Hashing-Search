/** 게임 맵 최단거리
* 각팀이 상대진영에 최대한 빨리 도착해야한다. 5x5크기맵의 1행1열에 위치했으며, 상대는 5행5열에 존재
* 벽은 갈수없음, 사방탐색한다. 만약 도착할 수 없다면 -1을 리턴하자
*
* 제한사항
* maps는 nXm크기이차원배열임 / 0은벽 1은길이다 / 시작점과 도착점은 늘 (0,0)rhk (n-1,m-1)로 고정
* 풀이 : 기본적 bfs탐색문제이다. 레벨탐색으로 구현할까싶네용!
*/
import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int answer = -1;
        
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        
        boolean[][] check = new boolean[maps.length][maps[0].length];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0}); //시작점 셋팅
        check[0][0] = true;
        
        int level = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int s=0; s<size; s++){
                int[] curr = q.poll();
                for(int i=0; i<4; i++){ //사방탐색
                    int nx = curr[0]+dx[i];
                    int ny = curr[1]+dy[i];
                    if(nx>=0 && ny>=0 && nx<maps.length && ny<maps[0].length
                      && maps[nx][ny]==1 && !check[nx][ny]){
                        check[nx][ny]=true;
                        if(check[maps.length-1][maps[0].length-1]) return level+1;
                        q.offer(new int[]{nx,ny});
                    }
                }
            }
            level++;
        }
        
        return answer;
    }
}
