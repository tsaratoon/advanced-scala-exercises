//import scalaz.Equal            // Import typeclass
//import scalaz.std.string._     // Import instances 
//import scalaz.std.anyVal._     // Import instances
//import scalaz.syntax.equal._   // Import syntax

object EqualExample {
  def example1() = {
    // TODO:
    //  - Summon an instance of `Equal` for `Int`
    //  - Use the `Equal.equal` method to compare some Ints
    import scalaz.Equal
    import scalaz.std.anyVal._
    
    val x: Equal[Int] = Equal[Int]
    println(x.equal(1, 2))
    println(x.equal(1, 1))
//    println(x.equal(1, "a"))  // Should fail here
  }

  def example2() = {
    // TODO:
    //  - Do the same thing as above, but use the `===` syntax
    import scalaz.Equal
    import scalaz.std.anyVal._
    import scalaz.syntax.equal._
    
    println(1 === 2)
    println(1 === 1)
//    println(1 === "a")  // Should fail here
    
  }

  def example3() = {
    // TODO:
    //  - Do the same thing as above, but compare two Lists of Ints
    import scalaz.Equal
    import scalaz.std.anyVal._
    import scalaz.std.list._
    import scalaz.syntax.equal._
    
    println(List(1,2,3) === List(3,2,1))
    println(List(1,2,3) === List(1,2,3))
    
  }

  def example4() = {
    // TODO:
    //  - Do the same thing as above, but compare two Options of Ints
    import scalaz.Equal
    import scalaz.std.anyVal._
    import scalaz.std.option._
    import scalaz.syntax.equal._
    
    println(Option(1) === Some(2))
    println(Option(1) === Some(1))
    println(Option.empty[Int] === None)
  }

  def main(args: Array[String]): Unit = {
    example1()
    example2()
    example3()
    example4()
  }
}