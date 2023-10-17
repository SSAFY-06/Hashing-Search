import java.util.*;

class Solution {
    
    //pq를 사용할 수 있을까?
    static class Music implements Comparable<Music>{
        int play;
        int idx;
        public Music(int play, int idx){
            this.play = play;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Music o){
            return o.play - this.play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answerList = new ArrayList<>();
        
        Map<String, Integer> map = new HashMap<>();
        Map<String, PriorityQueue<Music>> playlist = new HashMap<>();
        
        
        // System.out.println(Arrays.toString(genres));
        // System.out.println(Arrays.toString(plays));
        
        
        
        for(int i=0;i<genres.length;i++){
            //가지고 있으면 넣어주고
            if(!playlist.containsKey(genres[i])){
                playlist.put(genres[i], new PriorityQueue<Music>());
            }
            
            playlist.get(genres[i]).add(new Music(plays[i], i));
            map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);
        }
        
        //keySet을 담는 리스트 선언
        List<String> keySet = new ArrayList<>(map.keySet());
        
        
        //value값으로 정렬
        keySet.sort(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                return map.get(o2).compareTo(map.get(o1));
            }
        });
        
        //출력
        // for(int i=0;i<keySet.size();i++){
        //     System.out.println("key : "+keySet.get(i));
        //     System.out.println("value : "+map.get(keySet.get(i)));
        // }
        
        for(int i=0;i<keySet.size();i++)
        {
            if(playlist.get(keySet.get(i)).size()>=2){
                for(int j=0;j<2;j++){
                    Music m = playlist.get(keySet.get(i)).poll();
                    answerList.add(m.idx);
                }
            }
            else{
                Music m = playlist.get(keySet.get(i)).poll();
                    answerList.add(m.idx);
            }
        }
        
        int[] answer = new int[answerList.size()];
        
        for(int i=0;i<answerList.size();i++){
            answer[i]=answerList.get(i);
        }
        return answer;
    }
}
