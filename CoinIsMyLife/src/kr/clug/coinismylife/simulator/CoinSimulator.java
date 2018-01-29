package kr.clug.coinismylife.simulator;

import kr.clug.coinismylife.simulator.price.FetchEventHandler;
import kr.clug.coinismylife.simulator.price.FetchEventListener;
import kr.clug.coinismylife.simulator.price.PriceManager;
import kr.clug.coinismylife.simulator.wallet.OrderCloseEventHandler;
import kr.clug.coinismylife.simulator.wallet.OrderCloseEventListener;
import kr.clug.coinismylife.simulator.wallet.WalletManager;

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
