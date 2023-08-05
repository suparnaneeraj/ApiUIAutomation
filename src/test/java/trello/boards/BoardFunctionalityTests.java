package trello.boards;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features/boardFeature.feature", glue = "trello.boards", tags = "@Test", plugin = {
		"pretty", "html:target/cucumber/report.html" })

public class BoardFunctionalityTests {

}
