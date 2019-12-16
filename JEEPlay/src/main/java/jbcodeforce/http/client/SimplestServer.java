package jbcodeforce.http.client;


import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Provider;

public class SimplestServer implements Provider<Source>{


    public Source invoke(Source request) {
        return  new StreamSource(new StringReader("<p>Hello There!</p>"));
    }
    
    
	public static void main(String[] args) {
		String url = "http://127.0.0.1:1900/BasicWebService";
		System.out.println("Starting WebService at: " + url);
		
		/*Endpoint endpoint = Endpoint.create(HTTPBinding.HTTP_BINDING, new Server());
		endpoint.publish(url);*/

	}

}
