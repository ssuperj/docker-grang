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
        stage('Docker') {
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
        stage('Run and Test') {
            steps {
                sh 'cd $WORK_SPACE/docker-grang && docker-compose up -d'
                sh 'sleep 10s'
                sh 'cd $WORK_SPACE/docker-grang/mygrang && mvn test'
                sh 'cd $WORK_SPACE/docker-grang/chatapp && mvn test'
            }
        }
        stage('Stop Running Containers') {
            steps {
                sh 'docker-compose stop'
            }
            when {
                expression { currentBuild.result == null || currentBuild.result == 'FAILURE' }
            }
        }
            // steps {
            //     sh '''
            //         cd $WORK_SPACE/docker-grang && docker-compose up -d
            //         until $(curl --output /dev/null --silent --head --fail http://localhost:8080); do
            //         printf '.'
            //         sleep 5
            //         done
            //         cd $WORK_SPACE/docker-grang/mygrang && mvn test
            //         cd $WORK_SPACE/docker-grang/chatapp && mvn test
            //         cd $WORK_SPACE/docker-grang && docker-compose logs -f
            //     '''
            // }
    }
}

