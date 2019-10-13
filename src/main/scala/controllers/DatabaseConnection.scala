package controllers

import scala.slick.driver.PostgresDriver.simple._

//This class opens session with database
//Default port for postgres is 5432
class DatabaseConnection {
  val dbConnectionUrl = s"jdbc:postgresql://localhost:5432/Users?user=postgres&password=postgres"
  val db = Database.forURL(dbConnectionUrl, driver = "org.postgresql.Driver")
}