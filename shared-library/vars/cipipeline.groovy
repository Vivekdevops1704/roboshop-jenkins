def call(){
pipeline {
    agent any
       stages {
            stage("compile"){
                steps{
                    echo "this is compile phase"
                    script {
                      info 'starting'
                    }
                }
            }
            stage("build"){
                steps{
                    echo "this is build phase"
                }
            }
            stage("test"){
                steps{
                    echo "this is test phase"
                }
            }
            stage("sast check"){
                steps{
                    echo "this is sonarqibe scan phase"
                }
            }
            stage("code security"){
                when {
                    expression { 
                        BRANCH_NAME == "main" 
                        }
                }
                steps{
                    echo "this is testing phase"
                }
            }
            stage("release"){
                steps{
                    when {
                        expression { TAG_NAME ==~ ".*" }
                    }
                    echo "Hi Release"
                }
            }
        }
    }
}