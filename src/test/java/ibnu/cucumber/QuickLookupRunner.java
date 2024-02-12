package ibnu.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"ibnu.stepdefinition"}
        , features = {"src/test/resources/feature/QuickLoockUp.feature"}
        , plugin = "json:target/cucumber-result/json/QuickLoockUp.json"
)

public class QuickLookupRunner {
}
