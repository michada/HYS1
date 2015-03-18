package es.uvigo.esei.daa;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * An abstract test case with spring runner configuration, used by all test cases.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath:spring/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public abstract class AbstractTestCase {
	
}
