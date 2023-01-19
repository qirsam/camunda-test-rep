package com.qirsam.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("ВывестиПроцессуальныеПеременныеВКонсоль")
public class PrintProcessVariablesToConsole implements JavaDelegate {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        StringBuilder sb = new StringBuilder();

        sb.append("Доступные процессуальные переменные (начало)");
        sb.append(LINE_SEPARATOR);

        Map<String, Object> variables = execution.getVariables();
        variables.entrySet()
                .stream()
                .forEach(entry -> {
                    sb.append(entry.getKey());
                    sb.append(": ");
                    sb.append(entry.getValue());
                    sb.append(LINE_SEPARATOR);
                });

        sb.append("Доступные процессуальные переменные (конец)");
        sb.append(LINE_SEPARATOR);

        System.out.println(sb);
    }
}
