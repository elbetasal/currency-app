pipeline {
    def rtMaven = Artifactory.newMavenBuild()
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Maven build') {
          steps {
            rtMaven.run pom: 'back-end/pom.xml', goals: 'clean install'
          }
        }
    }
    post { 
        always { 
            echo 'I will always say Hello again!'
        }
    }
}
