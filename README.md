# Sber CI/CD Test

A solution to a test task that runs two containers 
with Jenkins and Nexus. After creating a repository in Nexus, 
Pipeline is launched in Jenkins and if the project passes the tests, 
it is built and sent to the Nexus repository.

## Installation

1. Start Jenkins and Nexus container:
```shell
make start
```

2. Create a PyPi repository via the [Nexus web interface](http://localhost:18081/):

 Format | Type | Name |Blob store | Deployment policy |
| ------------- | ------------- | ------------- | ------------- | ------------- |
pypi | hosted | pypi-internal | default | Allow redeploy |


## Usage
Go to the [Jenkins web interface](http://localhost:18080/) and see Pipelines.


## Options

Configuration as Code - casc.yaml:
```shell
pipelineJob('Sber-Test-CI/CD') {
  definition {
    cpsScm {
      scm {
        github('znhv/sber_test')
        # The repository on which Jenkinsfile is taken
      }
    }
  }
  triggers {
    urlTrigger {
      cron('*/2 * * * *')
      url('http://10.5.0.1:18081/repository/pypi-internal/') {
        # When the repository is created, the trigger starts the pipeline.
        status(200)
        check('status')
      }
    }
  }
}
```

Jenkinsfile Pipeline:
```shell
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

        // Build
        stage('Build') {
            steps {
                git branch: 'main', url: 'https://github.com/znhv/hello_world'
            }
        }

        // Unit Tests
        stage('Test') {
            steps {
                sh 'python3 -m src'
            }
        }

        // Deliver
        stage('Deliver') {
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
                sh 'pip install build'
                sh 'python3 -m build'
                sh 'pip install twine'
                sh 'twine upload --config-file .pypirc --repository pypi dist/*'
            }
        }
    }
}
```

| Tool | Link | Credentials |
| ------------- | ------------- | ------------- |
| Jenkins | http://localhost:18080/ | no login required |
| Nexus | http://localhost:18081/ | admin/admin123 |