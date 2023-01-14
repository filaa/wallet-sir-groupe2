pipeline{
    agent any
    stages{
            stage('Source'){
            steps{
            git branch: 'main', url:'https://github.com/filaa/wallet-sir-groupe2.git'
            }
            }
            stage('Build'){
                        steps{
                              bat 'mvnw clean package org.jacoco:jacoco-maven-plugin:prepare-agent'
                        }
                }

                 stage('SonarQube Analysis'){
                                        steps{
                                              bat 'mvnw  sonar:sonar'
                                        }
                                }

    }

}