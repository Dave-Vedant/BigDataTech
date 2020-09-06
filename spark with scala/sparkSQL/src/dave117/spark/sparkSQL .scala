package dave117.spark

 /** Flow: 
   *  import dependencies
   *  create object 
   *  	--- define class with properties data type
   *  	------ create split function to conver raw to class type
   *  	--- define main 
   *  	----- crate spark session (built and configure for parallel work with temp storage dictionary)
   *  	----- call file and give mapper
   *  	----- make data set schema, give name and print it.
   *  	----- give SQL query
   *  	----- collect and print result 
   *  
   */

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import org.apache.spark.sql._
//import org.apache.spark.sql.SparkSession

object sparkSQL  {
  case class Person(ID:Int, name:String, age:Int, numFriends:Int)
  
  def mapper(line:String): Person = {
    val fields = line.split(',')
    val person:Person = Person(fields(0).toInt, fields(1), fields(2).toInt, fields(3).toInt)
    return person
  }
  
  def main(args: Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val sparker = SparkSession                                // sparker is custom session name, do not confuse it to spark lib. You can give any name here 
     .builder
     .appName("sparkSQL")
     .master("local[*]")
     .config("spark.sql.warehouse.dir","file:///C:/temp")     // for windows (give data storage for temp condition)
     .getOrCreate()
     
     val lines = sparker.sparkContext.textFile("../fakefriends.csv")
     val people = lines.map(mapper) 
     
     // Infer tabular schema for data set 
     import sparker.implicits._
     val schemaPeople = people.toDS 
     schemaPeople.printSchema()
     schemaPeople.createOrReplaceTempView("people")
     
     val teenagers = sparker.sql("SELECT * FROM people WHERE age >=13 AND age<=19")
     val results = teenagers.collect()
     results.foreach(println)
   
     sparker.stop()                                                // stop the spark session which we .built. 
  }
  
 
}