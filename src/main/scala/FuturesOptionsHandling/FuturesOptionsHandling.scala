package FuturesOptionsHandling
import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global
object FuturesOptionsHandling extends App {
  case class Job(jobId: String, title: String, clicks: Option[Int] = None, applies: Option[Int] = None)

  case class ClicksStat(jobId: String, clicks: Int)

  case class AppliesStat(jobId: String, applies: Int)

  val jobs = Future.successful(List(Job("job1", "title1"), Job("job2", "title1"), Job("job3", "title3")))
  val clicks = Future.successful(List(ClicksStat("job2", 50)))
  val updatedjobs = Future.successful((List(Job("jobs1", "title1", None, None), Job("jobs2", "title2", Some(50), None), Job("jobs3", "title3", None, Some(150)))))
  val applies = List(AppliesStat("job3", 150))
  Thread.sleep(400)
  updatedjobs.onComplete {
    case Success(value) => println(value + "hello")
    case Failure(exception) => println("Something went wrong")
  }
  // If stats are not present clicks/Applies should be None
  /// It should contain => Future(List(Job("job1", "title1"), Job("job2", "title1", Some(50), None), Job("job3", "title3", None, Some(150))))
  val jobsEnrichedWithStats = Future {
    val finalupdatedjobs: Future[List[Job]] = updatedjobs.map(x => x.filter(y => (y.clicks != None || y.applies != None)))
    finalupdatedjobs
  }
  Thread.sleep(400)
  jobsEnrichedWithStats.onComplete {
    case Success(value) => println(value + "hello2")
    case Failure(exception: Exception) => println("something went wrong")
  }
  /// It should contain => Future(List(Job("job1", "title1"), Job("job2", "title1", Some(50), None), Job("job3", "title3", None, Some(150))))
  val jobsWithClicksNotNone = Future {
    val jobswithclicks: Future[List[Job]] = updatedjobs.map(x => x.filter(y => y.clicks != None))
    jobswithclicks
  }
  Thread.sleep(400)
  jobsWithClicksNotNone.onComplete {
    case Success(value) => println(value + "hello3")
    case Failure(exception: Exception) => println("something went wrong")
  }
  val jobsWithAppliesNotNone = Future {
    val jobswithApplies: Future[List[Job]] = updatedjobs.map(x => x.filter(y => y.applies != None))
    jobswithApplies
  }
  Thread.sleep(400)
  jobsWithAppliesNotNone.onComplete {
    case Success(value) => println(value + "hello4")
    case Failure(exception: Exception) => println("something went wrong")
  }
  //  title -> List[Job]
  var jobsGroupedByTitle:Map[String,Job] = Map.empty[String,Job]

 // println(updatedjobs)
  updatedjobs.onComplete{
    case Success(value)=> {
      var makemap:Map[String,Job] = Map.empty[String,Job]
      for (i <- value) {
        makemap = makemap + (i.title -> i)
      }
     // println(makemap)
      jobsGroupedByTitle = makemap
    }
    case Failure(exception) =>println("Something went wrong")
  }
  Thread.sleep(500)
  println(jobsGroupedByTitle)

  //val jobsGroupedByTitle = ???
  // Should return Map(title -> (sumClicks, sumApplies)). if clicks/applies is None, set its value as 0
  var statsPerTitle : Map[String,List[Int]] = Map.empty[String,List[Int]]

  // println(updatedjobs)
  updatedjobs.onComplete{
    case Success(value)=> {
      var makemap:Map[String,List[Int]] = Map.empty[String,List[Int]]
      for (i <- value) {
        var temp : List[Int] = List.empty
        if(i.clicks != None) temp = temp :+ i.clicks.get
        else
          temp = temp :+ 0
        if(i.applies != None) {
          temp = temp :+ i.applies.get
        }
        else
          temp = temp :+ 0
        makemap = makemap + (i.title -> temp)
      }
      // println(makemap)
      statsPerTitle = makemap
    }
    case Failure(exception) =>println("Something went wrong")
  }
  Thread.sleep(500)
  println(statsPerTitle)
}
