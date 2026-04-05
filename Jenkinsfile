pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }

    environment {
        DOCKER_HUB = "reshma0209"
        TAG = "${BUILD_NUMBER}"
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
                // Build with version tag
                sh 'docker build -t $DOCKER_HUB/user-service:$TAG ./user-service'
                sh 'docker build -t $DOCKER_HUB/product-service:$TAG ./product-service'
                sh 'docker build -t $DOCKER_HUB/cart-service:$TAG ./cart-service'
                sh 'docker build -t $DOCKER_HUB/payment-service:$TAG ./payment-service'
                sh 'docker build -t $DOCKER_HUB/frontend:$TAG ./frontend'

                // Tag as latest
                sh 'docker tag $DOCKER_HUB/user-service:$TAG $DOCKER_HUB/user-service:latest'
                sh 'docker tag $DOCKER_HUB/product-service:$TAG $DOCKER_HUB/product-service:latest'
                sh 'docker tag $DOCKER_HUB/cart-service:$TAG $DOCKER_HUB/cart-service:latest'
                sh 'docker tag $DOCKER_HUB/payment-service:$TAG $DOCKER_HUB/payment-service:latest'
                sh 'docker tag $DOCKER_HUB/frontend:$TAG $DOCKER_HUB/frontend:latest'
            }
        }

        stage('Push Images') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {

                    sh 'echo $PASS | docker login -u $USER --password-stdin'

                    // Push version tag
                    sh 'docker push $DOCKER_HUB/user-service:$TAG'
                    sh 'docker push $DOCKER_HUB/product-service:$TAG'
                    sh 'docker push $DOCKER_HUB/cart-service:$TAG'
                    sh 'docker push $DOCKER_HUB/payment-service:$TAG'
                    sh 'docker push $DOCKER_HUB/frontend:$TAG'

                    // Push latest tag
                    sh 'docker push $DOCKER_HUB/user-service:latest'
                    sh 'docker push $DOCKER_HUB/product-service:latest'
                    sh 'docker push $DOCKER_HUB/cart-service:latest'
                    sh 'docker push $DOCKER_HUB/payment-service:latest'
                    sh 'docker push $DOCKER_HUB/frontend:latest'
                }
            }
        }
    }
}
