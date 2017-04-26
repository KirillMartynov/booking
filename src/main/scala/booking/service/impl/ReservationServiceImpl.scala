package booking.service.impl

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

import booking.domain.{MovieDescriptor, ReservationDetails}
import booking.exception.{InvalidOperationException, ResourceNotFoundException}
import booking.service.ReservationService

private class Reservation(val numberOfSeats: Int) {
  private val reservedNumberOfSeats: AtomicInteger = new AtomicInteger(0)

  def reserve(count: Int): Unit = {
    var successful = true
    do {
      val currentValue = reservedNumberOfSeats.get()
      if (currentValue + count > numberOfSeats) {
        throw new InvalidOperationException(f"Cannot reserve ${count} seat(s). It is fully booked")
      }

      successful = reservedNumberOfSeats.compareAndSet(currentValue, currentValue + count)
    } while (!successful)
  }

  def getReservedNumberOfSeats: Int = {
    reservedNumberOfSeats.get()
  }
}

class ReservationServiceImpl extends ReservationService {
  private val storage = new ConcurrentHashMap[MovieDescriptor, Reservation]()

  override def registerMovie(movie: MovieDescriptor, numberOfSeats: Int): Unit = {
    require(movie != null)
    require(numberOfSeats > 0)

    val reservation = storage.putIfAbsent(movie, new Reservation(numberOfSeats))

    if (reservation != null) {
      throw new InvalidOperationException(f"Movie with imdb ${movie.imdb} and screen id ${movie.screenId} is already registered")
    }
  }

  override def reserveSingleSeat(movie: MovieDescriptor): Unit = {
    val reservation = getMovieReservationOrThrowException(movie)
    reservation.reserve(1)
  }

  override def getReservationDetails(movie: MovieDescriptor): ReservationDetails = {
    val reservation = getMovieReservationOrThrowException(movie)
    ReservationDetails(reservation.numberOfSeats, reservation.getReservedNumberOfSeats)
  }

  private def getMovieReservationOrThrowException(movie: MovieDescriptor): Reservation = {
    val reservation = storage.get(movie)

    if (reservation == null) {
      throw new ResourceNotFoundException()
    }

    reservation
  }
}