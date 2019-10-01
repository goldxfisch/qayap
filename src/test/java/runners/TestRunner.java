package runners;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stepdefs.MarvelAPIAccessTest;

public class TestRunner {
    private final static Logger log = LoggerFactory.getLogger(TestRunner.class);
    public static void main(String...cmdArgs){
        Result result = JUnitCore.runClasses(MarvelAPIAccessTest.class);

        for ( Failure failure : result.getFailures()){
            log.error(" Test Case Failed [{}]", failure.toString());
        }

        log.info("Result Successful [{}]",result.wasSuccessful());
    }
}
