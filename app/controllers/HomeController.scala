package controllers

import javax.inject._

import play.api._
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc._
import scala.concurrent._
import ExecutionContext.Implicits.global

import scala.concurrent.{ExecutionException, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(wsClient: WSClient) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action.async {
    val json = Map("vote" -> Seq("a"))
    val futures = Future.sequence((0 until 1000).map(s=> wsClient.url("http://ip10_0_50_3-5000.play-with-docker.com/")
      .post(json)))
    futures.map(resp => resp.map(resp => json + "\n" +  resp.body).mkString("\n\n")).map(Ok(_))
  }

}
