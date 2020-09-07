package dave117.spark 

import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DoubleType, IntegerType, StructType}

// create object

object TotalSpendingByCustomer {
  
  // define case class
  case class CustomerOrders(customer_id:Int, item_id: Int, amount_spent:Double)
  
  // define main functions for flow
  def main(args: Array[String]){
    
    // generate logger for flow trace
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // define spark session 
    val spark = SparkSession
      .builder 
      .appName("CustomerTotalSpeding")
      .master("local[*]")
      .getOrCreate()
    
    // generate schema for read data 
    val customerOrderSchema = new StructType()
      .add("customer_id", IntegerType, nullable=true)
      .add("iterm_id", IntegerType, nullable=true)
      .add("amount_spent", DoubleType, nullable=true)
      
    // convert schema to datasets
    import spark.implicits._
    val df = spark.read
      .schema(customerOrderSchema)
      .csv("customer-orders.csv")
      .as[CustomerOrders]
    
    // SQL logic queries
    val totalByCustomer = df.groupBy("customer_id").agg(round(sum("amount_spent"),2).alias("Total Spending By Customers"))
    val totalSpentByCustomerSorted = totalByCustomer.sort("amount_spent")
    totalSpentByCustomerSorted.show(totalSpentByCustomerSorted.count.toInt)
        
      
         
  }
}