package booking.config

import booking.service.{MovieInformationService, ReservationService}
import booking.service.impl.{MovieInformationServiceImpl, ReservationServiceImpl}
import booking.template.impl.MovieDetailsTemplateImpl
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

@Configuration
@EnableAutoConfiguration
@ComponentScan
class ApplicationConfig {

  @Bean
  def reservationService(): ReservationService = new ReservationServiceImpl

  @Bean
  def movieInformationService(): MovieInformationService = new MovieInformationServiceImpl(new MovieDetailsTemplateImpl)
}
