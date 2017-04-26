package booking

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class Application

object Application extends App {
  override def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args:_*)
  }
}
