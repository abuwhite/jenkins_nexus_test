pipeline {
    agent {
        docker {
            image 'python:3.8.2'
            args '--net=sber_test_prodnetwork -u root:root'
        }
    }
    environment {
      PROJECT_NAME = "Sber Test"
      OWNER_NAME   = "Boris Zhenikhov"
    }
    stages {

        stage('Build') {
            steps {
                git branch: 'main', url: 'https://github.com/znhv/hello_world'
            }
        }

        stage('Test') {
            steps {
                sh 'python3 -m src'
            }
        }

        stage('Deploy') {
            steps {
                sh 'pip install build'
                sh 'python3 -m build'
                sh 'pip install twine'
                sh 'twine upload --config-file .pypirc --repository pypi dist/*'
            }
            post {
                success {
                    archiveArtifacts allowEmptyArchive: true,
                    artifacts: 'dist/*whl',
                    caseSensitive: false,
                    defaultExcludes: false,
                    followSymlinks: false,
                    onlyIfSuccessful: true
                }
            }
        }
    }
}