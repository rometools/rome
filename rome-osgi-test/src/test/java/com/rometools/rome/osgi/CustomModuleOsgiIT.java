/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.rome.osgi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.rometools.rome.feed.impl.ConfigurableClassLoader;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

@RunWith(PaxExam.class)
public class CustomModuleOsgiIT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Configuration
    public Option[] configuration() throws IOException, URISyntaxException {
        return CoreOptions.options(
            CoreOptions.junitBundles(),
            CoreOptions.wrappedBundle("file:target/lib/jdom2.jar"),
            CoreOptions.bundle("file:target/lib/rome.jar"));
    }

    @Test
    public void testDefaultClassLoader() throws Exception {
        final XmlReader reader = new XmlReader(getClass().getResourceAsStream("custom-module.xml"));
        final SyndFeed feed = new SyndFeedInput().build(reader);

        final CustomModule customModule = (CustomModule) feed.getModule(CustomModule.URI);

        assertNull(customModule);
    }

    @Test
    public void testConfigurableClassLoader() throws Exception {
        ConfigurableClassLoader.INSTANCE.setClassLoader(getClass().getClassLoader());

        expectedException.expect(ExceptionInInitializerError.class);

        final XmlReader reader = new XmlReader(getClass().getResourceAsStream("custom-module.xml"));
        new SyndFeedInput().build(reader);
    }

    @Test
    public void testConfigurableCompositeClassLoader() throws Exception {
        final ClassLoader thisClassLoader = getClass().getClassLoader();
        final ClassLoader romeClassLoader = ConfigurableClassLoader.INSTANCE.getClassLoader();
        ConfigurableClassLoader.INSTANCE.setClassLoader(new CompositeClassLoader(thisClassLoader, romeClassLoader));

        final XmlReader reader = new XmlReader(getClass().getResourceAsStream("custom-module.xml"));
        final SyndFeed feed = new SyndFeedInput().build(reader);

        final CustomModule customModule = (CustomModule) feed.getModule(CustomModule.URI);

        assertNotNull(customModule);
        assertEquals("test-title", customModule.getTitle());
    }

    public static class CompositeClassLoader extends ClassLoader {

        private ClassLoader delegate;

        public CompositeClassLoader(ClassLoader delegate, ClassLoader parent) {
            super(parent);
            this.delegate = delegate;
        }

        @Override
        protected final Class<?> findClass(String name) throws ClassNotFoundException {
            return delegate.loadClass(name);
        }

        @Override
        protected final URL findResource(String name) {
            return delegate.getResource(name);
        }

        @Override
        protected final Enumeration<URL> findResources(String name) throws IOException {
            return delegate.getResources(name);
        }
    }
}
