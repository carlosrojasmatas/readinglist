pipeline {
    agent any
    stages{
        stage('Build'){
            steps{
                sh 'make echo'
            }
        }

        stage('Test'){
            steps{
                sh 'echo test'
            }
        }

        stage('Deploy'){
            steps{
                sh 'echo Deploy'
            }
        }

    }
}