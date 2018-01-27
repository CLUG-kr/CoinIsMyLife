package kr.clug.coinismylife.simulator;

public class CoinConstants {

	public static int AMOUNT_COIN = 12;
	public static int INDEX_BTC   = 0;
	public static int INDEX_ETH   = 1;
	public static int INDEX_DASH  = 2;
	public static int INDEX_LTC   = 3;
	public static int INDEX_ETC   = 4;
	public static int INDEX_XRP   = 5;
	public static int INDEX_BCH   = 6;
	public static int INDEX_XMR   = 7;
	public static int INDEX_ZEC   = 8;
	public static int INDEX_QTUM  = 9;
	public static int INDEX_BTG   = 10;
	public static int INDEX_EOS   = 11;
	
	public static int getCoinIndex(String key) {
		if (key.equals("BTC"))       return INDEX_BTC;
		else if (key.equals("ETH"))  return INDEX_ETH;
		else if (key.equals("DASH")) return INDEX_DASH;
		else if (key.equals("LTC"))  return INDEX_LTC;
		else if (key.equals("ETC"))  return INDEX_ETC;
		else if (key.equals("XRP"))  return INDEX_XRP;
		else if (key.equals("BCH"))  return INDEX_BCH;
		else if (key.equals("XMR"))  return INDEX_XMR;
		else if (key.equals("ZEC"))  return INDEX_ZEC;
		else if (key.equals("QTUM")) return INDEX_QTUM;
		else if (key.equals("BTG"))  return INDEX_BTG;
		else if (key.equals("EOS"))  return INDEX_EOS;
		else return -1;
	}
}
