package dave117.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

// First, make object for finding an average no of friends from the social network.

object FriendsByName {
  
  // function 
  def parseLine(line: String) = {
    val fields = line.split(",")         // split fields by , in line
    val name = fields(1)
    //val age = fields(2).toInt            // extract field according to title and convert to integer for math operation.
    val numFriends = fields(3).toInt
    //(age,numFriends)                    // create tuple
    (name,numFriends)
  }
  
  // create main function for action
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)           // log only print error (not full explaination of path)
    //val sc = new SparkContext("local[*]","FrindsByAge")     // parallelize by core (I have 4 cores) 
    val sc = new SparkContext("local[*]","FriendsByName")
    val lines = sc.textFile("../fakefriends.csv")
    val rdd = lines.map(parseLine)                          // apply parseLine function to convert tuple [its called lazy evaluation]
    
    /*
    // Apply MapReduce type logic
    val totalsByAge = rdd.mapValues(x => (x,1)).reduceByKey((x,y) => (x._1+ y._1, x._2+y._2))
    val averageByAge = totalsByAge.mapValues(x=> x._1/x._2)
    val averageFriends = averageByAge.collect()
   
    */
 
    val totalsByName = rdd.mapValues(x => (x,1)).reduceByKey((x,y) => (x._1+ y._1, x._2+y._2))
    val averageByName = totalsByName.mapValues(x=> x._1/x._2)
    val averageFriends = averageByName.collect()    
    // print in sorted way
    averageFriends.sorted.foreach(println)
  }
  
// DONE - @dave117 - keep learning, enjoy empowering
  
  
  
  
  
  
  
}