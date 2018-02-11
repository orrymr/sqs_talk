package statisticain;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;

public class Statistician {
	AmazonSQS sqs;

	public Statistician(AmazonSQS sqs){
		this.sqs = sqs;
	}

	public void getStats(String queueUrl){
		GetQueueAttributesRequest getQueueAttributesRequest = new GetQueueAttributesRequest(queueUrl)
				.withAttributeNames("All");
		GetQueueAttributesResult getQueueAttributesResult = sqs.getQueueAttributes(getQueueAttributesRequest);
		System.out.println(String.format("The number of messages on the queue: %s", getQueueAttributesResult.getAttributes().get("ApproximateNumberOfMessages")));
		System.out.println(String.format("The number of messages in flight: %s", getQueueAttributesResult.getAttributes().get("ApproximateNumberOfMessagesNotVisible")));
	}

}
