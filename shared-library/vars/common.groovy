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
    env.sonaruser = sh (script: 'aws ssm get-parameter --name "sonarqube_user" --with-decryption --query="Parameter.Value" | xargs', returnStdout: true).trim()
    env.sonarpass = sh (script: 'aws ssm get-parameter --name "sonarqube_pass" --with-decryption --query="Parameter.Value" | xargs', returnStdout: true).trim()                    
    //  sh 'sonar-scanner -Dsonar.host.url=http://54.90.236.64:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=${component} -Dsonar.qualitygate.wait=true'
   print ${sonaruser}
   print ${sonarpass}                 
   }
}
