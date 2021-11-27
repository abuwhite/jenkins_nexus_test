pipeline {
    agent none
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'python:2-alpine'
                }
            }

            steps {
                git branch: 'main', url: 'https://github.com/znhv/hello_world'
                sh 'python main.py'
            }

            post {
                success {
                    archiveArtifacts allowEmptyArchive: true,
                    artifacts: '*.py',
                    caseSensitive: false,
                    defaultExcludes: false,
                    followSymlinks: false,
                    onlyIfSuccessful: true
                }
            }
        }
    }
}
