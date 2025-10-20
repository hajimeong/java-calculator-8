package calculator.model;

import calculator.entity.SeparatorEntity;

import java.util.Arrays;
import java.util.List;

public class Separator {
    private static final String DEFAULT_SEPARATOR=",|:";

    public SeparatorEntity createSeparatorEntity(String userInput) {
        if(userInput==null||userInput.isEmpty()){
            return new SeparatorEntity(DEFAULT_SEPARATOR,"0");
        }

        String separator=DEFAULT_SEPARATOR;
        String numberPart=userInput;

        if(userInput.startsWith("//")){
            int newLineIndex=userInput.indexOf("\\n");

            String customSeparator=userInput.substring(2, newLineIndex);
            //구분자 이스케이프 처리 후 저장
            customSeparator=escapeRegex(customSeparator);
            numberPart=userInput.substring(newLineIndex+2);
            separator=separator+"|"+customSeparator;
        }

        return new SeparatorEntity(separator,numberPart);
    }

    public List<String> split(SeparatorEntity separatorEntity) {
        String numberPart=separatorEntity.getNumberPart();
        String separator=separatorEntity.getSeparator();

        if(numberPart==null||numberPart.isEmpty()){
            return List.of("0");
        }

        return Arrays.asList(numberPart.split(separator));
    }

    //이스케이프 처리
    private String escapeRegex(String separator){
        return separator.replaceAll("([\\\\.^$|?*)+()\\[\\]{}])","\\\\$1");
    }
}
