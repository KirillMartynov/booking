package booking.service

trait MovieInformationService {
  def findTitle(imdb: String): Option[String]
}
