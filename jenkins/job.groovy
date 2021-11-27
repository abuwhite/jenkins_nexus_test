job('git-ci') {
    description 'Build and test the app.'
    scm {
        github 'znhv/hello_world'
    }
    steps {
        gradle 'test'
    }
}