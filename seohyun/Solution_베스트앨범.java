/* 베스트앨범
* 장르별 최대재생곡을 2개씩 모아 베스트앨범을 출시한다.
* 노래 = 고유번호로 구분, 수록기준은 다음과같다
* 장르순서 : 속한노래가 많이 재생된 장르를 먼저수록
* 곡순서 : 장르내에서 많이재생된 수, 횟수 같으면 고유번호가 낮은 순서
*
* input : genres[] (노래의장르),plays[] (재생횟수)
* output : 베스트앨범에 들어갈 노래의 고유번호 int[]
* 풀이 : for문돌면서 같은 장르의 인덱스를 저장하는 map을 만들고 (key:장르, value:Genre 객체)
* 주의) 노래를 두개까지만 수록함!
*/
import java.util.*;
class Solution {
    public class Genre{
        public int totalPlays; //해당 장르의 총 재생수
        public Map<Integer, Integer> indexMap; //해당 장르의 인덱스, 재생횟수 맵
        
        public Genre(){
            totalPlays = 0;
            indexMap = new HashMap<>();
        }
        
        public void setField(int plays, int index){
            totalPlays += plays;
            indexMap.put(index, plays);
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> map = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            Genre tmp = map.getOrDefault(genres[i], new Genre());
            tmp.setField(plays[i], i);
            map.put(genres[i], tmp);
        }
        
        //장르별 재생수를 기준으로 내림차순 정렬 (정렬을 위해 list로 변환)
        List<Map.Entry<String, Genre>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue().totalPlays-o1.getValue().totalPlays));
        
        List<Integer> result = new ArrayList<>();
        //장르별로 출력하면서 그 내부의 곡 인덱스를 재생횟수가 큰 순서대로(같다면 i가 작은순) 출력한다.
        for(Map.Entry<String,Genre> g : list){
            List<Map.Entry<Integer,Integer>> indexList = new ArrayList<>(g.getValue().indexMap.entrySet());
            Collections.sort(indexList, (o1,o2)->(o2.getValue()).compareTo(o1.getValue()));
            
            int cnt=0;
            for(Map.Entry<Integer, Integer> me : indexList){
                if(cnt++ == 2) break;
                result.add(me.getKey());
            }
        }
        
        int[] answer = new int[result.size()];
        for(int i=0;i<result.size(); i++) answer[i] = result.get(i);
        
        return answer;
    }
}
