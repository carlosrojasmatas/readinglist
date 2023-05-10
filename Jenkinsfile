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
                script {
                        def userInput = input(
                            id: 'userInput',
                            message: 'Please provide input',
                            parameters: [
                                string(name: 'NAME', defaultValue: 'John Doe', description: 'Your name'),
                                choice(name: 'COLOR', choices: ['Red', 'Blue', 'Green'], description: 'Favorite color')
                            ]
                        )
                        echo "User input: ${userInput.NAME}"
                        echo "Favorite color: ${userInput.COLOR}"
                    }
            }
        }

        stage('Publish image'){
            steps{
                  dockerImage push
            }
         }

    }
}