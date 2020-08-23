pipeline {
    environment {
        DOCKER_BUILD = false
        DOCKER_DOCKERFILE_LOCATION = ''
        DOCKER_IMAGE_NAME = ''
    }
    agent {
      docker {  
        image 'maven:3-alpine'
        args '-v $HOME/.m2:/root/.m2'
      }
    }
    stages {
        stage("find-cp_build") {
            steps {
                script {
                    def files = findFiles(glob: "**/cp_build.yml")
                    if(files) {
                        def cpBuild = readYaml file: "${files[0]}"
                        echo(cpBuild)
                        if(cpBuild.docker) {
                            env.DOCKER_BUILD = true
                            env.DOCKERFILE_LOCATION = cp.build.docker.dockerFile
                            env.DOCKER_IMAGE_NAME = cp.build.docker.imageName
                        }
                    }
                }

            }

        }
        stage('Maven build') {
          steps {
            sh 'mvn -Dmaven.test.failure.ignore=true clean install -f back-end/pom.xml'
          }
        }
        stage('Docker build') {
            steps {
//                docker
//                        .build("pleymo/${env.DOCKER_IMAGE_NAME}:${env.BUILD_ID}")
//                        .push()
                echo ("We will build the docker image")
            }
        }
    }
    post { 
        always { 
            echo 'I will always say Hello agai;'
        }
    }
}
