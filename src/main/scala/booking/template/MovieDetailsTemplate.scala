package booking.template

trait MovieDetailsTemplate {
  def findMovieTitle(imdb: String): Option[String]
}
