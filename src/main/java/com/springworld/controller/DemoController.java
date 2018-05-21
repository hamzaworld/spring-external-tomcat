package com.springworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.servicebus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.servicebus.ServiceBusContract;
import com.microsoft.windowsazure.services.servicebus.ServiceBusService;
import com.microsoft.windowsazure.services.servicebus.models.BrokeredMessage;
import com.microsoft.windowsazure.services.servicebus.models.CreateQueueResult;
import com.microsoft.windowsazure.services.servicebus.models.QueueInfo;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveMessageOptions;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveMode;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveQueueMessageResult;

import javax.xml.datatype.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

	
	Configuration config = 
			ServiceBusConfiguration.configureWithSASAuthentication("mydemobusnamespace", "RootManageSharedAccessKey", "JZ0MK8SmVYeA8nCC6lopR0Vuz1Hg/8iqt7bruMEGLuk=", ".servicebus.windows.net");
			ServiceBusContract service = ServiceBusService.create(config);
	
	
	
	@GetMapping("/message")
	public String demoMessage() {
		return "This is a sample rest end point";
	}
	
	@GetMapping("/greeting")
	public String getGreeting() {
		return "This is a greeting via demo rest end point";
	}
	
	@GetMapping("/createQueue")
	public String getGreeting1() {
		
			QueueInfo queueInfo = new QueueInfo("TestQueue");
			try
			{
			    CreateQueueResult result = service.createQueue(queueInfo);
			}
			catch (ServiceException e)
			{
			    System.out.print("ServiceException encountered: ");
			    System.out.println(e.getMessage());
			 }
		return "This is a greeting via demo rest end point ";
	}
	
	@GetMapping("/postMessageToQueue")
	public String getGreeting2(@RequestParam String msg) {
	
	
	try
	{
	    BrokeredMessage message = new BrokeredMessage(msg);
	    service.sendQueueMessage("TestQueue", message);
	}
	catch (ServiceException e)
	{
	    System.out.print("ServiceException encountered: ");
	    System.out.println(e.getMessage());
	    System.exit(-1);
	}
	return "Message Posted Succesfully!";
	}
	
	
	@GetMapping("/getMessageFromQueue")
	public void getMessage() {
		try
		{
		    ReceiveMessageOptions opts = ReceiveMessageOptions.DEFAULT;
		    opts.setReceiveMode(ReceiveMode.PEEK_LOCK);

		    while(true)  {
		         ReceiveQueueMessageResult resultQM =
		        		                service.receiveQueueMessage("TestQueue", opts);
		        BrokeredMessage message = resultQM.getValue();
		        if (message != null && message.getMessageId() != null)
		        {
		            System.out.println("MessageID: " + message.getMessageId());
		            // Display the queue message.
		            System.out.print("From queue: ");
		            byte[] b = new byte[200];
		            String s = null;
		            int numRead = message.getBody().read(b);
		            while (-1 != numRead)
		            {
		                s = new String(b);
		                s = s.trim();
		                System.out.print(s);
		                numRead = message.getBody().read(b);
		            }
		            System.out.println();
		            System.out.println("Custom Property: " +
		                message.getProperty("MyProperty"));
		            // Remove message from queue.
		            System.out.println("Deleting this message.");
		            service.deleteMessage(message);
		        }  
		        else  
		        {
		            System.out.println("Finishing up - no more messages.");
		            break;
		            // Added to handle no more messages.
		            // Could instead wait for more messages to be added.
		        }
		        
		        
		    }
		}
		catch (ServiceException e) {
		    System.out.print("ServiceException encountered: ");
		    System.out.println(e.getMessage());
		    System.exit(-1);
		}
		catch (Exception e) {
		    System.out.print("Generic exception encountered: ");
		    System.out.println(e.getMessage());
		    System.exit(-1);
		}
	}
}
