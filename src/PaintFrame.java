// HW2_���l�a_102403020_���2A

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class PaintFrame extends JFrame {

	private JPanel controlPanel; // ������
	private JPanel statusPanel;// ���A��
	private JLabel statusLabelMouse; // �ƹ���m����
	private JLabel statusLabelTools; // �u�㪬�A����
	private String Brush = "����", Size = "�p", Fill = "�_";// �u���ܼ�
	private JLabel statusLabelOthers; // ��L���A����
	private JLabel brushSizeLabel, toolLabel; // ����Τu�����
	private JComboBox toolComboBox; // ø�Ϥu��U�Կ��
	private String[] toolName = { "����", "���u", "���", "�x��" }; // ø�Ϥu�㪺�W��
	private JRadioButton smallBrush, mediumBrush, bigBrush; // ����j�p
	private ButtonGroup brushSizeButtonGroup; // ��������s�s��
	private JCheckBox ChBox;// �񺡫��s
	private JButton newCanvasButton; // �M�����s

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

		super("�p�e�a");

		controlPanel = new JPanel();// �H�U������ƪ���å[��controlPanel��

		toolLabel = new JLabel("[ø�Ϥu��]");
		toolLabel.setToolTipText("ø�Ϥu��");
		controlPanel.add(toolLabel);

		toolComboBox = new JComboBox(toolName);
		controlPanel.add(toolComboBox);

		brushSizeLabel = new JLabel("[����j�p]");
		brushSizeLabel.setToolTipText("����j�p");
		controlPanel.add(brushSizeLabel);

		smallBrush = new JRadioButton("�p", true);
		mediumBrush = new JRadioButton("��", false);
		bigBrush = new JRadioButton("�j", false);
		controlPanel.add(smallBrush);
		controlPanel.add(mediumBrush);
		controlPanel.add(bigBrush);

		brushSizeButtonGroup = new ButtonGroup();// �N�T�ӫ��s�[�J�s��,�����̥u����
		brushSizeButtonGroup.add(smallBrush);
		brushSizeButtonGroup.add(mediumBrush);
		brushSizeButtonGroup.add(bigBrush);

		ChBox = new JCheckBox("��");
		ChBox.setSelected(true);
		ChBox.setEnabled(false);
		controlPanel.add(ChBox);

		newCanvasButton = new JButton("�M���e��");
		controlPanel.add(newCanvasButton);

		controlPanel.setLayout(new GridLayout(10, 1));// �]�wcontrolPanel�����Υ[��JFrame��
		add(controlPanel, BorderLayout.WEST);

		statusPanel = new JPanel();// ����ƪ��A�C.�s�誩���å[�JJFrame��
		statusLabelMouse = new JLabel("�o�̬O�ƹ���m�C", SwingConstants.LEFT);
		statusLabelTools = new JLabel("�u�� : ���� , ����j�p : �p , �� : �_",
				SwingConstants.CENTER);
		statusLabelOthers = new JLabel("�o�̬O��L���A�C", SwingConstants.RIGHT);
		statusPanel.add(statusLabelMouse);
		statusPanel.add(statusLabelTools);
		statusPanel.add(statusLabelOthers);
		statusPanel.setLayout(new GridLayout(1, 3));
		add(statusPanel, BorderLayout.SOUTH);

		ButtonHandler BHandler = new ButtonHandler();// �H�U������ƨƥ�B�z�`���õ��U
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

	// �M���e�����s Button Handler
	private class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			statusLabelOthers.setText("���A : �M���e��");
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

	// ø�Ϥu�� ComboBox handler
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
					"�u�� : %s , ����j�p : %s , �� : %s", Brush, Size, Fill));
		}
	}

	// ����j�p RadioButton handler
	private class RadioButtonHandler implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			if (smallBrush.isSelected()) {
				Size = "�p";
			} else if (mediumBrush.isSelected()) {
				Size = "��";
			} else {
				Size = "�j";
			}
			statusLabelTools.setText(String.format(
					"�u�� : %s , ����j�p : %s , �� : %s", Brush, Size, Fill));
		}
	}

	// �񺡤�� Check Handler
	private class CheckHandler implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			if (ChBox.isSelected()) {
				Fill = "�O";
			} else {
				Fill = "�_";
			}

			statusLabelTools.setText(String.format(
					"�u�� : %s , ����j�p : %s , �� : %s", Brush, Size, Fill));
		}

	}

	// �ƹ����A Mouse Handler
	private class MouseHandler implements MouseListener, MouseMotionListener {
		public void mouseClicked(MouseEvent event) {
			statusLabelOthers.setText("���A : �I���ƹ�");
			statusLabelMouse.setText(String.format("��Ц�m : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mouseEntered(MouseEvent event) {
			statusLabelOthers.setText("���A : �ƹ��i�J");
			statusLabelMouse.setText(String.format("��Ц�m : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mouseExited(MouseEvent event) {
			statusLabelOthers.setText("���A : �ƹ����}");
			statusLabelMouse.setText(String.format("��Ц�m : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mousePressed(MouseEvent event) {
			statusLabelOthers.setText("���A : ���U�ƹ�");
			statusLabelMouse.setText(String.format("��Ц�m : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mouseReleased(MouseEvent event) {
			statusLabelOthers.setText("���A : �u�}�ƹ�");
			statusLabelMouse.setText(String.format("��Ц�m : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mouseDragged(MouseEvent event) {
			statusLabelOthers.setText("���A : �즲�ƹ�");
			statusLabelMouse.setText(String.format("��Ц�m : ( %d , %d )",
					event.getX(), event.getY()));
		}

		public void mouseMoved(MouseEvent event) {
			statusLabelOthers.setText("���A : ���ʷƹ�");
			statusLabelMouse.setText(String.format("��Ц�m : ( %d , %d )",
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
