package simulator.wallet;

public interface OrderCloseEventListener {
	public void onOrderClose(Wallet owner, Order closeOrder);
}
