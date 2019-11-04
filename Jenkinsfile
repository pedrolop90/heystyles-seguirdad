pipeline{
    agent any
    stages {
        stage('install') {
            steps {
                withMaven(maven: '3.6.0') {
                    sh 'mvn clean install'
                }
            }
        }
    }
}