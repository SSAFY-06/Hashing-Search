/*
문제
0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수

제약조건
numbers의 길이는 1 이상 100,000 이하
numbers의 원소는 0 이상 1,000 이하
=> 자리수: 최대 3자리(1000의 경우 0이 없다면 제일 뒤로)

아이디어
1) 제일 앞 숫자 비교
2) 제일 앞 숫자가 동일한 경우, 다음 숫자 비교
다음 숫자 정렬 방법
- 다음 숫자를 크기 순 정렬
- 숫자의 길이가 다른 경우 이전 수 보다 큰지/작은지를 비교
ex) 
좌측 예시: 34, 3, 30 
새 예시: 334, 333, 332, 34, 33, 32, 3 - 어떻게 할 것인가?
34, 334, [333, 33, 3], 332, 32 (3, 33, 333은 순서 상관 X)
40, 403 -> 40403

생각했던 아이디어
모든 숫자 4자리로 만들기(4자리보다 적으면 0 붙이기), 큰 순서대로 정렬
단, 1000, 100, 10, 1과 같은 경우 자리수가 적은 순으로 정렬

최종 아이디어
문자열로 비교하고, 두 수 중 붙였을 때 더 큰 수 선택하기

런타임에러 - 왜 나는지 아직도 모르겠음
*/
import java.util.*;
class Solution {
    
        public String solution(int[] numbers) { 
        
        String answer = "";
        
        int n = numbers.length;
        String[] numList = new String[n];
        for(int i=0; i<n; i++)
            numList[i] = Integer.toString(numbers[i]);
        
        // Arrays.sort(numList, Collections.reverseOrder());
        Arrays.sort(numList, new Comparator<String>() { 
            public int compare(String o1, String o2) {
                return (o1.concat(o2)).compareTo(o2.concat(o1))*-1;
            }
        });
            
        for(String number : numList) {
            if(answer.equals("0") && number.equals("0")) continue;
            else {
                answer = answer.concat(number);
            }
        }
        
        return answer;
    
    }
    
}