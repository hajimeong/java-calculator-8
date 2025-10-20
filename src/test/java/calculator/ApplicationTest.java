package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 기본_구분자_테스트(){
        assertSimpleTest(()->{
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_테스트(){
        assertSimpleTest(()->{
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 이스케이프_커스텀_구분자_테스트(){
        assertSimpleTest(()->{
            run("//*\\n1*2*3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_기본_구분자_테스트(){
        assertSimpleTest(()->{
            run("//;\\n1;2,3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 빈문자열_입력_테스트(){
        assertSimpleTest(()->{
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 여러_커스텀_구분자_사용_테스트(){
        assertSimpleTest(()->{
            run("//;'\\n1;'2;'3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_형식_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;1;2;3"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("잘못된 커스텀 구분자 정의 형식입니다. (\\n 누락)")
        );
    }


    @Test
    void 커스텀_구분자_공백_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\\n1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("커스텀 구분자가 비어있습니다.")
        );
    }

    @Test
    void 커스텀_구분자_숫자_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//1\\n1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("커스텀 구분자에는 숫자 사용은 불가합니다.")
        );
    }

    @Test
    void 기본_구분자_예외_테스트(){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1@2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("구분자가 아닌 다른 문자가 포함되어 있습니다.")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
