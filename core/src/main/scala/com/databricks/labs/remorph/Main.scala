package com.databricks.labs.remorph

case class Payload(command: String, flags: Map[String, String])

object Main extends App {
  // scalastyle:off println
  route match {
    case Payload("debug-script", args) =>
      println("Debugging script...")
      println(args)
    case Payload(command, _) =>
      println(s"Unknown command: $command")
  }

  // parse json from the last CLI argument
  private def route: Payload = {
    val payload = ujson.read(args.last).obj
    val command = payload("command").str
    val flags = payload("flags").obj.mapValues(_.str).toMap
    Payload(command, flags)
  }
}
