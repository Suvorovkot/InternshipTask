package routes

import akka.http.scaladsl.server.Directives.{path, _}
import models.User
import repositories.UserRepository

//JSON marshalling/unmarshalling
import tools.Json4sSupport._

import scala.concurrent.ExecutionContext

//Adding execution context for running Futures
class UserRoute(implicit executionContext: ExecutionContext) {
  val userRepository = new UserRepository
  //Description of routes
  //Example: localhost:8080/users/name/Saul will respond with JSON, containing info about User with name "Saul"
  def routes = pathPrefix("users") {
    pathPrefix("id") {
      //Getting user by id
      path(IntNumber) { id =>
        get {
          complete(userRepository.getById(id))
        }
      }
    } ~ pathPrefix("update") {
      //Updating user by id
      path(IntNumber) { id =>
        patch {
          entity(as[User]) { params =>
            complete(userRepository.update(id, params))
          }
        }
      }
    } ~ pathPrefix("delete") {
      //Deleting user by id
      path(IntNumber) { id =>
        delete {
          complete(userRepository.delete(id))
          }
        }
    } ~ pathPrefix("name") {
      //Getting user by name
      path(Remaining) { name =>
        get {
          complete(userRepository.getByName(name))
        }
      }
    } ~ pathPrefix("surname") {
      //Getting user by surname
      path(Remaining) { surname =>
        get {
          complete(userRepository.getBySurname(surname))
        }
      }
    } ~ get {
      //Getting all users
      complete(userRepository.getAll)
    } ~ pathPrefix("create") {
      //Creating new user
      post {
        entity(as[User]) { elem =>
          complete(userRepository.create(elem))
        }
      }
    }
  }
}
