package consumer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

public class Consumer {
	AmazonSQS sqs;

	public Consumer(AmazonSQS sqs){
		this.sqs = sqs;
	}

	public void consume(String queueUrl){
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl)
				.withWaitTimeSeconds(10)
				.withMaxNumberOfMessages(10); //Max is ten

		List<Message> sqsJobs = sqs.receiveMessage(receiveMessageRequest).getMessages();

		System.out.println(String.format("Consumer received %s jobs... (but will only work on 1)", sqsJobs.size()));

		sqs.deleteMessage(new DeleteMessageRequest()
			.withQueueUrl(queueUrl)
			.withReceiptHandle(sqsJobs.get(0).getReceiptHandle()));

		System.out.println(sqsJobs.get(0).getBody());
	}
}
