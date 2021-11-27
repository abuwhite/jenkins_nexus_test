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
                script {
                    nexusArtifactUploader(
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        nexusUrl: 'my.nexus.address',
                        groupId: 'com.example',
                        version: version,
                        repository: 'RepositoryName',
                        credentialsId: 'CredentialsId',
                        artifacts: [
                            [artifactId: projectName,
                             classifier: '',
                             file: 'my-service-' + version + '.jar',
                             type: 'jar']
                        ]
                     )
                }
            }
        }
    }
}