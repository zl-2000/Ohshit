package cn.itcast;
import cn.itcast.SparkToMongoUtil;
import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.bson.Document;
public class SparkMongoDao {
    private JavaSparkContext jsc;
    private final String username = "zl";
    private final String port = "27021";
    private final String host = "localhost";
    private final String password = "123456";
    private final String source = "admin";

    public SparkMongoDao() {
        jsc = SparkToMongoUtil
                .getSparkconn(username, password, host, port, source);
    }
    public JavaMongoRDD<Document> readFromMongoDB() {
        JavaMongoRDD<Document> customRdd = MongoSpark.load(jsc);
        return customRdd;
    }
    public void writeToMongoDB(JavaRDD<Document> data) {
        MongoSpark.save(data);
    }
    public void close() {
        jsc.close();
    }
}
