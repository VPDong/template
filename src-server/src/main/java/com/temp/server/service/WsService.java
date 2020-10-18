package com.temp.server.service;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/ws/subscribe/{key}/{dev}")
public class WsService {
	private static AtomicInteger SERVER_ONLINE = new AtomicInteger(0);
	private static Map<String, CopyOnWriteArraySet<WsService>> SERVER_MAP = new ConcurrentHashMap<>();

	private Session session;
	private String key = "";
	private String dev = "";

	@OnOpen
	public void onOpen(Session session, @PathParam("key") String key, @PathParam("dev") String dev) {
		SERVER_ONLINE.incrementAndGet();
		if (!SERVER_MAP.containsKey(key)) {
			SERVER_MAP.put(key, new CopyOnWriteArraySet<>());
		}
		SERVER_MAP.get(key).add(this);

		this.session = session;
		this.key = key;
		this.dev = dev;
		try {
			session.getBasicRemote().sendText(String.valueOf(MSG_SIGN));
		} catch (Exception ignored) {
			// ignored
		}
	}

	@OnClose
	public void onClose() {
		if (!SERVER_MAP.containsKey(key)) {
			SERVER_MAP.put(key, new CopyOnWriteArraySet<>());
		}
		SERVER_MAP.get(key).remove(this);
		SERVER_ONLINE.decrementAndGet();
	}

	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (Exception ignored) {
			// ignored
		}
	}

	public static final int MSG_SIGN = 0;
	public static final int MSG_TREE = 1;
	public static final int MSG_COVER = 2;
	public static final int MSG_DELETE = -1;

	public static void sendMessage(String key, String dev, int message) {
		sendMessage(key, dev, String.valueOf(message));
	}

	public static void sendMessage(String key, String dev, String message) {
		try {
			for (Map.Entry<String, CopyOnWriteArraySet<WsService>> entry : SERVER_MAP.entrySet()) {
				if (key != null && !key.equals(entry.getKey())) continue;
				for (WsService service : entry.getValue()) {
					if (dev != null && !dev.equals(service.dev) && !service.dev.equals("all")) continue;
					service.session.getBasicRemote().sendText(message);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
