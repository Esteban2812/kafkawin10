package com.humble.go.consumer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.*;

import kafka.common.OffsetAndMetadata;



public class HandleRebalance implements ConsumerRebalanceListener {
	private Map<TopicPartition, OffsetAndMetadata> currentOffSets = new HashMap<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private KafkaConsumer<String,String> consumer;
	public HandleRebalance(KafkaConsumer<String,String> consumer) {
		this.consumer = consumer;
	}
	
	public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		
	}
	
public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
		System.out.println("Lost partitions in rebalance. Committing current offsets:"+currentOffSets);
		this.consumer.commitSync
	}

}
