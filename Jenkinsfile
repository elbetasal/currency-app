pipeline {
    agent any
    stages {
        stage('Checkout') {
          steps {
            checkout scm
          }
        }
        stage('Example') {
            steps {
                echo 'Hello World'
            }
        }
    }
    post { 
        always { 
            echo 'I will always say Hello again!'
        }
    }
}
