package booking.service.impl

import java.util.concurrent.ConcurrentHashMap

import booking.service.MovieInformationService
import booking.template.MovieDetailsTemplate

class MovieInformationServiceImpl(val template: MovieDetailsTemplate) extends MovieInformationService {
  private val cache = new ConcurrentHashMap[String, String]()

  override def findTitle(imdb: String): Option[String] = {
    if (cache.containsKey(imdb)) {
      Some(cache.get(imdb))
    } else {
      resolveAndCacheTitle(imdb)
    }
  }

  private def resolveAndCacheTitle(imdb: String): Option[String] = {
    val title = template.findMovieTitle(imdb)
    if (title.isDefined) {
      cache.putIfAbsent(imdb, title.get)
    }

    title
  }
}
