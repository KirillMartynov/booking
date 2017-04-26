package booking.service.impl

import booking.domain.{MovieDescriptor, ReservationDetails}
import booking.exception.InvalidOperationException
import booking.service.ReservationService
import org.scalatest.{FlatSpec, Matchers}

class ReservationServiceImplSpec extends FlatSpec with Matchers {
  val movie = MovieDescriptor("tt11", "id2")

  "Reservation service" should "be created successfully" in {
    val service = createInstance

    service should not be null
  }

  "Reservation service" should "throw IllegalArgumentException when registering movie is null" in {
    val service = createInstance
    a [IllegalArgumentException] should be thrownBy {
      service.registerMovie(null, 10)
    }
  }

  "Reservation service" should "throw IllegalArgumentException when registering movie if number of seats is zero" in {
    val service = createInstance
    a [IllegalArgumentException] should be thrownBy {
      service.registerMovie(MovieDescriptor("tt11", "id1"), 0)
    }
  }

  "Reservation service" should "throw IllegalArgumentException when registering movie if number of seats is less than zero" in {
    val service = createInstance
    a [IllegalArgumentException] should be thrownBy {
      service.registerMovie(movie, -1)
    }
  }

  "Reservation service" should "successfully register a new movie" in {
    // given
    val service = createInstance

    // when
    service.registerMovie(movie, 1)

    // then
    val details = service.getReservationDetails(movie)
    details shouldEqual ReservationDetails(1, 0)
  }

  "Reservation service" should "throw InvalidOperationException when movie with the same id is already registered" in {
    // given
    val service = createInstance
    service.registerMovie(movie, 1)

    // when
    a [InvalidOperationException] should be thrownBy {
      service.registerMovie(movie, 2)
    }
  }

  "Reservation service" should "reserve one seat" in {
    // given
    val service = createInstance
    service.registerMovie(movie, 1)

    // when
    service.reserveSingleSeat(movie)

    // then
    val reservationDetails = service.getReservationDetails(movie)
    reservationDetails shouldEqual ReservationDetails(1, 1)
  }

  "Reservation service" should "throw InvalidOperationException when reserving one seat if no free seat available" in {
    // given
    val service = createInstance
    service.registerMovie(movie, 1)
    service.reserveSingleSeat(movie)

    // when
    a [InvalidOperationException] should be thrownBy {
      service.reserveSingleSeat(movie)
    }
  }

  private def createInstance: ReservationService = {
    new ReservationServiceImpl
  }
}
