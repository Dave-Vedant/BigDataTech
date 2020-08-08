package dave117.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object WordCountBetter {
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "WordCountBetter")   
    val input = sc.textFile("../book.txt")
    val words = input.flatMap(x => x.split("\\W+"))                       // use NLTK function 
    val lowercaseWords = words.map(x => x.toLowerCase())                  // convert to lower case 
    val wordCounts = lowercaseWords.countByValue()                             
    wordCounts.foreach(println)
  }
}

