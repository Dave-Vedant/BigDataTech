package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/** Find the movies with the most ratings. */
object PopularMovies {
  def main(args: Array[String]) {                                               // generate function
    Logger.getLogger("org").setLevel(Level.ERROR)                               // set error function
    val sc = new SparkContext("local[*]", "PopularMovies")                      // parallelize action
    val lines = sc.textFile("../ml-100k/u.data")                                // call data
    val movies = lines.map(x => (x.split("\t")(1).toInt, 1))                    // generate tuple                  (mapping)
    val movieCounts = movies.reduceByKey( (x, y) => x + y )                     // count each movie - total .      (reduce job)
    val flipped = movieCounts.map( x => (x._2, x._1) )                          // set formatting
    val sortedMovies = flipped.sortByKey()                                      // sort by key (here, movie rating)
    val results = sortedMovies.collect()                                        // collect result
    results.foreach(println)                                                    // print result 
  }   
  
}

