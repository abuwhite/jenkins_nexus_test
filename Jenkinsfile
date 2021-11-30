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
        stage('Code pull') {
            steps {
                echo "Start of Stage Build..."
                git branch: 'main', url: 'https://github.com/znhv/hello_world'
                echo "Building......."
                sh 'python3 -m build'
                echo "End of Stage Build..."
            }
        }
        stage('Test') {
            steps {
                echo "Start of Stage Test..."
                echo "Testing......."
                sh 'python3 main.py'
                echo "End of Stage Test..."
            }
        }
        stage('Publish') {
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
            steps {
                echo "Start of Stage Publish..."
                echo "Publishing......."
                sh 'pip install twine'
                sh 'twine upload --config-file .pypirc --repository pypi dist/*'
                echo "End of Stage Publish..."
            }
        }
    }
}