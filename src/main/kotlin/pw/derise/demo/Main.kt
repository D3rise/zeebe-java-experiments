package pw.derise.demo

import io.camunda.zeebe.client.ZeebeClient
import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.client.api.worker.JobClient
import io.camunda.zeebe.client.api.worker.JobHandler

fun main() {
    val zeebeClient = ZeebeClient.newClientBuilder().usePlaintext().build()

    zeebeClient.newWorker().jobType("hello").handler(HelloHandler()).open()
    println("Worker is running!")

    val testVariables = mapOf<String, String>(
        "test" to "hello"
    )

    zeebeClient.newDeployCommand().addResourceFromClasspath("diagram_1.bpmn").send().join()
    val result = zeebeClient.newCreateInstanceCommand().bpmnProcessId("say-hello").latestVersion().variables(testVariables).withResult().send().join()
    println(result.variables)
}

class HelloHandler : JobHandler {
    override fun handle(client: JobClient, job: ActivatedJob) {
        println("Hello world!")

        val args = job.variables
        client.newCompleteCommand(job.key).variables(args).send()
    }
}