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

            withKubeConfig([credentialsId: 'k8s']) {
                 sh 'kubectl apply -f k8s/kaniko.yaml'
            }
        }

//         stage('Deploy'){
//             steps{
//                kubernetesDeploy(configs: "k8s/readinglist.yaml")
//              }
//          }
    }
}