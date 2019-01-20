import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class custom_consumer{
	public static void main(String args[]) throws Exception{
	
String topicName ="stud1-details";

String Name ="student1";

Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092,localhost:9093");
props.put("group.id", "StudentDetails");
props.put("id.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer","custom_deserializer");
KafkaConsumer<String, serializer> consumer = new KafkaConsumer<String, serializer>(props);
consumer.subscribe(Arrays.asList(topicName));

while (true){
    ConsumerRecords<String, serializer> records = consumer.poll(100);
    for (ConsumerRecord<String, serializer> record : records){
            System.out.println("Student id= " + String.valueOf(record.value().getID()) + " Student  Name = " + record.value().getName() );
    }
}
}
}