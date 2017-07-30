pipeline {
  agent any
  stages {
    stage('Get Latest') {
      steps {
        git(poll: true, credentialsId: '', url: 'https://github.com/comdotlinux/TasteOfJavaEE7', branch: 'master')
      }
    }
  }
}
