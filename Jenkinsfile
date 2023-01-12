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
                              bat './mvnw clean install jacoco:report'
                        }
                }

                 stage('SonarQube Analysis'){
                                        steps{
                                              bat './mvnw clean install sonar:sonar'
                                        }
                                }

    }

}