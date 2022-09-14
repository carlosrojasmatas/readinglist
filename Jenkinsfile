pipeline {
    agent any
    stages{
        stage('Build'){
            steps{
                sh 'make build'
            }
        }

        stage('Test'){
            steps{
                sh 'make test'
            }
        }

        stage('Build image'){
            steps{
                sh 'make buildImage'
                sh 'make pushImage'
            }
        }

        stage('Deploy'){
            steps{
               withKubeConfig([credentialsId: 'kind-kind', serverUrl: 'http://localhost']) {
                  sh 'kubectl apply -f k8s/readinglist.yaml'
                }
             }
         }
    }
}