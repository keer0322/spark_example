env.dockerimagename="devopsbasservice/buildonframework:buildon-jenkinsfile"
node {
   stage ('Code Checkout') {
    checkout scm
    
  }
   stage ('Code build') {
    sh """pwd
          cd ${WORKSPACE}
          mvn clean package -DskipTests=True
          """
    sh 'sleep 10s'
   }

   stage ('Code Deploy') {
    echo "Code Deployed skipped for now"
   }
}
