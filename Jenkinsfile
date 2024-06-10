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
                bat "docker build -t=vinsdocker/selenium"
            }
        }

        stage('Push Image'){
            steps{
                bat "docker push vinsdocker/selenium"
            }
        }
    }
    post {
        always {
            echo "doing clean up "
        }
    }

}