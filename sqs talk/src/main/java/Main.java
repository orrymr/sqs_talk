import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import consumer.Consumer;
import producer.Producer;
import statisticain.Statistician;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Main {
	public static void main(String[] args) throws IOException {
		// Create your own override.properties from the override.properties.template file
		// Put the path to you override.properties file here

		String propertiesPath = "/home/orrymr/Documents/sqs talk/sqs talk/src/main/resources/override.properties";
		String queueUrl = "https://sqs.us-east-1.amazonaws.com/771412604156/sqs_talk_queue";

		Properties appProps = new Properties();
		appProps.load(new FileInputStream(propertiesPath));
		String accessKey = appProps.getProperty("access_key_id");
		String secretKey = appProps.getProperty("secret_key_id");

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

		AmazonSQS sqs = new AmazonSQSClient(awsCreds);
		sqs.setEndpoint(queueUrl);

		if (args.length != 0 && args[0].equals("1")) {
			Producer producer = new Producer(sqs);
			producer.produce();
		} else if (args.length != 0 && args[0].equals("2")) {
			Consumer consumer = new Consumer(sqs);
			consumer.consume(queueUrl);
		} else if (args.length != 0 && args[0].equals("3")) {
			Statistician statistician = new Statistician(sqs);
			statistician.getStats(queueUrl);
		} else
			System.out.println("WHAT DO YOU WANT FROM ME?!");

	}
}
