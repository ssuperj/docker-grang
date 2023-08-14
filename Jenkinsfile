pipeline {
    agent { 
        label 'grang'
    }
    tools {
        jdk 'jdk11-agent'
        maven 'maven3'
    }
    environment {
        WORK_SPACE = "/var/lib/jenkins/workspace"
        BASE_URL = "http://localhost"
    }
    stages {
        // stage('Test') {
        //     steps {
        //         script {
        //             try {
        //                 sh 'curl --output /dev/null --silent --head --fail http://localhost:80'
        //                 echo 'Test Running'
        //                 sh 'cd $WORK_SPACE/docker-grang/mygrang && mvn test'
        //                 sh 'cd $WORK_SPACE/docker-grang/chatapp && mvn test'
        //             } catch (Exception e) {
        //                 echo 'I can\'t test because the application is not running'
        //             }
        //         }
        //     }
        // }






        stage('PreBuild') {
            steps {
                sh 'docker compose down --rmi all'
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
                            cd $WORK_SPACE/docker-grang && docker compose up -d
                            until $(curl --output /dev/null --silent --head --fail http://localhost:80); do
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

