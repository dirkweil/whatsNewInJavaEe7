package de.gedoplan.demo.websocket;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ServerEndpoint("/ticker")
@Stateless
public class TickerEndpoint
{
  private static final String[]      coolStuff = { "Faces Flows", "HTML 5", "Converter", "Entity Graphs", "REST Client", "Websockets", "Batch", "Concurrency Utils", "Messaging Context" };

  private static final Log           LOG       = LogFactory.getLog(TickerEndpoint.class);

  private static Collection<Session> sessions  = Collections.synchronizedSet(new HashSet<Session>());

  private static final Random        random    = new Random();

  @OnOpen
  public void onOpen(Session session)
  {
    LOG.debug("opened session: " + session.getId());
    sessions.add(session);
  }

  @OnClose
  public void onClose(Session session)
  {
    LOG.debug("closed session: " + session.getId());
    sessions.remove(session);
  }

  @OnMessage
  public void onMessage(String message)
  {
    LOG.debug("received message: " + message);
  }

  @Schedule(second = "*/2", minute = "*", hour = "*", persistent = false)
  public void tick(Timer timer)
  {

    for (Session session : sessions)
    {
      try
      {
        String message = coolStuff[random.nextInt(coolStuff.length)];
        LOG.debug("send \"" + message + "\" via session: " + session.getId());
        session.getBasicRemote().sendText(message);
      }
      catch (IOException e)
      {
        LOG.warn("Canot send message over websocket", e);
      }
    }
  }
}
