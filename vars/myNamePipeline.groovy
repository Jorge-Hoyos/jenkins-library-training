def call (body) {
  // Estas lineas siempre deben estar en el pipeline, me pasan las variables que tenga definicas a pipelineParams
  // creamos un mapa vacio que va a tener las variables del body
  pipelineParams = [:]

  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = pipelineParams
  body()
  pipelineParams.each { println(it) }

  // Definicion de variables por defecto, si no estan en el Jenkinsfile
  pipelineParams.pipelineTemplateName = this.getClass().toString().split(' ')[1]

  // ?: busca la variable, si no la encuentra le asigna el valor por defecto

  javaVersion = pipelineParams.javaVersion ?: '1.8'
  echoVariables = pipelineParams.echoVariables ?: true

  /*
  Pipeline structure
  echo Jenkins variables
  echo Default variables
  */

  pipeline {
    agent any
    stages {
      stage ('echo Jenkins variables') {
        steps {
          sh ("echo my name is ${pipelineParams.name} and im ${pipelineParams.age} years old")
        }
      }
      stage ('echo Default variables') {
        steps {
          sh ("echo name of the template is ${pipelineParams.pipelineTemplateName}")
        }
      }
      stage('echo all variables') {
        when { expression { return echoVariables == 'true' } }
        steps {
          script {
            pipelineParams.each { println "$it.key: $it.value" }
          }
        }
      }
    }
  }
}
