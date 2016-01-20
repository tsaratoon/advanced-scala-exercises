import scalaz.Monoid
import scalaz.std.anyVal._
import scalaz.syntax.monoid._
import scala.concurrent.Future

object MapReduceExample {
  // TODO:
  
  //  - Implement `foldMap`
	def foldMap[A,B](list: List[A])(f: A => B)(implicit monoid: Monoid[B]): B = {
		list.foldLeft(mzero[B])(_ |+| f(_))
	}
	
  //  - Implement `parallelFoldMap`
	def parallelFoldMap[A,B](list: List[A])(f: A => B)(implicit monoid: Monoid[B]): Future[B] = {
	  val groups = list.grouped(Common.groupSize(list))
	  ???
	}
	
  //  - Use each method to sum the lengths of all words in "/usr/share/dict/words" (see below)
  //  - Replace your definition of foldMap with the built-in Scalaz definition
  

  def main(args: Array[String]) = {

    val list = List("Hello","world")
    println(foldMap(list)(s => s.length))
    
    println(Common.time(foldMap(Common.wordList)(_.length)))
    
//    println(Await.result(parallelFoldMap(Common.wordList)(_.length), 3.seconds))
  }
}
