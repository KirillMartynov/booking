package booking.api.response

import scala.beans.BeanProperty

case class MovieInformationResponse(@BeanProperty imdbId: String,
                                    @BeanProperty screenId: String,
                                    @BeanProperty movieTitle: String,
                                    @BeanProperty availableSeats: Int,
                                    @BeanProperty reservedSeats: Int
                                   ) {
//  @BeanProperty
//  var imdbId: String = _
//
//  @BeanProperty
//  var screenId: String = _
//
//  @BeanProperty
//  var movieTitle: String = _
//
//  @BeanProperty
//  var availableSeats: Int = _
//
//  @BeanProperty
//  var reservedSeats: Int = _
}
