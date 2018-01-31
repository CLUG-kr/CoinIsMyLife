package simulator;

public class CoinConstants {

	public static final int AMOUNT_COIN = 12;
	public static final int INDEX_BTC   = 0;
	public static final int INDEX_ETH   = 1;
	public static final int INDEX_DASH  = 2;
	public static final int INDEX_LTC   = 3;
	public static final int INDEX_ETC   = 4;
	public static final int INDEX_XRP   = 5;
	public static final int INDEX_BCH   = 6;
	public static final int INDEX_XMR   = 7;
	public static final int INDEX_ZEC   = 8;
	public static final int INDEX_QTUM  = 9;
	public static final int INDEX_BTG   = 10;
	public static final int INDEX_EOS   = 11;

	public static final String KEY_BTC = "BTC";
	public static final String KEY_ETH = "ETH";
	public static final String KEY_DASH = "DASH";
	public static final String KEY_LTC = "LTC";
	public static final String KEY_ETC = "ETC";
	public static final String KEY_XRP = "XRP";
	public static final String KEY_BCH = "BCH";
	public static final String KEY_XMR = "XMR";
	public static final String KEY_ZEC = "ZEC";
	public static final String KEY_QTUM = "QTUM";
	public static final String KEY_BTG = "BTG";
	public static final String KEY_EOS = "EOS";
	
	public static boolean validCoinIndex(int coinType) {
		return (0 <= coinType && coinType <= AMOUNT_COIN-1);
	}
	
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
	
	public static String getCoinName(int id) {
		switch(id) {
		case INDEX_BTC: return KEY_BTC;
		case INDEX_ETH: return KEY_ETH;
		case INDEX_DASH: return KEY_DASH;
		case INDEX_LTC: return KEY_LTC;
		case INDEX_ETC: return KEY_ETC;
		case INDEX_XRP: return KEY_XRP;
		case INDEX_BCH: return KEY_BCH;
		case INDEX_XMR: return KEY_XMR;
		case INDEX_ZEC: return KEY_ZEC;
		case INDEX_QTUM: return KEY_QTUM;
		case INDEX_BTG: return KEY_BTG;
		case INDEX_EOS: return KEY_EOS;
		}
		return null;
	}
}
