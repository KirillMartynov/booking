package booking.template.impl

import java.net.URI

import booking.template.MovieDetailsTemplate
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.RequestEntity
import org.springframework.web.client.RestTemplate

import scala.beans.BeanProperty

private sealed class MovieDetailsResponse {
  @BeanProperty
  @JsonProperty("Title")
  var title: String = _

  @BeanProperty
  @JsonProperty("Error")
  var error: String = _
}

class MovieDetailsTemplateImpl extends MovieDetailsTemplate {
  override def findMovieTitle(imdb: String): Option[String] = {
    val template = new RestTemplate()
    val entity = RequestEntity.get(new URI(s"http://www.omdbapi.com/?i=$imdb&r=json")).build()
    val response = template.exchange(entity, classOf[MovieDetailsResponse])
    val details = response.getBody
    if (details.error != null) None else Some(details.title)
  }
}
