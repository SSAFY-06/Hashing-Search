package Programmers;

import java.util.Arrays;

public class PGMS_가장큰수 {

	public static String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            nums[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(nums, (o1, o2) -> (o1+o2).compareTo(o2+o1) * -1);
                
        if(nums[0].equals("0")) return "0";
        
        String answer = "";        
        for(int i=0; i<nums.length; i++) {
        	answer += nums[i];
        }
        return answer;
    }
	
	public static void main(String[] args) {
		System.out.println(solution(new int[]{6, 10, 2}));
		System.out.println(solution(new int[]{3, 30, 34, 5, 9}));
		System.out.println(solution(new int[]{0, 0, 0}));	
	}

}
