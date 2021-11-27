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
        stage('publish to nexus') {
            steps {
                nexusArtifactUploader {
                    nexusVersion: 'nexus3'
                    protocol: 'http'
                    nexusUrl: 'localhost:18081/nexus'
                    groupId: 'sp.sd'
                    version: '2.4'
                    repository: 'NexusArtifactUploader'
                    credentialsId: 'nexus-credentials'
                    artifacts: {
                        artifactId('nexus-artifact-uploader')
                        type('jar')
                        classifier('debug')
                        file('nexus-artifact-uploader.jar')
                    }
                }
            }
        }
    }
}