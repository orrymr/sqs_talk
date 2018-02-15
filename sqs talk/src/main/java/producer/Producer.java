package producer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Producer {

	AmazonSQS sqs;

	public Producer(AmazonSQS sqs) {
		this.sqs = sqs;
	}

	public void produce() {
		int numMessages = 10; // This is the maximum
		List<SendMessageBatchRequestEntry> messages = new ArrayList<>();

		for (int i = 0; i < numMessages; i++) {
			String uuid = UUID.randomUUID().toString();
			SendMessageBatchRequestEntry sendMessageBatchRequestEntry = new SendMessageBatchRequestEntry(uuid, "There is work to be done");
			messages.add(sendMessageBatchRequestEntry);
		}

		SendMessageBatchRequest sendMessageBatchRequest = new SendMessageBatchRequest().withEntries(messages);
		sqs.sendMessageBatch(sendMessageBatchRequest);
	}
}
