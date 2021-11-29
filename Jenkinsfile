pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'python:3.8.2-alpine'
                }
            }

            steps {
                git branch: 'main', url: 'https://github.com/znhv/hello_world'
            }

            post {
                success {
                    archiveArtifacts allowEmptyArchive: true,
                    artifacts: 'dist/*.whl',
                    caseSensitive: false,
                    defaultExcludes: false,
                    followSymlinks: false,
                    onlyIfSuccessful: true
                }
            }
        }
        stage('publish to nexus') {
            agent {
                docker {
                    image 'python:3.8.2'
                    args '-u root:root'
                }
            }

            steps {
                sh 'pip install twine'
                sh 'pwd'
                sh 'ls -la'
                sh 'cat .pypirc'
                sh 'twine upload --config-file .pypirc --repository pypi dist/*'
            }
        }
    }
}