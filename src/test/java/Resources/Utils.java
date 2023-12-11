package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Utils {
	
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null) {
			//new FileOutputStream will create file at run time
			//addFilter is to log everything like log().all() - before sending to HTTP Builder and HTTP Client contains additional headers
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			 req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).addQueryParam("key", "qaclick123")
					 .addFilter(RequestLoggingFilter.logRequestTo(log))
					 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		    		.setContentType(ContentType.JSON).build();
			 return req;
			}
			return req;
	}
		public static String getGlobalValue(String key) throws IOException
		{
			//Inbuilt java Properties class helps to get the input from .properties file 
			Properties prop = new Properties();
			//To read file using FileInoutStream
			FileInputStream fis = new FileInputStream("C:\\Users\\dell\\eclipse-workspace\\CucumberBDDPracticeSec17_24112023\\src\\test\\java\\Resources\\global.properties");
			//load file or integrate
			prop.load(fis);
		return prop.getProperty(key);

		}
		public String getJsonPath(Response response, String key)
		{
			String resp = response.asString();
			JsonPath js = new JsonPath(resp);
			return js.get(key).toString();
		}

	

}
