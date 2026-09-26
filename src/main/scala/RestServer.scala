import cats.effect.{IO, IOApp}
import com.comcast.ip4s.*
import org.http4s.HttpRoutes
import org.http4s.dsl.io.*
import org.http4s.ember.server.EmberServerBuilder
import org.slf4j.LoggerFactory

object RestServer extends IOApp.Simple:
  private val logger = LoggerFactory.getLogger(getClass)

  private val port: Port =
    sys.env
      .get("PORT")
      .flatMap(_.toIntOption)
      .flatMap(Port.fromInt)
      .getOrElse(port"3000")

  private val routes: HttpRoutes[IO] = HttpRoutes.of[IO]:
    case GET -> Root =>
      logger.info("GET /")
      Ok("Root /")

    case GET -> Root / "api" =>
      logger.info("GET /api")
      Ok("""{"message":"Hello from backend. Oh Yeah!"}""")

  def run: IO[Unit] =
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port)
      .withHttpApp(routes.orNotFound)
      .build
      .useForever