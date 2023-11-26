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

def codeQuality() {
  stage('Code Quality') {
      sh 'sonar-scanner -Dsonar.host.url=http://54.90.236.64:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=cart -Dsonar.qualitygate.wait=true'
    }
    }
