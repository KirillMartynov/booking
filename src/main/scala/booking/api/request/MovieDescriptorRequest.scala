package booking.api.request

import scala.beans.BeanProperty

class MovieDescriptorRequest {
  @BeanProperty var imdbId: String = _
  @BeanProperty var screenId: String = _
}
