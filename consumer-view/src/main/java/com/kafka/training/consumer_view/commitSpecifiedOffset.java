import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class commitSpecifiedOffset{
	public static void main(String args[]) throws Exception{
	
String topicName ="Employee";
Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092,localhost:9093");
props.put("group.id", "test");
props.put("enable.auto.commit", "false");
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
Consumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(Arrays.asList(topicName));
final Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>(); 

int count = 0; 
while (true) { 
	ConsumerRecords<String, String> records = consumer.poll(100); 
	
	for (ConsumerRecord<String, String> record : records) { 
	   System.out.printf("topic = %s, partition = %s, offset = %d,student = %s, marks = %s\n", record.topic(), record.partition(), record.offset(), record.key(), record.value()); 
	
	   currentOffsets.put(new TopicPartition(record.topic(),record.partition()), 
	   new OffsetAndMetadata(record.offset()+1, "no metadata")); 

	   if (count % 1000 == 0) 
		consumer.commitAsync(currentOffsets, null);
	   count++; 
	} 
} 
	}
}


