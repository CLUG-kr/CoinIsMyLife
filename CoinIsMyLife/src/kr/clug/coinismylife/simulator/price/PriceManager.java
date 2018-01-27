package kr.clug.coinismylife.simulator.price;

import java.net.MalformedURLException;

import kr.clug.coinismylife.simulator.CoinConstants;

public class PriceManager {
	
	private PriceFetcher priceFetcher;
	private volatile/*값 할당 등에서 atomic 하게 되도록 함.*/ double[] coinPrices = new double[CoinConstants.AMOUNT_COIN];
	
	private void setCoinPrice(int coinNum, double coinPrice) { 
		this.coinPrices[coinNum] = coinPrice;
	}
	
	public double getCoinPrice(int coinNum) {
		return this.coinPrices[coinNum];
	}
	
	// price fetcher 객체를 생성하는 메소드입니다.
	// 생성 실패시 false를 반환합니다. 성공시 true를 반환합니다.
	public boolean registerFetcher() {
		try {
			priceFetcher = new PriceFetcher(this);
			priceFetcher.threadStart();
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}	
	
	public boolean setPrice(String key, String value) {
		try {
			double val = Double.parseDouble(value);
			int index = CoinConstants.getCoinIndex(key);
			if (index == -1) {
				return false;
			}
			setCoinPrice(index, val);
			System.out.println(key + " value is set " + value);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
