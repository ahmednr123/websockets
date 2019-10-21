package com.websocket.sockets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/endpoint")
public class Socket {

    //private static Set<Session> clients =
    //        Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        //clients.add(session);
        System.out.println("onOpen::" + session.getId());
    }
    @OnClose
    public void onClose(Session session) {
        //clients.remove(session);
        System.out.println("onClose::" +  session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);

        //synchronized(clients){
            // Iterate over the connected sessions
            // and broadcast the received message
        System.out.println("Clientss: ");
            for(Session client : session.getOpenSessions()){
                    try {
                        client.getBasicRemote().sendText("Client " + session.getId() + ": " + message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        //}
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}