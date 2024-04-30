@Library('alvarium-pipelines') _

pipeline {
    agent any
    tools {
        maven 'M3'
    }
    stages {
        // stage('prep - generate source code checksum') {
        //     steps {
        //         sh 'mkdir -p $JENKINS_HOME/jobs/$JOB_NAME/$BUILD_NUMBER/'
        //         sh '''find . -type f -exec md5sum {} + |\
        //                 md5sum |\
        //                 cut -d" " -f1 \
        //                 > $JENKINS_HOME/jobs/$JOB_NAME/$BUILD_NUMBER/sc_checksum
        //         '''
        //     }
        // }



        stage('alvarium - pre-build annotations') {
            steps {
                script{
                    def optionalParams = ['sourceCodeChecksumPath':"${JENKINS_HOME}/jobs/${JOB_NAME}/${BUILD_NUMBER}/sc_checksum"]
                    alvariumCreate(['source-code'], optionalParams)
                }
            }
        }

        stage('build') {
            steps {
                sh 'mvn package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/**/*.jar', fingerprint: true
                }
            }
        }
    }
}
