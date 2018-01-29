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
		setResizable(false); // ����� ���Ƿ� â ũ�� ���� �Ұ�
		setLocationRelativeTo(null); // ����â�� ȭ�� ���߾ӿ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� â ���� ���α׷� ����
		// setLayout(null);
		add(begin);
		setVisible(true); // â ǥ��
	}

	public void IntroToStart() { // intro���� start��
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

class Begin extends JPanel { // ���� ���� ȭ��(���ο� ����/ �������� ���� â)
	private JButton start, loaded;
	private Basic basic;

	Begin(Basic p) {
		basic = p;
		start = new JButton("���ο� ����");
		loaded = new JButton("���� ����");

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
		label = new JLabel("���� �� ���� �̸��� �Է��Ͻʽÿ�.");
		fileName = new JTextField(10);
		ok = new JButton("Ȯ��");

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
						//���̾�α�
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
		label = new JLabel("���� �� �������� �̸��� �����ʽÿ�");
		fileName = new JTextField(10);
		ok = new JButton("Ȯ��");

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
						// ���̾�α�
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

class Intro extends JPanel { // ��Ʈ�� ȭ��

	private Basic basic;
	private JLabel label;

	Intro(Basic p) {
		setLayout(null);
		this.basic = p;

		setBackground(Color.BLACK);
		setSize(1280, 720);
		setLocation(0, 0);

		label = new JLabel("<html>������ �� �λ�...<br> �����!!!!</html>");
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
	private Image screenImage; // �⺻ �̹��� Ʋ
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

		btn_houseList = new JButton("��������");
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
			if (e.getSource() == character) { // ĳ���� Ŭ���ϸ� �������� ��簡 ����

				class Timer_line extends Thread { // ��ư�� �ӽ� ��Ȱ��ȭ��Ű�� ��ư ���� ��ü�� ��Ȱ��ȭ ����� ��. ���߿� �̹����� �ٲ���ҵ�?
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
		screenImage = createImage(ProjectMain.SCREEN_WIDTH, ProjectMain.SCREEN_HEIGHT); // ���α׷� ȭ�� ũ�⸸ŭ
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null); // â�� �̹��� �׸�
		g.setFont(new Font("����", Font.PLAIN, 20));
		g.drawString("�ŷ� ���� : " + Basic.hero.getLevelOfDeal(), 5, 20);
		g.drawString("�� : " + Basic.hero.getMoney(), 5, 40);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(back, 0, 0, null);
		paintComponents(g); // ��ư���� �����ΰ͵��� paintComponents�� �׷�����Ѵ�
		this.repaint();
	}
}

class HouseListView extends JPanel {
	private Basic basic;

	private Image screenImage;
	private Graphics screenGraphics;
	private Image bang, houses; // bang : ��� houses : �� �̹���
	private JButton back;

	HouseListView(Basic p) {
		this.basic = p;

		setLayout(null);
		back = new JButton("���ư���");
		back.setBounds(5, 630, 100, 50);
		back.addActionListener(listener);

		add(back);

		bang = new ImageIcon(ProjectMain.class.getResource("./images/bang.png")).getImage();
		houses = new ImageIcon(ProjectMain.class.getResource("./images/back.png")).getImage();

	}

	public void paint(Graphics g) {
		screenImage = createImage(ProjectMain.SCREEN_WIDTH, ProjectMain.SCREEN_HEIGHT); // ���α׷� ȭ�� ũ�⸸ŭ
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null); // â�� �̹��� �׸�
		g.setColor(Color.BLACK);
		g.setFont(new Font("����", Font.PLAIN, 20));
		g.drawString("�ŷ� ���� : " + Basic.hero.getLevelOfDeal(), 5, 20);
		g.drawString("�� : " + Basic.hero.getMoney(), 5, 40);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(bang, 0, 0, null);
		g.drawImage(houses, 50, 50, 300, 200, null);
		paintComponents(g); // ��ư���� �����ΰ͵��� paintComponents�� �׷�����Ѵ�
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
	private Image img_phone; // phone : �޴��� �̹���
	private JButton back;

	Phone(Basic p) {

		this.basic = p;

		setLayout(null);
		back = new JButton("���ư���");
		back.setBounds(5, 630, 100, 50);
		back.addActionListener(listener);

		add(back);

		img_phone = new ImageIcon(ProjectMain.class.getResource("./images/phone.png")).getImage();
	}

	public void paint(Graphics g) {
		screenImage = createImage(ProjectMain.SCREEN_WIDTH, ProjectMain.SCREEN_HEIGHT); // ���α׷� ȭ�� ũ�⸸ŭ
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null); // â�� �̹��� �׸�
		g.setColor(Color.BLACK);
		g.setFont(new Font("����", Font.PLAIN, 20));
		g.drawString("�ŷ� ���� : " + Basic.hero.getLevelOfDeal(), 5, 20);
		g.drawString("�� : " + Basic.hero.getMoney(), 5, 40);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(img_phone, 440, 0, null);
		paintComponents(g); // ��ư���� �����ΰ͵��� paintComponents�� �׷�����Ѵ�
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
	private Image img_tv; // phone : �޴��� �̹���
	private JButton back;

	TV(Basic p) {
		this.basic = p;
		
		setLayout(null);
		back = new JButton("���ư���");
		back.setBounds(5, 630, 100, 50);
		back.addActionListener(listener);

		add(back);


		img_tv = new ImageIcon(ProjectMain.class.getResource("./images/tv_80.png")).getImage();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(ProjectMain.SCREEN_WIDTH, ProjectMain.SCREEN_HEIGHT); // ���α׷� ȭ�� ũ�⸸ŭ
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null); // â�� �̹��� �׸�
		g.setColor(Color.BLACK);
		g.setFont(new Font("����", Font.PLAIN, 20));
		g.drawString("�ŷ� ���� : " + Basic.hero.getLevelOfDeal(), 5, 20);
		g.drawString("�� : " + Basic.hero.getMoney(), 5, 40);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(img_tv, 200, 0, null);
		paintComponents(g); // ��ư���� �����ΰ͵��� paintComponents�� �׷�����Ѵ�
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
