pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }

    environment {
        DOCKER_HUB = "your-dockerhub-username"
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/ecommerce-microservices.git'
            }
        }

        stage('Build Services') {
            steps {
                dir('user-service') {
                    sh 'mvn clean package -DskipTests'
                }
                dir('product-service') {
                    sh 'mvn clean package -DskipTests'
                }
                dir('cart-service') {
                    sh 'mvn clean package -DskipTests'
                }
                dir('payment-service') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker build -t $DOCKER_HUB/user-service ./user-service'
                sh 'docker build -t $DOCKER_HUB/product-service ./product-service'
                sh 'docker build -t $DOCKER_HUB/cart-service ./cart-service'
                sh 'docker build -t $DOCKER_HUB/payment-service ./payment-service'
                sh 'docker build -t $DOCKER_HUB/frontend ./frontend'
            }
        }

        stage('Push Images') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS')]) {

                    sh 'echo $PASS | docker login -u $USER --password-stdin'

                    sh 'docker push $DOCKER_HUB/user-service'
                    sh 'docker push $DOCKER_HUB/product-service'
                    sh 'docker push $DOCKER_HUB/cart-service'
                    sh 'docker push $DOCKER_HUB/payment-service'
                    sh 'docker push $DOCKER_HUB/frontend'
                }
            }
        }
    }
}
