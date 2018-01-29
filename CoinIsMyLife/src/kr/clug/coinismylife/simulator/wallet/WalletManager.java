package kr.clug.coinismylife.simulator.wallet;

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

import kr.clug.coinismylife.simulator.CoinConstants;
import kr.clug.coinismylife.simulator.price.FetchEventHandler;
import kr.clug.coinismylife.simulator.price.FetchEventListener;

public class WalletManager implements FetchEventListener{
	
	// �������� �����ϴ� �ڷᱸ���̴�.
	// hashmap �� �̿��ؼ� �����Ϸ��� �Ͽ�����, hashtable ���� �񵿱�� ���α׷����� �����Ѵٰ� �ϹǷ� �̰��� ���.
	private Hashtable<String, Wallet> wallets;
	
	// �ʱ� ��ü�� �����ϰ� �̺�Ʈ �����ʿ� ��Ͻ�Ų��.
	public WalletManager() {
		wallets = new Hashtable<>();
		FetchEventHandler.addListener(this);
	}
	
	// �������� ������ �����ϴ� �Լ�
	// ���忡 �������� ��� false �� �����ϰ� �������� ��� true�� �����Ѵ�.
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
	
	// �������� �ҷ����� �޼ҵ�
	// �������� ��� false �� �����Ѵ�.
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
	
	// ������ �߰��ϴ� �޼ҵ�
	// ���� Ű������ id�� ���� ���� ���� ��� ���������� ������ Wallet ��ü�� �����Ѵ�.
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
	
	// ������ �������� �޼ҵ�
	public Wallet getWallet(String id) {
		return wallets.get(id);
	}
	
	// id �� �����ϴ��� ��ȯ�ϴ� �޼ҵ�
	public boolean isKeyExist(String id) {
		return wallets.containsKey(id);
	}
	
	// ���� ����ϴ� �޼ҵ�
	// ���� ������ false �� �����Ѵ�.
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
	
	// ���� ���� �޼ҵ�
	// ���̵� �ٸ��� false �� �����Ѵ�.
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
	
	// ���� ��û�ϴ� �޼ҵ�
	// id�� Ʋ���� -1 �� �����Ѵ�.
	public int getMoney(String id) {
		return isKeyExist(id) ? getMoney(getWallet(id)) : -1;
	}
	private int getMoney(Wallet w) {
		return w.getMoney();
	}
	
	// ������ ���� ��û�ϴ� �޼ҵ�
	// ���ſ�û�� �����ϸ� return false
	public boolean orderBuy(String id, String coinName, double price, double amount) {
		int coinType = CoinConstants.getCoinIndex(coinName);
		if (coinType != -1) return orderBuy(id, coinType, price, amount);
		else return false;
			
	}
	public boolean orderBuy(String id, int coinType, double price, double amount) {
		if (!isKeyExist(id)) return false;
		if (CoinConstants.validCoinIndex(coinType)) return false;
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
	
	// ������ �Ǹ� ��û�ϴ� �޼ҵ�
	// �Ǹſ�û�� �����ϸ� return false
	public boolean orderSell(String id, String coinName, double price, double amount) {
		int coinType = CoinConstants.getCoinIndex(coinName);
		if (coinType != -1) return orderSell(id, coinType, price, amount);
		else return false;
			
	}
	public boolean orderSell(String id, int coinType, double price, double amount) {
		if (!isKeyExist(id)) return false;
		if (CoinConstants.validCoinIndex(coinType)) return false;
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
	
	// �ŷ��� ������ �����Ǿ��� ���
	@Override
	public void onFetch(double prices[]) {
		for (Wallet wallet : wallets.values()) {
			Iterator<Order> iter = wallet.getOrderList().iterator();
			while(iter.hasNext()) {
				Order order = iter.next();
				if (order.getOrderType() == Order.BUY) { // ���� ��û�� ���
					if (buyCondition(order.getPrice(), prices[order.getCoinType()])) { // ���� ��û ������ ���� ���ΰ��� �̻��� ���
						wallet.addCoinAmount(order.getCoinType(), order.getAmount());
						wallet.addMoney((int) -order.getTotalPrice());
						OrderCloseEventHandler.callEvent(this.getClass(), order.getOwner(), order);
						iter.remove();
					}
				}
				else { // �Ǹ� ��û�� ���
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
