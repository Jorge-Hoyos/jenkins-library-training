def call(String buildStatus = 'FAILURE', String customMessage = null){
  summary = "${Message from Jenkins Pipeline} (<${env.BUILD_URL}|Open>)"
  slackSend color: "good", message: summary
}
