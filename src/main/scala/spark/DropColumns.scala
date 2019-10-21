package spark

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object DropColumns extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row("1", "1,0,0,0,0", "FN"),
    Row("2", "0,,0,0,0,0", "TN"),
    Row("3", "0,,0,0,0,0", "TP"),
    Row("4", "0,,0,0,0,0", "FP"),
    Row("5", "1,0,0,0,0", "TN")
  )

  val someSchema1 = List(
    StructField("c1", StringType, true),
    StructField("c2", StringType, true),
    StructField("name", StringType, true)
  )
  var df1 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  df1.show(false)
  val dropCols = List("c2")
  val df = df1.drop(dropCols:_*)

  println("\n****************************************************************************\n")
  df.show(false)
}