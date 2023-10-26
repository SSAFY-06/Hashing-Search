/*
문제
H-Index란?
어떤 과학자가 발표한 논문 n편 중, 
h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었을 때 h의 최댓값
어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 
이 과학자의 H-Index를 return

제한 조건
과학자가 발표한 논문의 수는 1편 이상 1,000편 이하
논문별 인용 횟수는 0회 이상 10,000회 이하
*/
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        for(int c : citations) {
            if(c>answer) answer = c; // 최대 논문 인용 횟수 확인
        }
        
        int cnt = 0;
        while(answer>=0) {
            cnt = 0;
            for(int c : citations) {
                if(answer <= c) cnt++; // 논문 인용 횟수가 조건을 만족하는 경우 세기
            }
            if(answer <= cnt) break; // H-index를 만족하면 종료
            answer--; // 논문 인용 횟수를 1씩 감소하면서 확인
        }
        
        return answer;
    }
}