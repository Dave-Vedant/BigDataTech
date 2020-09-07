package dave117.spark

import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{FloatType, IntegerType, StringType, StructType}

// define object for minMaxTemperature
object MinMaxTempDataset{
  
  // generate case class
  case class Temperature(stationID: String, data:Int, measure_type:String, temperature:Float)
  
  // define function 
  def main(args: Array[String]) {
    
    // set logger for flow trace
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // create sparkSession using the local machine cores
    val spark = SparkSession
      .builder   
      .appName("MinMaxTemperature")
      .master("local[*]")
      .getOrCreate()
      
      
    // generate schema (not generated yet)
    val temperatureSchema = new StructType()
      .add("stationID", StringType, nullable=true)
      .add("date", IntegerType, nullable= true)
      .add("measure_type", StringType, nullable= true)
      .add("temperature", FloatType, nullable=true)
      
      
    // Read file as Dataset (convert schema to Dataset)
    import spark.implicits._
    val df = spark.read
      .schema(temperatureSchema)
      .csv("1800.csv")
      .as[Temperature]
    
    // Filterout Min data entries
    val minTemps = df.filter($"measure_type" === "TMIN")
    // select stationID and temperature
    val stationTemps = minTemps.select("stationID", "temperature")
    // minTemp per station 
    val minTempsByStation = stationTemps.groupBy("stationID").min("temperature")
    
    // convert to fahrenheit and sort the data
    val minTempsByStationFrnht = minTempsByStation
      .withColumn("temperature", round($"min(temperature)" * 0.1f * (9.0f/5.0f) + 32.0f, scale=2))
      .select("stationID", "temperature").sort("temperature")
      
    val results = minTempsByStationFrnht.collect()
    
    for (result <- results) {
      val station = result(0)
      val temp = result(1).asInstanceOf[Float]
      val formattedTemp = f"$temp%.2f F"
      println(s"$station minimum temperature :  $formattedTemp" )
    }     
  }
}