import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DmnTest3 {
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
// Дружественные страны (начало)
                { "Азербайджан", true},
                { "Алжир", true},
                { "Ангола", true},
                { "Антигуа и Барбуда", true},
                { "Аргентина", true},
                { "Армения", true},
                { "Афганистан", true},
                { "Багамы", true},
                { "Бангладеш", true},
                { "Барбадос", true},
                { "Бахрейн", true},
                { "Белоруссия", true},
                { "Белиз", true},
                { "Бенин", true},{ "Боливия", true},
                { "Босния и Герцеговина", true},
                { "Ботсвана", true},
                { "Бразилия", true},
                { "Бруней", true},
                { "Буркина-Фасо", true},
                { "Бурунди", true},
                { "Бутан", true},
                { "Вануату", true},
                { "Венесуэла", true},
                { "Восточный Тимор", true},
                { "Вьетнам", true},
                { "Габон", true},
                { "Гаити", true},
                { "Гайана", true},
                { "Гамбия", true},
                { "Гана", true},
                { "Гватемала", true},
                { "Гвинея", true},
                { "Гвинея-Биссау", true},
                { "Гондурас", true},
                { "Гренада", true},
                { "Грузия", true},
                { "Джибути", true},
                { "Доминика", true},
                { "Доминикана", true},
                { "Египет", true},
                { "Индия", true},
                { "Индонезия", true},
                { "Иордания", true},
                { "Ирак", true},
                { "Иран", true},
                { "Йемен", true},
                { "Кабо-Верде", true},
                { "Казахстан", true},
                { "Камбоджа", true},
                { "Камерун", true},
                { "Катар", true},
                { "Кения", true},
                { "Киргизия", true},
                { "Кирибати", true},
                { "Китай", true},
                { "Колумбия", true},
                { "Коморы", true},
                { "Конго", true},
                { "ДР Конго", true},
                { "КНДР", true},
                { "Корея", true},
                { "Коста-Рика", true},
                { "Кот-д'Ивуар", true},
                { "Куба", true},
                { "Кувейт", true},
                { "Лаос", true},
                { "Лесото", true},
                { "Либерия", true},
                { "Ливан", true},
                { "Ливия", true},
                { "Маврикий", true},
                { "Мавритания", true},
                { "Мадагаскар", true},
                { "Малави", true},
                { "Малайзия", true},
                { "Мали", true},
                { "Мальдивы", true},
                { "Марокко", true},
                { "Маршалловы Острова", true},
                { "Мексика", true},
                { "Мозамбик", true},
                { "Молдавия", true},
                { "Монголия", true},
                { "Мьянма", true},
                { "Намибия", true},{ "Науру", true},
                { "Непал", true},
                { "Нигер", true},
                { "Нигерия", true},
                { "Никарагуа", true},
                { "ОАЭ", true},
                { "Оман", true},
                { "Пакистан", true},
                { "Палау", true},
                { "Панама", true},
                { "Папуа -- Новая Гвинея", true},
                { "Парагвай", true},
                { "Перу", true},
                { "Руанда", true},
                { "Сальвадор", true},
                { "Самоа", true},
                { "Сан-Томе и Принсипи", true},
                { "Саудовская Аравия", true},
                { "Сейшелы", true},
                { "Сенегал", true},
                { "Сент-Винсент и Гренадины", true},
                { "Сент-Киттс и Невис", true},
                { "Сент-Люсия", true},
                { "Сербия", true},
                { "Сирия", true},
                { "Соломоновы Острова", true},
                { "Сомали", true},
                { "Судан", true},
                { "Суринам", true},
                { "Сьерра-Леоне", true},
                { "Таджикистан", true},
                { "Таиланд", true},
                { "Танзания", true},
                { "Того", true},
                { "Тонга", true},
                { "Тринидад и Тобаго", true},{ "Тувалу", true},
                { "Тунис", true},
                { "Туркмения", true},
                { "Турция", true},
                { "Уганда", true},
                { "Узбекистан", true},
                { "Уругвай", true},
                { "Фиджи", true},
                { "Филиппины", true},
                { "ЦАР", true},
                { "Чад", true},
                { "Черногория", false},
                { "Чили", true},
                { "Шри-Ланка", true},
                { "Эквадор", true},
                { "Экваториальная Гвинея", true},
                { "Эритрея", true},
                { "Эсватини", true},
                { "Эфиопия", true},
                { "ЮАР", true},
                { "Южный Судан", true},
                { "Ямайка", true},
                { "Израиль", true},
                { "Ватикан", true},
                { "Палестина", true},
                { "Абхазия", true},
                { "Южная Осетия", true},
                { "ДНР", true},
                { "ЛНР", true},
// Дружественные страны (конец)
                { null, false },
// Вражеские страны (начало){"Австрия", false},
                { "Бельгия", false },
                { "Болгария", false },
                { "Хорватия", false },
                { "Кипр", false },
                { "Чехия", false },
                { "Дания", false },
                { "Эстония", false },
                { "Финляндия", false },
                { "Франция", false },
                { "Германия", false },
                { "Греция", false },
                { "Венгрия", false },
                { "Ирландия", false },
                { "Италия", false },
                { "Латвия", false },
                { "Литва", false },
                { "Люксембург", false },
                { "Мальта", false },
                { "Нидерланды", false },
                { "Польша", false },
                { "Португалия", false },
                { "Румыния", false },
                { "Словакия", false },
                { "Словения", false },
                { "Испания", false },
                { "Швеция", false },
                { "Австралия", false },
                { "Албания", false },
                { "Андорра", false },
                { "Великобритания", false },
                { "Исландия", false },
                { "Канада", false },
                { "Лихтенштейн", false },
                { "Микронезия", false },
                { "Монако", false },
                { "Новая Зеландия", false },
                { "Норвегия", false },
                { "Республика Корея", false },
                { "Сан-Марино", false },
                { "Северная Македония", false },
                { "Сингапур", false },
                { "Соединенные Штаты Америки", false },
                { "Тайвань", false },
                { "Украина", false },
                { "Швейцария", false },
                { "Япония", false },
// Вражеские страны (конец)
        });
    }
    private final String country;
    private final boolean expectedResult;

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

    public DmnTest3(String country, boolean expectedResult) {
        this.country = country;
        this.expectedResult = expectedResult;
    }

    @Test
    @Deployment(resources = {"friendly_country.dmn"})
    public void givenCountry_whenEvaluatingDmnTable_thenReturnCorrectResult() {
        DecisionService decisionService = processEngineRule.getDecisionService();
        VariableMap variables = Variables.createVariables()
                .putValue("country", country);
        DmnDecisionTableResult actRes = decisionService.evaluateDecisionTableByKey("FriendlyNationDecision", variables);

        assertEquals(1, actRes.size());
        assertEquals(country, expectedResult, actRes.getSingleResult().getEntry("is_friendly"));
    }
}
