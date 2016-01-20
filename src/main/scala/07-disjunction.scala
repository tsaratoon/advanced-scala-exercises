import scalaz.\/
import scalaz.syntax.either._  // this is the syntax for \/, not either (don't ask...)

object DisjunctionExample {
  case class User(username: String, password: String)

  val users = List(
    User("alice"   , "god"),
    User("bob"     , "love"),
    User("charlie" , "sex"),
    User("dan"     , "secret"))

  type MightFail[A] = String \/ A

  // TODO:
  //  - Implement the three methods below:
  //     - readString succeeds if the args array is long enough
  //     - fetchUser succeeds if the user is in the list
  //     - checkPassword succeeds if the password is correct
  //  - Implement the for-comprehension below

  def readString(args: Array[String], num: Int): MightFail[String] =
    if (args.length > 0)
      args(num).right
    else
      "Not enough command line arguments".left

  def fetchUser(username: String): MightFail[User] = {
    val user = users.find(user => user.username == username)
    user match {
      case Some(user) => user.right
      case None       => "User not found".left
    }
  }

  def checkPassword(user: User, password: String): MightFail[User] =
    if (user.password == password)
      user.right
    else
      "Incorrect password".left

  def main(args: Array[String]): Unit = {
    val authedUser: String \/ User =
      for {
        username <- readString(args, 0)
        password <- readString(args, 1)
        unauthed <- fetchUser(username)
        authed   <- checkPassword(unauthed, password)
      } yield authed
      
    println(authedUser)
  }
}
