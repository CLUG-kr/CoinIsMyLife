import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;

import simulator.price.FetchEventListener;
import simulator.price.PriceManager;
import simulator.wallet.Order;
import simulator.wallet.OrderCloseEventListener;
import simulator.wallet.Wallet;
import simulator.wallet.WalletManager;
//import test.TestSimulator;
import simulator.CoinConstants;
import simulator.CoinSimulator;

public class Basic extends JFrame {
	public static Character hero;
	public static TimeManager tm;

	public Intro intro = new Intro(this);
	public Start start;
	public Begin begin = new Begin(this);
	public HouseListView houseListView = new HouseListView(this);
	public Phone phone;
	public LoadedGame loadedGame = new LoadedGame(this);
	public Coin coin;
	// public CreateNewGame createNewFile = new CreateNewGame(this);
	public TV tv = new TV(this);

	public Basic() {
		setTitle("Coin Is My Life");
		setSize(ProjectMain.SCREEN_WIDTH, ProjectMain.SCREEN_HEIGHT);
		setResizable(false); // 사용자 임의로 창 크기 조절 불가
		setLocationRelativeTo(null); // 게임창이 화면 정중앙에
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 창 끄면 프로그램 종료
		// setLayout(null);
		add(begin);
		setVisible(true); // 창 표시
	}

	public void IntroToStart() throws ParseException { // intro에서 start로
		start = new Start(this);
		getContentPane().removeAll();
		getContentPane().add(start);
		revalidate();
		repaint();
	}

	public void beginToStart() {
		getContentPane().removeAll();
		getContentPane().add(intro);
		revalidate();
		repaint();
	}

	public void beginToLoaded() {
		getContentPane().removeAll();
		getContentPane().add(loadedGame);
		revalidate();
		repaint();
	}

	public void toHouseList() {
		getContentPane().removeAll();
		getContentPane().add(houseListView);
		revalidate();
		repaint();
	}

	// public void toCreateNewFile() {
	// getContentPane().removeAll();
	// getContentPane().add(createNewFile);
	// revalidate();
	// repaint();
	// }

	public void toStart() throws ParseException {
		start = new Start(this);
		getContentPane().removeAll();
		getContentPane().add(start);
		revalidate();
		repaint();
	}

	public void toPhone() {
		phone = new Phone(this);
		getContentPane().removeAll();
		getContentPane().add(phone);
		revalidate();
		repaint();
	}

	public void toTV() {
		getContentPane().removeAll();
		getContentPane().add(tv);
		revalidate();
		repaint();
	}

	public void toCoin() {
		coin = new Coin();
		getContentPane().removeAll();
		getContentPane().add(coin);
		revalidate();
		repaint();
	}
}

class Begin extends JPanel { // 게임 시작 화면(새로운 게임/ 기존게임 선택 창)
	private JButton start, loaded;
	private Basic basic;

	Begin(Basic p) {
		basic = p;
		start = new JButton("새로운 게임");
		loaded = new JButton("기존 게임");

		add(start);
		add(loaded);
		start.addActionListener(listener);
		loaded.addActionListener(listener);

	}

	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == start) {
				basic.beginToStart();
			}
			if (e.getSource() == loaded) {
				basic.beginToLoaded();
			}
		}
	};

}

class LoadedGame extends JPanel {
	private Basic basic;
	private JPanel cast;
	private JLabel label;
	private JTextField fileName;
	private JButton ok;
	private File f;

