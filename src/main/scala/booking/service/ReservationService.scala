package booking.service

import booking.domain.{MovieDescriptor, ReservationDetails}

trait ReservationService {
  def registerMovie(movie: MovieDescriptor, numberOfSeats: Int): Unit

  def reserveSingleSeat(movie: MovieDescriptor): Unit

  def getReservationDetails(movie: MovieDescriptor): ReservationDetails
}
