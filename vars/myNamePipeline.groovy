def call (body) {
  // Estas lineas siempre deben estar en el pipeline, me pasan las variables que tenga definicas a pipelineParams
  def pipelineParams = [:]
  body.resolverStrategy = Closure.DELEGATE_FIRST
  body.delegate = pipelineParams
  body()
  pipelineParams.each { println(it) }

  // Definicion de variables por defecto, si no estan en el Jenkinsfile
  pipelineParams.pipelineTemplateName = this.getClass().toString().split(' ')[1]

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
    }
  }
}
