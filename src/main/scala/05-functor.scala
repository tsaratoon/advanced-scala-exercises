import scala.language.higherKinds
import scalaz.Functor
import scalaz.std.list._
import scalaz.std.option._
import scalaz.std.stream._

object FunctorExample {
  // TODO:
  //  - Define your own Functor type class
  
  // Functor for Future, Option, List, but NOT for Future[Int], Option[Int], List[Int]
  // So the F in Functor[F] refers to the functor, which is on any type
  // and so we need to include the [_] to make it a functor on a higher-kinded type
  // The [] is used when you declare the type parameter, and nowhere else
  
//  trait Functor[F[_]] {
//    def map[A,B](input: F[A])(func: A => B): F[B]
//  }
  
  //  - Define instances for List and Option
  //  - Use these definitions to write an `addOneEverywhere` method
  //    that accepts an `F[Int]` (for some arbitrary `F`) and adds 1 to the contents
  
//  implicit val listFunctor: Functor[List] = new Functor[List] {
//    def map[A,B](input: List[A])(func: A => B): List[B] =
//      input.map(func)
//  }
//  
//  implicit val optionFunctor: Functor[Option] = new Functor[Option] {
//    def map[A,B](input: Option[A])(func: A => B): Option[B] = {
////      input.map(func)
//      // Pretend Option doesn't have a map function:
//      input match {
//        case Some(value) => Some(func(value))
//        case None        => None
//      }
//    } 
//  }
  
  def addOneEverywhere[F[_]](value: F[Int])(implicit functor: Functor[F]): F[Int] =
    functor.map(value)(_ + 1)

   println(addOneEverywhere(Option(1)))
   println(addOneEverywhere(List(1, 2, 3, 4, 5)))

  // TODO:
  //  - Replace your definition of Functor with Scalaz' definition
  //  - Make everything compile again
  //  - Use Scalaz to add 1 to the numbers in this Stream:

   println(addOneEverywhere(Stream(1, 2, 3, 4, 5)))

  def main(args: Array[String]) = ()
}
