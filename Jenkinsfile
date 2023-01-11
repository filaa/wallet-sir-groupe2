pipeline{
    agent any

    stages{
            stage('Source'){
            steps{
            git branch: 'main',url:'https://github.com/filaa/wallet-sir-groupe2'
            }
            }
            stage('Build'){
                        steps{
                        sh 'echo "build in process" '
                        }
                }

    }

}