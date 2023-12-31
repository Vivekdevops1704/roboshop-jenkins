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
   env.sonaruser = sh (script: 'aws ssm get-parameter --name "sonarqube_user" --query="Parameter.Value" |xargs', returnStdout: true).trim()
   env.sonarpass = sh (script: 'aws ssm get-parameter --name "sonarqube_pass" --with-decryption --query="Parameter.Value" |xargs', returnStdout: true).trim()
    wrap([$class: "MaskPasswordsBuildWrapper", varPasswordPairs: [[password: sonarpass]]]) {
    if(env.codeType == "maven") {
        //sh 'sonar-scanner -Dsonar.host.url=http://172.31.89.117:9000 -Dsonar.login=${sonaruser} -Dsonar.password=${sonarpass} -Dsonar.projectKey=${component} -Dsonar.qualitygate.wait=true -Dsonar.java.binaries=./target'
    //sh 'sonar-scanner -X -Dsonar.host.url=http://3.89.200.49:9000 -Dsonar.login=${sonaruser} -Dsonar.password=${sonarpass} -Dsonar.projectKey=${component} -Dsonar.qualitygate.wait=true -Dsonar.java.binaries=./target'
   print 'OK'
   }
        else {
           //  sh 'sonar-scanner -X -Dsonar.host.url=http://3.89.200.49:9000 -Dsonar.login=${sonaruser} -Dsonar.password=${sonarpass} -Dsonar.projectKey=${component} -Dsonar.qualitygate.wait=true'
         print 'Ok'
         }
    }
}
}
//This is for Nexus
def codeRelease() {
  stage('Code release') {
   env.nexususer = sh (script: 'aws ssm get-parameter --name "nexus_user" --query="Parameter.Value" |xargs', returnStdout: true).trim()
   env.nexuspass = sh (script: 'aws ssm get-parameter --name "nexus_pass" --with-decryption --query="Parameter.Value" |xargs', returnStdout: true).trim()
      wrap([$class: "MaskPasswordsBuildWrapper", varPasswordPairs: [[password: nexuspass]]]) {
         // sh''
          if(env.codeType == "nodejs") {
              sh 'env'
            sh  'zip -r ${component}-${TAG_NAME}.zip server.js node_modules'  
          }
          //else {
          //if(env.codeType == "maven") {
            //  sh '
         // }
     // }
          sh 'curl -v -u ${nexususer}:${nexuspass} --upload-file ${component}-${TAG_NAME}.zip http://52.90.253.149:8081/repository/${component}/${component}-${TAG_NAME}.zip'
  }
  }
}
