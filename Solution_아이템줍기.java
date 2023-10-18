/** 아이템 줍기
* 풀이 : 테두리만 숫자로 표현해야함. 즉 각 사각형의 테두리에 1을, 내부엔 2를 넣는다.
* 만약 다른 사각형이 이전의 사각형에 겹치는경우, 즉 맵값이 2인경우엔 그냥 2를 넣어주면됨. 
* 그리고 1따라서 BFS한다(방향이양쪽이므로)..
* 또한 테케(1)의 (3,5)의경우 잘못된 경로로 가는걸 막아주기위해 맵을 두배 확대해서 그린다.(사각형도 같이 확대)
* input : 지형을나타내는 이차원배열 rectangle, 초기 캐릭터의 위치 characterX characterY,
*           아이템의 위치 itemX,itemY가 주어졌을때 아이템을 줍기위해 이동하는 최단거리
*/
import java.util.*;
class Solution {
    //1. 좌표가 1이상 50이하이므로 51x51짜리인데 경로오류를 처리하기위해 101x101 맵을 만들자
    int[][] map = new int[101][101];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        //2. 맵을 채운다.(뒤집어지는거 당연함 좌표는 우상단일수록 크지만 행렬의 행값은 작아지니까)
        //   테두리만 체크하기위해 테두리엔 1을 저장하며, 내부는 2로 채운다. 따라서 겹치는경우 = 맵이 이미 2로 채워짐 => continue한다.
        for(int[] xy : rectangle){
            int startX = xy[0]*2,startY = xy[1]*2, endX = xy[2]*2,endY = xy[3]*2;
            for(int i=startX; i<=endX; i++) { //1-7
                for(int j=startY; j<=endY; j++){ //1-4
                    if(map[j][i]==2) continue;
                    else if(i==startX || i==endX || j==startY || j==endY ) map[j][i] = 1;
                    else map[j][i] = 2;
                }
            }
        }
        // 3. BFS를 통해 최단경로 탐색(길은 하나지만 경로는 좌우 두가지니까)
        int answer = bfs(new int[]{characterY*2,characterX*2}, new int[]{itemY*2,itemX*2})/2;
        return answer;
    }
    
    public int bfs(int[] character, int[] item){
        int result = 0;
        boolean[][] check = new boolean[101][101];
        int[] dx={0,0,1,-1}, dy={1,-1,0,0};
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(character);
        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0){
                int[] curr = q.poll();
                for(int i=0; i<4; i++){
                    int nx = curr[0]+dx[i];
                    int ny = curr[1]+dy[i];
                    if(nx>=0 && ny>=0 && nx<101 && ny<101 && map[nx][ny]==1 && !check[nx][ny]){
                        if(nx==item[0] && ny==item[1]) return result+1; //도착시 바로 리턴
                        check[nx][ny] = true;
                        q.offer(new int[]{nx,ny});
                    }
                }
            }
            result++;
        }
        return result;
    }
}
