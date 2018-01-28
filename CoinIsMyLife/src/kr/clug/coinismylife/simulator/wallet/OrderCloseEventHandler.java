package kr.clug.coinismylife.simulator.wallet;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderCloseEventHandler {
private static final int MAX_THREAD_POOL = 5;
	
	/**
	 * Note : ArrayList may occur ConcurrentModificationException so using
	 * CopyOnWriteArrayList for prevent Exception based on multi thread. Do not
	 * use below source code. private static List<EventListener> listeners = new
	 * ArrayList<EventListener>();
	 * reference : http://silentsoft.kr/archives/10
	 */
	private static List<OrderCloseEventListener> listeners = new CopyOnWriteArrayList<OrderCloseEventListener>();
 
	private static synchronized List<OrderCloseEventListener> getListeners() {
		return listeners;
	}
 
	public static synchronized void addListener(OrderCloseEventListener eventListener) {
		if (getListeners().indexOf(eventListener) == -1) {
			listeners.add(eventListener);
		}
	}
 
	public static synchronized void removeListener(OrderCloseEventListener eventListener) {
		if (getListeners().indexOf(eventListener) != -1) {
			listeners.remove(eventListener);
		}
	}
	
	public static synchronized void callEvent(final Class<?> caller, Wallet owner, Order closeOrder) {
		callEventByAsynch(caller, owner, closeOrder);
	}
 
	private static synchronized void callEventByAsynch(final Class<?> caller, Wallet owner, Order closeOrder) {
		ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_POOL);
 
		for (final OrderCloseEventListener listener : listeners) {
			executorService.execute(new Runnable() {
				public void run() {
					if (listener.getClass().getName().equals(caller.getName())) {
					} else {
						listener.onOrderClose(owner, closeOrder);
					}
				}
			});
		}
 
		executorService.shutdown();
	}
}
