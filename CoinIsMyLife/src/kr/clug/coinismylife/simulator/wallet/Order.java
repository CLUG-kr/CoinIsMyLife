package kr.clug.coinismylife.simulator.wallet;

import java.io.Serializable;

public class Order implements Serializable{
	
	public static final int BUY = 0, SELL = 1;
	
	private Wallet owner;
	private int orderType;
	private int coinType;
	private double amount;
	private double price;
	private double totalPrice;
	
	public Order(Wallet owner, int orderType, int coinType, double price, double amount, double totalPrice) {
		this.owner = owner;
		this.orderType = orderType;
		this.coinType = coinType;
		this.amount = amount;
		this.price = price;
		this.totalPrice = totalPrice;
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
	public double getAmount() {
		return amount;
	}
	public double getPrice() {
		return price;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
}
