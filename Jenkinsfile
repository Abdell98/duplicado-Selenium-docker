pipeline{

    agent any

    stages{
        stage('stage-1'){
            steps{
                echo "doing mv clean"
                echo "doing mvn package"
            }
        }

        stage('stage-2'){
            steps{
                echo "building docker image"
            }
        }

        stage('stage-3'){
            steps{
                echo "pushing docker image"
            }
        }
    }
    post {
        always {
            echo "doing clean up "
        }
    }

}