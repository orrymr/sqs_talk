import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import producer.Producer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Main {
	public static void main(String[] args) throws IOException {
		// Create your own override.properties from the override.properties.template file
		// Put the path to you override.properties file here
		String propertiesPath = "/home/orrymr/Documents/sqs talk/sqs talk/src/main/resources/override.properties";

		Properties appProps = new Properties();
		appProps.load(new FileInputStream(propertiesPath));
		String accessKey = appProps.getProperty("access_key_id");
		String secretKey = appProps.getProperty("secret_key_id");

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

		AmazonSQS sqs = new AmazonSQSClient(awsCreds);
		sqs.setEndpoint("https://sqs.us-east-1.amazonaws.com/771412604156/sqs_talk_queue");
		// Prod

		Producer producer = new Producer(sqs);
		producer.produce();

	}
}
