package com.zkzy.portal.dumu.server.system.provider.websocket;


import com.zkzy.portal.common.utils.DateHelper;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


public class WebSocketServer extends TextWebSocketHandler {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketSession> webSocketSet = new CopyOnWriteArraySet<WebSocketSession>();


    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //所有用户
    private static Map<String, WebSocketSession> users = new ConcurrentHashMap();

    //所有分组
    private static Map<String, Map<String, WebSocketSession>> groups = new ConcurrentHashMap();


    public WebSocketServer() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //连入
        WebSocketServer.add(session);
        log.info("用户连接当前数量:" + WebSocketServer.getOnlineCount());

        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
        TextMessage returnMessage = new TextMessage("Connect to the server successfully");
        session.sendMessage(returnMessage);
    }

    /**
     * 关闭连接时触发
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        //断开
        WebSocketServer.sub(session);
        log.info("用户连接当前数量:" + WebSocketServer.getOnlineCount());
    }

    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        //断开
        WebSocketServer.sub(session);
    }

    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 给某个用户发送消息
     *
     * @param username
     * @param message
     */
    public void sendMessageToUser(String username, TextMessage message) {
        WebSocketSession user = users.get(username);
        if (user != null) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : webSocketSet) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 根据组别推送消息
     */
    public static void sendMessage(String jsonStr, String group) throws IOException {
        //查出用户在线组别
        Map<String, WebSocketSession> mygroup = groups.get(group);
        if (mygroup != null) {
            Iterator<Map.Entry<String, WebSocketSession>> it = mygroup.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, WebSocketSession> entry = it.next();
                WebSocketSession user = entry.getValue();
                if (entry != null) {
                    try {
                        if (user.isOpen()) {
                            TextMessage message = new TextMessage(jsonStr);
                            user.sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //查出在线管理员组
        Map<String, WebSocketSession> admin = groups.get("admin");
        if (admin != null) {
            Iterator<Map.Entry<String, WebSocketSession>> it = admin.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, WebSocketSession> entry = it.next();
                WebSocketSession user = entry.getValue();
                if (entry != null) {
                    try {
                        if (user.isOpen()) {
                            TextMessage message = new TextMessage(jsonStr);
                            user.sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static synchronized void add(WebSocketSession session) {
        try {
            String time = DateHelper.getDateTime();
            String username = (String) session.getAttributes().get("username");

            String group = (String) session.getAttributes().get("group");
            WebSocketSession user = users.get(username);
            if (user == null) {
                WebSocketServer.onlineCount++;
            }
            //用户纳入在线队列
            users.put(username, session);
            //用户纳入在线分组
            Map<String, WebSocketSession> mygroup = groups.get(group);
            if (mygroup == null) {
                mygroup = new HashedMap();
            }
            mygroup.put(username, session);
            groups.put(group, mygroup);

            log.info(time + "-------帐号:" + username + "连入webcocket,当前分组为" + group);
        } catch (Exception e) {
            log.info("异常");
        }
    }


    public static synchronized void sub(WebSocketSession session) {
        try {
            String time = DateHelper.getDateTime();
            String username = (String) session.getAttributes().get("username");
            String group = (String) session.getAttributes().get("group");
            WebSocketSession user = users.get(username);
            if (user != null) {
                WebSocketServer.onlineCount--;
                //用户移除在线map
                users.remove(username);
                //查出用户在线组别
                Map<String, WebSocketSession> mygroup = groups.get(group);
                mygroup.remove(username);
                log.info(time + "-------帐号:" + username + "断开连接,移出分组" + group);
            }
        } catch (Exception e) {
            log.info("异常");
        }

    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

}
