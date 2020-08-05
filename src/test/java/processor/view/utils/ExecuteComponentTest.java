package processor.view.utils;

import org.junit.jupiter.api.Test;
import processor.view.AlphaSearcher;

import java.util.Arrays;
import java.util.List;

public class ExecuteComponentTest {
    public ExecuteComponent executeComponent;

    @Test
    public void executeComponentTest() {
        executeComponent = new ExecuteComponent();
        executeComponent.put("hello", (String... str) -> showTheSymbol());
        executeComponent.findExecutor("hello");
        executeComponent.findExecutor("fjfjfjfjf");
    }

    private void showTheSymbol() {
        System.out.println("Listen And Repeat, Man, Just Repeat It");
        List<String> list = Arrays.asList("**** YOU UnitTest Creator",
                "**** YOU UnitTest Developer",
                "**** YOU UnitTest Idea Bringer",
                "**** YOU UnitTest Tester. TellMe, Huh, TellMe. How The **** Did You Test The ****ing UnitTest????????");
        list.forEach(System.out::println);
        System.out.println("Tomorrow Is Gonna Be Better\n");
    }

}
