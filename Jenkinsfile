pipeline {
    agent {
      docker {  
        image 'maven:3-alpine'
        label 'mvn3'
      }
    }
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Maven build') {
          steps {
            sh 'mvn -Dmaven.test.failure.ignore=true clean install -f back-end/pom.xml'
          }
        }
    }
    post { 
        always { 
            echo 'I will always say Hello again!'
        }
    }
}
