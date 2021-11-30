# Sber Test

The test task of Sber CI/CD.

## Installation

Start Jenkins and Nexus container
```shell
make start
```

Create a PyPi repository via the [Nexus web interface](http://localhost:18081/)

| Format | Type | Blob store | Deployment policy |
| ------------- | ------------- | ------------- | ------------- |
| pypi | hosted | default | Allow redeploy |


## Usage
Go to the [Jenkins web interface](http://localhost:18080/) and see Pipelines.


## Login details for Jenkins and Nexus

| Tool | Link | Credentials |
| ------------- | ------------- | ------------- |
| Jenkins | http://localhost:18080/ | no login required |
| Nexus | http://localhost:18081/ | admin/admin123 |