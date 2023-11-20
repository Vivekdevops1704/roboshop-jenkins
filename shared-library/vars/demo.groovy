def info(message) {
    echo "INFO: ${message}"
}
def call()
pipeline {
    agent any
       stages {
            stage("compile"){
                steps{
                    echo "this is compile phase"
                    script {
                      demo.info 'starting'
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
            stage("test check"){
                steps{
                    echo "this is testing phase"
                }
            }
        }
    }