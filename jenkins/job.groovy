job('Git-CI') {
    scm {
        github('znhv/hello_world', 'main')
    }
    triggers {
        cron('*/1 * * * *')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('example/project-a')
            tag('${BUILD_TIMESTAMP}-${GIT_REVISION,length=7}')
            registryCredentials('docker-hub')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
        shell('main.py')
    }
    publishers {
        archiveArtifacts('job-dsl-plugin/build/libs/job-dsl.hpi')
    }
}