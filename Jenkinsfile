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
            stage('Build Docker image'){
                                         steps{
                                           bat 'docker build -t palaye/sir-soir:v2 .'
                                               }
                                         }
            stage('login to docker Hub'){
                                      steps{
                                             bat 'docker login -u palaye -p gamakishi'
                                                        }
                                             }
            stage('Push to docker Hub'){
                                       steps{
                                           bat 'docker push palaye/sir-soir:v2'
                                                   }
                                           }

    }

}