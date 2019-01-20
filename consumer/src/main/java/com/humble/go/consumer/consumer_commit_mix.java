package com.humble.go.consumer;
import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

//import org.json

public class consumer_commit_mix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String topicName ="stud1-details";

		String Name ="student1";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("group.id", "StudentDetails");
		props.put("id.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList(topicName));

		try{
			HashMap<String, Integer> stuNameMap = new HashMap<String,Integer>();
			Integer updatedCount;
			while (true){
			    ConsumerRecords<String, String> records = consumer.poll(100);
			     for(ConsumerRecord<String, String> record : records){
			    	System.out.printf("Topic =%s,partition=%s, offset = %d,"+
			    			"Student=%s, marks= %s\n",record.topic(),record.partition(),record.offset(),
			    			record.key(),record.value());
			    }
			     consumer.commitAsync();
			}
		}finally{
			try{
				consumer.commitSync();
			}finally{				
				consumer.close();
				System.out.println("Consumer closed.");
			}
		}
	}

}
