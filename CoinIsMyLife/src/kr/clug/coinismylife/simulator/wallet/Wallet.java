package kr.clug.coinismylife.simulator.wallet;

import java.io.Serializable;
import java.util.ArrayList;

import kr.clug.coinismylife.simulator.CoinConstants;

public final class Wallet implements Serializable{
	
	private String id;
	private int money;
	private double coinAmount[];
	private ArrayList<Order> orderList;
	public Wallet(String id) {
		this.id = id;
		this.orderList = new ArrayList<>();
		this.coinAmount = new double[CoinConstants.AMOUNT_COIN];
	}
	
	public String getId() {return id;}
	
	public int getMoney() {return money;}
	public void addMoney(int money) {this.money += money;}
	public void setMoney(int money) {this.money = money;}

	
	public double getCoinAmount(int index) {
		if (0 <= index && index <= CoinConstants.AMOUNT_COIN -1) 
			return coinAmount[index];
		else 
			return -1;
	}
	public void setCoinAmount(int index, double amount) {
		if (0 <= index && index <= CoinConstants.AMOUNT_COIN -1) 
			coinAmount[index] = amount;
	}
	public void addCoinAmount(int index, double amount) {
		if (0 <= index && index <= CoinConstants.AMOUNT_COIN -1) 
			coinAmount[index] += amount;
	}
	
	public ArrayList<Order> getOrderList() {return orderList;}
	public void addOrder(Order order) {orderList.add(order);}
	
	
}
