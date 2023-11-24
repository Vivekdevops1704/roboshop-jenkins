def call(){
node {
            sh "find . | sed -e '1d' | xargs rm -rf "
            if(env.TAG_NAME ==~ ".*") {
            env.branch_name = "refs/tags/${env.TAG_NAME}"
            } else {
            env.branch_name = "${env.BRANCH_NAME}"
            }
             checkout scmGit(
             branches: [[name: branch_name]],
             userRemoteConfigs: [[url: "https://github.com/raghudevopsb74/${component}"]]
             )

            stage("compile and build"){             
               common.compile()                 
                }           
            stage("test"){               
                    echo "this is test phase"
            }
            stage("sast check"){               
                    sh 'env'
                    echo "this is sonarqibe scan phase"           
            }
            stage("code security"){                          
                    echo "this is testing phase"               
            }
            stage("release"){
                    echo "Hi Release"
                }
            }
        }
