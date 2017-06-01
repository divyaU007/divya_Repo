package com.divya.CodingWork;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.divya.util.App;

public class PrimeRandomizer {

	final static Logger logger = Logger.getLogger(PrimeRandomizer.class);

	/**
	 * This method will receive the message from JMS and will check the number is prime or not
	 */
	public void receiveMessage() {

		Connection conn = null;

		try {
			// Getting JMS connection from the server
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			
			conn = connectionFactory.createConnection();
			
			if(conn!=null) {
				
				logger.debug(" Got Connection .....");
				
				//session for sending messages
				Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

				Destination dest = session.createQueue(App.MESSAGE_QUEUE);

				MessageConsumer consumer = session.createConsumer(dest);
				
				consumer.setMessageListener(new MessageListener() {
					
					public void onMessage(Message msg) {
				      try {
							if (msg instanceof ObjectMessage) {
								
								ObjectMessage objMessage = (ObjectMessage) msg;
								
								logger.debug("Received number '" + objMessage.getObject() + "'");

								if(objMessage!=null && objMessage.getObject()!=null) {
							
									if (isPrime(new Integer(objMessage.getObject().toString()).intValue())) {
										logger.debug(objMessage.getObject() + " is a prime number ");
									} else {
										logger.debug(objMessage.getObject() + " is not a prime number ");
									}
									objMessage.acknowledge();
								}
							}

				      }
				      catch (JMSException e) {
				    	  logger.error("Error reading message");
				    	  e.printStackTrace();
				      }
				    }
				  });
				}
			
			conn.start();

		} catch (JMSException e) {
			logger.error(" JMSException : "+ e.getMessage());
			e.printStackTrace();
		} finally {
			logger.debug(" Closing connection");
			if(conn!=null) {
				try {
					conn.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}


	/**
	 * This method will checks whether the number is prime or not
	 * @param number
	 * @return
	 */
	private boolean isPrime(int number) {
		// check if n is a multiple of 2
		if (number % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args)  {
		PrimeRandomizer primeRandomizer = new PrimeRandomizer();
		primeRandomizer.receiveMessage();
	}

	
}
