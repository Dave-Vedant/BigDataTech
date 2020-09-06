package dave117.spark

import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

// create object 
object FriendsByAge{
  
  // generate class
  case class FakeFriends(id: Int, name: String, age: Int, numberFriends: Long)
  def main(args: Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // define spark session 
    val spark = SparkSession
      .builder 
      .appName("FriendsByAge")
      .master("local[*]")
      .getOrCreate()
     
    // convert schema to data sets
    import spark.implicits._
    val df = spark.read
      .option("header", "true")
      .option("inferSchema","true")
      .csv("fakefriends.csv")
      .as[FakeFriends]
    
    // generate results
    val friendsByAge = df.select("age","numberfriends")
    // groupby age with average numberFrinedsn
    friendsByAge.groupBy("age").avg("numberFriends").show()
    // sort by age
    friendsByAge.groupBy("age").avg("numberFriends").sort("age").show()
    // define decimal values for the average age 
    friendsByAge.groupBy("age").agg(round(avg("numberFriends"),2)).sort("age").show()
    // final required result with proper data table.
    friendsByAge.groupBy("age").agg(round(avg("numberFriends"),2).alias("friends_avg")).sort("age").show()   

  }
}