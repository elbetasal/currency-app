pipeline {
//    agent {
//      docker {
//        image 'maven:3-alpine'
//        args '-v $HOME/.m2:/root/.m2'
//      }
//    }
    environment {
        dockerImage = ''
    }
    agent any
    stages {
        stage("configuration") {
            steps {
                script {
                    def files = findFiles(glob: "**/cp_build.yml")
                    if (files) {
                        def cpBuild = readYaml file: "${files[0]}"
                        echo(cpBuild.toString())
                        if (cpBuild.build.docker.enable) {
                            env.DOCKER_BUILD = true
                            env.DOCKERFILE_LOCATION = cpBuild.build.docker.dockerFile
                            env.DOCKER_IMAGE_NAME = cpBuild.build.docker.imageName
                        }
                    }
                }

            }

        }
        stage('currency:build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true clean package -f back-end/pom.xml'
            }

        }
        stage('docker:build'){
            steps {
                script {
                    if(env.DOCKER_BUILD) {
                        def shortSha = env.GIT_COMMIT.substring(0, 7)
                        dockerImage = docker.build("pleymo/${env.DOCKER_IMAGE_NAME}:${shortSha}", env.DOCKERFILE_LOCATION)
                    } else {
                        echo 'docker build is disabled for project'
                    }
                }
            }
        }
        stage('Docker build') {
            steps {
                echo("Building docker image with ${env.DOCKER_IMAGE_NAME}")
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-creds') {
                       dockerImage.push()
                    }

                }


            }
        }
    }
    post {
        always {
            echo 'I will always say Hello agai;'
        }
    }
}
