package repositories

import controllers.DatabaseConnection
import models.{User, UsersTable}

import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._

//Repository for User
//Implementation of CRUD actions with slick
class UserRepository(implicit executionContext: ExecutionContext) {
  val dataBase = new DatabaseConnection

  //Returns List of all users
  def getAll(): Future[List[User]] = Future {
    dataBase.db withSession { implicit session =>
      val users = TableQuery[UsersTable]
      users.list.sortBy(_.name)
    }
  }

  //Returns User with given id
  def getById(stId: Int): Future[User] = Future {
    dataBase.db withSession { implicit session =>
      val users = TableQuery[UsersTable]
      val u     = users.filter(_.id === stId).list
      if (!u.isEmpty) {
        u.head
      } else {
        throw new NoSuchElementException(s"Could not find user with id: $stId")
      }
    }
  }

  //Returns List of users with given name
  def getByName(uName: String): Future[List[User]] = Future {
    dataBase.db withSession { implicit session =>
      val users = TableQuery[UsersTable]
      val u     = users.filter(_.name === uName).list
      if (!u.isEmpty) {
        u.sortBy(_.name)
      } else {
        throw new NoSuchElementException(s"Could not find user with name: $uName")
      }
    }
  }

  //Returns List of users with given surname
  def getBySurname(uSurname: String): Future[List[User]] = Future {
    dataBase.db withSession { implicit session =>
      val users = TableQuery[UsersTable]
      val u     = users.filter(_.surname === uSurname).list
      if (!u.isEmpty) {
        u.sortBy(_.surname)
      } else {
        throw new NoSuchElementException(s"Could not find user with name: $uSurname")
      }
    }
  }


  //Returns List of users, which have the same name or surname as given
  def getByNameOrSurname(uName: String): Future[List[User]] = Future {
    dataBase.db withSession { implicit session =>
      val users = TableQuery[UsersTable]
      val u = (users.filter(_.name === uName).list ::: users.filter(_.surname === uName).list)
      if (!u.isEmpty) {
        u.sortBy(_.id)
      } else {
        throw new NoSuchElementException(s"Could not find user with name or surname: $uName")
      }
    }
  }

  //Creates new User in base, returns number of rows inserted
  def create(params: User): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val users = TableQuery[UsersTable]
      val n     = users.insert(params)
      s"$n row inserted"
    }
  }

  //Updates User in base by id, returns number of rows updated
  def update(uId: Int, params: User): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val users = TableQuery[UsersTable]
      val n     = users.filter(_.id === uId).update(params)
      s"$n row updated"
    }
  }

  //Deletes User in base by id, returns number of rows deleted
  def delete(uId: Int): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val users = TableQuery[UsersTable]
      val n     = users.filter(_.id === uId).delete
      s"$n row deleted"
    }
  }
}
