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
                nexusVersion('nexus3')
                protocol('http')
                nexusUrl('localhost:8081/nexus3')
                groupId('sp.sd')
                version('2.4')
                repository('NexusArtifactUploader')
                credentialsId('44620c50-1589-4617-a677-7563985e46e1')
                artifact {
                    artifactId('nexus-artifact-uploader')
                    type('py')
                    classifier('debug')
                    file('main.py')
                }
              }
            }
        }
    }
}