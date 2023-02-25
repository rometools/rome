## OSGi

Starting with Rome 1.0 RC2 the jars contain OSGi informations in its manifests.
Note that we have received some reports that Rome plugin classloading may cause 
problems with OSGi. Setting the system property 
```rome.pluginmanager.useloadclass``` to ```true``` may help avoid this.
