pipeline {
  agent any
  stages {
    stage('Get Latest') {
      steps {
        git(poll: true, credentialsId: '8b1c69c1ea113b87833a88ce6956cbb964dc4d94', url: 'https://github.com/comdotlinux/TasteOfJavaEE7', branch: 'master')
      }
    }
  }
}