pipeline{
    agent any
                tools{
                      maven '3.6.3'
                    }
    stages{
            stage('Source'){
            steps{
            git branch: 'main', url:'https://github.com/filaa/wallet-sir-groupe2.git'
            }
            }
            stage('Build'){
                        steps{
                              bat './mvnw clean package'
                        }
                }

                 stage('SonarQube Analysis'){
                                        steps{
                                              bat './mvnw sonar:sonar'
                                        }
                                }

    }

}