import java.util.*;
/** H-index
* 과학자의 생산성과 영향력을 나타내는 지표이다. 어떤 과학자의 Index인 h를구하려고한다
* -> 발표한 논문 n편중 h번이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었을때 h의 최댓값
* input : 어떤과학자가 발표한 논문의 인용횟수를 담은 배열 citations
* output : H-idex
* 풀이 : citations에 후보숫자인 h이상의 수가 h개 이상 있어야 하는것임
*       미리 정렬을 해두면 모든 숫자를 탐색할 필요까진 없겠지..
        
*/
class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        System.out.println(Arrays.toString(citations));
        int h = 1;
        int max = 0;
        int answer = 0;
        
        while(h<1000){
            //System.out.print(h+"번 이상 인용된 논문을 탐색 >> ");
            int cnt= 0;
            for(int i=0; i<citations.length; i++){
                if(h<=citations[i]) cnt++;
            }
            
            System.out.println(cnt+"개가 탐색됨");
            if(h<=cnt) { //h번 이상 인용된 논문(cnt)이 h개 이상이라면
                max = cnt;
                answer = h;
                //System.out.println("h-index update : "+answer);
            }
            else break;
            h++;            
        }
        
        return answer;
    }
}
