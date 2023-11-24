def call(){
node {
            sh "find ."
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
