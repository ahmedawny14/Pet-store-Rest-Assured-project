pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Pulling source code from Git...'
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Executing Maven Tests...'
                // استخدام bat بدلاً من sh لنظام Windows
                bat 'mvn clean test' 
            }
        }
    }

    post {
        always {
            echo 'Generating Allure Report...'
            // استخدام اسم الأداة المعرفة في Jenkins
            allure includeProperties: false, 
                   jdk: '', 
                   results: [[path: 'target/allure-results']]
        }
    }
}
