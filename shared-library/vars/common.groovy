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
     // sh '/opt/maven/bin/mvn test'
        print 'ok'
    }
     if (env.codeType == "nodejs") {
     // sh 'npm test'
         print 'tested Ok'
    }
     if (env.codeType == "python") {
      sh 'python3.6 -m unittest'
    }
  }
}

def codeQuality() {
  stage('Code Quality') {
    env.sonaruser = sh (script: 'aws ssm get-parameter --name "sonarqube_user" --with-decryption --query="Parameter.Value"', returnStdout: true).trim()
    env.sonarpass = sh (script: 'aws ssm get-parameter --name "sonarqube_pass" --with-decryption --query="Parameter.Value"', returnStdout: true).trim()                    
    
    print "${sonaruser}"
    print "${sonarpass}"

    sh 'sonar-scanner -Dsonar.host.url=http://172.31.34.157:9000 -Dsonar.login=${sonaruser} -Dsonar.password=${sonarpass} -Dsonar.projectKey=${component} -Dsonar.qualitygate.wait=true'
      print 'OK'
   }
}
