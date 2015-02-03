[33mtag v1.6.1[m
Tagger: unknown <manish.svk@simpro288.metricstream.com>
Date:   Tue Feb 3 12:23:46 2015 +0530

cb tag changes

[33mcommit 4adbd791e39f75f861f3a436e596346507365d74[m
Author: unknown <manish.svk@simpro288.metricstream.com>
Date:   Wed Jan 28 18:06:33 2015 +0530

    Commit after incorporating Norbert's code review changes

[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBEvent.java b/src/main/java/com/rometools/rome/feed/module/CBEvent.java[m
[1mindex 57ee4b5..b82b745 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBEvent.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBEvent.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -21,7 +21,7 @@[m [mimport java.util.List;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Event of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[36m@@ -108,16 +108,16 @@[m [mpublic interface CBEvent extends Cloneable, CopyFrom {[m
      * @return the CB event keyword, <b>null</b> if none.[m
      *[m
      */[m
[31m-    List<String> getKeyword();[m
[32m+[m[32m    List<String> getKeywords();[m
     [m
     /**[m
      * Sets the CB event keyword.[m
      * <p>[m
      *[m
[31m-     * @param strkey the CB event keyword to set, <b>null</b> if none.[m
[32m+[m[32m     * @param strkeyList the CB event keyword to set, <b>null</b> if none.[m
      *[m
      */[m
[31m-    void setKeyword(List<String> strkey);[m
[32m+[m[32m    void setKeywords(List<String> strkeyList);[m
 [m
 	/**[m
      * Returns the CB event resource.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBEventImpl.java b/src/main/java/com/rometools/rome/feed/module/CBEventImpl.java[m
[1mindex fbbab60..837671e 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBEventImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBEventImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -27,7 +27,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Subject of the CB ModuleImpl, default implementation.[m
[32m+[m[32m * Event of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[36m@@ -254,7 +254,7 @@[m [mpublic class CBEventImpl implements Cloneable, Serializable, CBEvent {[m
      *[m
      */[m
 	@Override[m
[31m-    public List<String> getKeyword() {[m
[32m+[m[32m    public List<String> getKeywords() {[m
         if (keyword == null) {[m
             keyword = new ArrayList<String>();[m
         }[m
[36m@@ -269,7 +269,7 @@[m [mpublic class CBEventImpl implements Cloneable, Serializable, CBEvent {[m
      *[m
      */[m
 	@Override[m
[31m-    public void setKeyword(List<String> keyList) {[m
[32m+[m[32m    public void setKeywords(List<String> keyList) {[m
         this.keyword = keyList;[m
     }[m
 [m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBExchangeRate.java b/src/main/java/com/rometools/rome/feed/module/CBExchangeRate.java[m
[1mindex 026b442..51542c5 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBExchangeRate.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBExchangeRate.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -19,7 +19,7 @@[m [mpackage com.rometools.rome.feed.module;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ExchangeRate.[m
[32m+[m[32m * Exchange Rate of the Central Bank ModuleImpl[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBExchangeRateImpl.java b/src/main/java/com/rometools/rome/feed/module/CBExchangeRateImpl.java[m
[1mindex f8d5467..4f1d5f1 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBExchangeRateImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBExchangeRateImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -24,7 +24,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ExchangeRateImpl[m
[32m+[m[32m * Exchange Rate of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBInterestRate.java b/src/main/java/com/rometools/rome/feed/module/CBInterestRate.java[m
[1mindex 05e0fb5..555576d 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBInterestRate.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBInterestRate.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -19,7 +19,7 @@[m [mpackage com.rometools.rome.feed.module;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Interest Rate of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBInterestRateImpl.java b/src/main/java/com/rometools/rome/feed/module/CBInterestRateImpl.java[m
[1mindex 7ab97a8..2af75aa 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBInterestRateImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBInterestRateImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -25,7 +25,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Subject of the CB ModuleImpl, default implementation.[m
[32m+[m[32m * Interest Rate of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBModule.java b/src/main/java/com/rometools/rome/feed/module/CBModule.java[m
[1mindex 76b216c..60f2e29 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBModule.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBModule.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -18,7 +18,7 @@[m [mpackage com.rometools.rome.feed.module;[m
 [m
 [m
 /**[m
[31m- * Dublin Core Module.[m
[32m+[m[32m * Central Bank Module.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[36m@@ -31,7 +31,6 @@[m [mpublic interface CBModule extends Module {[m
      * URI of the RSS CB Module[m
      *[m
      */[m
[31m-    //String URI = "http://www.centralbanks.org/rss/";[m
     String URI = "http://www.cbwiki.net/wiki/index.php/Specification_1.1";[m
 [m
     /**[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBModuleImpl.java b/src/main/java/com/rometools/rome/feed/module/CBModuleImpl.java[m
[1mindex 33ace25..f4d3302 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBModuleImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBModuleImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -27,7 +27,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * CB RSS ModuleImpl, default implementation.[m
[32m+[m[32m * Central Bank RSS ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBNews.java b/src/main/java/com/rometools/rome/feed/module/CBNews.java[m
[1mindex 8b8704c..355e345 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBNews.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBNews.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -21,7 +21,7 @@[m [mimport java.util.List;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * News of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[36m@@ -90,7 +90,7 @@[m [mpublic interface CBNews extends Cloneable, CopyFrom {[m
      * @return the CB news keyword, <b>null</b> if none.[m
      *[m
      */[m
[31m-    List<String> getKeyword();[m
[32m+[m[32m    List<String> getKeywords();[m
     [m
     /**[m
      * Sets the CB news keyword.[m
[36m@@ -99,7 +99,7 @@[m [mpublic interface CBNews extends Cloneable, CopyFrom {[m
      * @param strkey the CB paper news to set, <b>null</b> if none.[m
      *[m
      */[m
[31m-    void setKeyword(List<String> strkey);[m
[32m+[m[32m    void setKeywords(List<String> keyList);[m
 [m
 	/**[m
      * Returns the CB news resource.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBNewsImpl.java b/src/main/java/com/rometools/rome/feed/module/CBNewsImpl.java[m
[1mindex 7b2d423..c3bfacb 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBNewsImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBNewsImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -18,7 +18,6 @@[m [mpackage com.rometools.rome.feed.module;[m
 [m
 import java.io.Serializable;[m
 import java.util.ArrayList;[m
[31m-import java.util.Collections;[m
 import java.util.HashMap;[m
 import java.util.List;[m
 import java.util.Map;[m
[36m@@ -28,7 +27,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Subject of the CB ModuleImpl, default implementation.[m
[32m+[m[32m * News of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[36m@@ -217,7 +216,7 @@[m [mpublic class CBNewsImpl implements Cloneable, Serializable, CBNews {[m
      *[m
      */[m
 	@Override[m
[31m-    public List<String> getKeyword() {[m
[32m+[m[32m    public List<String> getKeywords() {[m
         if (keyword == null) {[m
             keyword = new ArrayList<String>();[m
         }[m
[36m@@ -232,7 +231,7 @@[m [mpublic class CBNewsImpl implements Cloneable, Serializable, CBNews {[m
      *[m
      */[m
 	@Override[m
[31m-    public void setKeyword(List<String> keyList) {[m
[32m+[m[32m    public void setKeywords(List<String> keyList) {[m
         this.keyword = keyList;[m
     }[m
 	[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBObservation.java b/src/main/java/com/rometools/rome/feed/module/CBObservation.java[m
[1mindex 6b55718..6456578 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBObservation.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBObservation.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -19,7 +19,7 @@[m [mpackage com.rometools.rome.feed.module;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Observation of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBObservationImpl.java b/src/main/java/com/rometools/rome/feed/module/CBObservationImpl.java[m
[1mindex 03c959d..db99e77 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBObservationImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBObservationImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -25,7 +25,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Subject of the CB ModuleImpl, default implementation.[m
[32m+[m[32m * Observation of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBObservationPeriod.java b/src/main/java/com/rometools/rome/feed/module/CBObservationPeriod.java[m
[1mindex 5f35b6b..330e9c2 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBObservationPeriod.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBObservationPeriod.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -19,7 +19,7 @@[m [mpackage com.rometools.rome.feed.module;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ObservationPeriod.[m
[32m+[m[32m * Observation Period of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBObservationPeriodImpl.java b/src/main/java/com/rometools/rome/feed/module/CBObservationPeriodImpl.java[m
[1mindex 181da84..2115d86 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBObservationPeriodImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBObservationPeriodImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -25,7 +25,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ObservationPeriod.[m
[32m+[m[32m * Observation Period of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBOtherStatistics.java b/src/main/java/com/rometools/rome/feed/module/CBOtherStatistics.java[m
[1mindex 12f382d..606eb75 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBOtherStatistics.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBOtherStatistics.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -19,7 +19,7 @@[m [mpackage com.rometools.rome.feed.module;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Other Statistics of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBOtherStatisticsImpl.java b/src/main/java/com/rometools/rome/feed/module/CBOtherStatisticsImpl.java[m
[1mindex 561c425..1438b33 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBOtherStatisticsImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBOtherStatisticsImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -25,7 +25,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Subject of the CB ModuleImpl, default implementation.[m
[32m+[m[32m * Other Statistics of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBPaper.java b/src/main/java/com/rometools/rome/feed/module/CBPaper.java[m
[1mindex d621efd..11402d0 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBPaper.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBPaper.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -23,7 +23,7 @@[m [mimport java.util.List;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Paper of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[36m@@ -98,16 +98,16 @@[m [mpublic interface CBPaper extends Cloneable, CopyFrom{[m
  	 *  @param keyword the CB news keyword to set, <b>null</b> if none.[m
      * [m
      */[m
[31m-    public List<String> getKeyword();[m
[32m+[m[32m    public List<String> getKeywords();[m
     [m
     /**[m
      * Sets the CB paper keyword.[m
      * <p>[m
      *[m
[31m-     * @param strkey the CB paper keyword to set, <b>null</b> if none.[m
[32m+[m[32m     * @param keyList the CB paper keyword to set, <b>null</b> if none.[m
      *[m
      */[m
[31m-    public void setKeyword(List<String> strkey);[m
[32m+[m[32m    public void setKeywords(List<String> keyList);[m
 [m
     /**[m
      * Gets the value of the resource property.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBPaperImpl.java b/src/main/java/com/rometools/rome/feed/module/CBPaperImpl.java[m
[1mindex 9df5e50..611b8a3 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBPaperImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBPaperImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -27,7 +27,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Subject of the CBPaperImpl, default implementation.[m
[32m+[m[32m * Paper of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB Paper</a>.[m
[36m@@ -231,7 +231,7 @@[m [mpublic class CBPaperImpl implements Cloneable, Serializable, CBPaper{[m
      *[m
      */[m
 	@Override[m
[31m-    public List<String> getKeyword() {[m
[32m+[m[32m    public List<String> getKeywords() {[m
         if (this.keyword == null) {[m
             this.keyword = new ArrayList<String>();[m
         }[m
[36m@@ -246,7 +246,7 @@[m [mpublic class CBPaperImpl implements Cloneable, Serializable, CBPaper{[m
      *[m
      */[m
 	@Override[m
[31m-    public void setKeyword(List<String> keyList) {[m
[32m+[m[32m    public void setKeywords(List<String> keyList) {[m
         this.keyword = keyList;[m
     }[m
 	[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBPerson.java b/src/main/java/com/rometools/rome/feed/module/CBPerson.java[m
[1mindex fce3278..471c65f 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBPerson.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBPerson.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -19,7 +19,7 @@[m [mpackage com.rometools.rome.feed.module;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Person of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBPersonImpl.java b/src/main/java/com/rometools/rome/feed/module/CBPersonImpl.java[m
[1mindex edc0578..5c943f4 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBPersonImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBPersonImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -17,7 +17,6 @@[m
 package com.rometools.rome.feed.module;[m
 [m
 import java.io.Serializable;[m
[31m-import java.util.Collections;[m
 import java.util.HashMap;[m
 import java.util.Map;[m
 [m
[36m@@ -26,7 +25,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Person of the CB ModuleImpl, default implementation.[m
[32m+[m[32m * Person of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBResource.java b/src/main/java/com/rometools/rome/feed/module/CBResource.java[m
[1mindex 8db217c..441c58f 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBResource.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBResource.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -19,7 +19,7 @@[m [mpackage com.rometools.rome.feed.module;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Resource of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBResourceImpl.java b/src/main/java/com/rometools/rome/feed/module/CBResourceImpl.java[m
[1mindex 4f4a60b..a466e55 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBResourceImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBResourceImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -26,7 +26,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Subject of the CB ModuleImpl, default implementation.[m
[32m+[m[32m * Resource of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBRole.java b/src/main/java/com/rometools/rome/feed/module/CBRole.java[m
[1mindex 368a1cf..2edebf3 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBRole.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBRole.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -19,7 +19,7 @@[m [mpackage com.rometools.rome.feed.module;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Role of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBRoleImpl.java b/src/main/java/com/rometools/rome/feed/module/CBRoleImpl.java[m
[1mindex 40bb2cb..e1b3880 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBRoleImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBRoleImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -26,7 +26,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Role of the CB ModuleImpl, default implementation.[m
[32m+[m[32m * Role of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBSpeech.java b/src/main/java/com/rometools/rome/feed/module/CBSpeech.java[m
[1mindex 3a6454e..a225f4a 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBSpeech.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBSpeech.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -24,7 +24,7 @@[m [mimport com.rometools.rome.feed.CopyFrom;[m
 import com.rometools.rome.io.ISO3166CountyCode;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Speech of the Central Bank SpeechImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[36m@@ -123,16 +123,16 @@[m [mpublic interface CBSpeech extends Cloneable, CopyFrom  {[m
      * [m
      * [m
      */[m
[31m-    public List<String> getKeyword();[m
[32m+[m[32m    public List<String> getKeywords();[m
     [m
     /**[m
      * Sets the CB speech keyword.[m
      * <p>[m
      *[m
[31m-     * @param strkey the CB speech keyword to set, <b>null</b> if none.[m
[32m+[m[32m     * @param keyList the CB speech keyword to set, <b>null</b> if none.[m
      *[m
      */[m
[31m-    public void setKeyword(List<String> strkey);[m
[32m+[m[32m    public void setKeywords(List<String> keyList);[m
     [m
     /**[m
      * Gets the value of the resource property.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBSpeechImpl.java b/src/main/java/com/rometools/rome/feed/module/CBSpeechImpl.java[m
[1mindex 48f4f22..ab6c4d6 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBSpeechImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBSpeechImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -28,7 +28,7 @@[m [mimport com.rometools.rome.feed.impl.ObjectBean;[m
 import com.rometools.rome.io.ISO3166CountyCode;[m
 [m
 /**[m
[31m- * Subject of the CB SpeechImpl, default implementation.[m
[32m+[m[32m * Speech of the Central Bank SpeechImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[36m@@ -269,7 +269,7 @@[m [mpublic class CBSpeechImpl implements Cloneable, Serializable, CBSpeech {[m
      * [m
      */[m
 	@Override[m
[31m-    public List<String> getKeyword() {[m
[32m+[m[32m    public List<String> getKeywords() {[m
         if (keyword == null) {[m
             keyword = new ArrayList<String>();[m
         }[m
[36m@@ -284,7 +284,7 @@[m [mpublic class CBSpeechImpl implements Cloneable, Serializable, CBSpeech {[m
      *[m
      */[m
 	@Override[m
[31m-    public void setKeyword(List<String> keyList) {[m
[32m+[m[32m    public void setKeywords(List<String> keyList) {[m
         this.keyword = keyList;[m
     }[m
 	[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBStatistics.java b/src/main/java/com/rometools/rome/feed/module/CBStatistics.java[m
[1mindex 1abddf2..3810810 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBStatistics.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBStatistics.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -22,7 +22,7 @@[m [mimport com.rometools.rome.feed.CopyFrom;[m
 import com.rometools.rome.io.ISO3166CountyCode;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Statistics of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBStatisticsImpl.java b/src/main/java/com/rometools/rome/feed/module/CBStatisticsImpl.java[m
[1mindex 721d9e5..151c215 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBStatisticsImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBStatisticsImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -26,7 +26,7 @@[m [mimport com.rometools.rome.feed.impl.ObjectBean;[m
 import com.rometools.rome.io.ISO3166CountyCode;[m
 [m
 /**[m
[31m- * Subject of the CBStatisticsImpl, default implementation.[m
[32m+[m[32m * Statistics of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB Statistics</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBTransaction.java b/src/main/java/com/rometools/rome/feed/module/CBTransaction.java[m
[1mindex c34aebb..e10c11e 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBTransaction.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBTransaction.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -19,7 +19,7 @@[m [mpackage com.rometools.rome.feed.module;[m
 import com.rometools.rome.feed.CopyFrom;[m
 [m
 /**[m
[31m- * Subject of the Dublin Core ModuleImpl.[m
[32m+[m[32m * Transaction of the Central Bank ModuleImpl.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/feed/module/CBTransactionImpl.java b/src/main/java/com/rometools/rome/feed/module/CBTransactionImpl.java[m
[1mindex 009fcc3..d36a7b4 100644[m
[1m--- a/src/main/java/com/rometools/rome/feed/module/CBTransactionImpl.java[m
[1m+++ b/src/main/java/com/rometools/rome/feed/module/CBTransactionImpl.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -25,7 +25,7 @@[m [mimport com.rometools.rome.feed.impl.CopyFromHelper;[m
 import com.rometools.rome.feed.impl.ObjectBean;[m
 [m
 /**[m
[31m- * Subject of the CB ModuleImpl, default implementation.[m
[32m+[m[32m * Transaction of the Central Bank ModuleImpl, default implementation.[m
  * <p>[m
  *[m
  * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.[m
[1mdiff --git a/src/main/java/com/rometools/rome/io/impl/CBModuleGenerator.java b/src/main/java/com/rometools/rome/io/impl/CBModuleGenerator.java[m
[1mindex cb395f4..1bbcb42 100644[m
[1m--- a/src/main/java/com/rometools/rome/io/impl/CBModuleGenerator.java[m
[1m+++ b/src/main/java/com/rometools/rome/io/impl/CBModuleGenerator.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -91,25 +91,22 @@[m [mpublic class CBModuleGenerator implements ModuleGenerator {[m
     }[m
 [m
     /**[m
[31m-     * Utility method to generate an element for a subject.[m
[32m+[m[32m     * Utility method to generate an element for a speech.[m
      * <p>[m
      *[m
[31m-     * @param subject the subject to generate an element for.[m
[31m-     * @return the element for the subject.[m
[32m+[m[32m     * @param speech the speech to generate an element for.[m
[32m+[m[32m     * @return the element for the speech.[m
      */[m
[31m-    protected final Element generateSpeechElement(final CBSpeech subject) {[m
[32m+[m[32m    protected final Element generateSpeechElement(final CBSpeech speech) {[m
 [m
         final Element speechElement = new Element("speech", getCBNamespace());[m
[31m-        final String simpleTitle = subject.getSimpleTitle();[m
[32m+[m[32m        final String simpleTitle = speech.getSimpleTitle();[m
 [m
         if (simpleTitle != null) {[m
         	final Element simpleTitleElement = new Element("simpleTitle", getCBNamespace());[m
         	simpleTitleElement.addContent(simpleTitle);[m
         	speechElement.addContent(simpleTitleElement);[m
         }[m
[31m-        System.out.println("-------------------------");[m
[31m-        System.out.println(speechElement);[m
[31m-        System.out.println(speechElement.toString());[m
         return speechElement;[m
     }[m
 [m
[1mdiff --git a/src/main/java/com/rometools/rome/io/impl/CBModuleParser.java b/src/main/java/com/rometools/rome/io/impl/CBModuleParser.java[m
[1mindex e94e6d2..3ff2f3b 100644[m
[1m--- a/src/main/java/com/rometools/rome/io/impl/CBModuleParser.java[m
[1m+++ b/src/main/java/com/rometools/rome/io/impl/CBModuleParser.java[m
[36m@@ -1,5 +1,5 @@[m
 /*[m
[31m- * Copyright 2004 Sun Microsystems, Inc.[m
[32m+[m[32m * Copyright 2015 MetricStream, Inc.[m
  *[m
  * Licensed under the Apache License, Version 2.0 (the "License");[m
  * you may not use this file except in compliance with the License.[m
[36m@@ -408,7 +408,7 @@[m [mpublic class CBModuleParser implements ModuleParser {[m
 		[m
 		final List<Element> keyList = element.getChildren("keyword", getCBNamespace());[m
 		for(Element keyword : keyList){[m
[31m-			speech.getKeyword().add(keyword.getText());[m
[32m+[m			[32mspeech.getKeywords().add(keyword.getText());[m
 		}[m
 		[m
 		final Element resource = element.getChild("resource", getCBNamespace());[m
[36m@@ -482,7 +482,7 @@[m [mpublic class CBModuleParser implements ModuleParser {[m
 [m
 		final List<Element> keyList = element.getChildren("keyword", getCBNamespace());[m
 		for(Element keyword : keyList){[m
[31m-			event.getKeyword().add(keyword.getText());[m
[32m+[m			[32mevent.getKeywords().add(keyword.getText());[m
 		}[m
 [m
 		final Element resource = element.getChild("resource", getCBNamespace());[m
[36m@@ -558,7 +558,7 @@[m [mpublic class CBModuleParser implements ModuleParser {[m
 		for(Element keyword : keyList){[m
 			kList.add(keyword.getText());[m
 		}[m
[31m-		paperObj.setKeyword(kList);[m
[32m+[m		[32mpaperObj.setKeywords(kList);[m
 		[m
 		final Element resource = element.getChild("resource", getCBNamespace());[m
 		if (resource != null) {[m
[36m@@ -715,7 +715,7 @@[m [mpublic class CBModuleParser implements ModuleParser {[m
 [m
 		final List<Element> keyList = element.getChildren("keyword", getCBNamespace());[m
 		for(Element keyword : keyList){[m
[31m-			news.getKeyword().add(keyword.getText());[m
[32m+[m			[32mnews.getKeywords().add(keyword.getText());[m
 		}[m
 [m
 		 final Element resource = element.getChild("resource", getCBNamespace());[m
[1mdiff --git a/src/test/java/com/rometools/rome/unittest/TestSyndFeedRSS10DCCBModules.java b/src/test/java/com/rometools/rome/unittest/TestSyndFeedRSS10DCCBModules.java[m
[1mindex a6acc4f..8e8a5cb 100644[m
[1m--- a/src/test/java/com/rometools/rome/unittest/TestSyndFeedRSS10DCCBModules.java[m
[1m+++ b/src/test/java/com/rometools/rome/unittest/TestSyndFeedRSS10DCCBModules.java[m
[36m@@ -45,14 +45,14 @@[m [mpublic class TestSyndFeedRSS10DCCBModules extends FeedTest {[m
 			assertEquals("2015-01-16T11:00:00Z", paper.getOccurrenceDate());[m
 			assertEquals("Secular stagnation, debt overhang and other rationales for sluggish growth, six years on", paper.getSimpleTitle());[m
 			assertEquals("BIS", paper.getInstitutionAbbrev());[m
[31m-			List<String> keyList = paper.getKeyword();[m
[32m+[m			[32mList<String> keyList = paper.getKeywords();[m
 			assertEquals("3", Integer.toString(keyList.size()));[m
 			for(String key : keyList){[m
 				assertNotNull(key);[m
 			}[m
 			[m
 			//cb:speech[m
[31m-			assertEquals("Benoît Cœuré: Interview in Libération", speech.getSimpleTitle());[m
[32m+[m			[32massertEquals("Sample Speech Simple Title", speech.getSimpleTitle());[m
 			assertEquals("2015-01-19T11:09:00Z", speech.getOccurrenceDate());[m
 			assertEquals("BIS", speech.getInstitutionAbbrev());[m
 			[m
[1mdiff --git a/src/test/resources/rss_1.0_DC_CB.xml b/src/test/resources/rss_1.0_DC_CB.xml[m
[1mindex 9e680c6..fc5b677 100644[m
[1m--- a/src/test/resources/rss_1.0_DC_CB.xml[m
[1m+++ b/src/test/resources/rss_1.0_DC_CB.xml[m
[36m@@ -29,7 +29,7 @@[m
 			<cb:keyword>term premium</cb:keyword>[m
 		</cb:paper>[m
 		<cb:speech>[m
[31m-			<cb:simpleTitle>Benoît Cœuré: Interview in Libération</cb:simpleTitle>[m
[32m+[m			[32m<cb:simpleTitle>Sample Speech Simple Title</cb:simpleTitle>[m
 			<cb:occurrenceDate>2015-01-19T11:09:00Z</cb:occurrenceDate>[m
 			<cb:institutionAbbrev>BIS</cb:institutionAbbrev>[m
 		</cb:speech>[m
