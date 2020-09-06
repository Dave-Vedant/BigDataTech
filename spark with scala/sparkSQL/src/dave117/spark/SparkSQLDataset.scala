package dave117.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import org.apache.spark.sql._

object SparkSQLDataset {
  
  case class Person(id:Int, name:String, age:Int, numFriends:Int)
  
  // main function
  def main(args: Array[String]){
    
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    
   // use spark Session interface
    val spark = SparkSession
      .builder
      .appName("SparkSQLDataset")
      .master("local[*]")
      .getOrCreate()
    
      
    import spark.implicits._
    val schemaPeople = spark.read
      .option("header", "true")
      .option("inferSchema", "true")              // BUG : inter and infer 
      .csv("fakefriends.csv")
      //.as[Person]                                 // .as[Person] take schema and convert to "data set" (proper display)
    
    schemaPeople.printSchema()
    
    schemaPeople.createOrReplaceTempView("people")
    
    
    val teenagers = spark.sql("SELECT * FROM people WHERE age >=13 AND age <=19")
    
    val results = teenagers.collect()
    
    results.foreach(println)
    
    spark.stop()
      
  }
}