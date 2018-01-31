package simulator;

import simulator.price.FetchEventHandler;
import simulator.price.FetchEventListener;
import simulator.price.PriceManager;
import simulator.wallet.OrderCloseEventHandler;
import simulator.wallet.OrderCloseEventListener;
import simulator.wallet.WalletManager;

public class CoinSimulator{
	
	private PriceManager priceManager;
	private WalletManager walletManager;
	public CoinSimulator() {
		priceManager = new PriceManager();
		walletManager = new WalletManager();
	}
	
	public PriceManager getPriceManager() {
		return this.priceManager;
	}
	
	public WalletManager getWalletManager() {
		return this.walletManager;
	}
	
	public void addFetchEventListener(FetchEventListener fetchEventListener) {
		FetchEventHandler.addListener(fetchEventListener);
	}
	
	public void addOrderCloseEventListener(OrderCloseEventListener orderCloseEventListener) {
		OrderCloseEventHandler.addListener(orderCloseEventListener);
	}
}
