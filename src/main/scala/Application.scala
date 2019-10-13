import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import routes.UserRoute

import scala.util.{Failure, Success}

object Application {
  val PORT = 5000
  implicit val actor                           = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext                = actor.dispatcher

  def main(args: Array[String]): Unit = {
    val router = new UserRoute()

    //Runs server on localhost:5000
    Http().bindAndHandle(router.routes, "0.0.0.0", PORT).onComplete {
      case Success(b) =>
        println(s"Server is running at ${b.localAddress.getHostName}:${b.localAddress.getPort}")
      case Failure(e) => println(s"Could not start application: {}", e.getMessage)
    }
  }
}