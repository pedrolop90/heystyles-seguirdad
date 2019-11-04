pipeline {
    agent any

    stages {
        stage ('clean') {
            steps {
                withMaven(maven : 'maven_3.6.0') {
                    sh 'mvn clean'
                }
            }
        }
        stage ('install') {
             steps {
                  withMaven(maven : 'maven_3.6.0') {
                  sh 'mvn install'
               }
           }
        }
    }
}