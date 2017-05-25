import groovyx.net.http.RESTClient
import org.apache.http.client.HttpResponseException

import static groovyx.net.http.ContentType.*

def buildNumber = System.getenv("CIRCLE_BUILD_NUM")
def circleCIResultFilesPath = "${System.getenv("CIRCLE_TEST_REPORTS")}/junit/"

println "CircleCI Build Number: ${buildNumber}"
println "CircleCI Test Result Path: ${circleCIResultFilesPath}"
println "CircleCI TestRail API Key: ${System.getenv("TestRailAPI")}"

// Only used for debugging at localhost
def localResultFilesPath = "${System.getProperty("user.dir")}/target/failsafe-reports/"

def junitResultFiles = new FileNameFinder().getFileNames(circleCIResultFilesPath, "TEST-*.xml")

def testrailAPI = new RESTClient("https://knilsen.testrail.net/")
def baseURI = "${testrailAPI.uri.toString()}index.php?/api/v2/"
def authCredentials = "testrail@kristiannilsen.com:${System.getenv("TestRailAPI")}".bytes.encodeBase64()

testrailAPI.defaultRequestHeaders."Content-Type" = "application/json"
testrailAPI.defaultRequestHeaders."Authorization" = "Basic ${authCredentials}"

try {
    def testSection = testrailAPI.post(
            uri: new URI("${baseURI}add_section/1"),
            body: [name: "CircleCI Integration Tests for Build Number ${buildNumber}"],
            requestContentType: JSON
    )

    def testRun = testrailAPI.post(
            uri: new URI("${baseURI}add_run/1"),
            body: [name: "CircleCI Test Run for Build Number ${buildNumber}"],
            requestContentType: JSON
    )

    junitResultFiles.each { resultFile ->
        def result = new XmlSlurper().parse(resultFile)
        def testsuiteName = result.@name

        result.testcase.each { testcase ->
            def status = 1
            def description = "Test OK"
            def testCaseName = testcase.@name

            // Breadth-First Traversal to find any direct children
            // '*' is shorthand for Breadth-First Traversal
            def errorMessage =  testcase.'*'.find { testcase.error || testcase.failure }

            if (!errorMessage.isEmpty()) {
                status = 5
                description = errorMessage
            }

            def testCase = testrailAPI.post(
                    uri: new URI("${baseURI}add_case/${testSection.data.id}"),
                    body: [title: testCaseName.toString()],
                    requestContentType: JSON
            )

            testrailAPI.post(
                    uri: new URI("${baseURI}add_result_for_case/${testRun.data.id}/${testCase.data.id}"),
                    body: [status_id: status.toString(), comment: description.toString()],
                    requestContentType: JSON
            )
        }
    }
} catch (HttpResponseException ex) {
    r = ex.response
    println("Success: $r.success")
    println("Status:  $r.status")
    println("Reason:  $r.statusLine.reasonPhrase")
    println("Content: \n${JsonOutput.prettyPrint(JsonOutput.toJson(r.data))}")
}