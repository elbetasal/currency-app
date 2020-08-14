pipeline {
    agent {
      docker {  
        image 'maven:3-alpine'
        args '-v $HOME/.m2:/root/.m2'
      }
    }
    stages {
        stage('Maven build') {
          steps {
            sh 'mvn -Dmaven.test.failure.ignore=true clean install -f back-end/pom.xml'
          }
        }
    }
    post { 
        always { 
            echo 'I will always say Hello againi this is Esau!'
        }
    }
}
