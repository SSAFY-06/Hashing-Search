import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        
        String[] arr = new String[numbers.length];
        
        for(int i=0;i<arr.length;i++){
            arr[i]=String.valueOf(numbers[i]);
        }
        
        // System.out.println(Arrays.toString(arr));
        
        Arrays.sort(arr, (o1,o2)->(o2+o1).compareTo(o1+o2));
        
        if(arr[0].equals("0")) return "0";
        System.out.println(Arrays.toString(arr));
        
        for(int i=0;i<arr.length;i++){
            answer.append(arr[i]);
        }
        
        // stringbuilder를 출력하려면 string으로 바꿔줘야 하는데 이때는 sb.toString()을 사용한다
        return answer.toString();
    }
}
