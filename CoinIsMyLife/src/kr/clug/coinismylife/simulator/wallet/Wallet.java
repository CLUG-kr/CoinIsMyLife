package kr.clug.coinismylife.simulator.wallet;

public final class Wallet {
	
	private String id;
	private int money;
	private double coinAmount[];
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMoney() {
		return money;
	}
	public void addMoney(int money) {
		this.money += money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public double[] getCoinAmount() {
		return coinAmount;
	}
	public void setCoin(double coinAmount[]) {
		this.coinAmount = coinAmount;
	}
	
	
	
}
