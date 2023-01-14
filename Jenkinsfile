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
                              sh 'mvnw clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
                        }
                }

                 stage('SonarQube Analysis'){
                                        steps{
                                              sh 'mvnw  sonar:sonar'
                                        }
                                }

    }

}