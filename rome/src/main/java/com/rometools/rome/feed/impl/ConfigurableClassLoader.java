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
package com.rometools.rome.feed.impl;

/**
 * This class addresses some ClassLoader problems in OSGi environments. If you have ClassLoader
 * problems, simply override the default ClassLoader by calling the
 * {@link #setClassLoader(ClassLoader)} method before calling any ROME code. Unfortunately I don't
 * know whether this works because I have absolutely no experience with OSGi.
 */
public enum ConfigurableClassLoader {

    INSTANCE;

    private ClassLoader classLoader;

    /**
     * Get the configured ClassLoader. Returns
     *
     * @return The configured ClassLoader. Returns the ClassLoader of this enum when the ClassLoader
     *         was not overridden.
     */
    public ClassLoader getClassLoader() {
        if (classLoader == null) {
            classLoader = ConfigurableClassLoader.class.getClassLoader();
        }
        return classLoader;
    }

    /**
     * Overrides the default ClassLoader (the ClassLoader of this enum).
     *
     * @param classLoader The ClassLoader to set.
     */
    public void setClassLoader(final ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

}
