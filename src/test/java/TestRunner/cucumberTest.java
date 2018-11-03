package TestRunner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="features",
        plugin={"pretty","html:src/main/java/reportFolder"},
        monochrome = true
)
public class cucumberTest {
}
