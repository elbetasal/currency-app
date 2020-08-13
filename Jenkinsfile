pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Maven build') {
          steps {
            sh 'mvn -Dmaven.test.failure.ignore=true install'
          }
        }
    }
    post { 
        always { 
            echo 'I will always say Hello again!'
        }
    }
}