	LoadedGame(Basic p) {
		this.basic = p;
		cast = new JPanel();
		label = new JLabel("가져 올 파일 이름을 입력하십시오.");
		fileName = new JTextField(10);
		ok = new JButton("확인");

		ok.addActionListener(listener);

		cast.add(label);
		cast.add(fileName);
		cast.add(ok);

		add(cast);
	}

	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == ok) {
				try {
					f = new File(fileName.getText() + ".txt");

					if (f.isFile()) {
						LoadFile lf = new LoadFile(fileName.getText());
						Basic.hero = lf.getLoadedCharacter();
						Basic.tm = new TimeManager(Basic.hero);
						Basic.tm.TimeSetUp(); // 게임에 없었던 시간 차이 동기화
						Basic.tm.start_main(); // 시간 카운트 시작
						Basic.hero.monthlyPayStart(); // 월세 카운트 시작
						basic.toStart();
					} else {
						// 다이얼로그
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
}

// class CreateNewGame extends JPanel {
// private Basic basic;
// private JPanel cast;
// private JLabel label;
// private JTextField fileName;
// private JButton ok;
// private File f;
//
// CreateNewGame(Basic p) {
// this.basic = p;
// cast = new JPanel();
// label = new JLabel("생성 할 게임파일 이름을 적으십시오");
// fileName = new JTextField(10);
// ok = new JButton("확인");
//
// ok.addActionListener(listener);
//
// cast.add(label);
// cast.add(fileName);
// cast.add(ok);
//
// add(cast);
//
// }
//
// ActionListener listener = new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
//
// if (e.getSource() == ok) {
// try {
// f = new File(fileName.getText() + ".txt");
//
// if (f.isFile()) {
// // 다이얼로그
// } else {
// Basic.hero = new Character();
// CreateFile createFile = new CreateFile(Basic.hero, fileName.getText());
// basic.toStart();
// }
// } catch (IOException e1) {
// // TODO Auto-generated catch block
// e1.printStackTrace();
// }
// }
// }
// };
// }

class Intro extends JPanel { // 인트로 화면

	private Basic basic;
	private JLabel label;

	Intro(Basic p) {
		setLayout(null);
		this.basic = p;

		setBackground(Color.BLACK);
		setSize(1280, 720);
		setLocation(0, 0);

		label = new JLabel("<html>코인은 내 인생...<br> 가즈아!!!!</html>");
		label.setForeground(Color.WHITE);
		label.setBounds(50, 50, 100, 100);
		add(label);
		addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					Basic.hero = new Character(); // 새로운 게임 시작 시 캐릭터 생성
					Basic.tm = new TimeManager(Basic.hero);
					Basic.tm.start_main();
					Basic.hero.monthlyPayStart();
					basic.IntroToStart();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

}

class Start extends JPanel {

	private Calendar cal;
	private TimeManager tm;
	//////////////////////////
	private Lines lines;
	private Basic basic;
	//////////////////////////

	////////////////////////////
	private Image screenImage; // 기본 이미지 틀
	private Graphics screenGraphics;
	private Image back;
	private JButton character, btn_houseList, btn_phone, btn_TV, btn_save, btn_computer;
	private JLabel label_money, label_dealLevel, label_line;
	/////////////////////////////

	Start(Basic p) {
		this.basic = p;
		this.lines = new Lines(Basic.hero);
		cal = Calendar.getInstance();
		//////////////////////////////////////////////////////
		setLayout(null);
		setSize(1280, 720);
		setLocation(0, 0);
		character = new JButton(new ImageIcon(ProjectMain.class.getResource("./images/character.png")));
		btn_phone = new JButton(new ImageIcon(ProjectMain.class.getResource("./images/phone_small.png")));
		btn_TV = new JButton(new ImageIcon(ProjectMain.class.getResource("./images/tv_80_small.jpg")));
		btn_computer = new JButton(new ImageIcon(ProjectMain.class.getResource("./images/computer.jpg")));

		character.setBounds(600, 150, 150, 220);

		character.setBorderPainted(false);
		character.setFocusPainted(false);
		character.setContentAreaFilled(false);

		character.addActionListener(listener);

		btn_houseList = new JButton("새집보기");
		btn_houseList.setBounds(5, 630, 100, 50);
		btn_houseList.addActionListener(listener);

		btn_phone.setBounds(750, 200, 30, 54);
		btn_phone.addActionListener(listener);

		btn_phone.setBorderPainted(false);
		btn_phone.setFocusPainted(false);
		btn_phone.setContentAreaFilled(false);

		btn_TV.setBounds(20, 200, 300, 200);
		btn_TV.addActionListener(listener);
		btn_TV.setBorderPainted(false);
		btn_TV.setFocusPainted(false);
		btn_TV.setContentAreaFilled(false);

		btn_save = new JButton("게임저장");
		btn_save.setBounds(1150, 630, 100, 50);
		btn_save.addActionListener(listener);

		btn_computer.setBounds(600, 400, 300, 200);
		btn_computer.addActionListener(listener);

		btn_computer.setBorderPainted(false);
		btn_computer.setFocusPainted(false);
		btn_computer.setContentAreaFilled(false);

		label_line = new JLabel();
		label_line.setBounds(600, 120, 200, 30);
		label_line.setVisible(false);
		add(label_line);

		add(character);
		add(btn_houseList);
		add(btn_phone);
		add(btn_TV);
		add(btn_save);
		add(btn_computer);
		back = new ImageIcon(ProjectMain.class.getResource("./images/back.png")).getImage();
		//////////////////////////////////////////////////////

	}

	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == character) { // 캐릭터 클릭하면 랜덤으로 대사가 등장

				class Timer_line extends Thread { // 버튼을 임시 비활성화시키면 버튼 색깔 자체가 비활성화 색깔로 감. 나중에 이미지로 바꿔야할듯?
					public void run() {
						label_line.setVisible(true);
						// character.setEnabled(false);
						try {
							Thread.sleep(2000);
						} catch (Exception e) {

						}
						label_line.setVisible(false);
						// character.setEnabled(true);
					}
				}

				label_line.setText(lines.getCharacterLine().getLines());
				Thread t = new Timer_line();
				t.start();

			}
			if (e.getSource() == btn_houseList) {
				basic.toHouseList();
			}

			if (e.getSource() == btn_phone) {
				basic.toPhone();
			}

			if (e.getSource() == btn_TV) {
				basic.toTV();
			}
			if (e.getSource() == btn_computer) {
				basic.toCoin();
			}
			if (e.getSource() == btn_save) {
				String fileName = JOptionPane.showInputDialog("생성 할 파일 이름을 입력하세요.");
				File f;
				try {
					f = new File(fileName + ".txt");

					// 덮어쓰기 주의
					// if (f.isFile()) {
					// // 다이얼로그
					// } else {
					Basic.hero = new Character();
					CreateFile createFile = new CreateFile(Basic.tm.getCharacter(), fileName,
							Basic.tm.getCumulativeTime());

					// }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};

	public void paint(Graphics g) {
		screenImage = createImage(ProjectMain.SCREEN_WIDTH, ProjectMain.SCREEN_HEIGHT); // 프로그램 화면 크기만큼
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null); // 창에 이미지 그림
		g.setFont(new Font("돋움", Font.PLAIN, 20));
		g.drawString("거래 레벨 : " + Basic.hero.getLevelOfDeal(), 5, 20);
		g.drawString("돈 : " + Basic.hero.getMoney(), 5, 40);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(back, 0, 0, null);
		paintComponents(g); // 버튼같은 정적인것들은 paintComponents로 그려줘야한다
		this.repaint();
	}
}

class HouseListView extends JPanel {
	private Basic basic;

	private Image screenImage;
	private Graphics screenGraphics;
	private Image bang, houses; // bang : 배경 houses : 집 이미지
	private JButton back;

	HouseListView(Basic p) {
		this.basic = p;

		setLayout(null);
		back = new JButton("돌아가기");
		back.setBounds(5, 630, 100, 50);
		back.addActionListener(listener);

		add(back);

		bang = new ImageIcon(ProjectMain.class.getResource("./images/bang.png")).getImage();
		houses = new ImageIcon(ProjectMain.class.getResource("./images/back.png")).getImage();

	}

	public void paint(Graphics g) {
		screenImage = createImage(ProjectMain.SCREEN_WIDTH, ProjectMain.SCREEN_HEIGHT); // 프로그램 화면 크기만큼
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null); // 창에 이미지 그림
		g.setColor(Color.BLACK);
		g.setFont(new Font("돋움", Font.PLAIN, 20));
		g.drawString("거래 레벨 : " + Basic.hero.getLevelOfDeal(), 5, 20);
		g.drawString("돈 : " + Basic.hero.getMoney(), 5, 40);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(bang, 0, 0, null);
		g.drawImage(houses, 50, 50, 300, 200, null);
		paintComponents(g); // 버튼같은 정적인것들은 paintComponents로 그려줘야한다
		this.repaint();
	}

	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == back) {
				try {
					basic.toStart();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
}

class Phone extends JPanel {
	private Basic basic;

	private JTable table;
	private JScrollPane scrollPane;

	private JPanel[] friendColumn;
	private JLabel[] name, age, relationship, moneyLevel, closeness, loan;
	private JButton[] rice, alcohol, btn_loan;
	private JLabel title;

	private Image screenImage;
	private Graphics screenGraphics;
	private Image img_phone; // phone : 휴대폰 이미지
	private JButton back;

	Phone(Basic p) {
		this.basic = p;

		friendColumn = new JPanel[Basic.hero.friendsList.size()];
		name = new JLabel[Basic.hero.friendsList.size()];
		age = new JLabel[Basic.hero.friendsList.size()];
		relationship = new JLabel[Basic.hero.friendsList.size()];
		moneyLevel = new JLabel[Basic.hero.friendsList.size()];
		closeness = new JLabel[Basic.hero.friendsList.size()];
		loan = new JLabel[Basic.hero.friendsList.size()];

		rice = new JButton[Basic.hero.friendsList.size()];
		alcohol = new JButton[Basic.hero.friendsList.size()];
		btn_loan = new JButton[Basic.hero.friendsList.size()];

		DefaultTableModel dm = new DefaultTableModel();
		Object tableHeader[] = { "이름", "나이", "관계", "재력레벨", "친밀도", "빌린 돈", "밥 사주기", "술 사주기", "돈 빌리기" };
		Object[][] contents = new Object[Basic.hero.friendsList.size()][9];
		for (int i = 0; i < Basic.hero.friendsList.size(); i++) {
			contents[i][0] = Basic.hero.friendsList.get(i).getName();
			contents[i][1] = Integer.toString(Basic.hero.friendsList.get(i).getAge());
			contents[i][2] = Basic.hero.friendsList.get(i).relationship();
			contents[i][3] = Integer.toString(Basic.hero.friendsList.get(i).getMoneyLevel());
			contents[i][4] = Double.toString(Basic.hero.friendsList.get(i).getCloseness());
			contents[i][5] = Integer.toString(Basic.hero.friendsList.get(i).getLoan());

			if (Basic.hero.friendsList.get(i).getMoneyLevel() == 1) {
				contents[i][6] = "10000원";
				contents[i][7] = "20000원";
			} else if (Basic.hero.friendsList.get(i).getMoneyLevel() == 2) {
				contents[i][6] = "20000원";
				contents[i][7] = "40000원";
			} else if (Basic.hero.friendsList.get(i).getMoneyLevel() == 3) {
				contents[i][6] = "30000원";
				contents[i][7] = "80000원";
			} else {
				contents[i][6] = "40000원";
				contents[i][7] = "160000원";
			}

			if (Basic.hero.friendsList.get(i).relationship() == "지인") {
				contents[i][8] = "20만원";
			} else if (Basic.hero.friendsList.get(i).relationship() == "친구") {
				contents[i][8] = "50만원";
			} else if (Basic.hero.friendsList.get(i).relationship() == "베프") {
				contents[i][8] = "100만원";
			} else {
				contents[i][8] = "500만원";
			}

		}
		dm.setDataVector(contents, tableHeader);

		table = new JTable(contents, tableHeader);
		table.setFont(new Font("돋움", Font.PLAIN, 15));
		table.setRowHeight(50);
		//// 버튼 설정
		table.getColumn("밥 사주기").setCellRenderer(new ButtonRenderer());
		table.getColumn("밥 사주기").setCellEditor(new ButtonEditor(new JCheckBox()));

		table.getColumn("술 사주기").setCellRenderer(new ButtonRenderer());
		table.getColumn("술 사주기").setCellEditor(new ButtonEditor(new JCheckBox()));

		table.getColumn("돈 빌리기").setCellRenderer(new ButtonRenderer());
		table.getColumn("돈 빌리기").setCellEditor(new ButtonEditor(new JCheckBox()));

		table.getColumnModel().getColumn(2).setPreferredWidth(120); // 넓이 설정
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(150);

		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule = table.getColumnModel();

		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < 6; i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}

		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1000, 600));

		back = new JButton("돌아가기");
		back.setBounds(5, 630, 100, 50);
		back.addActionListener(listener);

		add(scrollPane);
		add(back);

		// img_phone = new
		// ImageIcon(ProjectMain.class.getResource("./images/phone.png")).getImage();
	}

	public void paint(Graphics g) {
		screenImage = createImage(ProjectMain.SCREEN_WIDTH, ProjectMain.SCREEN_HEIGHT); // 프로그램 화면 크기만큼
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null); // 창에 이미지 그림
		g.setColor(Color.BLACK);
		g.setFont(new Font("돋움", Font.PLAIN, 20));
		g.drawString("거래 레벨 : " + Basic.hero.getLevelOfDeal(), 5, 20);
		g.drawString("돈 : " + Basic.hero.getMoney(), 5, 40);
	}

	public void screenDraw(Graphics g) {
		// g.drawImage(img_phone, 440, 0, null);
		paintComponents(g); // 버튼같은 정적인것들은 paintComponents로 그려줘야한다
		this.repaint();
	}

	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == back) {
				try {
					basic.toStart();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};

	class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {

		protected JButton button;
		private String label;
		private boolean isPushed;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedColumn() == 6) {
						if (Basic.hero.friendsList.get(table.getSelectedRow()).rice_check) { // 쿨타임 중이면
							JOptionPane.showMessageDialog(button,
									Basic.hero.friendsList.get(table.getSelectedRow()).getTime() + "초 이후 사용 가능합니다.");
						} else { // 쿨타임 돌았으면
									// '원'을 기준으로 문자열을 추출할 것이다.
							String text = button.getText();

							// 먼저 '원' 의 인덱스를 찾는다
							int idx = text.indexOf("원");

							// '원' 앞부분을 추출
							// substring은 첫번째 지정한 인덱스는 포함하지 않는다.
							String price = text.substring(0, idx);

							Basic.hero.friendsList.get(table.getSelectedRow()).start_rice(); // 타임 카운트 시작
							Basic.hero.setMoney(Basic.hero.getMoney() - Integer.parseInt(price)); // 돈에서 차감
							Basic.hero.friendsList.get(table.getSelectedRow()).closenessControl_rice(
									Basic.hero.friendsList.get(table.getSelectedRow()).relationship()); // 친밀도 조절

							basic.toPhone(); // 다시 그리기
						}

					}

					if (table.getSelectedColumn() == 7) {
						if (Basic.hero.friendsList.get(table.getSelectedRow()).alcohol_check) { // 쿨타임 중이면
							JOptionPane.showMessageDialog(button,
									Basic.hero.friendsList.get(table.getSelectedRow()).getTime() + "초 이후 사용 가능합니다.");
						} else { // 쿨타임 돌았으면
									// '원'을 기준으로 문자열을 추출할 것이다.
							String text = button.getText();

							// 먼저 '원' 의 인덱스를 찾는다
							int idx = text.indexOf("원");

							// '원' 앞부분을 추출
							// substring은 첫번째 지정한 인덱스는 포함하지 않는다.
							String price = text.substring(0, idx);

							Basic.hero.friendsList.get(table.getSelectedRow()).start_alcohol(); // 타임 카운트 시작
							Basic.hero.setMoney(Basic.hero.getMoney() - Integer.parseInt(price)); // 돈에서 차감
							Basic.hero.friendsList.get(table.getSelectedRow()).closenessControl_alcohol(
									Basic.hero.friendsList.get(table.getSelectedRow()).relationship()); // 친밀도 조절
							basic.toPhone();
						}

					}

					if (table.getSelectedColumn() == 8) {
						if (Basic.hero.friendsList.get(table.getSelectedRow()).loan_time_check) { // 쿨타임 중이면
							JOptionPane.showMessageDialog(button,
									Basic.hero.friendsList.get(table.getSelectedRow()).getTime() + "초 이후 사용 가능합니다.");
						} else { // 쿨타임 돌았으면
									// '원'을 기준으로 문자열을 추출할 것이다.
							String text = button.getText();
							// 먼저 '원' 의 인덱스를 찾는다
							int idx = text.indexOf("만원");

							// '원' 앞부분을 추출
							// substring은 첫번째 지정한 인덱스는 포함하지 않는다.
							String price = text.substring(0, idx) + "000";

							Basic.hero.friendsList.get(table.getSelectedRow()).start_friendLoan();
							; // 타임 카운트 시작
							Basic.hero.setMoney(Basic.hero.getMoney() + Integer.parseInt(price)); // 돈에서 차감
							Basic.hero.friendsList.get(table.getSelectedRow())
									.setLoan(Basic.hero.friendsList.get(table.getSelectedRow()).getLoan()
											+ Integer.parseInt(price));
							basic.toPhone();
						}

					}
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			label = (value == null) ? "" : value.toString();
			button.setText(label);
			isPushed = true;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			if (isPushed) {
				JOptionPane.showMessageDialog(button, label + ": Ouch!");
			}
			isPushed = false;
			return label;
		}

		@Override
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}
	}
}

