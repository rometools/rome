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
        stage('build') {
            steps {
                sh 'mvn -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true clean verify'
            }
        }
        stage('deploy') {
            when {
                branch 'master'
            }
            steps {
                sh 'mvn deploy'
            }
        }
    }
}