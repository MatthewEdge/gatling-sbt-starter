package simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.language.postfixOps

// run with "sbt gatling:test" on another machine so you don't have resources contending
class GatlingSpec extends Simulation {

  val httpConf = http.baseURL("http://localhost:8080/")
  val readClients = scenario("Clients").exec(Index.refreshManyTimes)

  setUp(
    // For reference, this hits 25% CPU on a 5820K with 32 GB, running both server and load test.
    readClients.inject(rampUsers(10000) over (100 seconds)).protocols(httpConf)
  )
  
}

object Index {

  def refreshAfterOneSecond =
    exec(http("Index").get("/").check(status.is(200))).pause(1)

  val refreshManyTimes = repeat(10000) {
    refreshAfterOneSecond
  }

}