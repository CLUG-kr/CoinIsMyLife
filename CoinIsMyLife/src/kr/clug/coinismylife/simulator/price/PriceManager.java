package kr.clug.coinismylife.simulator.price;

import java.net.MalformedURLException;

import kr.clug.coinismylife.simulator.CoinConstants;

public class PriceManager {
	
	private PriceFetcher priceFetcher;
	private volatile/*�� �Ҵ� ��� atomic �ϰ� �ǵ��� ��.*/ double[] coinPrices = new double[CoinConstants.AMOUNT_COIN];
	
	private void setCoinPrice(int coinNum, double coinPrice) { 
		this.coinPrices[coinNum] = coinPrice;
	}
	
	public double getCoinPrice(int coinNum) {
		return this.coinPrices[coinNum];
	}
	
	// price fetcher ��ü�� �����ϴ� �޼ҵ��Դϴ�.
	// ���� ���н� false�� ��ȯ�մϴ�. ������ true�� ��ȯ�մϴ�.
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
