## OSGi

ROME contains OSGi informations in its manifests since version 1.0 RC2. Note  
that we have received some reports that Rome plugin classloading may cause 
problems with OSGi. Setting the system property 
```rome.pluginmanager.useloadclass``` to ```true``` may help avoid this.
