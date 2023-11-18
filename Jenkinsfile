pipeline {
    agent { label 'node' }
        stages {
            stage("checkout"){
                steps{
                    echo "this is checkout phase"
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
}