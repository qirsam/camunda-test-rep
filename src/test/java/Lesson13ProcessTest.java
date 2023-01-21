import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.community.mockito.CamundaMockito.registerJavaDelegateMock;
import static org.camunda.community.mockito.CamundaMockito.verifyJavaDelegateMock;

public class Lesson13ProcessTest {

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

    @Test
    @Deployment(resources = {"lesson13my.bpmn"})
    public void givenError_whenRunningLesson13Bpmn_thenProcessError(){
        RuntimeService runtimeService = processEngineRule.getRuntimeService();
        //Регистрируем заглушки для внутренних служебных задач "Вызвать
        //внутреннюю служебную задачу" и "Обработать ошибку":
        registerJavaDelegateMock("SomeInternalServiceTask");
        registerJavaDelegateMock("ProcessorError");
        //запускаем наш процесс с идентификатором Lesson13_Process
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("Lesson13_Process");
        //проверяем, что процесс действительно был запущен:
        assertThat(pi).isStarted();
        //проверяем, что токен находится на активности "Вызвать внутреннюю
        //служебную задачу":
        assertThat(pi).isWaitingAt("call_internal_service_task");
        //имитируем выполнение этой активности:
        execute(job());
        //В активности "Вызвать внутреннюю служебную задачу" должен выполнится делегат
        //#{SomeInternalServiceTask}. Чтобы проверить, произошло это или нет, мы
        //проверяем, была ли вызвана заглушка, которую мы создали ранее:
        verifyJavaDelegateMock("SomeInternalServiceTask").executed(Mockito.times(1));
        //проверяем, что токен находится на активности Вызвать внешнюю служебную
        //задачу:
        assertThat(pi).isWaitingAt("call_external_service_task");
        //имитируем завершение этой задачи
        execute(job());
        complete(externalTask("call_external_service_task"));
        //После выполнения внешней служебной задачи мы ожидаем, что процесс будет ждать
        //получения сообщения:
        assertThat(pi).isWaitingAt("some_message_received_from_external_system");
        //имитируем отправку сообщения сторонней системой:
        execute(job());
        processEngineRule.getRuntimeService()
                .createMessageCorrelation("some_message")
                .processInstanceId(pi.getProcessInstanceId())
                .setVariable("SUCCESS", true)
                .correlate();
        //После этого токен должен перейти на открывающий шлюз:
        assertThat(pi).isWaitingAt("gateway_open");
        execute(job());
        //Так как процессуальная переменная SUCCESS равна true, активность Обработать
        //ошибку не должна выполниться. Мы ожидаем, что после открывающего шлюза,
        //выполнение перейдет к шлюзу закрывающему:
        assertThat(pi).isWaitingAt("gateway_close");
        execute(job());
        //На этом процесс должен завершиться:
        assertThat(pi).isEnded();
    }
}
