package wedevx.digitalbank.automation.ui.runners;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ui/features")
@ConfigurationParameter(key="GLUE_PROPERTY_NAME", value="wedevx.digitalbank.automation.ui.steps")
//@ExcludeTags("IGNORE")
@IncludeTags("Test")


public class RegressionRunner {

}
