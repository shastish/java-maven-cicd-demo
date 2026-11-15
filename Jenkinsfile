pipeline {
    agent any

    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and test') {
            steps {
                sh '''#!/bin/sh
                    set -eu
                    if [ -x ./mvnw ]; then
                        ./mvnw --batch-mode --no-transfer-progress clean verify
                    elif command -v mvn >/dev/null 2>&1; then
                        mvn --batch-mode --no-transfer-progress clean verify
                    else
                        echo "Maven is not installed and the repository has no executable Maven Wrapper." >&2
                        exit 1
                    fi
                '''
            }
            post {
                always {
                    junit testResults: '**/target/surefire-reports/*.xml,**/target/failsafe-reports/*.xml', allowEmptyResults: true
                }
            }
        }

        stage('Archive artifact') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar,**/target/*.war', allowEmptyArchive: false, fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'CI completed: build passed, tests passed, and the artifact was archived. Deployment is not configured.'
        }
        failure {
            echo 'CI failed. Review the stage logs and console output.'
        }
        always {
            deleteDir()
        }
    }
}
