package simulator.wallet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Iterator;

import simulator.CoinConstants;
import simulator.price.FetchEventHandler;
import simulator.price.FetchEventListener;

public class WalletManager implements FetchEventListener{
	
	// 지갑들을 저장하는 자료구조이다.
	// hashmap 을 이용해서 구현하려고 하였지만, hashtable 에서 비동기식 프로그래밍을 지원한다고 하므로 이것을 사용.
	private Hashtable<String, Wallet> wallets;
	
	// 초기 객체를 정의하고 이벤트 리스너에 등록시킨다.
	public WalletManager() {
		wallets = new Hashtable<>();
		FetchEventHandler.addListener(this);
	}
	
	// 지갑들을 폴더에 저장하는 함수
	// 저장에 실패했을 경우 false 를 리턴하고 성공했을 경우 true를 리턴한다.
	public synchronized boolean saveWallets(File folder) {
		if (!folder.exists()) return false;
		if (!folder.isDirectory()) return false;
		for (Wallet w : wallets.values()) {
			try {
				File saveFile = new File(folder, w.getId()+".wallet");
				if (!saveFile.exists()) saveFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(saveFile);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(w);
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	// 지갑들을 불러오는 메소드
	// 실패했을 경우 false 를 리턴한다.
	public synchronized boolean loadWallets(File folder) {
		if (!folder.exists()) return false;
		if (!folder.isDirectory()) return false;
		File files[] = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".wallet");
			}
		});
		for (File f : files) {
			try {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Wallet w = (Wallet) ois.readObject();
				wallets.put(w.getId(), w);
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	// 지갑을 추가하는 메소드
	// 만약 키값으로 id가 존재 하지 않을 경우 성공적으로 생성된 Wallet 객체를 리턴한다.
	public Wallet registerWallet(String id) {
		if (wallets.containsKey(id)) {
			return null;
		}
		else {
			Wallet w = new Wallet(id);
			wallets.put(id, w);
			return w;
		}
	}
	
	// 지갑을 가져오는 메소드
	public Wallet getWallet(String id) {
		return wallets.get(id);
	}
	
	// id 가 존재하는지 반환하는 메소드
	public boolean isKeyExist(String id) {
		return wallets.containsKey(id);
	}
	
	// 돈을 사용하는 메소드
	// 돈이 적으면 false 를 리턴한다.
	public boolean spendMoney(String id, int amount) {
		return isKeyExist(id) ? spendMoney(getWallet(id), amount) : false;
	}
	private boolean spendMoney(Wallet w, int amount) {
		if (w.getMoney() < amount) {
			return false;
		}
		else {
			w.addMoney(-amount);
			return true;
		}
	}
	
	// 돈을 버는 메소드
	// 아이디가 다르면 false 를 리턴한다.
	public boolean earnMoney(String id, int amount) {
		return isKeyExist(id) ? earnMoney(getWallet(id), amount) : false;
	}
	private boolean earnMoney(Wallet w, int amount) {
		if (w.getMoney() < amount) {
			return false;
		}
		else {
			w.addMoney(amount);
			return true;
		}
	}
	
	// 돈을 요청하는 메소드
	// id가 틀리면 -1 을 리턴한다.
	public int getMoney(String id) {
		return isKeyExist(id) ? getMoney(getWallet(id)) : -1;
	}
	private int getMoney(Wallet w) {
		return w.getMoney();
	}
	
	// 코인을 구매 요청하는 메소드
	// 구매요청에 실패하면 return false
	public boolean orderBuy(String id, String coinName, double price, double amount) {
		int coinType = CoinConstants.getCoinIndex(coinName);
		if (coinType != -1) return orderBuy(id, coinType, price, amount);
		else return false;
			
	}
	public boolean orderBuy(String id, int coinType, double price, double amount) {
		if (!isKeyExist(id)) return false;
		if (!CoinConstants.validCoinIndex(coinType)) return false;
		if (price <= 0.0 || amount <= 0.0) return false;
		return orderBuy(getWallet(id), coinType, price, amount, price * amount);
	}
	private boolean orderBuy(Wallet w, int coinType, double price, double amount, double totalPrice) {
		if (w.getMoney() < totalPrice) return false; // not enough money
		Order order = new Order(w, Order.BUY, coinType, price, amount, totalPrice);
		w.addMoney((int) -totalPrice);
		w.addOrder(order);
		return true;
	}
	
	// 코인을 판매 요청하는 메소드
	// 판매요청에 실패하면 return false
	public boolean orderSell(String id, String coinName, double price, double amount) {
		int coinType = CoinConstants.getCoinIndex(coinName);
		if (coinType != -1) return orderSell(id, coinType, price, amount);
		else return false;
			
	}
	public boolean orderSell(String id, int coinType, double price, double amount) {
		if (!isKeyExist(id)) return false;
		if (!CoinConstants.validCoinIndex(coinType)) return false;
		if (price <= 0.0 || amount <= 0.0) return false;
		return orderSell(getWallet(id), coinType, price, amount, price * amount);
	}
	private boolean orderSell(Wallet w, int coinType, double price, double amount, double totalPrice) {
		if (w.getCoinAmount(coinType) < amount) return false; // not enough coin
		Order order = new Order(w, Order.SELL, coinType, price, amount, totalPrice);
		w.addCoinAmount(coinType, -amount);
		w.addOrder(order);
		return true;
	}
		
	private boolean buyCondition(double orderPrice, double currentPrice) {
		return orderPrice >= currentPrice;
	}
	
	private boolean sellCondition(double orderPrice, double currentPrice) {
		return orderPrice <= currentPrice;
	}
	
	// 거래소 가격이 변동되었을 경우
	@Override
	public void onFetch(double prices[]) {
		for (Wallet wallet : wallets.values()) {
			Iterator<Order> iter = wallet.getOrderList().iterator();
			while(iter.hasNext()) {
				Order order = iter.next();
				if (order.getOrderType() == Order.BUY) { // 구매 요청일 경우
					if (buyCondition(order.getPrice(), prices[order.getCoinType()])) { // 구매 요청 가격이 현재 코인가격 이상일 경우
						wallet.addCoinAmount(order.getCoinType(), order.getAmount());
						wallet.addMoney((int) -order.getTotalPrice());
						OrderCloseEventHandler.callEvent(this.getClass(), order.getOwner(), order);
						iter.remove();
					}
				}
				else { // 판매 요청일 경우
					if (sellCondition(order.getPrice(), prices[order.getCoinType()])) {
						wallet.addCoinAmount(order.getCoinType(), -order.getAmount());
						wallet.addMoney((int) (order.getAmount() * prices[order.getCoinType()]));
						OrderCloseEventHandler.callEvent(this.getClass(), order.getOwner(), order);
						iter.remove();
					}
				}
			}
		}
	}
	
	
	
}
