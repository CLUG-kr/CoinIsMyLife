package test;

import java.io.File;

import kr.clug.coinismylife.simulator.CoinConstants;
import kr.clug.coinismylife.simulator.CoinSimulator;
import kr.clug.coinismylife.simulator.price.FetchEventListener;
import kr.clug.coinismylife.simulator.price.PriceManager;
import kr.clug.coinismylife.simulator.wallet.Order;
import kr.clug.coinismylife.simulator.wallet.OrderCloseEventListener;
import kr.clug.coinismylife.simulator.wallet.Wallet;
import kr.clug.coinismylife.simulator.wallet.WalletManager;

public class TestSimulator implements FetchEventListener, OrderCloseEventListener{
	
	public static TestSimulator test;
	public static void main(String data[]) {
		
		test = new TestSimulator();
		
		// �����尡 ���������� ������� ������ �����Ѵ�.
		if (!test.startSimulation()) {
			return;
		}
		
		// ���̺� ������ ����� �ش�.
		File saveFolder = new File("saves");
		if (!saveFolder.exists()) saveFolder.mkdirs();

		// ���� ���̺� �ε��� �������� ��쿣 
		if (!test.walletManager.loadWallets(saveFolder)) {
			System.out.println("�Ϳ� ��ƼǮ! ���ФФ�");
		}
		else {
			System.out.println("�ε��� �����߽��ϴ�.");
			System.out.println();
		}
		
		// Test ��� ������ ������ �ϳ� �����.
		Wallet create = test.walletManager.registerWallet("Test");
		// ���� ��� �����ߴ��� Ȯ��
		if (create == null) {
			System.out.println("�����߽��ϴ�.");
		}
		
		// Test ��� ������ �����´�.
		Wallet get = test.walletManager.getWallet("Test");
		// �� ����
		get.setMoney(1000000000);
		System.out.println(get.getId() + " �� ���� " + get.getMoney());
		// �� ����
		get.addMoney(-100);
		System.out.println(get.getId() + " �� ���� " + get.getMoney());
		
		if (test.walletManager.orderBuy("Test", 0, 12345.0, 1000000)) {
			System.out.println("����!");
		}
		else {
			System.out.println("����");
		}
		
		if (test.walletManager.orderBuy("Test", 0, 139770000, 0.1)) {
			System.out.println("����!");
		}
		else {
			System.out.println("����");
		}
		
	}

	CoinSimulator coinSimulator = new CoinSimulator();
	PriceManager priceManager = coinSimulator.getPriceManager();
	WalletManager walletManager = coinSimulator.getWalletManager();
	public TestSimulator() {
		coinSimulator.addFetchEventListener(this);
		coinSimulator.addOrderCloseEventListener(this);
	}
	
	public boolean startSimulation() {
		return priceManager.registerFetcher();
	}

	@Override
	public void onOrderClose(Wallet owner, Order closeOrder) {
		System.out.println(owner.getId() + " ���� " + CoinConstants.getCoinName(closeOrder.getCoinType()) + " ��" + (closeOrder.getOrderType() == Order.BUY ? "����" : "�Ǹ�") + "�߽��ϴ�.");
		System.out.println(owner.getId() + " ���� �� : " + owner.getMoney());
		for (int i = 0; i < CoinConstants.AMOUNT_COIN; i++) {
			System.out.println(CoinConstants.getCoinName(i) + " : " + owner.getCoinAmount(i));
		}
	}

	@Override
	public void onFetch(double[] prices) {
		System.out.println("fetch...");
		for (int i = 0; i < prices.length; i++) {
			//System.out.println(CoinConstants.getCoinName(i) + " �� ���� : " + prices[i]);
		}
	}
	
}
