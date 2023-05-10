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
                script{
                    def version = input(id: 'Build_id',message: 'Enter Build Version',parameters: [string(defaultValue: '1.0', name: 'NUMBER', trim: true)])
//                     dockerImage = docker.build registry + "${version.NUMBER}"
                    echo "Input: ${version.NUMBER}"
                }
            }
        }

//         stage('Publish image'){
//             steps{
//                   dockerImage push
//             }
//          }

    }
}