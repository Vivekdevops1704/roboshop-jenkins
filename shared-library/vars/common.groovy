def compile() {
    if (env.codeType == "maven"){
         sh '/home/centos/maven/bin/mvn package'
    }
    if (env.codeType == "nodejs"){
        print 'nodejs'
    }
    if (env.codeType == "python"){
        print 'python'
    }
    if (env.codeType == "static"){
        print 'static'
    }
    if (env.codeType == "golang"){
        print 'golang'
    }
}
