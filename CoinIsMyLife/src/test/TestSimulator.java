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
		
		// 스레드가 정상적으로 실행되지 않으면 종료한다.
		if (!test.startSimulation()) {
			return;
		}
		
		// 세이브 폴더를 만들어 준다.
		File saveFolder = new File("saves");
		if (!saveFolder.exists()) saveFolder.mkdirs();

		// 만약 세이브 로딩을 실패했을 경우엔 
		if (!test.walletManager.loadWallets(saveFolder)) {
			System.out.println("와우 뷰티풀! 실패ㅠㅠ");
		}
		else {
			System.out.println("로딩을 성공했습니다.");
			System.out.println();
		}
		
		// Test 라는 계정의 지갑을 하나 만든다.
		Wallet create = test.walletManager.registerWallet("Test");
		// 지갑 등록 성공했는지 확인
		if (create == null) {
			System.out.println("실패했습니다.");
		}
		
		// Test 라는 지갑을 가져온다.
		Wallet get = test.walletManager.getWallet("Test");
		// 돈 설정
		get.setMoney(1000000000);
		System.out.println(get.getId() + " 의 돈은 " + get.getMoney());
		// 돈 빼기
		get.addMoney(-100);
		System.out.println(get.getId() + " 의 돈은 " + get.getMoney());
		
		if (test.walletManager.orderBuy("Test", 0, 12345.0, 1000000)) {
			System.out.println("성공!");
		}
		else {
			System.out.println("실패");
		}
		
		if (test.walletManager.orderBuy("Test", 0, 139770000, 0.1)) {
			System.out.println("성공!");
		}
		else {
			System.out.println("실패");
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
		System.out.println(owner.getId() + " 님이 " + CoinConstants.getCoinName(closeOrder.getCoinType()) + " 을" + (closeOrder.getOrderType() == Order.BUY ? "구매" : "판매") + "했습니다.");
		System.out.println(owner.getId() + " 님의 돈 : " + owner.getMoney());
		for (int i = 0; i < CoinConstants.AMOUNT_COIN; i++) {
			System.out.println(CoinConstants.getCoinName(i) + " : " + owner.getCoinAmount(i));
		}
	}

	@Override
	public void onFetch(double[] prices) {
		System.out.println("fetch...");
		for (int i = 0; i < prices.length; i++) {
			//System.out.println(CoinConstants.getCoinName(i) + " 의 가격 : " + prices[i]);
		}
	}
	
}
