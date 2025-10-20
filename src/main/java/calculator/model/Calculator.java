package calculator.model;

import java.util.List;

/***
 * 추출된 정수 리스트의 합 구하기
 */
public class Calculator {
    public Integer calculate(List<Integer> numbers) {
        int result=0;
        for(Integer number : numbers) {
            result+=number;
        }
        return result;
    }
}
