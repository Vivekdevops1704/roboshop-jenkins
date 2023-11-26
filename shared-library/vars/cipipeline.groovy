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
             userRemoteConfigs: [[url: "https://github.com/Vivekdevops/${component}"]]
             )

            stage("compile and build"){             
               common.compile()                 
                }           
            stage("test"){               
               common.test() 
            }
            stage("sast check"){               
                    common.codeQuality()        
            }
            stage("code security"){                          
                    echo "this is security phase"               
            }
            stage("release"){
                    echo "Hi Release"
                }
            }
        }
