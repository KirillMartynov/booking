package booking.api.request

import scala.beans.BeanProperty

class RegisterMoveRequest {
  @BeanProperty var imdbId: String = _
  @BeanProperty var screenId: String = _
  @BeanProperty var availableSeats: Int = _
}
