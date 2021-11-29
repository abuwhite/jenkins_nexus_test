pipeline {
    agent {
        docker {
            image 'python:3.8.2'
            args '--net=sber_test_prodnetwork -u root:root'
        }
    }
    stages {
        stage('Build') {
            steps {
                git branch: 'main', url: 'https://github.com/znhv/hello_world'
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
        stage('publish to nexus') {
            steps {
                sh 'pip install twine'
                sh 'pwd'
                sh 'twine upload --config-file .pypirc --repository pypi dist/*'
            }
        }
    }
}