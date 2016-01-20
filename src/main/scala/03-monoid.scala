import scalaz.Monoid

object MonoidExample {
  // TODO:
  //  - Complete the implementation of `add1`,
  //    which sums a List of Ints without using Monoids:
  def add1(items: List[Int]): Int =
    items.foldLeft(0)(_ + _)

  println(add1(List(1, 2, 3, 4, 5)))

  // TODO:
  //  - Complete the implementation of `add2`.
  //    This time, summon a Monoid for Int
  //    and use Monoid syntax to sum the list
  def add2(items: List[Int]): Int = {
    import scalaz.std.anyVal._
    import scalaz.syntax.monoid._
    items.foldLeft(mzero[Int])(_ |+| _)
  }

  println(add2(List(1, 2, 3, 4, 5)))

  // TODO:
  //  - Write an `add3` method that takes two parameters:
  //     - a List[A]
  //     - an implicit Monoid[A]
  //  - Use the monoid to sum the list
  def add3[A](items: List[A])(implicit m: Monoid[A]): A = {
    import scalaz.syntax.monoid._
    items.foldLeft(mzero[A])(_ |+| _)
  }

  // TODO:
  //  - Use `add3` to sum the following lists.
  //    You may need to import certain instances of Monoid
  //    to make your code compile:
   
   import scalaz.std.anyVal._
   println(add3(List(1, 2, 3, 4, 5)))
   import scalaz.std.string._
   println(add3(List("1", "2", "3", "4", "5")))
   import scalaz.std.option._
   println(add3(List(None, Some(1), Some(2), Some(3), Some(4), Some(5))))

  // TODO:
  //  - Use `add3` to *multiply together* the following list of ints.
  //    You'll need to select the multiplication Monoid
  //    instead of the default addition monoid

  import scalaz.Tags.Multiplication
  println(add3(List(1, 2, 3, 4, 5).map(Multiplication(_))))

  // This makes this module runnable using `sbt run`:
  def main(args: Array[String]): Unit = ()
}