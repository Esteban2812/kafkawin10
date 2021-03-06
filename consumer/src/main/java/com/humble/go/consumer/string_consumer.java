package com.humble.go.consumer;


import java.util.*;

import javax.xml.datatype.Duration;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class string_consumer{
	
	public static void main(String args[]) throws Exception{
	
String topicName ="stud1-details";

String Name ="student1";

Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("group.id", "StudentDetails");
props.put("id.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
props.put(	"value.deserializer",			
		"org.apache.kafka.common.serialization.StringDeserializer"
		);
KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
consumer.subscribe(Arrays.asList(topicName));


try{
	Duration duration;
	while (true){
	    ConsumerRecords<String, String> records = consumer.poll(500);
	    for (ConsumerRecord<String, String> record : records){
	    		System.out.println("Topic="+record.topic());
	    		System.out.println("Partition="+record.partition());
	    		System.out.println("Offset="+record.offset());
	    		System.out.println("key="+record.key());
	    		
	            System.out.println("Student id= " + String.valueOf(record.value()) + " Student  Name = " + record.value() );
	            
	    }
	}
}finally{
	consumer.close();
}
}
}