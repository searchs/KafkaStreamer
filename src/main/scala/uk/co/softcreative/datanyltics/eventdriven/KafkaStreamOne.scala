package uk.co.softcreative.datanyltics.eventdriven

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object KafkaStreamOne extends Serializable {

  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[3]")
      .appName("Kafka Stream application")
      .config("spark.streaming.stopGracefullyOnShutdown", "true")
      .config("spark.sql.streaming.schemaInference", "true")
      .config("spark.sql.shuffle.partitions", "3")
      .getOrCreate()

//invoice data schema
    val schema = StructType(List(
      StructField("InvoiceNumber", StringType),
      StructField("CreatedTime", LongType),
      StructField("StoreID", StringType),
      StructField("PosID", StringType),
      StructField("CashierID", StringType),
      StructField("CustomerType", StringType),
      StructField("CustomerCardNo", StringType),
      StructField("TotalAmount", DoubleType),
      StructField("NumberOfItems", IntegerType),
      StructField("PaymentMethod", StringType),
      StructField("CGST", DoubleType),
      StructField("SGST", DoubleType),
      StructField("CESS", DoubleType),
      StructField("DeliveryType", StringType),
      StructField("DeliveryAddress", StructType(List(
        StructField("AddressLine", StringType),
        StructField("City", StringType),
        StructField("State", StringType),
        StructField("PinCode", StringType),
        StructField("ContactNumber", StringType)
      ))),
      StructField("InvoiceLineItems", ArrayType(StructType(List(
        StructField("ItemCode", StringType),
        StructField("ItemDescription", StringType),
        StructField("ItemPrice", DoubleType),
        StructField("ItemQty", IntegerType),
        StructField("TotalValue", DoubleType)
      )))),
    ))

    logger.info("\n\t#### Event-driven : Message-driven application  ####\n")

    val kafkaDF = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:29092")
      .option("subscribe", "invoices")
      .option("startingOffsets", "earliest")
      .load()

    kafkaDF.printSchema()
  }


}
