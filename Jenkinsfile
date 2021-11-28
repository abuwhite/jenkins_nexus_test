pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'python:2-alpine'
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
            steps {
                script {
                    nexusArtifactUploader(
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        nexusUrl: '172.21.0.1:18081',
                        groupId: 'gpy',
                        version: '0.1',
                        repository: 'pypi-internal/',
                        credentialsId: 'nexus-credentials',
                        artifacts: [
                            [artifactId: 'pytest',
                             classifier: '',
                             file: 'dist/hello_world-0.0.1-py3-none-any.whl',
                             type: 'whl']
                        ]
                     );
                }
              }
            }
        }
    }