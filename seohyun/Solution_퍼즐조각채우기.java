/* 퍼즐조각 채우기
* table에있는 조각을 game_board에 채운다.
* 조각은 회전시킬수 있으나 뒤집을순없다. 또한 채워넣었을때 해당 퍼즐조각의 인접칸이 비어있으면 안됨
* 최대한 많은 퍼즐조각을 채울때 총 몇칸을 채울 수 있는지 찾아 리턴해라
* input : 게임보드의 상태 game_board[][], 퍼즐조각의 상태 table[][]
* 0은빈칸, 1은 채워진칸이다. 두 인풋은 크기가 같은 정사각형 격자이다.
* output : 채울수있는 최대 칸수
*
* 풀이 : 인접칸이 비지않도록 채우는거니까 어떤 빈곳을 채울수있는 블록모양은 단 한개일거임
* game_board의 빈칸모양을 찾아서 그와 같은 모양 블록이 있는지 체크하면 될것같다
* 즉 dfs를 통해 빈칸 하나의 위치값을 구한다.
* 그리고 기준점을 만들어줄건데, 오름차순정렬했을때 맨 처음값을 기준점으로 할거임
* 따라서 기준점을 기준으로 상대좌표를 저장하는 벡터집합을 만들어준다. 이렇게 모든 빈칸에 대한 정보를저장한다
* 그리고 퍼즐조각의 위치값도 구해서 벡터집합 만들고 비교해주면 될듯,,,
* 회전에 대한연산이 필요하므로 돌려서도 비교해준다.
*/
import java.util.*;
class Solution {
    public List<ArrayList<int[]>> blank_list = new ArrayList<>();
    public List<ArrayList<int[]>> puzzle_list = new ArrayList<>();
    
    public List<ArrayList<int[]>> puzzle90_list = new ArrayList<>();
    public List<ArrayList<int[]>> puzzle180_list = new ArrayList<>();
    public List<ArrayList<int[]>> puzzle270_list = new ArrayList<>();
    
    public ArrayList<int[]> tmpList;
    public int[][] gameMap, tableMap;
    public boolean[][] check;
    public int n,m;
    
