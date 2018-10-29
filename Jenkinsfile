pipeline {
    agent any
    tools {
        jdk 'Java 8'
        maven 'Maven 3.5.4'
    }
    triggers {
        pollSCM('H/5 * * * *')
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean verify'
            }
        }
    }
}