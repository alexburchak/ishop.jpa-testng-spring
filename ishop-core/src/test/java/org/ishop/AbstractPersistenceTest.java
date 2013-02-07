package org.ishop;

import org.testng.annotations.BeforeClass;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.naming.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Base class for all persistence-based tests
 *
 * @author Alexander Burchak
 */
@ContextConfiguration(locations = {"classpath:/test-applicationContext.xml"})
public abstract class AbstractPersistenceTest extends AbstractTestNGSpringContextTests {
    @BeforeClass(groups = "database")
    public final void setUpDatabase() throws NamingException, IOException {
        Properties props = new Properties();
        props.load(AbstractPersistenceTest.class.getResourceAsStream("/persistence.properties"));
        new InitialContext(props);
    }

    @BeforeClass(dependsOnMethods = "setUpDatabase")
    protected void springTestContextPrepareTestInstance() throws Exception {
        super.springTestContextPrepareTestInstance();
    }
}
