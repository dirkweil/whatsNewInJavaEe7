package de.gedoplan.demo.websocket;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.tyrus.client.ClientManager;

@ClientEndpoint
public class TickerEndpointClient
{
  private static final Log logger = LogFactory.getLog(TickerEndpointClient.class);

  @OnOpen
  public void onOpen(Session session)
  {
    logger.debug("opened session: " + session.getId());
  }

  @OnMessage
  public void onMessage(String message, Session session)
  {
    logger.debug("received \"" + message + "\" via session: " + session.getId());
  }

  @OnClose
  public void onClose(Session session)
  {
    logger.debug("closed session: " + session.getId());
  }

  public static void main(String[] args) throws Exception
  {
    // Client aufbauen und verbinden
    ClientManager client = ClientManager.createClient();
    client.connectToServer(TickerEndpointClient.class, new URI("ws://localhost:8080/whats-new-in-javaee/ticker"));

    // Main Thread ewig warten lassen
    new CountDownLatch(1).await();
  }

}
