package com.sun.syndication.feed.impl;

/**
 * This class addresses some ClassLoader problems in OSGi environments. If you have ClassLoader
 * problems, simply override the default ClassLoader by calling the
 * {@link #setClassLoader(ClassLoader)} method before calling any ROME code. Unfortunately I don't
 * know whether this works because I have absolutely no experience with OSGi.
 *
 * @author Patrick Gotthard
 *
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
