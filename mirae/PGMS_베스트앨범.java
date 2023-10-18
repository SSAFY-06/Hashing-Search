package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class PGMS_베스트앨범 {
	
	static class Album implements Comparable<Album>{
		int num, play;
		public Album(int num, int play) {
			this.num = num;
			this.play = play;
		}
		@Override
		public int compareTo(Album o) {
			if(o.play == this.play) return this.num - o.num;
			return o.play - this.play;
		}
	}

	public static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreList = new HashMap<String, Integer>();
        Map<String, PriorityQueue<Album>> playList = new HashMap<String, PriorityQueue<Album>>();
        for(int i=0; i<genres.length; i++) {
        	if(!genreList.containsKey(genres[i])) {
        		genreList.put(genres[i], plays[i]);
        		playList.put(genres[i], new PriorityQueue<Album>());
        		playList.get(genres[i]).add(new Album(i, plays[i]));
        	} else {
            	genreList.put(genres[i], genreList.get(genres[i])+plays[i]);
            	playList.get(genres[i]).add(new Album(i, plays[i]));
        	}
        }
        
        List<String> keyset = new ArrayList<>(genreList.keySet());
        keyset.sort((o1, o2) -> genreList.get(o2).compareTo(genreList.get(o1)));
        
        List<Integer> result = new ArrayList<Integer>();
        for(String key: keyset) {
        	int cnt = 0;
        	while(cnt++ < 2 && !playList.get(key).isEmpty()) {
        		result.add(playList.get(key).poll().num);
        	}
        }
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++) {
        	answer[i] = result.get(i);
        }
        return answer;
    }
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[] {500, 600, 150, 800, 2500})));
	}

}
