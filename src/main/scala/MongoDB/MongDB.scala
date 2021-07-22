package MongoDB
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}
import org.bson.codecs.configuration.CodecRegistries._
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.ObjectId

import scala.language.postfixOps
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase, Observable, Observer}

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
/*object MongoConnector {
  val uri: String = "mongodb://localhost:27017"
  val dbName: String = "Mydb"
  def getCollection(collection: String) = MongoClient(uri).getDatabase(dbName).getCollection(collection)
}*/
object MongDB extends App{
  //println(MongoConnector.getCollection("test"))
  object Person {
    def apply(firstName: String, lastName: String): Person =
      Person(new ObjectId(), firstName, lastName)
  }

  val codecRegistry = fromRegistries(fromProviders(classOf[Person]), DEFAULT_CODEC_REGISTRY )
  val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
  val database: MongoDatabase = mongoClient.getDatabase("Mydb").withCodecRegistry(codecRegistry)
  val collection: MongoCollection[Person] = database.getCollection("test")
  val person: Person = Person("Ada", "Lovelace")
 /* val res=collection.insertOne(person)
  res.subscribe(new Observer[Any] {
    override def onNext(result: Any): Unit = println("inserted")
    override def onError(e: Throwable): Unit = println("failed")
    override def onComplete(): Unit = println("completed")
  })*/
  //Thread.sleep(5000)
  //Thread.sleep(5000)
  val res = Await.result(collection.updateOne(equal("name", "Mongodb"), set("name", "MongoDB")).toFuture(),10 seconds)
   println(res)
 // val res=Await.result(collection.find(equal("name","MongoDB")).first().toFuture(),10 seconds )
   //println(res)
  case class Person(_id: ObjectId, firstName: String, lastName: String)

}
