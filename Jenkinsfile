pipeline{

    agent any

    stages{
        stage('Build Jar'){
            steps{
                bat "mvn clean package -DsKipTests"
            }
        }

        stage('Build Image'){
            steps{
                bat "docker build -t=abdell98/selenium ."
            }
        }

        stage('Push Image'){
            steps{
                bat "docker push abdell98/selenium"
            }
        }
    }
}
