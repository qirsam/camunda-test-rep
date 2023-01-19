import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class DmnTest1 {

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

    @Test
    @Deployment(resources = {"eu_member.dmn"})
    public void givenCountry_whenEvaluatingDmnTable_thenReturnCorrectResult() {
        DecisionService decisionService = processEngineRule.getDecisionService();
        DmnDecisionTableResult actRes = decisionService.evaluateDecisionTableByKey("EU_Membership", Variables.createVariables()
                .putValue("country", "Германия"));

        Assert.assertEquals(1, actRes.size());
        Assert.assertEquals(Boolean.TRUE, actRes.getSingleResult().getEntry("eu_member"));

    }
}
