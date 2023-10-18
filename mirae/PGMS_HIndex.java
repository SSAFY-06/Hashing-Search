package Programmers;

import java.util.Arrays;

public class PGMS_HIndex {

	public static int solution(int[] citations) {
		Arrays.sort(citations);
        int size = citations.length;
        for(int h=size; h > 0; h--) {
        	int cnt = 0;
        	for(int i=0; i<size; i++) {
        		if(citations[i] >= h) break;
        		cnt++;
        	}
        	if(size-cnt >= h) return h;
        }
        return 0;
    }
	
	public static void main(String[] args) {
		System.out.println(solution(new int[]{3, 0, 6, 1, 5}));
		System.out.println(solution(new int[]{1, 1, 1, 1, 1}));
	}

}
