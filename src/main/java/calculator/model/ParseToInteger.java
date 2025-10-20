package calculator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 구분자를 기준으로 분리된 문자열 리스트를 정수 리스트로 변환
 */
public class ParseToInteger {
    public List<Integer> parsing(List<String> splitString){
        List<Integer> parsingToInt = new ArrayList<>();

        for(String split : splitString){
            if(split==null) continue;
            int num=Integer.parseInt(split);

            parsingToInt.add(num);
        }
        return parsingToInt;
    }
}
