/*
 * ModuleGeneratorTest.java
 * JUnit based test
 *
 * Created on April 29, 2006, 9:34 PM
 */

package org.rometools.feed.module.sle.io;

import junit.framework.*;
import com.sun.syndication.feed.module.Module;
import org.rometools.feed.module.sle.SimpleListExtension;
import org.rometools.feed.module.sle.types.Group;
import org.rometools.feed.module.sle.types.Sort;
import java.util.HashSet;
import java.util.Set;
import org.jdom.Element;
import org.jdom.Namespace;

/**
 *
 * @author cooper
 */
public class ModuleGeneratorTest extends TestCase {
    
    public ModuleGeneratorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ModuleGeneratorTest.class);
        
        return suite;
    }

    /**
     * Test of generate method, of class com.sun.syndication.feed.module.sle.io.ModuleGenerator.
     */
    public void testGenerate() {
        // TODO add your test code.
    }

    
    
}
