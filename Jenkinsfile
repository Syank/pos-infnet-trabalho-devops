pipeline {
    agent any
    
    tools {
        maven "mvn"
    }
    
    environment {
        DOCKERHUB = credentials("dockerhub")
    }

    stages {
        stage('Clone repository') {
            steps {
                git(
                    url: "https://github.com/Syank/pos-infnet-trabalho-devops.git",
                    branch: "main"
                )
                
            }
        }
        stage('Build JAR') {
            steps {
                bat "cd src && cd Skillshare && mvn -DskipTests clean package"
            }
        }
        stage('Move JAR') {
            steps {
                bat "move /Y src\\SkillShare\\target\\SkillShare-0.0.1-SNAPSHOT.jar src\\SkillShare\\SkillShare-0.0.1-SNAPSHOT.jar"
            }
        }
        stage("Build docker image") {
            steps {
                script {
                    def output = bat(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                    
                    CURRENT_SHA_VERSION = output.substring(output.lastIndexOf(" ", output.length())).trim()
                    echo "CURRENT_SHA_VERSION: ${CURRENT_SHA_VERSION}"
                }
                
                echo "CURRENT_SHA_VERSION: ${CURRENT_SHA_VERSION}"
                bat "cd src && cd Skillshare && docker build -t rafael097/infnet-devops-application:$CURRENT_SHA_VERSION ."
                bat "docker tag rafael097/infnet-devops-application:$CURRENT_SHA_VERSION rafael097/infnet-devops-application:latest"
            }
        }
        stage("Publish docker image") {
            steps {
                withCredentials(
                    [
                        usernamePassword(
                            credentialsId: "dockerhub",
                            usernameVariable: "USERNAME",
                            passwordVariable: "PASSWORD")
                    ]) {
                    bat "docker login --username=$USERNAME --password=$PASSWORD"
                    bat "docker push rafael097/infnet-devops-application:$CURRENT_SHA_VERSION"
                    bat "docker push rafael097/infnet-devops-application:latest"
                }
            }
        }
    }
}
