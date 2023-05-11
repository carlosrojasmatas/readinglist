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
                sh './gradlew clean bootJar'
            }
        }

        stage('Build image'){
            steps{
                script{
                    def version = input(
                        id: 'Build_id',
                        message: 'Enter Build Version',
                        parameters: [
                            string(defaultValue: '1.0', name: 'NUMBER', trim: true)
                        ]
                    )
                    dockerImage = docker.build(registry + ":${version}")
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