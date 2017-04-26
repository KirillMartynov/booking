package booking.service.impl

import booking.service.MovieInformationService
import booking.template.MovieDetailsTemplate
import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class MovieInformationServiceImplSpec extends FlatSpec with Matchers with MockFactory with BeforeAndAfter {
  var template: MovieDetailsTemplate = _
  val imdb = "imdb"
  val title = "movie title"

//
//  before {
//    withExpectations {
//      template = stub[MovieDetailsTemplate]
//      (template.getMovieTitle _).when(imdb).returns(title)
//    }
//  }


  "Movie information service" should "be created successfully" in {
    val service = createInstance

    service should not be null
  }

  "Movie information service" should "return title" in {
    // given
    template = stub[MovieDetailsTemplate]
    (template.findMovieTitle _).when(imdb).returns(Some(title))
    val service = createInstance

    // when
    val result = service.findTitle(imdb)

    // then
    result.isDefined shouldEqual true
    result.get shouldEqual title
    (template.findMovieTitle _).verify(imdb)
  }

  "Movie information service" should "cache title" in {
    // given
    template = stub[MovieDetailsTemplate]
    (template.findMovieTitle _).when(imdb).returns(Some(title))
    val service = createInstance
    service.findTitle(imdb)

    // when
    val result = service.findTitle(imdb)

    // then
    result.isDefined shouldEqual true
    result.get shouldEqual title
    (template.findMovieTitle _).verify(imdb)
  }

  "Movie information service" should "return None if movie was not found" in {
    // given
    template = stub[MovieDetailsTemplate]
    (template.findMovieTitle _).when(imdb).returns(None)
    val service = createInstance

    // when
    val result = service.findTitle(imdb)

    // then
    result.isEmpty shouldEqual true
    (template.findMovieTitle _).verify(imdb)
  }

  "Movie information service" should "not cache result if movie was not found" in {
    // given
    template = stub[MovieDetailsTemplate]
    (template.findMovieTitle _).when(imdb).returns(None)
    val service = createInstance
    service.findTitle(imdb)

    // when
    val result = service.findTitle(imdb)

    // then
    result.isEmpty shouldEqual true
    (template.findMovieTitle _).verify(imdb).twice()
  }

  private def createInstance(): MovieInformationService = {
    new MovieInformationServiceImpl(template)
  }
}
