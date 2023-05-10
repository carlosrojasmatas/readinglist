pipeline {

    environment {
        registry = "crojasmatas/readinglist"
        registryCredential = 'docker_id'
        dockerImage = ''
    }
    agent any
    stages{
        stage('Build'){
            steps{
                sh 'make build'
            }
        }

        stage('Build image'){
            steps{
                    input id: 'Build_id', message: 'Enter Build Version', parameters: [string(defaultValue: '1..0', name: 'BUILD_VERSION', trim: true)]
                    dockerImage = docker build + $BUILD_VERSION
            }
        }

        stage('Publish image'){
            steps{
                    dockerImag push
            }
         }

    }
}