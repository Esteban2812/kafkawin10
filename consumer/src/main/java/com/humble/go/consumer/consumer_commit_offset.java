package com.humble.go.consumer;
import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.json.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

//import org.json

public class consumer_commit_offset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String topicName ="newStudents";

		String Name ="student1";

		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.1.107:9092");
		props.put("group.id", "StudentDetails");
		props.put("id.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList(topicName));

			HashMap<String, Integer> stuNameMap = new HashMap<String,Integer>();
			Integer updatedCount;
			
			int count = 0;
			
			final Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
			
			while (true){
			    ConsumerRecords<String, String> records = consumer.poll(100);
			     for(ConsumerRecord<String, String> record : records){
			    	System.out.printf("Topic =%s,partition=%s, offset = %d,"+
			    			"Student=%s, marks= %s\n",record.topic(),record.partition(),record.offset(),
			    			record.key(),record.value());
			    	
			    	currentOffsets.put(
			    			new TopicPartition(record.topic(), record.partition()),
			    			new OffsetAndMetadata(record.offset()+1,"no metadata")			
			    			);
			    	
			    	if(count % 10 == 0){
			    		consumer.commitAsync(currentOffsets,null);
			    		System.out.println("commit done for offset "+count);
			    	}
			    	
			    	count++;
			    }
			     
			}
		
	}

}
