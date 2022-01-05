package cn.itcast;
import com.google.gson.JsonObject;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org .apache.spark.api.java.function.* ;
import org.bson.Document;
import scala.Tuple2;
public class DeathRate {
    public static void main (String[ ] args){
        SparkMongoDao sparkMongoDao =new SparkMongoDao ();
        JavaMongoRDD< Document>mongoRDD = sparkMongoDao
                .readFromMongoDB();
        JavaPairRDD<String,Integer> sparkRDD =mongoRDD.mapToPair(
        new PairFunction<Document,String,Integer>(){
            @Override
            public Tuple2<String,Integer>call ( Document document) throws Exception //正则表达式去除字符串中的门
            {
                String regex = "\\(.*?\\)";
                String district =document.getString("district")
                    .replaceAll(regex,"");
                Integer unit_price =document
                    .getInteger ("unit_price");
                return new Tuple2<>(district, unit_price) ;
            }
        });
        JavaPairRDD<String,Iterable<Integer>>groupRDD=
                sparkRDD.groupByKey();
                JavaPairRDD< Tuple2<String,Integer>,Integer>mapRDD =
                groupRDD.mapToPair(
        new PairFunction<
                Tuple2<String,Iterable<Integer>>,
                Tuple2<String,Integer>,Integer>(){
        @Override

        public Tuple2< Tuple2<String,Integer >, Integer >call(Tuple2 < String,Iterable < Integer >> tuple2)throws Exception {
            int num = 0;
            int sum = 0;
            for (int i : tuple2._2) {
                sum = sum + i;
                num = num + 1;
            }
            Tuple2<String, Integer> tuple =
                    new Tuple2<>(tuple2._1, sum);
            return new Tuple2<>(tuple, num);
        }
        } );
            JavaPairRDD<String, Double> avgRDD =mapRDD.mapToPair(
                    new PairFunction<
                            Tuple2<Tuple2<String, Integer> , Integer>,String,Double> ( ){
                            @Override
            public Tuple2<String,Double>call(
                    Tuple2<Tuple2<String,Integer>,Integer>tuple2)throws Exception {
                                double confirmedCount = tuple2._1()._2.doubleValue();
                                double death = tuple2._2.doubleValue();
                                double death_rate = death/ confirmedCount;
                                double avg_num
                                        = (double) (Math.round(death_rate * 100)) / 100;
                                return new Tuple2<>(tuple2._1()._1, new Double(avg_num));
                            }
            });
            JavaRDD< Document>resultRDD = avgRDD.map(
                    new Function< Tuple2<String,Double>,Document> (){
                @Override
                public Document call (Tuple2<String,Double>tuple2)
throws Exception {
                    JsonObject resultJson = new JsonObject();
                    resultJson.addProperty("district", tuple2._1);
                    resultJson.addProperty("death_rate", tuple2._2);
                    Document.parse(resultJson.toString());
                    return Document.parse(resultJson.toString());
                }
                    });
                sparkMongoDao.writeToMongoDB (resultRDD);
                sparkMongoDao.close( ) ;}}
