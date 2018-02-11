package producer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.UUID;

public class Producer {

	AmazonSQS sqs;

	public Producer(AmazonSQS sqs) {
		this.sqs = sqs;
	}

	public void produce() {

//		List<SendMessageBatchRequestEntry> messages = new ArrayList<>();
//
//		for (int i = 0; i < numMessages; i++) {
//			String uuid = UUID.randomUUID().toString();
//			SendMessageBatchRequestEntry sendMessageBatchRequestEntry = new SendMessageBatchRequestEntry(uuid, "There is work to be done");
//			messages.add(sendMessageBatchRequestEntry);
//		}
//
//
//		SendMessageBatchRequest sendMessageBatchRequest = new SendMessageBatchRequest().withEntries(messages);

		String uuid = UUID.randomUUID().toString();
		SendMessageRequest sendMessageRequest = new SendMessageRequest()
				.withMessageBody("There is work to be done. " + uuid);
		sqs.sendMessage(sendMessageRequest);
		System.out.println(String.format("Produced message %s", uuid));
	}
}
