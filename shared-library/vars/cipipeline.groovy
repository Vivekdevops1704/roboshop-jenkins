def call(){
node {
            sh "find . | sed -e '1d' | xargs rm -rf "
            stage("checkout the code"{
                git branch: 'main', url: 'https://github.com/Vivekdevops1704/${component}.git'
            }
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
