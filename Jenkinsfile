pipeline {
    agent any

    tools {
        // اسم أداة المافن كما هي معرفة في Jenkins (Global Tool Configuration)
        maven 'Maven 3.9' 
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Pulling source code from Git...'
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Executing TestNG Automation Tests...'
                // تشغيل الاختبارات وتوليد نتائج Allure في target/allure-results
                sh 'mvn clean test' 
                // إذا كنت تعمل على بيئة Windows استخدم: bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Generating Allure Report...'
            // تجميع وقراءة نتائج Allure وإنشاء التقرير النهائي
            allure includeProperties: false, 
                   jdk: '', 
                   results: [[path: 'target/allure-results']]
        }
        success {
            echo 'Tests execution passed successfully!'
        }
        failure {
            echo 'Some tests failed. Check the Allure Report for details.'
        }
    }
}
