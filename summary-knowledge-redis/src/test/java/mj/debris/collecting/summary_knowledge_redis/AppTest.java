package mj.debris.collecting.summary_knowledge_redis;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    @SuppressWarnings("unused")
	public void testApp()
    {
    	Set<?> set  = new HashSet<Object>();
    	ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        assertTrue( true );
    }
}
