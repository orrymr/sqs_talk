package producer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.UUID;

public class Producer {

	AmazonSQS sqs;

	public Producer(AmazonSQS sqs){
		this.sqs = sqs;
	}

	public void produce(){
		String uuid = UUID.randomUUID().toString();
		SendMessageRequest sendMessageRequest = new SendMessageRequest()
				.withMessageBody("There is work to be done. " + uuid);
		sqs.sendMessage(sendMessageRequest);
	}
}
