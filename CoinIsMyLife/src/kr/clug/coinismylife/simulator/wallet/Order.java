package kr.clug.coinismylife.simulator.wallet;

public class Order {
	
	private Wallet owner;
	private int orderType;
	private int coinType;
	private double amout;
	
	public Order(Wallet owner, int orderType, int coinType, int amout) {
		this.owner = owner;
		this.orderType = orderType;
		this.coinType = coinType;
		this.amout = amout;
	}
	public Wallet getOwner() {
		return owner;
	}
	public int getOrderType() {
		return orderType;
	}
	public int getCoinType() {
		return coinType;
	}
	public double getAmout() {
		return amout;
	}
}
