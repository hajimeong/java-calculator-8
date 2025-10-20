package calculator.controller;

import calculator.entity.SeparatorEntity;
import calculator.model.Calculator;
import calculator.model.ParseToInteger;
import calculator.model.Separator;
import calculator.model.Validator;
import calculator.view.InputView;
import calculator.view.OutputView;

import java.util.List;

public class CalculatorController {
    private final InputView inputView=new InputView();
    private final OutputView outputView=new OutputView();
    private final Calculator calculator=new Calculator();
    private final ParseToInteger parsing =new ParseToInteger();
    private final Separator separator=new Separator();
    private final Validator validator=new Validator();

    public void run(){
        //1. 문자열 입력
        String userInput= inputView.getInput();
        
        //2. 문자열 형식 검증
        validator.validateInput(userInput);

        //3. 구분자 및 메인 문자열 추출
        SeparatorEntity entity=separator.createSeparatorEntity(userInput);

        //4. entity 기반 구분자 검증
        validator.validateNotSeparator(entity);

        //5. 구분자 기준 문자열 분리 및 문자열 정수 변환
        List<String> splitList = separator.split(entity);
        List<Integer> integerList = parsing.parsing(splitList);

        //6. 계산 수행
        validator.validateNegativeInteger(integerList);
        Integer result = calculator.calculate(integerList);

        //7. 결과 출력
        outputView.getResult(result);
    }

}
