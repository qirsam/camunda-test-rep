package com.qirsam.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("СпровоцироватьОшибку")
public class ThrowException implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        throw new RuntimeException("Ошибка вышла");
    }
}
