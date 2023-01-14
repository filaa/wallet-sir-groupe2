pipeline{
    agent any
                tools{
                      maven "maven"
                    }
    stages{
            stage('Source'){
            steps{
            git branch: 'main', url:'https://github.com/filaa/wallet-sir-groupe2.git'
            }
            }
            stage('Build'){
                        steps{
                              bat './mvn clean package '
                        }
                }

                 stage('SonarQube Analysis'){
                                        steps{
                                              bat './mvn  sonar:sonar'
                                        }
                                }

    }

}