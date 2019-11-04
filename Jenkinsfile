pipeline {
    agent any

    stages {
         stage ('install core') {
              steps {
                  sh 'cd heystyles-seguridad-api'
                  sh 'mvn install'
             }
         }
        stage ('install api') {
            steps {
                 sh 'cd ..'
                 sh 'mvn install'
            }
        }
        stage ('install client') {
              steps {
                 sh 'cd heystyles-seguridad-core'
                 sh 'mvn install'
             }
        }
    }
}