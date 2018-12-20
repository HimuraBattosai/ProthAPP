
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class TPCase01 extends Simulation {

	val httpProtocol = http
		.baseUrl("http://127.0.0.1:18238")
		.inferHtmlResources()
		.acceptHeader("application/json, text/plain, */*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Blitz/0.8.7 Chrome/61.0.3163.100 Electron/2.0.13 Safari/537.36")

	val headers_0 = Map("Proxy-Connection" -> "keep-alive")

    val uri1 = "http://127.0.0.1:8090"

	val users = Integer.getInteger("users", 10).toInt
	
	val scn = scenario("TPCase01")
		.exec(
			http("request_1")
			.get(uri1 + "/Patient")
		)
		.pause(5)

       .exec(
            http("request_2")
			.get(uri1 + "/Commande/index")
		)
		.pause(30)

		.exec(
			http("request_3")
			.get(uri1 + "/Patient")
		)
		.pause(5)
        
        .exec(    
            http("request_4")
			.get(uri1 + "/ProtheseCatalogue/index")
		)
		.pause(30)

       .exec( 
            http("request_5")
			.get(uri1 + "/Patient")
		)

	setUp(scn.inject(atOnceUsers(users))).protocols(httpProtocol)
}