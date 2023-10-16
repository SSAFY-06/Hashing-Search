/** 가장 큰 수
* 0 또는 양의정수 주어졌을때 이어붙여서 만들수있는 가장 큰 수 구하기
* input : numbers[] 배열
* output : String
* 풀이 : 숫자가 큰것부터 선택해야됨
* CompareTo를 구현하여 두 숫자를 이어 붙였을때 큰 수가 되는걸 선택하면 된다.
*
* 구현 안하고 사전순으로 만든뒤 삽입정렬하며 예외처리 하려했다가 머리터짐..ㅜ;;
*/
import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        //1. int를 String으로 전환
        String[] arr = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            String str = String.valueOf(numbers[i]);
            arr[i] = str;
        }
        //2. 이어붙였을때 가장 큰 숫자가 앞에오도록 정렬
        Arrays.sort(arr, (o1,o2) -> (o2+o1).compareTo(o1+o2));
        
        //3. 예외처리
        if(arr[0].equals("0")) return "0";
        
        String answer = "";
        for(String str : arr) answer+=str;
        return answer;
    }
}
