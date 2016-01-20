import scalaz.Monoid
import scalaz.std.anyVal._
import scalaz.syntax.monoid._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object MapReduceExample {
  // TODO:
  
  //  - Implement `foldMap`
	def foldMap[A,B](list: List[A])(f: A => B)(implicit monoid: Monoid[B]): B = {
		list.foldLeft(mzero[B])(_ |+| f(_))
	}
	
  //  - Implement `parallelFoldMap`
	def parallelFoldMap[A,B](list: List[A])(f: A => B)(implicit monoid: Monoid[B]): Future[B] = {
	  val groups = list.grouped(Common.groupSize(list))
	  
	  val groupFutures: Iterator[Future[List[A]]] =
	    groups.map(group => Future(group))
	    
	  val resultFutures: Iterator[Future[B]] = {
	    groupFutures.map { groupFuture =>
	      groupFuture.map { group: List[A] =>
	        foldMap(group)(f)
	      }
	    }
	  }
	  
	  // Wait for all futures to be completed before combining results
	  val resultsFuture: Future[Iterator[B]] =
	    Future.sequence(resultFutures)
	    
	  val resultFuture: Future[B] =
	    resultsFuture.map { resultsIterator =>
	      foldMap(resultsIterator.toList)(identity)  // Results are already computed, no need to do anything
	    }
	  
	  resultFuture
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
