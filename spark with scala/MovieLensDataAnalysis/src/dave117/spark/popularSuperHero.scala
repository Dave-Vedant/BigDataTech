package dave117.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object popularSuperHero {
  def coOccurances(line:String) = {
    var elements = line.split("\\s+")
    (elements(0).toInt, elements.length - 1)
  }
  
  def parseNames(line: String) : Option[(Int,String)] = {
    var fields = line.split('\"')
    if(fields.length >1){
      return Some(fields(0).trim().toInt, fields(1))
    } else {
      return None 
    }
  }
  
  def main(args: Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR)
   
    
    val sc = new SparkContext("local[*]", "MostPopularSuperHero")
    val names = sc.textFile("../Marvel-names.txt")
    val namesRdd = names.flatMap(parseNames)
    
    val lines = sc.textFile("../Marvel-graph.txt")    
    val pairings = lines.map(coOccurances)
    val totalFriendsByCharacter = pairings.reduceByKey((x,y) => x+y)
    val flipped = totalFriendsByCharacter.map(x=>(x._2,x._1))
    val mostPopular = flipped.max()
    val mostPopularName = namesRdd.lookup(mostPopular._2)(0)
    
    println(s"$mostPopularName is the most popular superhero with ${mostPopular._1} co-apperances.") 
  }
}