def compile() {
    if (env.codeType == "python" || env.codeType == "static") {
    return "Return, Do not need Compilation"
    }
    
    if (env.codeType == "maven") {
      sh '/home/centos/maven/bin/mvn package'
    }
    if (env.codeType == "nodejs"){
        sh 'npm install'
    }
    }
    if (env.codeType == "golang"){
        print 'golang'
    }
}
