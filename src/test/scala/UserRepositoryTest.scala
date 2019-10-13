package fintech.school.RepostitoriesTests

import models.User
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{AsyncFlatSpec, Matchers}
import repositories.UserRepository

class UserRepositoryTest extends AsyncFlatSpec with Matchers with ScalaFutures {

  behavior of "user repository"

  val uRep = new UserRepository()

  val existUser = User(30, "Homer", "Simpson", "1956-05-12", "742 Evergreen Terrace")

  it should "get all users from users table" in {
    uRep.getAll.map(res => res should contain(existUser))
  }

  it should "get user by id from users table" in {
    uRep.getById(existUser.id).map(res => res.name shouldBe existUser.name)
  }

  it should "get user by name from users table" in {
    uRep.getByName(existUser.name).map(res => res.head.name shouldBe existUser.name)
  }

  it should "get user by surname from users table" in {
    uRep.getBySurname(existUser.surname).map(res => res.head.surname shouldBe existUser.surname)
  }

  it should "insert new row in users table" in {
    val newUser = User(1, "Youzver", "Examplov", "1999-01-01", "Somewhere")
    uRep.create(newUser).map(res => res shouldBe "1 row inserted")
  }

  it should "update row in users table" in {
    val updUser = User(1, "Youzver", "Examplov", "1999-01-01", "Somewhere")
    uRep.update(1, updUser).map(res => res shouldBe "1 row updated")
  }

  it should "delete row with given id in Users table" in {
    uRep.delete(1).map(res => res shouldBe "1 row deleted")
  }

}
