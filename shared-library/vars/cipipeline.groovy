def call(){
node {
            sh "find . | sed -e '1d' | xargs rm -rf "
            if(env.TAG_NAME ==~ ".*") {
            env.branch_name = "refs/tags/${env.TAG_NAME}"
            } else {
            env.branch_name = "${env.BRANCH_NAME}"
            }
            git branch: "${BRANCH_NAME}", url: "https://github.com/Vivekdevops1704/${component}.git"
            stage("compile"){             
               common.compile()                 
                }           
            stage("build"){                
                    echo "this is build phase"                
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
