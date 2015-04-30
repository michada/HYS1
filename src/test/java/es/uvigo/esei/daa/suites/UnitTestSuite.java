package es.uvigo.esei.daa.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.uvigo.esei.daa.dao.EventDAOTest;
import es.uvigo.esei.daa.facade.PublicFacadeTest;
import es.uvigo.esei.daa.rest.EventTest;

@RunWith(value=Suite.class)
@SuiteClasses({ 
	EventTest.class,
	PublicFacadeTest.class,
	EventDAOTest.class
})
public class UnitTestSuite {

}