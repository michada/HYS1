package es.uvigo.esei.daa.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.uvigo.esei.daa.web.AllEventsWebTest;
import es.uvigo.esei.daa.web.PublicEventsWebTest;

@RunWith(value=Suite.class)
@SuiteClasses({ 
	AllEventsWebTest.class,
	PublicEventsWebTest.class
})
public class AcceptanceTestSuite {

}