    public int solution(int[][] game_board, int[][] table) {
        gameMap = game_board;
        tableMap = table;
        n = game_board.length;
        m = game_board[0].length;
        
        //1. dfs 연산을 통해서 각 빈칸의 벡터를 구해줄것임
        //1-1. gameMap에 대한 dfs
        check = new boolean[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!check[i][j] && gameMap[i][j]==0) {
                    tmpList = new ArrayList<>();
                    tmpList.add(new int[]{i,j});
                    check[i][j] = true;
                    dfs(i,j,gameMap,0);
                    blank_list.add(tmpList);
                }
            }
        }
        //1-2. tableMap에 대한 dfs
        check = new boolean[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!check[i][j] && tableMap[i][j]==1) {
                    tmpList = new ArrayList<>();
                    tmpList.add(new int[]{i,j});
                    check[i][j] = true;
                    dfs(i,j,tableMap,1);
                    puzzle_list.add(tmpList);
                }
            }
        }
        
        //2. 세 전체list의 각 블록list를 오름차순 정렬한 뒤 맨 첫번째 원소를 기준으로 상대좌표집합 생성
        listToVectorArr(blank_list);
        listToVectorArr(puzzle_list);
        
        
        //3. 비교해서 맞는거 찾기.. 기준점 제외 나머지가 같으면됨
        int answer = getAnswer(puzzle_list);
        
        //4. 90도, 180도, 270도 회전해서도 찾아준다.
        turnPuzzleList(puzzle90_list);
        answer += getAnswer(puzzle90_list);
        turnPuzzleList(puzzle180_list);
        answer += getAnswer(puzzle180_list);
        turnPuzzleList(puzzle270_list);
        answer += getAnswer(puzzle270_list);
        
        return answer;
    }
    
    public int[] dx = {0,0,1,-1};
    public int[] dy = {1,-1,0,0};
    
    //tmpList에 좌표값을 누적하는 메소드
    public void dfs(int x,int y, int[][] map, int std){
        for(int i=0; i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx>=0 && ny>=0 && nx<n && ny<m 
               && map[nx][ny]==std && !check[nx][ny]){
                tmpList.add(new int[]{nx,ny});
                check[nx][ny] = true;
                dfs(nx,ny,map,std);
            }
        }
    }
    
    public void printList(List<ArrayList<int[]>> list){
        for(List<int[]> li : list) {
            for(int[] arr : li) System.out.print(Arrays.toString(arr)+ ", ");
            System.out.println("");
        }
        System.out.println();
    }
    

    public void listToVectorArr(List<ArrayList<int[]>> plist){
        for(List<int[]> li : plist) {
            Collections.sort(li, (o1,o2) -> {
                if(o1[0]==o2[0]) return Integer.compare(o1[1],o2[1]);  
                else return Integer.compare(o1[0],o2[0]);
            });
            for(int i=li.size()-1; i>0; i--){
                int[] curr = li.get(i);
                int[] std = li.get(0);
                int[] result = new int[]{curr[0]-std[0],curr[1]-std[1]};
                li.set(i,result);
            }
        }

    }
    
    //남은 퍼즐리스트의 상대좌표를 절대좌표로 다시 바꿔서 퍼즐테이블을 만든뒤, 회전하고 상대좌표로 변환
    //즉 회전한 퍼즐의 상대좌표 리스트를 반환한다.
    public void turnPuzzleList(List<ArrayList<int[]>> pt_list){
        //(1)절대좌표로 변환
        int[][] tmpPuzzleMap = new int[n][n];
        for(ArrayList<int[]> target : puzzle_list){
            int[] std = target.get(0);
            tmpPuzzleMap[std[0]][std[1]] = 1;
            for(int i=1; i<target.size(); i++){
                int[] curr = target.get(i);
                tmpPuzzleMap[std[0]+curr[0]][std[1]+curr[1]] = 1;
            }
        }
        //(2)90도 회전 => 0행은 n-1열이됨 => (0,0)(0,1)(0,2)이 (0,2)(1,2)(2,2)가 됨
        int[][] turnPuzzleMap = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                turnPuzzleMap[i][j] = tmpPuzzleMap[j][(n-1)-i];
            }
        }
        //(3)dfs로 회전한 퍼즐맵에 대한 좌표값을 리스트화
        check = new boolean[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!check[i][j] && turnPuzzleMap[i][j]==1) {
                    tmpList = new ArrayList<>();
                    tmpList.add(new int[]{i,j});
                    check[i][j] = true;
                    dfs(i,j,turnPuzzleMap,1);
                    pt_list.add(tmpList);
                }
            }
        }
        //(4)회전한 퍼즐맵을 상대좌표화 시킨다
        listToVectorArr(pt_list);
    }
    
    public int getAnswer(List<ArrayList<int[]>> p_list){
        int answer = 0;
        for(int i=blank_list.size()-1; i>=0; i--){
            ArrayList<int[]> std = blank_list.get(i);
            for(int j=0; j<p_list.size(); j++){
                ArrayList<int[]> target = p_list.get(j);
                
                if(std.size() == target.size()){
                     boolean flag = true;
                     for(int k=1; k<std.size(); k++) {
                         if( (std.get(k))[0] != (target.get(k))[0]
                            || (std.get(k))[1] != (target.get(k))[1]) {
                             flag = false;
                             break;
                         }
                     }
                    if(flag) {
                        answer+=target.size();
                        p_list.remove(j);
                        blank_list.remove(i);
                        break; //선택완료했으면 더이상 i번째 빈칸을 채울필요없음
                    }
                }
            }
        }
        puzzle_list = p_list; //기준점이 절대좌표인 상대좌표 퍼즐리스트를 업데이트
        
        return answer;
    }
}
