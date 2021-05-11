pipeline {
    agent any
    tools {
        jdk 'jdk8'
        maven 'maven_3_6_3'
    }
    environment {
        //once you sign up for Docker hub, use that user_id here
        registry = "qubedprince/dms-demo-service-image"
        //- update your credentials ID after creating credentials for connecting to Docker Hub
        registryCredential = '	dockerHub'
    }
    stages {
        stage ('Version'){
            steps {
                sh 'mvn --version'
            }
        }
        stage ('Dependency'){
            steps {
                sh 'mvn dependency:tree'
            }
        }
        stage ('Verify and LoadTest'){
            steps {
               sh 'mvn clean verify'
            }
         }

        stage ('Build') {
            steps {
                sh 'mvn package'
            }
        }

	    stage('Building image') {
	      steps{
            script {
              dockerImage = docker.build registry
            }
	      }
	    }


	    stage('Upload Image') {
	     steps{    
             script {
                docker.withRegistry( '', registryCredential ) {
                dockerImage.push()
                }
            }
	      }
	    }

		
        stage ('Deploy on Server & Cleanup'){
            steps{
                sh 'ansible-playbook deploy.yml'
                sh "docker rmi ${registry} && mvn clean" 
            }
        }

    }
    post {
        failure {
           telegramSend(message: "*JOB* : *${env.JOB_NAME}*\n*Report* : ${env.BUILD_TAG} \n*Branch*: main \n*Build* : ${env.BUILD_ID} \n*Status*: _FAILED_ \n*Vist* : ${env.BUILD_URL} for more informantion\n", chatId: 717316992 ) 
        }
         success {
            telegramSend(message: "*JOB* : *${env.JOB_NAME}*\n*Report* : ${env.BUILD_TAG} \n*Branch*: main \n*Build* : ${env.BUILD_ID} \n*Status*: _SUCCESS_ \n*Vist* : ${env.BUILD_URL} for more informantion\n", chatId: 717316992)
                  
        }
    }
}

