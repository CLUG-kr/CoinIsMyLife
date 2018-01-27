package kr.clug.coinismylife.simulator;

import kr.clug.coinismylife.simulator.price.FetchEventHandler;
import kr.clug.coinismylife.simulator.price.FetchEventListener;
import kr.clug.coinismylife.simulator.price.PriceManager;

public class CoinSimulator{
	
	private PriceManager priceManager;
	public CoinSimulator() {
		priceManager = new PriceManager();
	}
	
	public PriceManager getPriceManager() {
		return this.priceManager;
	}
	
	public void addFetchEventListener(FetchEventListener fetchEventListener) {
		FetchEventHandler.addListener(fetchEventListener);
	}
	
}
