package vc.view.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteComponent {
    private HashMap<String, Executer> executors;

    private Function<String, String[]> inputToGroupArray;

    public ExecuteComponent() {
        executors = new HashMap<>();
        inputToGroupArray = (String input) -> {
            String[] strings = input.split(" ");
            return strings.length == 1 ? strings : Arrays.copyOfRange(strings, 1, strings.length);
        };
    }

    public void put(String regex, Executer executer) {
        executors.put(regex, executer);
    }

    public void findExecutor(String input) {
        for (String commandRegex : executors.keySet()) {
            Matcher matcher = getMatcher(commandRegex, input);
            if(matcher.matches()) {
                executors.get(commandRegex).execute(inputToGroupArray.apply(input));
                return;
            }
        }

        System.out.println("Invalid Command");
    }

    private Matcher getMatcher(String regex, String command) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }

}
