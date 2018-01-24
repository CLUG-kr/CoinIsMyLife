package kr.clug.coinismylife.simulator;

public class Wallet {
	
	private String id;
	private int money;
	private double coin[];
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
	public double[] getCoin() {
		return coin;
	}
	public void setCoin(double coin[]) {
		this.coin = coin;
	}
	
	
	
}
