pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }

    environment {
        DOCKER_HUB = "reshma0209"
        TAG = "${BUILD_NUMBER}"   // 🔥 dynamic tag
    }

    stages {

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
                sh 'docker build -t $DOCKER_HUB/user-service:$TAG ./user-service'
                sh 'docker build -t $DOCKER_HUB/product-service:$TAG ./product-service'
                sh 'docker build -t $DOCKER_HUB/cart-service:$TAG ./cart-service'
                sh 'docker build -t $DOCKER_HUB/payment-service:$TAG ./payment-service'
                sh 'docker build -t $DOCKER_HUB/frontend:$TAG ./frontend'
            }
        }

        stage('Push Images') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS')]) {

                    sh 'echo $PASS | docker login -u $USER --password-stdin'

                    sh 'docker push $DOCKER_HUB/user-service:$TAG'
                    sh 'docker push $DOCKER_HUB/product-service:$TAG'
                    sh 'docker push $DOCKER_HUB/cart-service:$TAG'
                    sh 'docker push $DOCKER_HUB/payment-service:$TAG'
                    sh 'docker push $DOCKER_HUB/frontend:$TAG'
                }
            }
        }
    }
}
