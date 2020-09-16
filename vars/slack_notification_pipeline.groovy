def call(String buildStatus = 'FAILURE', String customMessage = null){
  slackSend color: "good", message: "Message from Jenkins Pipeline"
}
