package dave117.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.io.Codec
import java.nio.charset.CodingErrorAction
import scala.io.Source


object PopularMovieDataMerge {
  def loadMovieNames() : Map[Int,String] = {
    implicit val codec = Codec("UTF-8")                   // to solve character encoding issue
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)
    
    var movieNames:Map[Int,String] = Map()
    val lines = Source.fromFile("../ml-100k/u.item").getLines()
    for (line <- lines) {
      var fields = line.split('|')
      if(fields.length > 1){
        movieNames += (fields(0).toInt -> fields(1))
      }
    }
    return movieNames
  }
  
  // Action function (main)
  
  def main(args:Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR)                                            // set logger for error name only
    val sc = new SparkContext("local[*]", "PopularMovieDataMerge")                           // give parallel action command for local machine (I have 4 core) 
    val nameDict = sc.broadcast(loadMovieNames)                                              // broadcast - for distributed programming (to all node- 
    val lines = sc.textFile("../ml-100k/u.data")                                             // load data
    val movies = lines.map(x=>(x.split("\t")(1).toInt,1))                                    // set for counting - Mapping job 
    val movieCounts = movies.reduceByKey( (x,y) => (x+y))                                    // reduce operation 
    val flipped = movieCounts.map(x=> (x._2,x._1))
    val sortedMovies = flipped.sortByKey()
    val sortedMoviesWithName = sortedMovies.map(x=>(nameDict.value(x._2),x._1) )             // merge data (movie name and rating with common ID)
    val results = sortedMoviesWithName.collect()
    results.foreach(println)
  }
}