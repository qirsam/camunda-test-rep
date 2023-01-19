import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DmnTest2 {

    @Parameters
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][] {
                {"Россия", false},
                {"Украина", false},
                {"Франция", true},
                {"Испания", true},
                {"Швеция", true},
                {"Норвегия", false},
                {"Казахстан", false},
                {"Германия", true},
                {"Финляндия", true},
                {"Польша", true},
                {"Италия", true},
                {"Великобритания", false},
                {"Румыния", true},
                {"Белоруссия", false},
                {"Греция", true},
                {"Болгария", true},
                {"Исландия", false},
                {"Венгрия", true},
                {"Португалия", true},
                {"Сербия", false},
                {"Австрия", true},
                {"Чехия", true},
                {"Ирландия", true},
                {"Литва", true},
                {"Латвия", true},
                {"Хорватия", true},
                {"Босния и, true Герцеговина", false},
                {"Словакия", true},
                {"Эстония", true},
                {"Дания", true},
                {"Нидерланды", true},
                {"Швейцария", false},
                {"Молдавия", false},
                {"Бельгия", true},
                {"Албания", false},
                {"Северная Македония", false},
                {"Турция", false},
                {"Словения", true},
                {"Черногория", false},
                {"Люксембург", true},
                {"Андорра", false},
                {"Мальта", true},
                {"Лихтенштейн", false},
                {"Сан-, trueМарино", false},
                {"Монако", false},
                {"Ватикан", false},
                {"Страна 404", false},
                {"Мадагаскар", false},
                {null, false}
        });
    }

    private final String country;
    private final boolean expectedResult;

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

    public DmnTest2(final String country, final boolean expectedResult) {
        this.country = country;
        this.expectedResult = expectedResult;
    }

    @Test
    @Deployment(resources = {"eu_member.dmn"})
    public void givenCountry_whenEvaluatingDmnTable_thenReturnCorrectResult(){
        DecisionService decisionService = processEngineRule.getDecisionService();
        DmnDecisionTableResult actRes = decisionService.evaluateDecisionTableByKey("EU_Membership", Variables.createVariables()
                .putValue("country", country));

        assertEquals(1, actRes.size());
        assertEquals(expectedResult, actRes.getSingleResult().getEntry("eu_member"));
    }

}
