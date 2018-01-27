package kr.clug.coinismylife.simulator.price;

import java.net.MalformedURLException;

import kr.clug.coinismylife.simulator.CoinConstants;

public final class PriceManager {
	
	private PriceFetcher priceFetcher;
	private volatile/*값 할당 등에서 atomic 하게 되도록 함.*/ double[] coinPrices = new double[CoinConstants.AMOUNT_COIN];
	
	// 해당 코인의 가격을 설정합니다.
	private void setCoinPrice(int coinNum, double coinPrice) { 
		this.coinPrices[coinNum] = coinPrice;
	}
	
	// 해당 코인의 가격을 가져옵니다.
	public double getCoinPrice(int coinNum) {
		return this.coinPrices[coinNum];
	}
	
	// price fetcher 객체를 생성하고 실행시키는 함수입니다.
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
	
	// 해당 키를 가진 코인의 가격을 책정합니다.
	// 만약 실패할 경우 false를 리턴합니다.
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
	
	// 모든 코인의 가격을 가져옵니다.
	// 복사된 배열이 넘어갑니다.
	public double[] getCoinPrices() {
		return coinPrices.clone();
	}
	

}
