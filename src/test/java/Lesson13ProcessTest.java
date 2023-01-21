import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.community.mockito.CamundaMockito;
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

        registerJavaDelegateMock("SomeInternalServiceTask");
        registerJavaDelegateMock("ProcessorError");

        ProcessInstance pi = runtimeService.startProcessInstanceByKey("Lesson13_Process");

        assertThat(pi).isStarted();

        assertThat(pi).isWaitingAt("call_internal_service_task");
        execute(job());
        verifyJavaDelegateMock("SomeInternalServiceTask").executed(Mockito.times(1));

        assertThat(pi).isWaitingAt("call_external_service_task");
        execute(job());
        complete(externalTask("call_external_service_task"));

        assertThat(pi).isWaitingAt("some_message_received_from_external_system");
        execute(job());

        processEngineRule.getRuntimeService()
                .createMessageCorrelation("some_message")
                .processInstanceId(pi.getProcessInstanceId())
                .setVariable("SUCCESS", Boolean.FALSE)
                .correlate();

        assertThat(pi).isWaitingAt("gateway_open");
        execute(job());

        assertThat(pi).isWaitingAt("process_error");
        execute(job());
        verifyJavaDelegateMock("ProcessError").executed(Mockito.times(1));

        assertThat(pi).isWaitingAt("gateway_close");
        execute(job());

        assertThat(pi).isEnded();
    }
}
