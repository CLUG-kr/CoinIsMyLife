package kr.clug.coinismylife.simulator.price;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PriceFetcher {
	
	Thread priceThread;
	BithumbConnector bithumbConn;
	PriceManager priceManager;
	public PriceFetcher(PriceManager priceManager) throws MalformedURLException {
		this.priceManager = priceManager;
		this.bithumbConn = new BithumbConnector(this);
		this.priceThread = new Thread(bithumbConn);
	}
	
	public void threadStart() {

		this.priceThread.start();
	}
	
	// string 을 json obj로 변환하는 메소드입니다.
	@SuppressWarnings("rawtypes")
	public void translateStringToJSON(String data) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jData = (JSONObject) ((JSONObject)parser.parse(data)).get("data");
		Iterator itKey = jData.keySet().iterator();
		while (itKey.hasNext()) {
			String key = (String) itKey.next();
			if (!key.equals("date")) {
				JSONObject coinObj = (JSONObject) jData.get(key);
				priceManager.setPrice(key, (String)coinObj.get("average_price"));
				coinObj.get("average_price");
			}
		}
	}
	
}

// Bithumb RESTful API 에서 데이터를 가져오기만 하는 Thread 입니다.
class BithumbConnector implements Runnable{
	

	PriceFetcher parent; // 이 스레드가 관리될 클래스
	URL url; // bithumb url 주소입니다.
	public BithumbConnector(PriceFetcher parent) throws MalformedURLException {
		this.parent = parent;
		url = new URL("https://api.bithumb.com/public/ticker/ALL");
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				URLConnection conn = url.openConnection();
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String str = br.readLine();
				System.out.println("get::" + str);
				parent.translateStringToJSON(str);
				br.close();
				is.close();
				System.out.println("thread ran");
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(200);
				System.out.println("thread sleep");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}