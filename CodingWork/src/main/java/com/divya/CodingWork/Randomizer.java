package com.divya.CodingWork;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.divya.util.App;

/**
 * @author divyauppalapati
 * 
 */
public class Randomizer {

	final static Logger logger = Logger.getLogger(Randomizer.class);

	public void sendMessage() {

		Connection conn = null;
		ConnectionFactory connectionFactory = null;
		Session session = null;
		MessageProducer producer = null;

		try {
			// Obtain a JNDI connection
			InitialContext jndi = new InitialContext();

			connectionFactory = (ConnectionFactory) jndi.lookup(App.CONNECTION_FACTORY);

			// Check whether we got the connectionFactory
			if (connectionFactory != null) {
				conn = connectionFactory.createConnection();
				logger.debug("connection :: " + conn);

				if (conn != null) {
					conn.start();
					session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

					Destination dest = (Destination) jndi.lookup(App.MESSAGE_QUEUE);

					if (session != null) {
						producer = session.createProducer(dest);
						// generate a random number
						int num = new Random().nextInt(1000) + 1;
						// Creating object message
						ObjectMessage message = session.createObjectMessage(new Integer(num));
						producer.send(message);
						logger.debug("Sent number '" + message.getObject() + "'");

					} else {
						logger.debug(" Session object is null");
					}
				} else {
					logger.debug(" Connection object is null");
				}

			}
		} catch (JMSException e) {
			logger.error(" JMSException : " + e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			logger.error(" NamingException : " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(" Exception : " + e.getMessage());
			e.printStackTrace();
		} finally {
			logger.error("Closing connection");
			try {
				if (conn != null) {
					conn.stop();
					conn.close();
				}
				if (session != null) {
					session.close();
				}
				if (producer != null) {
					producer.close();
				}
			} catch (JMSException e) {
				logger.error(" Exception :" + e.getMessage());
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		Randomizer randomizer = new Randomizer();
		randomizer.sendMessage();
	}

}