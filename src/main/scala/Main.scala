import com.sun.net.httpserver.{HttpExchange, HttpServer}
import java.net.InetSocketAddress
import java.nio.charset.StandardCharsets
import java.util.concurrent.CountDownLatch

object Main:
  def main(args: Array[String]): Unit =
    val port = sys.env.get("PORT").flatMap(_.toIntOption).getOrElse(3000)
    val server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0)

    server.createContext("/api", (exchange: HttpExchange) =>
      val path = exchange.getRequestURI.getPath
      val method = exchange.getRequestMethod

      if path != "/api" then
        respond(exchange, 404, "{\"error\":\"Not found\"}")
      else if method != "GET" then
        exchange.getResponseHeaders.set("Allow", "GET")
        respond(exchange, 405, "{\"error\":\"Method not allowed\"}")
      else
        respond(exchange, 200, "{\"message\":\"Hello from backend. Oh Yeah!\"}")
    )

    server.start()
    println(s"API running on port $port")
    new CountDownLatch(1).await()

  private def respond(exchange: HttpExchange, status: Int, body: String): Unit =
    val bytes = body.getBytes(StandardCharsets.UTF_8)
    exchange.getResponseHeaders.set("Content-Type", "application/json; charset=utf-8")
    exchange.sendResponseHeaders(status, bytes.length)
    val responseBody = exchange.getResponseBody
    try responseBody.write(bytes)
    finally exchange.close()