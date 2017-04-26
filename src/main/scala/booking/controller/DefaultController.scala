package booking.controller

import booking.api.request.{MovieDescriptorRequest, RegisterMoveRequest}
import booking.api.response.{HelloWorldResponse, MovieInformationResponse}
import booking.config.ApplicationConfig
import booking.domain.MovieDescriptor
import booking.service.{MovieInformationService, ReservationService}
import booking.template.impl.MovieDetailsTemplateImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._

import scala.beans.BeanProperty
import scala.util.Random

@RestController
class DefaultController

object DefaultController {
  val ac = new AnnotationConfigApplicationContext(classOf[ApplicationConfig])
  val reservationService = ac.getBean("reservationService", classOf[ReservationService])
  val movieInformationService = ac.getBean("movieInformationService", classOf[MovieInformationService])

  @PostMapping(value = Array("movies"))
  def registerMovie(@RequestBody request: RegisterMoveRequest): Unit = {
    println(s"Registering ${request.imdbId}-${request.screenId} with seats ${request.availableSeats}")
    reservationService.registerMovie(MovieDescriptor(request.imdbId, request.screenId), request.availableSeats)
  }

  @PostMapping(value = Array("movies/seat/reserve"))
  def reserveSeat(@RequestBody request: MovieDescriptorRequest): Unit = {
    println(s"Reserving seat ${request.imdbId}-${request.screenId}")
    reservationService.reserveSingleSeat(MovieDescriptor(request.imdbId, request.screenId))
  }

  @PostMapping(value = Array("movies/information"))
  def getMovieInformation(@RequestBody request: MovieDescriptorRequest): MovieInformationResponse = {
    getMovieInformation(request.imdbId, request.screenId)
  }

  @GetMapping(value = Array("movies/{imdbId}/{screenId}"))
  def getMovieInformation(@PathVariable(name = "imdbId") imdbId: String, @PathVariable(name = "screenId") screenId: String): MovieInformationResponse = {
    val details = reservationService.getReservationDetails(MovieDescriptor(imdbId, screenId))
    val title = movieInformationService.findTitle(imdbId).getOrElse("Cannot find movie name")

    MovieInformationResponse(imdbId, screenId, title, details.numberOfSeats, details.reservedNumberOfSeats)
  }
}
