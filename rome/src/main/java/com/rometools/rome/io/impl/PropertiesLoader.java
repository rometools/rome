/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.rometools.rome.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

import com.rometools.rome.feed.impl.ConfigurableClassLoader;

/**
 * Properties loader that aggregates a master properties file and several extra
 * properties files, all from the current classpath.
 * <P>
 * The master properties file has to be in a distinct location than the extra
 * properties files. First the master properties file is loaded, then all the
 * extra properties files in their order of appearance in the classpath.
 * <P>
 * Current use cases (plugin manager for parsers/converters/generators for feeds
 * and modules and date formats) assume properties are list of tokens, that why
 * the only method to get property values is the getTokenizedProperty().
 */
public class PropertiesLoader {

	private static final String MASTER_PLUGIN_FILE = "com/rometools/rome/rome.properties";
	private static final String EXTRA_PLUGIN_FILE = "rome.properties";

	private static Map<ClassLoader, PropertiesLoader> clMap = new WeakHashMap<ClassLoader, PropertiesLoader>();

	/**
	 * Returns the PropertiesLoader singleton used by ROME to load plugin
	 * components.
	 *
	 * @return PropertiesLoader singleton.
	 *
	 */
	public static PropertiesLoader getPropertiesLoader() {
		synchronized (PropertiesLoader.class) {
			final ClassLoader classLoader = ConfigurableClassLoader.INSTANCE.getClassLoader();
			PropertiesLoader loader = clMap.get(classLoader);
			if (loader == null) {
				try {
					loader = new PropertiesLoader(MASTER_PLUGIN_FILE, EXTRA_PLUGIN_FILE);
					clMap.put(classLoader, loader);
				} catch (final IOException ex) {
					throw new RuntimeException(ex);
				}
			}
			return loader;
		}
	}

	private final Properties[] properties;

	/**
	 * Creates a PropertiesLoader.
	 * <p>
	 *
	 * @param masterFileLocation
	 *            master file location, there must be only one.
	 * @param extraFileLocation
	 *            extra file location, there may be many.
	 * @throws IOException
	 *             thrown if one of the properties file could not be read.
	 *
	 */
	private PropertiesLoader(final String masterFileLocation, final String extraFileLocation) throws IOException {
		final List<Properties> propertiesList = new ArrayList<Properties>();
		final ClassLoader classLoader = ConfigurableClassLoader.INSTANCE.getClassLoader();

		try {
			final InputStream is = classLoader.getResourceAsStream(masterFileLocation);
			final Properties p = new Properties();
			p.load(is);
			is.close();
			propertiesList.add(p);
		} catch (final IOException ioex) {
			final IOException ex = new IOException("could not load ROME master plugins file [" + masterFileLocation + "], " + ioex.getMessage());
			ex.setStackTrace(ioex.getStackTrace());
			throw ex;
		}

		final Enumeration<URL> urls = classLoader.getResources(extraFileLocation);
		while (urls.hasMoreElements()) {
			final URL url = urls.nextElement();
			final Properties p = new Properties();
			try {
				final InputStream is = url.openStream();
				p.load(is);
				is.close();
			} catch (final IOException ioex) {
				final IOException ex = new IOException("could not load ROME extensions plugins file [" + url.toString() + "], " + ioex.getMessage());
				ex.setStackTrace(ioex.getStackTrace());
				throw ex;
			}
			propertiesList.add(p);
		}

		properties = new Properties[propertiesList.size()];
		propertiesList.toArray(properties);
	}

	/**
	 * Returns an array of tokenized values stored under a property key in all
	 * properties files. If the master file has this property its tokens will be
	 * the first ones in the array.
	 * <p>
	 *
	 * @param key
	 *            property key to retrieve values
	 * @param separator
	 *            String with all separator characters to tokenize from the
	 *            values in all properties files.
	 * @return all the tokens for the given property key from all the properties
	 *         files.
	 *
	 */
	public String[] getTokenizedProperty(final String key, final String separator) {
		final List<String> entriesList = new ArrayList<String>();
		for (final Properties property : properties) {
			final String values = property.getProperty(key);
			if (values != null) {
				final StringTokenizer st = new StringTokenizer(values, separator);
				while (st.hasMoreTokens()) {
					final String token = st.nextToken();
					entriesList.add(token);
				}
			}
		}
		final String[] entries = new String[entriesList.size()];
		entriesList.toArray(entries);
		return entries;
	}

	/**
	 * Returns an array of values stored under a property key in all properties
	 * files. If the master file has this property it will be the first ones in
	 * the array.
	 * <p>
	 *
	 * @param key
	 *            property key to retrieve values
	 * @return all the values for the given property key from all the properties
	 *         files.
	 *
	 */
	public String[] getProperty(final String key) {
		final List<String> entriesList = new ArrayList<String>();
		for (final Properties property : properties) {
			final String values = property.getProperty(key);
			if (values != null) {
				entriesList.add(values);
			}
		}
		final String[] entries = new String[entriesList.size()];
		entriesList.toArray(entries);
		return entries;
	}

}