class TV extends JPanel {
	private Basic basic;

	private Image screenImage;
	private Graphics screenGraphics;
	private Image img_tv; // phone : 휴대폰 이미지
	private JButton back;

	TV(Basic p) {
		this.basic = p;

		setLayout(null);
		back = new JButton("돌아가기");
		back.setBounds(5, 630, 100, 50);
		back.addActionListener(listener);

		add(back);

		img_tv = new ImageIcon(ProjectMain.class.getResource("./images/tv_80.png")).getImage();
	}

	public void paint(Graphics g) {
		screenImage = createImage(ProjectMain.SCREEN_WIDTH, ProjectMain.SCREEN_HEIGHT); // 프로그램 화면 크기만큼
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null); // 창에 이미지 그림
		g.setColor(Color.BLACK);
		g.setFont(new Font("돋움", Font.PLAIN, 20));
		g.drawString("거래 레벨 : " + Basic.hero.getLevelOfDeal(), 5, 20);
		g.drawString("돈 : " + Basic.hero.getMoney(), 5, 40);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(img_tv, 200, 0, null);
		paintComponents(g); // 버튼같은 정적인것들은 paintComponents로 그려줘야한다
		this.repaint();
	}

	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == back) {
				try {
					basic.toStart();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
}

class Coin extends JPanel implements FetchEventListener, OrderCloseEventListener {
	CoinSimulator coinSimulator = new CoinSimulator();
	PriceManager priceManager = coinSimulator.getPriceManager();
	WalletManager walletManager = coinSimulator.getWalletManager();
	
	////////////다이얼로그/////////////
	private JDialog dialog = new JDialog();
	private JComboBox combo;
	private JLabel label_price, label_amount;
	private JTextField price, amount; // 매수금액, 매수수량
	private JPanel one, two, three;
	private JButton ok;
	///////////////////////////////
	
	private JPanel coinView, walletView, checkPanel;
	private DefaultTableModel dm;
	private JTable table;
	private JScrollPane scroll;
	private JButton buy, sell;
	
	Object tableHeader[] = { "코인", "시세", "보유", "총 보유자산"};
	Object[][] contents = new Object[12][4];

	Coin() {

		startThread();
		
		///////////////다이얼로그/////////////
		dialog.setLayout(new LinearLayout(Orientation.VERTICAL,20));
		dialog.setSize(500,300);
		one = new JPanel();
		two = new JPanel();
		three = new JPanel();
		
		label_price = new JLabel("매수금액");
		label_amount = new JLabel("매수수량");
		
		price = new JTextField(10);
		amount = new JTextField(10);
		
		ok = new JButton("확인");
		ok.addActionListener(listener);

		combo = new JComboBox();
		combo.addItem("BTC");combo.addItem("ETH");combo.addItem("DASH");combo.addItem("LTC");
		combo.addItem("ETC");combo.addItem("XRP");combo.addItem("BCH");combo.addItem("XMR");
		combo.addItem("ZEC");combo.addItem("QTUM");combo.addItem("BTG");combo.addItem("EOS");
		combo.setEditable(false);
		
		
		
		one.add(label_price);
		one.add(price);
		
		two.add(label_amount);
		two.add(amount);
		
		three.add(combo);
		three.add(ok);
		
		dialog.add(one);
		dialog.add(two);
		dialog.add(three);
		
		///////////////////////
		setLayout(new LinearLayout(Orientation.HORIZONTAL,50));
		coinView = new JPanel();
		walletView = new JPanel();
		checkPanel = new JPanel();
		buy = new JButton("구매");
		sell = new JButton("매수");
		
		sell.addActionListener(listener);		
		buy.addActionListener(listener);
		
		dm = new DefaultTableModel();
		dm.setDataVector(contents, tableHeader);

		table = new JTable(dm);

		scroll = new JScrollPane(table);

	
		checkPanel.add(buy);
		checkPanel.add(sell);
		checkPanel.setLayout(new LinearLayout(Orientation.HORIZONTAL,50));
		
		coinView.setLayout(new LinearLayout(Orientation.VERTICAL,15));
		coinView.add(scroll);
		coinView.add(checkPanel);
		
		dialog.setTitle("코인구매");
		
		add(coinView);
		add(walletView);
	}
	
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buy) { // 구매
				dialog.setVisible(true);
			}
			if (e.getSource() == sell) { // 매수
				
			}
			if (e.getSource() == ok) {
				walletManager.orderBuy("userid", CoinConstants.getCoinIndex(combo.getSelectedItem().toString()),
						Integer.parseInt(price.getText()),Integer.parseInt(amount.getText()));
				System.out.println("d");
				dialog.setVisible(false);
			}
		}
	};

	public void updateTable() { // 테이블 실시간 업데이트
		dm.setDataVector(contents, tableHeader);
		table = new JTable(dm);
		scroll = new JScrollPane(table);
	}

	public void setButton() {
		for (int i = 0; i < 12; i++) {
			contents[i][4] = "구매";
			contents[i][5] = "매수";
		}
	}

	// 구매
	public void gumae() {
		walletManager.orderBuy("userid", CoinConstants.INDEX_BCH, 1000, 1); // 이걸 하면 구매 계약을 함.
		// 나중에 orderCloseEvent 에서 구매 완료가 됩니당. 적당한 가격을 했을경우에!
	}

	// 보유
	public void hai() {
		walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("BTC")); // 이런식으로 원하는 코인의 개수를 알 수
																							// 잇씀당.
		// walletManager.orderSell(id, coinType, price, amount);
	}

	public void startThread() {
		coinSimulator.addFetchEventListener(this);
		coinSimulator.addOrderCloseEventListener(this);

		// 스레드가 정상적으로 실행되지 않으면 종료한다.
		if (!startSimulation()) {
			return;
		}
		// Test 라는 계정의 지갑을 하나 만든다.
		Wallet create = walletManager.registerWallet("Test");
		// 지갑 등록 성공했는지 확인
		if (create == null) {
			System.out.println("실패했습니다.");
		}

		// Test 라는 지갑을 가져온다.
		Wallet get = walletManager.getWallet("Test");

	}

	public boolean startSimulation() {
		return priceManager.registerFetcher();
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
			setOpaque(true);

		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {

		protected JButton button;
		private String label;
		private boolean isPushed;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			label = (value == null) ? "" : value.toString();
			button.setText(label);
			isPushed = true;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			if (isPushed) {
				JOptionPane.showMessageDialog(button, label + ": Ouch!");
			}
			isPushed = false;
			return label;
		}

		@Override
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}
	}

	@Override
	public void onOrderClose(Wallet owner, Order closeOrder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFetch(double[] prices) {
		for (int i = 0; i < prices.length; i++) {
			contents[i][0] = CoinConstants.getCoinName(i);
			contents[i][1] = prices[i];
		}
//		contents[0][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("BTC"));
//		contents[1][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("ETH"));
//		contents[2][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("DASH"));
//		contents[3][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("LTC"));
//		contents[4][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("ETC"));
//		contents[5][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("XRP"));
//		contents[6][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("BCH"));
//		contents[7][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("XMR"));
//		contents[8][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("ZEC"));
//		contents[9][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("QTUM"));
//		contents[10][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("BTG"));
//		contents[11][2] = walletManager.getWallet("userid").getCoinAmount(CoinConstants.getCoinIndex("EOS"));
		updateTable();

	}
}
