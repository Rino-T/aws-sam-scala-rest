package handler

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.URL
import java.util.stream.Collectors

import com.amazonaws.services.lambda.runtime.events.{APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent}
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

import scala.jdk.CollectionConverters.MapHasAsJava
import scala.util.{Failure, Success, Try}

class HelloWorldHandler extends RequestHandler[APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent] {
  override def handleRequest(input: APIGatewayProxyRequestEvent, context: Context): APIGatewayProxyResponseEvent = {
    val headers = Map(
      "Content-Type" -> "application/json",
      "X-Custom-Header" -> "application/json"
      ).asJava

    val response = new APIGatewayProxyResponseEvent()
      .withHeaders(headers)

    getPageContents("https://checkip.amazonaws.com") match {
      case Success(pageContents) =>
        val output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents)
        response.withStatusCode(200).withBody(output)
      case Failure(exception) =>exception match {
        case e: IOException => response.withBody("{}").withStatusCode(500)
      }
    }
  }

  private def getPageContents(address: String): Try[String] = {
    val url = new URL(address)
    Try {
      val inputStreamReader = new InputStreamReader(url.openStream())
      val br = new BufferedReader(inputStreamReader)
      br.lines().collect(Collectors.joining(System.lineSeparator()))
    }
  }
}
