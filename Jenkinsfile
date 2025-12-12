pipeline {
    agent any

    tools {
        maven "Maven_3"
    }

    environment {
        SONARQUBE_ENV = "MySonar"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Onsko/TPCafe_Ons_kochtane.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean verify'
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                withSonarQubeEnv(SONARQUBE_ENV) {
                    bat 'mvn sonar:sonar'
                }
            }
        }
    }
}
