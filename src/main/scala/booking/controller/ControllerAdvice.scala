package booking.controller

import booking.exception.{InvalidOperationException, ResourceNotFoundException}
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{ExceptionHandler, RestControllerAdvice}

@RestControllerAdvice
class ControllerAdvice {

  @ExceptionHandler(Array(classOf[InvalidOperationException]))
  def handleException(e: InvalidOperationException): ResponseEntity[String] = {
    ResponseEntity.status(600).body(e.getMessage)
  }

  @ExceptionHandler(Array(classOf[ResourceNotFoundException]))
  def handleException(e: ResourceNotFoundException): ResponseEntity[String] = {
    ResponseEntity.notFound().build()
  }
}
