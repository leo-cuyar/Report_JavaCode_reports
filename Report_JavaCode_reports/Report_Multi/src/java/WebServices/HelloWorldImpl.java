/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;
import Email.SendEmail;
import javax.jws.WebService;

//Service Implementation Bean

@WebService(endpointInterface = "WebServices.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String getHelloWorldAsString() {
            
               SendEmail email=new SendEmail();
               String outSend=email.sendGmail();          
            
            
            
		return "Hello World JAX-WS";
	}
}