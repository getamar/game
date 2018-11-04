package com.uk.runcukes;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome=true, strict = true,
        features ="src/test/resources/features",
        glue={"com.uk"},
        plugin={"html:target/cucumber-html-report","json:target/cucumber.json"}
)
public class RunCukes{

}


