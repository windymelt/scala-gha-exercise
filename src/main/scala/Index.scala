import scala.scalajs.js.annotation.*
import npm.actionsCore.mod as core
import npm.actionsGithub.mod as github
import scala.scalajs.js

@main def hello(): Unit =
  println("Hello github action!")
  try {
    val nameToGreet = core.getInput("who-to-greet")
    println(s"Hello $nameToGreet!")
    val time = new js.Date().toUTCString()
    core.setOutput("time", time)
    val payload = js.JSON.stringify(github.context.payload)
    println(s"Payload: $payload")
  } catch {
    case e: Throwable =>
      println(s"Error: ${e.getMessage}")
      e.printStackTrace()
  }
