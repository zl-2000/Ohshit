package cn.itcast;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
public class SparkToMongoUtil {
    public static JavaSparkContext getSparkconn (
            String username,
            String password,
            String host,
            String port,
            String source) {
        SparkSession spark = SparkSession.builder()
                .master("local")//在idea中运行,即本地模式运行
                .appName("MongoSparkconnectorIntro")
                .config("spark.mongodb.input.uri", "mongodb://" + username + ":" + password + "@" + host + ":"//指定写人数据的数据库和集合 "
                        + port + "/data.test?authSource=" + source)
                .config("spark.mongodb.output.uri", "mongodb:/!" + username + ":"
                        + password + "@"
                        + host + ":"
                        // mongoproject.avgprice指定写人数据的数据库和集合
                        + port + " /data.testrs?authsource= " + source)
                .getOrCreate();
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
        return jsc;
    }};