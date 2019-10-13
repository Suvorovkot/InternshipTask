package models

import scala.slick.driver.PostgresDriver.simple._


//Description for columns and linking shapes with values
class UsersTable(tag: Tag) extends Table[User](tag, "Users") {

  def id = column[Int]("id")

  def name = column[String]("name")

  def surname = column[String]("surname")

  def birthDate = column[String]("birthDate")

  def adress = column[String]("adress")

  def * = (id, name, surname, birthDate, adress) <> (User.tupled, User.unapply)
}
