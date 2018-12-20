
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class TPCase02 extends Simulation {

	val httpProtocol = http
		.baseUrl("http://127.0.0.1:18238")
		.inferHtmlResources()
		.acceptHeader("application/json, text/plain, */*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Blitz/0.8.7 Chrome/61.0.3163.100 Electron/2.0.13 Safari/537.36")

	val headers_0 = Map("Proxy-Connection" -> "keep-alive")

    val uri1 = "http://localhost:8090"

	val scn = scenario("TPCase02")
		.exec(
			http("request_0")
			.get(uri1 + "/Patient")
			.resources
			(
				http("request_1")
				.get(uri1 + "/css/bootstrap.min.css")
				.check(status.is(404))
			)
		)
		.pause(10)

		.exec(http("request_2").get(uri1 + "/Patient/1"))
		.exec(http("request_3").get(uri1 + "/Patient/1/Mesure/index"))
		.exec(
			http("request_4")
				.post(uri1 + "/Patient/1/Mesure/SUP/create")
				.formParam("mesure1", "V45-H30-A100")
				.formParam("mesure2", "V5-H10-A90")
		)
		.pause(50)

		.exec(
			http("request_5")
			.get(uri1 + "/Patient")
		)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}