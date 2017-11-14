package fileprocess

import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.{SparkConf,SparkContext}
import org.apache.spark.sql.hive.HiveContext
import org.apache.log4j.{Level,LogManager}

object Scala_exmpl extends Serializable {
  @transient lazy var log =LogManager.getLogger(getClass.getName)

  def main(args:Array[String]) :Unit={
    var input=args(0)
    var output= args(1)
    var RejOutput = arg(2)
    
   val conf=new SparkConf()
     .setAppNAme("Trial_App")
     .setMaster("yarn-client")
     .set("spark-serializer","org.apache.spark.serializer.KryoSerializer")
     
     val sc= new SparkContext(conf)
    val sqlContext(conf)
    import sqlContext.implicits._
    try{
      val schemastr="col1 col2 col3 col4 col5 col6 col 7 col8 col9"
      val schema= StrcutType(schemastr.split(" ").map(P=> StructField(P,StringType,true)))
      val finalFile= sc.textFile(input)
      val maxval= finalFile.map(_.split("\u0001")).map(x=>Row.fromSeq(x))
      val df=sqlContext.createDatFrame(maxval,schema)
      val filedata=df.withColumn("col1,col2,col3")
      val finaloutput=filedata.rdd.map(x=> x.mkString("\u0001"))
      finaloutput.saveAsTextFile(output)
    }catch{
      case ex:Exception => log.debug("Exception ",ex)
    }finally{
      log.info("Completed processing or errored out")
      sc.stop()
    }
  }
  
}