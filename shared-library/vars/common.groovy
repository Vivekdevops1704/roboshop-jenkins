def compile() {
    if (env.codeType == "python" || env.codeType == "static") {
    return "Return, Do not need Compilation"
    }
    
    if (env.codeType == "maven") {
      sh '/opt/maven/bin/mvn package'
    }
    if (env.codeType == "nodejs"){
        sh 'npm install'
    }
    if (env.codeType == "golang"){
        print 'golang'
    }
}
def test() {
  stage('Test Cases') {
    if (env.codeType == "maven") {
      sh '/opt/maven/bin/mvn test'
    }
     if (env.codeType == "nodejs") {
      sh 'npm test'
    }
     if (env.codeType == "python") {
      sh 'python3.6 -m unittest'
    }
  }
}
