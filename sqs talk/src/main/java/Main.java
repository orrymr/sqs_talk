import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;


public class Main {
	public static void main(String[] args) throws IOException {
		// Create your own override.properties from the override.properties.template file
		// Put the path to you override.properties file here
		String propertiesPath = "/home/orrymr/Documents/sqs talk/sqs talk/src/main/resources/override.properties";

		Properties appProps = new Properties();
		appProps.load(new FileInputStream(propertiesPath));
		System.out.println(appProps.getProperty("testProp"));

		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();


	}
}
