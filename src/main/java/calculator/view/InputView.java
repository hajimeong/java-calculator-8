package calculator.view;

import camp.nextstep.edu.missionutils.Console;

/***
 * 문자열 입력받는 view
 */
public class InputView {
    public String getInput(){
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        return Console.readLine();
    }
}
