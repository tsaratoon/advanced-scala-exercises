object ShowExample {

  // The type class -----------------------------

  trait Show[A] {
    def stringify(value: A): String
  }

  // Standard type class instances --------------

  // TODO:
  //  - You'll need to define some type class instances
  //    to finish the exercises below
  
  implicit val showForString = new Show[String] {
    def stringify(value: String): String =
      "\"" + value.replaceAll("\"", "\\\"") + "\"" 
  }
  
  implicit def showForOption[A](implicit showA: Show[A]) = new Show[Option[A]] {
    def stringify(value: Option[A]): String =
      value match {
        case Some(a) => a.stringify
        case None    => "<none>"
      }
  }
  
  implicit def showForList[A](implicit showA: Show[A]) = new Show[List[A]] {
    def stringify(value: List[A]): String =
      value.map(showA.stringify).mkString("[", ", ", "]")
  }
  
  // Type class interface -----------------------

  object Show {
    def apply[A](implicit showInstance: Show[A]): Show[A] =
      showInstance
  }

  // Extra syntax (optional) --------------------

  implicit class ShowOps[A](value: A) {
    def stringify(implicit showInstance: Show[A]): String =
      showInstance.stringify(value)

    def print(implicit showInstance: Show[A]): Unit =
      println(value.stringify)
  }

  // Demonstration:

  def main(args: Array[String]) = {
    // TODO:
    //  - Print a String to the console
    //    by explicitly summoning an instance of Show
    //    and using its stringify method
    
    val stringShow: Show[String] = Show[String]
    println(stringShow.stringify("This is a string"))
    
    // Using the extra syntax we don't need the val (or the println!):
    "This is another string".print

    //  - Print a List of Strings to the console
    //    using the syntax provided in ShowOps
    
    List("Hello", "world").print

    //  - Print a List of Options of Strings to the console
    //    using any method you like
    
    List(Some("Hello"), Some("world"), None).print
    
  }

}
