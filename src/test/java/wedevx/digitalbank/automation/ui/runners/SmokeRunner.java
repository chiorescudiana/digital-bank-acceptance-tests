package wedevx.digitalbank.automation.ui.runners;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key="GLUE_PROPERTY_NAME", value="steps")
@ExcludeTags("IGNORE")
@IncludeTags("smoke")
public class SmokeRunner {
}
