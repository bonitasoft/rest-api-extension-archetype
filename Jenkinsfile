timestamps {
    ansiColor('xterm') {
        node {
            stage('Setup') {
                checkout scm
            }

            stage('Build') {
                try {
                    if(isBaseBranch()){
                      sh "./mvnw -B clean deploy -Djvm=${env.JAVA_HOME_11}/bin/java -DaltDeploymentRepository=${env.ALT_DEPLOYMENT_REPOSITORY_SNAPSHOTS}"  
                    }else{
                       sh "./mvnw -B clean verify -Djvm=${env.JAVA_HOME_11}/bin/java"
                    }
                } finally {
                    junit allowEmptyResults: true, testResults: '**/target/*-reports/*.xml'
                }
            }
        }
    }
}
def isBaseBranch() {
    def currentBranch = env.BRANCH_NAME
    currentBranch == 'master' || currentBranch == 'dev' || currentBranch?.startsWith('release-')
}