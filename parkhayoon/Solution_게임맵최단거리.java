/*
C++로 풀어봤던 문제

최단거리로 캐릭터가 목적지까지 가는 경우의 수
갈 수 없으면 -1 출력

제한사항
2차원 배열 maps n x m(1~100 자연수, n=1 and m=1인 경우는 주어지지 않음)
0: 벽, 1: 빈 칸
시작 위치: 좌상단(1,1) -> 도착지(n,m)

아이디어
최단거리 구하기 - BFS
*/
import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        int[][] dist = new int[n][m]; // 해당 위치까지의 최단거리
        int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
        
        // BFS 탐색
        dist[0][0] = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0,0});
        while(!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            for(int d = 0; d<4; d++) {
                int dr = r+deltas[d][0];
                int dc = c+deltas[d][1];
                if(dr<0 || dr>=n || dc<0 || dc>=m || maps[dr][dc]==0 || dist[dr][dc]!=0) continue; // 범위, 벽, 이미 지난 곳 체크
                dist[dr][dc] = dist[r][c]+1; // 현재까지 최단거리 = 이전까지의 최단거리 + 1
                q.add(new int[] {dr, dc});
            }
           if(r==n-1 && c==m-1) break; // 목적지 도착 후 종료
        }
        
        if(dist[n-1][m-1] == 0) // 목적지 도착하지 못한 경우 -1 출력
            answer = -1;
        else
            answer = dist[n-1][m-1];
        
        return answer;
    }
}