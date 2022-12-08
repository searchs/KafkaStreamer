package uk.co.softcreative.datanyltics.eventdriven

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.functions.expr

object KafkaStreamOne extends  Serializable {

  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[3]")
      .appName("Kafka Stream application")
      .config("spark.streaming.stopGracefullyOnShutdown", "true")
      .config("spark.sql.streaming.schemaInference", "true")
      .config("spark.sql.shuffle.partitions", "3")
      .getOrCreate()



    logger.info("\n\t#### Event-driven : Message-driven application  ####\n")



  }



}
