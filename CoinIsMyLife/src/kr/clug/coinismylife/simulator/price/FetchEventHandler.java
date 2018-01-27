package kr.clug.coinismylife.simulator.price;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FetchEventHandler {

	private static final int MAX_THREAD_POOL = 5;
	
	/**
	 * Note : ArrayList may occur ConcurrentModificationException so using
	 * CopyOnWriteArrayList for prevent Exception based on multi thread. Do not
	 * use below source code. private static List<EventListener> listeners = new
	 * ArrayList<EventListener>();
	 * reference : http://silentsoft.kr/archives/10
	 */
	private static List<FetchEventListener> listeners = new CopyOnWriteArrayList<FetchEventListener>();
 
	private static synchronized List<FetchEventListener> getListeners() {
		return listeners;
	}
 
	public static synchronized void addListener(FetchEventListener eventListener) {
		if (getListeners().indexOf(eventListener) == -1) {
			listeners.add(eventListener);
		}
	}
 
	public static synchronized void removeListener(FetchEventListener eventListener) {
		if (getListeners().indexOf(eventListener) != -1) {
			listeners.remove(eventListener);
		}
	}
	
	public static synchronized void callEvent(final Class<?> caller) {
		callEventByAsynch(caller);
	}
 
	private static synchronized void callEventByAsynch(final Class<?> caller) {
		ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_POOL);
 
		for (final FetchEventListener listener : listeners) {
			executorService.execute(new Runnable() {
				public void run() {
					if (listener.getClass().getName().equals(caller.getName())) {
					} else {
						listener.onFetch();
					}
				}
			});
		}
 
		executorService.shutdown();
	}
}
