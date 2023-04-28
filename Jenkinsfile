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
                kubernetesDeploy(configs: "k8s/kaniko.yaml")
                sh 'export workspace=env.workspace'
                sh 'make buildImage'
                sh 'make pushImage'
            }
        }

//         stage('Deploy'){
//             steps{
//                kubernetesDeploy(configs: "k8s/readinglist.yaml")
//              }
//          }
    }
}