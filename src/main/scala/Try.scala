object Try {

}
import org.mongodb.scala.MongoClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
object MongoConnector {
  val uri: String = "mongodb://localhost:27017"
  val dbName: String = "mojo"
  def getCollection(collection: String) = MongoClient(uri).getDatabase(dbName).getCollection(collection)
}
object Hello extends App{
  println("starting mongo operation")
  ////////////
  /// get Mongo client
  ///////////
  //val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
  ///////////
  /// get database on which want to work
  ///////////
  //val database: MongoDatabase = mongoClient.getDatabase("mydb")
  //////////
  /// get collection from fetched db
  //////////
  //val collection: MongoCollection[Document] = database.getCollection("test")
  //////////
  /// create document if want insert in fetched collection
  //////////
  //val doc: Document = Document("name" -> "MongoDB", "type" -> "database", "count" -> 1,
  // "info" -> Document("x" -> 203, "y" -> 102))
  //////////
  /// insert one doc in collection
  //////////
  //val res= collection.insertOne(doc)
  /*res.subscribe(new Observer[Any] {
    override def onNext(result: Any): Unit = println("inserted")
    override def onError(e: Throwable): Unit = println("failed")
    override def onComplete(): Unit = println("completed")
  })
  */
}