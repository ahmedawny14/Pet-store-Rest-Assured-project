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
                // تشغيل التستات عبر Maven
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            // إنشاء ورسم تقرير Allure بغض النظر عن نتيجة الاختبارات
            allure includeProperties: false, 
                   jdk: '', 
                   results: [[path: 'target/allure-results']]
        }
    }
}
