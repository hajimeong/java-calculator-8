package calculator.model;

import calculator.entity.SeparatorEntity;

public class Validator {

    //사용자 입력 형식 검증
    public void validateInput(String userInput){
        if(userInput==null||userInput.isEmpty()) return;

        boolean startWithCustom=userInput.startsWith("//");
        boolean endWithCustom=userInput.contains("\\n");

        // "//"로 문자열이 시작하지만, "\n"가 없는 경우
        if(startWithCustom&&!endWithCustom){
            throw new IllegalArgumentException("잘못된 커스텀 구분자 정의 형식입니다. (\\n 누락)");
        }

        // "//"로 문자열이 시작하지 않는데 "\n"가 있는 경우
        if(!startWithCustom&&endWithCustom){
            throw new IllegalArgumentException("잘못된 문자 입력 형식입니다. (\\n 포함 불가)");
        }

        if(startWithCustom){
            int newLineIndex=userInput.indexOf("\\n");
            String customSeparator=userInput.substring(2,newLineIndex);

            //커스텀 구분자 정의 형식인데 구분자가 비어있을 때
            if(customSeparator.isEmpty()){
                throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
            }

            //커스텀 구분자가 숫자일 때
            for(char c:customSeparator.toCharArray()){
                if(Character.isDigit(c)){
                    throw new IllegalArgumentException("커스텀 구분자에는 숫자 사용은 불가합니다.");
                }
            }
        }
    }

    //entity가 가진 구분자를 기준으로
    //numberPart에 구분자가 아닌 다른 문자가 있는지 검증
    public void validateNotSeparator(SeparatorEntity entity){
        String separator=entity.getSeparator();
        String numberPart=entity.getNumberPart();

        for(char c:numberPart.toCharArray()){
            if(!Character.isDigit(c)&&!separator.contains(String.valueOf(c))){
                throw new IllegalArgumentException("구분자가 아닌 다른 문자가 포함되어 있습니다.");
            }
        }
    }


}
