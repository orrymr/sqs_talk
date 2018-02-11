package consumer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

public class Consumer {
	AmazonSQS sqs;

	public Consumer(AmazonSQS sqs){
		this.sqs = sqs;
	}

	public void consume(String queueUrl){
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);

		List<Message> sqsJobs = sqs.receiveMessage(receiveMessageRequest).getMessages();

		System.out.println(sqsJobs.get(0).getBody());
	}
}
