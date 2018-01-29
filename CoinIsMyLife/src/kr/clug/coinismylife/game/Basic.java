import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Basic extends JFrame {
	public static Character hero;

	public Intro intro = new Intro(this);
	public Start start = new Start(this);
	public Begin begin = new Begin(this);
	public HouseListView houseListView = new HouseListView(this);
	public Phone phone = new Phone(this);
	public LoadedGame loadedGame = new LoadedGame(this);
	public CreateNewGame createNewFile = new CreateNewGame(this);
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

	public void IntroToStart() { // intro에서 start로
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
	
	public void toCreateNewFile() {
		getContentPane().removeAll();
		getContentPane().add(createNewFile);
		revalidate();
		repaint();
	}

	public void toStart() {
		getContentPane().removeAll();
		getContentPane().add(start);
		revalidate();
		repaint();
	}

	public void toPhone() {
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
				basic.toCreateNewFile();
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
						basic.toStart();
					} else {
						//다이얼로그
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
}

class CreateNewGame extends JPanel {
	private Basic basic;
	private JPanel cast;
	private JLabel label;
	private JTextField fileName;
	private JButton ok;
	private File f;

	CreateNewGame(Basic p) {
		this.basic = p;
		cast = new JPanel();
		label = new JLabel("생성 할 게임파일 이름을 적으십시오");
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
						// 다이얼로그
					} else {
						Basic.hero = new Character();
						CreateFile createFile = new CreateFile(Basic.hero, fileName.getText());
						basic.toStart();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
}

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
				basic.IntroToStart();
			}
		});

	}

}

class Start extends JPanel {

	//////////////////////////
	private Lines lines;
	private Basic basic;
	//////////////////////////

	////////////////////////////
	private Image screenImage; // 기본 이미지 틀
	private Graphics screenGraphics;
	private Image back;
	private JButton character, btn_houseList, btn_phone, btn_TV;
	private JLabel label_money, label_dealLevel, label_line;
	/////////////////////////////

	Start(Basic p) {
		this.basic = p;
		this.lines = new Lines(Basic.hero);
		//////////////////////////////////////////////////////
		setLayout(null);
		setSize(1280, 720);
		setLocation(0, 0);
		character = new JButton(new ImageIcon(ProjectMain.class.getResource("./images/character.png")));
		btn_phone = new JButton(new ImageIcon(ProjectMain.class.getResource("./images/phone_small.png")));
		btn_TV = new JButton(new ImageIcon(ProjectMain.class.getResource("./images/tv_80_small.jpg")));
		
		
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
		
		btn_TV.setBounds(200,200,300,200);
		btn_TV.addActionListener(listener);
		btn_TV.setBorderPainted(false);
		btn_TV.setFocusPainted(false);
		btn_TV.setContentAreaFilled(false);

		label_line = new JLabel();
		label_line.setBounds(600, 120, 200, 30);
		label_line.setVisible(false);
		add(label_line);

		add(character);
		add(btn_houseList);
		add(btn_phone);
		add(btn_TV);
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
			
			if(e.getSource() == btn_TV) {
				basic.toTV();
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
				basic.toStart();
			}
		}
	};
}

class Phone extends JPanel {
	private Basic basic;

	private Image screenImage;
	private Graphics screenGraphics;
	private Image img_phone; // phone : 휴대폰 이미지
	private JButton back;

	Phone(Basic p) {

		this.basic = p;

		setLayout(null);
		back = new JButton("돌아가기");
		back.setBounds(5, 630, 100, 50);
		back.addActionListener(listener);

		add(back);

		img_phone = new ImageIcon(ProjectMain.class.getResource("./images/phone.png")).getImage();
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
		g.drawImage(img_phone, 440, 0, null);
		paintComponents(g); // 버튼같은 정적인것들은 paintComponents로 그려줘야한다
		this.repaint();
	}

	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == back) {
				basic.toStart();
			}
		}
	};
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
				basic.toStart();
			}
		}
	};
}
