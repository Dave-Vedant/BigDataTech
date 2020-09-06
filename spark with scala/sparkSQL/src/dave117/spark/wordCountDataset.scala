package dave117.spark

import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

// generate object 
object WordCountSortedDataset {
  
  // create class
  case class book(value:String)
  
  // define function flow
  def main(args: Array[String]){
    
    // set logggr for data trace
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // create spark session using every core of local machine 
    val spark = SparkSession
      .builder
      .appName("WordCount")
      .master("local[*]")
      .getOrCreate()
      
    import spark.implicits._
    val input = spark.read.text("book.txt").as[book]
    
    val words = input
      .select(explode(split($"value","\\W")).alias("word"))
      .filter($"word" =!= " ")
      
    val lowerCaseWords = words.select(lower($"word").alias("word"))
    
    val wordCounts = lowerCaseWords.groupBy("word").count()
    
    val wordCountsSorted = wordCounts.sort("count")
    
    wordCountsSorted.show(wordCountsSorted.count.toInt)
    
    
    // Other methods: 
    
    
    
    
      
  }
}