/*
HashMap 사용방법 익히기!!!
*/
import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answerList = new ArrayList<>(); // index를 쉽게 추가하기 위해 ArrayList에 저장
        int n = genres.length; // 노래 개수
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0; i<n; i++) {
            if(!map.containsKey(genres[i])) { // 처음 확인한 음악 장르의 경우 새로 추가
                map.put(genres[i],plays[i]);
            }
            else { // 이미 확인한 음악 장르의 경우 기존 값에 추가
                map.replace(genres[i], map.get(genres[i])+plays[i]);
            }
        }
        
        // 장르당 재생수 내림차순 정렬
        List<String> keySet = new ArrayList<>(map.keySet()); 
        keySet.sort(new Comparator<String>() { // 오름차순 정렬
            public int compare(String o1, String o2) {
                return map.get(o1).compareTo(map.get(o2)); // key값으로 value 받아온 다음 비교
            }
        });
        Collections.reverse(keySet); // 오름차순 정렬 역전 = 내림차순
        
        for(String key : keySet) {
            int firstPlays = -1; // 최대 재생수
            int firstIdx = -1; // 최대 재생수 노래 인덱스
            int secondPlays = -1; // 2번째 많은 재생수
            int secondIdx = -1; // 2번째 많은 재생수 노래 인덱스
            for(int i=0; i<n; i++) {
                if(key.equals(genres[i])) {
                    System.out.println(i);
                    // 동일 장르 중 더 재생수 많거나 재생수 동일하지만 고유 번호가 더 낮은 경우
                    if(firstPlays < plays[i] || (firstPlays == plays[i] && firstIdx > i)) {
                        secondPlays = firstPlays;
                        secondIdx = firstIdx;
                        firstPlays = plays[i];
                        firstIdx = i;
                    }
                    else if(secondPlays < plays[i] || (secondPlays == plays[i] && secondIdx > i)) {
                        secondPlays = plays[i];
                        secondIdx = i;
                    }
                }
            }
            answerList.add(firstIdx);
            if(secondIdx!=-1) // 2번째 많은 노래가 갱신되지 않음 - 노래는 1개 뿐, 하나만 추가
                answerList.add(secondIdx);
        }
        
        int[] answer = new int[answerList.size()];
        for(int i=0; i<answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}