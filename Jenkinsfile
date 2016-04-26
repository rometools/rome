node {

   stage 'Checkout'
   checkout scm

   stage 'Build'
   def mvnHome = tool 'Maven 3.3.9'
   sh "${mvnHome}/bin/mvn clean install"

}
