// HW2_曾子軒_102403020_資管2A

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class PaintFrame extends JFrame {

	private JPanel controlPanel; // 控制欄
	private JPanel statusPanel;// 狀態欄
	private JLabel statusLabelMouse; // 滑鼠位置說明
	private JLabel statusLabelTools; // 工具狀態說明
	private String Brush = "筆刷", Size = "小", Fill = "否";// 工具變數
	private JLabel statusLabelOthers; // 其他狀態說明
	private JLabel brushSizeLabel, toolLabel; // 筆刷及工具標籤
	private JComboBox toolComboBox; // 繪圖工具下拉選單
	private String[] toolName = { "筆刷", "直線", "橢圓", "矩形" }; // 繪圖工具的名稱
	private JRadioButton smallBrush, mediumBrush, bigBrush; // 筆刷大小
	private ButtonGroup brushSizeButtonGroup; // 控制單選按鈕群組
	private JCheckBox ChBox;// 填滿按鈕
	private JButton newCanvasButton; // 清除按鈕

	private Point Spoint = new Point(-10, -10);
	private Point Cpoint = new Point(-10, -10);
	private int RectSPointCount = 0;
	private int RectEPointCount = 0;
	private Point[] RectSPoints = new Point[100];
	private Point[] RectEPoints = new Point[100];
	private int RectFSPointCount = 0;
	private int RectFEPointCount = 0;
	private Point[] RectFSPoints = new Point[100];
	private Point[] RectFEPoints = new Point[100];
	private int OvalSPointCount = 0;
	private int OvalEPointCount = 0;
	private Point[] OvalSPoints = new Point[100];
	private Point[] OvalEPoints = new Point[100];
	private int OvalFSPointCount = 0;
	private int OvalFEPointCount = 0;
	private Point[] OvalFSPoints = new Point[100];
	private Point[] OvalFEPoints = new Point[100];
	private int LineFSPointCount = 0;
	private int LineFEPointCount = 0;
	private Point[] LineFSPoints = new Point[100];
	private Point[] LineFEPoints = new Point[100];
	private int SPointCount = 0;
	private int MPointCount = 0;
	private int LPointCount = 0;
	private Point[] SPoints = new Point[10000];
	private Point[] MPoints = new Point[10000];
	private Point[] LPoints = new Point[10000];
	private int LineSPointCount = 0;
	private int LineEPointCount = 0;
	private Point[] LineSPoints = new Point[100];
	private Point[] LineEPoints = new Point[100];

	public PaintFrame() {

		super("小畫家");

		controlPanel = new JPanel();// 以下為實體化物件並加至controlPanel中

		toolLabel = new JLabel("[繪圖工具]");
		toolLabel.setToolTipText("繪圖工具");
		controlPanel.add(toolLabel);

		toolComboBox = new JComboBox(toolName);
		controlPanel.add(toolComboBox);

		brushSizeLabel = new JLabel("[筆刷大小]");
		brushSizeLabel.setToolTipText("筆刷大小");
		controlPanel.add(brushSizeLabel);

		smallBrush = new JRadioButton("小", true);
		mediumBrush = new JRadioButton("中", false);
		bigBrush = new JRadioButton("大", false);
		controlPanel.add(smallBrush);
		controlPanel.add(mediumBrush);
		controlPanel.add(bigBrush);

		brushSizeButtonGroup = new ButtonGroup();// 將三個按鈕加入群組,讓它們只能單選
		brushSizeButtonGroup.add(smallBrush);
		brushSizeButtonGroup.add(mediumBrush);
		brushSizeButtonGroup.add(bigBrush);

		ChBox = new JCheckBox("填滿");
		ChBox.setSelected(true);
		ChBox.setEnabled(false);
		controlPanel.add(ChBox);

		newCanvasButton = new JButton("清除畫面");
		controlPanel.add(newCanvasButton);

		controlPanel.setLayout(new GridLayout(10, 1));// 設定controlPanel版面及加到JFrame中
		add(controlPanel, BorderLayout.WEST);

		statusPanel = new JPanel();// 實體化狀態列.編輯版面並加入JFrame中
		statusLabelMouse = new JLabel("這裡是滑鼠位置列", SwingConstants.LEFT);
		statusLabelTools = new JLabel("工具 : 筆刷 , 筆刷大小 : 小 , 填滿 : 否",
				SwingConstants.CENTER);
		statusLabelOthers = new JLabel("這裡是其他狀態列", SwingConstants.RIGHT);
		statusPanel.add(statusLabelMouse);
		statusPanel.add(statusLabelTools);
		statusPanel.add(statusLabelOthers);
		statusPanel.setLayout(new GridLayout(1, 3));
		add(statusPanel, BorderLayout.SOUTH);

		ButtonHandler BHandler = new ButtonHandler();// 以下為實體化事件處理常式並註冊
		newCanvasButton.addActionListener(BHandler);

		CheckHandler CHandler = new CheckHandler();
		ChBox.addItemListener(CHandler);

		RadioButtonHandler RHandler = new RadioButtonHandler();
		smallBrush.addItemListener(RHandler);
		mediumBrush.addItemListener(RHandler);
		bigBrush.addItemListener(RHandler);

		ToolTipHandler THandler = new ToolTipHandler();
		toolComboBox.addItemListener(THandler);

		drawPanel drawPanel = new drawPanel();
		drawPanel.setBackground(Color.WHITE);
		add(drawPanel, BorderLayout.CENTER);

		MouseHandler MHandler = new MouseHandler();
		drawPanel.addMouseListener(MHandler);
		drawPanel.addMouseMotionListener(MHandler);

	}

	// 清除畫面按鈕 Button Handler
	private class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			statusLabelOthers.setText("狀態 : 清除畫面");
			Spoint = new Point(-10, -10);
			Cpoint = new Point(-10, -10);
			SPointCount = 0;
			MPointCount = 0;
			LPointCount = 0;
			SPoints = new Point[10000];
			MPoints = new Point[10000];
			LPoints = new Point[10000];

			RectSPointCount = 0;
			RectEPointCount = 0;
			RectSPoints = new Point[100];
			RectEPoints = new Point[100];
			RectFSPointCount = 0;
			RectFEPointCount = 0;
			RectFSPoints = new Point[100];
			RectFEPoints = new Point[100];
			OvalSPointCount = 0;
			OvalEPointCount = 0;
			OvalSPoints = new Point[100];
			OvalEPoints = new Point[100];
			OvalFSPointCount = 0;
			OvalFEPointCount = 0;
			OvalFSPoints = new Point[100];
			OvalFEPoints = new Point[100];
			LineFSPointCount = 0;
			LineFEPointCount = 0;
			LineFSPoints = new Point[100];
			LineFEPoints = new Point[100];
			LineSPointCount = 0;
			LineEPointCount = 0;
			LineSPoints = new Point[100];
			LineEPoints = new Point[100];
			SPointCount = 0;
			MPointCount = 0;
			LPointCount = 0;
			SPoints = new Point[10000];
			MPoints = new Point[10000];
			LPoints = new Point[10000];
			repaint();
		}
	}

	// 繪圖工具 ComboBox handler
	private class ToolTipHandler implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				Brush = toolName[toolComboBox.getSelectedIndex()];
			}
			if (toolComboBox.getSelectedIndex() == 0) {
				ChBox.setSelected(true);
				ChBox.setEnabled(false);
			} else {
				ChBox.setSelected(false);
				ChBox.setEnabled(true);
			}
			if (toolComboBox.getSelectedIndex() == 0) {
				smallBrush.setSelected(true);
				smallBrush.setEnabled(true);
				mediumBrush.setEnabled(true);
				bigBrush.setEnabled(true);
			} else {
				smallBrush.setSelected(true);
				smallBrush.setEnabled(false);
				mediumBrush.setEnabled(false);
				bigBrush.setEnabled(false);
			}
			statusLabelTools.setText(String.format(
					"工具 : %s , 筆刷大小 : %s , 填滿 : %s", Brush, Size, Fill));
		}
	}

	// 筆刷大小 RadioButton handler
	private class RadioButtonHandler implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			if (smallBrush.isSelected()) {
				Size = "小";
			} else if (mediumBrush.isSelected()) {
				Size = "中";
			} else {
				Size = "大";
			}
			statusLabelTools.setText(String.format(
					"工具 : %s , 筆刷大小 : %s , 填滿 : %s", Brush, Size, Fill));
		}
	}

	// 填滿方塊 Check Handler
	private class CheckHandler implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			if (ChBox.isSelected()) {
				Fill = "是";
			} else {
				Fill = "否";
			}

			statusLabelTools.setText(String.format(
					"工具 : %s , 筆刷大小 : %s , 填滿 : %s", Brush, Size, Fill));
		}

	}

	// 滑鼠狀態 Mouse Handler
	private class MouseHandler implements MouseListener, MouseMotionListener {
		public void mouseClicked(MouseEvent event) {
			statusLabelOthers.setText("狀態 : 點擊滑鼠");
			statusLabelMouse.setText(String.format("游標位置 : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mouseEntered(MouseEvent event) {
			statusLabelOthers.setText("狀態 : 滑鼠進入");
			statusLabelMouse.setText(String.format("游標位置 : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mouseExited(MouseEvent event) {
			statusLabelOthers.setText("狀態 : 滑鼠離開");
			statusLabelMouse.setText(String.format("游標位置 : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mousePressed(MouseEvent event) {
			statusLabelOthers.setText("狀態 : 按下滑鼠");
			statusLabelMouse.setText(String.format("游標位置 : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mouseReleased(MouseEvent event) {
			statusLabelOthers.setText("狀態 : 彈開滑鼠");
			statusLabelMouse.setText(String.format("游標位置 : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mouseDragged(MouseEvent event) {
			statusLabelOthers.setText("狀態 : 拖曳滑鼠");
			statusLabelMouse.setText(String.format("游標位置 : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mouseMoved(MouseEvent event) {
			statusLabelOthers.setText("狀態 : 移動滑鼠");
			statusLabelMouse.setText(String.format("游標位置 : ( %d , %d )",
					event.getX(), event.getY()));
		}
	}

	private class drawPanel extends JPanel {

		public drawPanel() {
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent event) {
					if (toolComboBox.getSelectedIndex() == 0) {
						Cpoint = event.getPoint();
						repaint();
						if (smallBrush.isSelected()) {
							if (SPointCount < SPoints.length) {
								SPoints[SPointCount] = event.getPoint();
								SPointCount++;
							}
						} else if (mediumBrush.isSelected()) {
							if (MPointCount < MPoints.length) {
								MPoints[MPointCount] = event.getPoint();
								MPointCount++;
							}
						} else {
							if (LPointCount < LPoints.length) {
								LPoints[LPointCount] = event.getPoint();
								LPointCount++;
							}
						}
					} else if (toolComboBox.getSelectedIndex() == 1) {
						Cpoint = event.getPoint();
						repaint();
					} else if (toolComboBox.getSelectedIndex() == 2) {
						Cpoint = event.getPoint();
						repaint();
					} else if (toolComboBox.getSelectedIndex() == 3) {
						Cpoint = event.getPoint();
						repaint();
					}
				}
			});
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent event) {
					if (toolComboBox.getSelectedIndex() == 0) {
						Cpoint = event.getPoint();
						repaint();
						if (smallBrush.isSelected()) {
							if (SPointCount < SPoints.length) {
								SPoints[SPointCount] = event.getPoint();
								SPointCount++;
							}
						} else if (mediumBrush.isSelected()) {
							if (MPointCount < MPoints.length) {
								MPoints[MPointCount] = event.getPoint();
								MPointCount++;
							}
						} else {
							if (LPointCount < LPoints.length) {
								LPoints[LPointCount] = event.getPoint();
								LPointCount++;
							}
						}
					} else if (toolComboBox.getSelectedIndex() == 1) {
						Spoint = event.getPoint();
						if (ChBox.isSelected()) {
							if (LineFSPointCount < LineFSPoints.length) {
								LineFSPoints[LineFSPointCount] = event
										.getPoint();
								LineFSPointCount++;
							}
						} else {
							if (LineSPointCount < LineSPoints.length) {
								LineSPoints[LineSPointCount] = event.getPoint();
								LineSPointCount++;
							}
						}
					} else if (toolComboBox.getSelectedIndex() == 2) {
						Spoint = event.getPoint();
						if (ChBox.isSelected()) {
							if (OvalFSPointCount < OvalFSPoints.length) {
								OvalFSPoints[OvalFSPointCount] = event
										.getPoint();
								OvalFSPointCount++;
							}
						} else {
							if (OvalSPointCount < OvalSPoints.length) {
								OvalSPoints[OvalSPointCount] = event.getPoint();
								OvalSPointCount++;
							}
						}
					} else if (toolComboBox.getSelectedIndex() == 3) {
						Spoint = event.getPoint();
						if (ChBox.isSelected()) {
							if (RectFSPointCount < RectFSPoints.length) {
								RectFSPoints[RectFSPointCount] = event
										.getPoint();
								RectFSPointCount++;
							}
						} else {
							if (RectSPointCount < RectSPoints.length) {
								RectSPoints[RectSPointCount] = event.getPoint();
								RectSPointCount++;
							}
						}
					}
				}

				public void mouseReleased(MouseEvent event) {
					if (toolComboBox.getSelectedIndex() == 1) {
						if (ChBox.isSelected()) {
							if (LineFEPointCount < LineFEPoints.length) {
								LineFEPoints[LineFEPointCount] = event
										.getPoint();
								LineFEPointCount++;
							}
						} else {
							if (LineEPointCount < LineEPoints.length) {
								LineEPoints[LineEPointCount] = event.getPoint();
								LineEPointCount++;
							}
						}
					} else if (toolComboBox.getSelectedIndex() == 2) {
						if (ChBox.isSelected()) {
							if (OvalFEPointCount < OvalFEPoints.length) {
								OvalFEPoints[OvalFEPointCount] = event
										.getPoint();
								OvalFEPointCount++;
								repaint();
							}
						} else {
							if (OvalEPointCount < OvalEPoints.length) {
								OvalEPoints[OvalEPointCount] = event.getPoint();
								OvalEPointCount++;
								repaint();
							}
						}
					} else if (toolComboBox.getSelectedIndex() == 3) {
						if (ChBox.isSelected()) {
							if (RectFEPointCount < RectFEPoints.length) {
								RectFEPoints[RectFEPointCount] = event
										.getPoint();
								RectFEPointCount++;
								repaint();
							}
						} else {
							if (RectEPointCount < RectEPoints.length) {
								RectEPoints[RectEPointCount] = event.getPoint();
								RectEPointCount++;
								repaint();
							}
						}
					}
				}

				public void mouseExited(MouseEvent event) {
					Spoint = new Point();
					Cpoint = new Point();

				}

			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g.create();
			BasicStroke Stroke = new BasicStroke(2, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_BEVEL, 20, new float[] { 10F, 20F }, 0);
			g2.setStroke(Stroke);
			if (toolComboBox.getSelectedIndex() == 0) {
				if (smallBrush.isSelected()) {
					g.fillOval(Cpoint.x, Cpoint.y, 4, 4);
				} else if (mediumBrush.isSelected()) {
					g.fillOval(Cpoint.x, Cpoint.y, 7, 7);
				} else {
					g.fillOval(Cpoint.x, Cpoint.y, 10, 10);
				}
			} else if (toolComboBox.getSelectedIndex() == 1) {
				if (ChBox.isSelected()) {
					g.drawLine(Spoint.x, Spoint.y, Cpoint.x, Cpoint.y);
				} else {

					g2.drawLine(Spoint.x, Spoint.y, Cpoint.x, Cpoint.y);
				}
			} else if (toolComboBox.getSelectedIndex() == 2) {

				if (ChBox.isSelected()) {
					if ((Cpoint.x - Spoint.x > 0) && (Cpoint.y - Spoint.y > 0)) {
						g.fillOval(Spoint.x, Spoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else if ((Cpoint.x - Spoint.x > 0)
							&& (Cpoint.y - Spoint.y < 0)) {
						g.fillOval(Spoint.x, Cpoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else if ((Cpoint.x - Spoint.x < 0)
							&& (Cpoint.y - Spoint.y > 0)) {
						g.fillOval(Cpoint.x, Spoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else {
						g.fillOval(Cpoint.x, Cpoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					}
				} else {
					if ((Cpoint.x - Spoint.x > 0) && (Cpoint.y - Spoint.y > 0)) {
						g.drawOval(Spoint.x, Spoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else if ((Cpoint.x - Spoint.x > 0)
							&& (Cpoint.y - Spoint.y < 0)) {
						g.drawOval(Spoint.x, Cpoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else if ((Cpoint.x - Spoint.x < 0)
							&& (Cpoint.y - Spoint.y > 0)) {
						g.drawOval(Cpoint.x, Spoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else {
						g.drawOval(Cpoint.x, Cpoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					}
				}
			} else if (toolComboBox.getSelectedIndex() == 3) {

				if (ChBox.isSelected()) {
					if ((Cpoint.x - Spoint.x > 0) && (Cpoint.y - Spoint.y > 0)) {
						g.fillRect(Spoint.x, Spoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else if ((Cpoint.x - Spoint.x > 0)
							&& (Cpoint.y - Spoint.y < 0)) {
						g.fillRect(Spoint.x, Cpoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else if ((Cpoint.x - Spoint.x < 0)
							&& (Cpoint.y - Spoint.y > 0)) {
						g.fillRect(Cpoint.x, Spoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else {
						g.fillRect(Cpoint.x, Cpoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					}
				} else {
					if ((Cpoint.x - Spoint.x > 0) && (Cpoint.y - Spoint.y > 0)) {
						g.drawRect(Spoint.x, Spoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else if ((Cpoint.x - Spoint.x > 0)
							&& (Cpoint.y - Spoint.y < 0)) {
						g.drawRect(Spoint.x, Cpoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else if ((Cpoint.x - Spoint.x < 0)
							&& (Cpoint.y - Spoint.y > 0)) {
						g.drawRect(Cpoint.x, Spoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					} else {
						g.drawRect(Cpoint.x, Cpoint.y,
								Math.abs(Cpoint.x - Spoint.x),
								Math.abs(Cpoint.y - Spoint.y));
					}
				}
			}
			for (int i = 0; i < RectEPointCount; i++) {
				if ((RectEPoints[i].x - RectSPoints[i].x > 0)
						&& (RectEPoints[i].y - RectSPoints[i].y > 0)) {
					g.drawRect(RectSPoints[i].x, RectSPoints[i].y,
							Math.abs(RectEPoints[i].x - RectSPoints[i].x),
							Math.abs(RectEPoints[i].y - RectSPoints[i].y));
				} else if ((RectEPoints[i].x - RectSPoints[i].x > 0)
						&& (RectEPoints[i].y - RectSPoints[i].y < 0)) {
					g.drawRect(RectSPoints[i].x, RectEPoints[i].y,
							Math.abs(RectEPoints[i].x - RectSPoints[i].x),
							Math.abs(RectEPoints[i].y - RectSPoints[i].y));
				} else if ((RectEPoints[i].x - RectSPoints[i].x < 0)
						&& (RectEPoints[i].y - RectSPoints[i].y > 0)) {
					g.drawRect(RectEPoints[i].x, RectSPoints[i].y,
							Math.abs(RectEPoints[i].x - RectSPoints[i].x),
							Math.abs(RectEPoints[i].y - RectSPoints[i].y));
				} else {
					g.drawRect(RectEPoints[i].x, RectEPoints[i].y,
							Math.abs(RectEPoints[i].x - RectSPoints[i].x),
							Math.abs(RectEPoints[i].y - RectSPoints[i].y));
				}
			}
			for (int i = 0; i < RectFEPointCount; i++) {
				if ((RectFEPoints[i].x - RectFSPoints[i].x > 0)
						&& (RectFEPoints[i].y - RectFSPoints[i].y > 0)) {
					g.fillRect(RectFSPoints[i].x, RectFSPoints[i].y,
							Math.abs(RectFEPoints[i].x - RectFSPoints[i].x),
							Math.abs(RectFEPoints[i].y - RectFSPoints[i].y));
				} else if ((RectFEPoints[i].x - RectFSPoints[i].x > 0)
						&& (RectFEPoints[i].y - RectFSPoints[i].y < 0)) {
					g.fillRect(RectFSPoints[i].x, RectFEPoints[i].y,
							Math.abs(RectFEPoints[i].x - RectFSPoints[i].x),
							Math.abs(RectFEPoints[i].y - RectFSPoints[i].y));
				} else if ((RectFEPoints[i].x - RectFSPoints[i].x < 0)
						&& (RectFEPoints[i].y - RectFSPoints[i].y > 0)) {
					g.fillRect(RectFEPoints[i].x, RectFSPoints[i].y,
							Math.abs(RectFEPoints[i].x - RectFSPoints[i].x),
							Math.abs(RectFEPoints[i].y - RectFSPoints[i].y));
				} else {
					g.fillRect(RectFEPoints[i].x, RectFEPoints[i].y,
							Math.abs(RectFEPoints[i].x - RectFSPoints[i].x),
							Math.abs(RectFEPoints[i].y - RectFSPoints[i].y));
				}
			}
			for (int i = 0; i < OvalEPointCount; i++) {
				if ((OvalEPoints[i].x - OvalSPoints[i].x > 0)
						&& (OvalEPoints[i].y - OvalSPoints[i].y > 0)) {
					g.drawOval(OvalSPoints[i].x, OvalSPoints[i].y,
							Math.abs(OvalEPoints[i].x - OvalSPoints[i].x),
							Math.abs(OvalEPoints[i].y - OvalSPoints[i].y));
				} else if ((OvalEPoints[i].x - OvalSPoints[i].x > 0)
						&& (OvalEPoints[i].y - OvalSPoints[i].y < 0)) {
					g.drawOval(OvalSPoints[i].x, OvalEPoints[i].y,
							Math.abs(OvalEPoints[i].x - OvalSPoints[i].x),
							Math.abs(OvalEPoints[i].y - OvalSPoints[i].y));
				} else if ((OvalEPoints[i].x - OvalSPoints[i].x < 0)
						&& (OvalEPoints[i].y - OvalSPoints[i].y > 0)) {
					g.drawOval(OvalEPoints[i].x, OvalSPoints[i].y,
							Math.abs(OvalEPoints[i].x - OvalSPoints[i].x),
							Math.abs(OvalEPoints[i].y - OvalSPoints[i].y));
				} else {
					g.drawOval(OvalEPoints[i].x, OvalEPoints[i].y,
							Math.abs(OvalEPoints[i].x - OvalSPoints[i].x),
							Math.abs(OvalEPoints[i].y - OvalSPoints[i].y));
				}
			}
			for (int i = 0; i < OvalFEPointCount; i++) {
				if ((OvalFEPoints[i].x - OvalFSPoints[i].x > 0)
						&& (OvalFEPoints[i].y - OvalFSPoints[i].y > 0)) {
					g.fillOval(OvalFSPoints[i].x, OvalFSPoints[i].y,
							Math.abs(OvalFEPoints[i].x - OvalFSPoints[i].x),
							Math.abs(OvalFEPoints[i].y - OvalFSPoints[i].y));
				} else if ((OvalFEPoints[i].x - OvalFSPoints[i].x > 0)
						&& (OvalFEPoints[i].y - OvalFSPoints[i].y < 0)) {
					g.fillOval(OvalFSPoints[i].x, OvalFEPoints[i].y,
							Math.abs(OvalFEPoints[i].x - OvalFSPoints[i].x),
							Math.abs(OvalFEPoints[i].y - OvalFSPoints[i].y));
				} else if ((OvalFEPoints[i].x - OvalFSPoints[i].x < 0)
						&& (OvalFEPoints[i].y - OvalFSPoints[i].y > 0)) {
					g.fillOval(OvalFEPoints[i].x, OvalFSPoints[i].y,
							Math.abs(OvalFEPoints[i].x - OvalFSPoints[i].x),
							Math.abs(OvalFEPoints[i].y - OvalFSPoints[i].y));
				} else {
					g.fillOval(OvalFEPoints[i].x, OvalFEPoints[i].y,
							Math.abs(OvalFEPoints[i].x - OvalFSPoints[i].x),
							Math.abs(OvalFEPoints[i].y - OvalFSPoints[i].y));
				}
			}
			for (int i = 0; i < LineFEPointCount; i++) {
				g.drawLine(LineFSPoints[i].x, LineFSPoints[i].y,
						LineFEPoints[i].x, LineFEPoints[i].y);
			}
			for (int i = 0; i < LineEPointCount; i++) {

				g2.drawLine(LineSPoints[i].x, LineSPoints[i].y,
						LineEPoints[i].x, LineEPoints[i].y);
			}
			for (int i = 0; i < SPointCount; i++) {
				g.fillOval(SPoints[i].x, SPoints[i].y, 4, 4);
			}
			for (int i = 0; i < MPointCount; i++) {
				g.fillOval(MPoints[i].x, MPoints[i].y, 7, 7);
			}
			for (int i = 0; i < LPointCount; i++) {
				g.fillOval(LPoints[i].x, LPoints[i].y, 10, 10);
			}
		}
	}
}
