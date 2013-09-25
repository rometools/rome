# ROME and OSGI


From ROME 1.0 RC2 onwards, the ROME jar includes OSGi information in its manifest.



Note that we have received some reports that ROME plugin classloading may cause problems with OSGi. Setting the system property "rome.pluginmanager.useloadclass" to "true" may help avoid this. See [Issue 118](http://java.net/jira/browse/ROME-118) for further information.



\-\- Main.nicklothian \- 08 Jan 2009

