pipeline {
    agent any

    stages {
        stage ('install') {
            steps {
                withMaven(maven : 'maven_3.6.0') {
                    sh 'mvn clean install'
                }
            }
        }
    }
}