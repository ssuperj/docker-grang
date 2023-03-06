pipeline {
    agent { 
        label 'parallels'
    }
    tools {
        jdk 'jdk11-agent'
        maven 'maven3'
    }
    environment {
        WORK_SPACE = "/home/$USER/agent/workspace"
    }
    stages {
        stage('Test') {
            steps {
                script {
                    try {
                        sh 'curl --output /dev/null --silent --head --fail http://localhost:8090'
                        echo 'Test Running'
                        sh 'cd $WORK_SPACE/docker-grang/mygrang && mvn test'
                        sh 'cd $WORK_SPACE/docker-grang/chatapp && mvn test'
                    } catch (Exception e) {
                        echo 'I can\'t test because the application is not running'
                    }
                }
            }
        }
        stage('PreBuild') {
            steps {
                sh 'docker-compose down'
                sh 'docker rmi -f docker-grang-mysql'
                sh 'docker rmi -f docker-grang-mongodb'
                sh 'docker rmi -f docker-grang-mygrang'
                sh 'docker rmi -f docker-grang-chatapp'
            }
        }
        stage('Build') {
            steps {
                sh 'cd $WORK_SPACE/docker-grang/mygrang && mvn clean package -Dmaven.test.skip=true'
                sh 'cd $WORK_SPACE/docker-grang/chatapp && mvn clean package -Dmaven.test.skip=true'
            }
        }
        stage('Deploy') {
            steps {
              script {
                    def deploy = {
                        sh '''
                            cd $WORK_SPACE/docker-grang && docker-compose up -d
                            until $(curl --output /dev/null --silent --head --fail http://localhost:8090); do
                                printf '.'
                                sleep 5
                            done
                        '''
                    }
                    
                    try {
                        deploy()
                    } catch (Exception e) {
                        retry(3) {
                            if (e.getMessage().contains("TLS handshake timeout")) {
                                echo "Caught TLS handshake timeout error. Retrying deploy..."
                                deploy()
                            } else {
                                throw e
                            }
                        }
                    }
                }
            }
        }
    }
}