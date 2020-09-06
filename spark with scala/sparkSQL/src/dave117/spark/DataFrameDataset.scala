package dave117.spark

import org.apache.spark.sql._
import org.apache.log4j._

object DataFrameDataset{
  
  // case class for input schema
  case class Person(id:Int, name:String, age:Int, numFriends:Int)
  
  // function
  def main(args: Array[String]) {
    
    // generate logger for trace
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    //spark session creation 
    val spark = SparkSession
      .builder
      .appName("SparkDataFrameDataset")
      .master("local[*]")
      .getOrCreate()
      
    // implicit is for infer the data sets  
    import spark.implicits._
    val people = spark.read.format("csv")
      .option("sep", ";")
      .option("header","true")
      .option("inferSchema", "true")
      .load("fakefriends.csv")
      //.as[Person]                               // convert schema to data set
    
    println("Here is our Inferred coluns:")
    people.printSchema()
    
    println("Let's select the new column:")
    people.select("name").show()
    
    println("Filter out anyone over 21:")
    people.filter(people("age") <21).show()
    
    println("Group by age:")
    people.groupBy("age").count().show()
    
    println("Make everyone 10 years older:")
    people.select(people("name"), people("age") +10).show()
      
    
    
    
    
    
  }
}