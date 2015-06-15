package ua.kharkiv.mongorigami.Evaluation.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.Scrollable;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class MainFrame {

	private JFrame frame;
	private JTable table;
	private JTextField txtInsert_1;
	private JTextField txtInsert;
	private JTextField txtexists;
	private JTextField txtRemove;
	private JTextField textField_2;
	private JTextField txtUpdate;
	private JTextField txtpush;
	private JTextField txtunderwriting;
	private JTextField txtpush_1;
	private JTextField txtAggregate;
	private JTextField textField_3;
	private JTextField txtsum;
	private JTextField txtcount;
	private JTextField txtUnderwritingstaffid;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTable table_1;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_11;
	private JTextField txtUnderwritingstaffid_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 909, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Schemas") {
				{
					add(new DefaultMutableTreeNode("Schema 1"));
					add(new DefaultMutableTreeNode("Schema 2"));
					add(new DefaultMutableTreeNode("Schema 1 (1)"));
				}
			}
		));
		frame.getContentPane().add(new JScrollPane(tree), BorderLayout.WEST);

		JPanel panel = new JPanel();
		JScrollPane scrollPane_1 = new JScrollPane(panel);
		frame.getContentPane().add(scrollPane_1, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder("Insert ass."));
		panel_2.setBounds(10, 97, 586, 217);
		panel_2.setPreferredSize(new Dimension(586, 315));

		txtInsert_1 = new JTextField();
		txtInsert_1.setText("Insert1");
		txtInsert_1
				.setToolTipText("Insert a reference name of the assessment herre");
		txtInsert_1.setBounds(66, 29, 166, 20);
		panel_2.add(txtInsert_1);
		txtInsert_1.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(21, 32, 46, 14);
		panel_2.add(lblName);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Find ass.",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 97, 586, 389);

		txtInsert = new JTextField();
		txtInsert.setText("Insert1");
		txtInsert
				.setToolTipText("Insert a reference name of the assessment herre");
		txtInsert.setColumns(10);
		txtInsert.setBounds(66, 29, 166, 20);
		panel_3.add(txtInsert);

		JLabel label = new JLabel("Name:");
		label.setBounds(21, 32, 46, 14);
		panel_3.add(label);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Fields",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(21, 76, 525, 157);
		panel_3.add(panel_4);
		panel_4.setLayout(null);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"policy._id", Boolean.FALSE},
				{"policy.state", Boolean.TRUE},
				{"car._id", Boolean.TRUE},
				{"car.model", Boolean.FALSE},
				{"car.mileage", Boolean.FALSE},
				{"car.plateNum", Boolean.FALSE},
				{"car.company", Boolean.FALSE},
				{"car.buildYear", Boolean.FALSE},
			},
			new String[] {
				"Filed", "Select"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(113);
		table.getColumnModel().getColumn(1).setPreferredWidth(42);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(table);

		JScrollPane scrollPane_2 = new JScrollPane(table);
		scrollPane_2.setBounds(12, 29, 489, 116);
		panel_4.add(scrollPane_2);
		scrollPane_2.setViewportBorder(new BevelBorder(BevelBorder.LOWERED,
				null, null, null, null));

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Query", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		panel_5.setBounds(21, 245, 525, 97);
		panel_3.add(panel_5);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {
				"car.model", "policy._id", "policy.state", "car._id",
				"car.model", "car.mileage", "car.plateNum" }));
		comboBox_1.setBounds(12, 23, 189, 24);
		panel_5.add(comboBox_1);

		txtexists = new JTextField();
		txtexists.setText("{ $exists : true }");
		txtexists.setBounds(233, 26, 269, 19);
		panel_5.add(txtexists);
		txtexists.setColumns(10);

		JButton btnAddQueryElement = new JButton("Add query element");
		btnAddQueryElement.setBounds(334, 57, 168, 25);
		panel_5.add(btnAddQueryElement);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Remove ass.", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_6.setBounds(10, 88, 586, 490);
		panel_6.setPreferredSize(new Dimension(586, 540));

		txtRemove = new JTextField();
		txtRemove
				.setToolTipText("Insert a reference name of the assessment herre");
		txtRemove.setText("Remove1");
		txtRemove.setColumns(10);
		txtRemove.setBounds(66, 29, 166, 20);
		panel_6.add(txtRemove);

		JPanel panel_23 = new JPanel();
		panel_23.setLayout(null);
		panel_23.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Stop criterion", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_23.setBounds(21, 434, 525, 87);
		panel_6.add(panel_23);

		JRadioButton radioButton = new JRadioButton("Confidence value");
		radioButton.setBounds(131, 19, 109, 23);
		panel_23.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("Sample cardinality");
		radioButton_1.setSelected(true);
		radioButton_1.setBounds(6, 19, 111, 23);
		panel_23.add(radioButton_1);

		textField_13 = new JTextField();
		textField_13.setText("50000");
		textField_13.setColumns(10);
		textField_13.setBounds(10, 49, 230, 20);
		panel_23.add(textField_13);

		JLabel label_1 = new JLabel("Name:");
		label_1.setBounds(21, 32, 46, 14);
		panel_6.add(label_1);

		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Fields to remove", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_9.setBounds(21, 267, 525, 157);
		panel_6.add(panel_9);

		JScrollPane scrollPane_3 = new JScrollPane(table);
		scrollPane_3.setViewportBorder(new BevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		scrollPane_3.setBounds(12, 29, 489, 116);
		panel_9.add(scrollPane_3);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Options",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setLayout(null);
		panel_7.setBounds(21, 60, 525, 60);
		panel_6.add(panel_7);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Just one");
		chckbxNewCheckBox.setBounds(6, 18, 129, 23);
		panel_7.add(chckbxNewCheckBox);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Query", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		panel_8.setBounds(21, 132, 525, 123);
		panel_6.add(panel_8);

		JComboBox comboBox_2 = new JComboBox(new String[] { "car.model",
				"policy._id", "policy.state", "car._id", "car.model",
				"car.mileage", "car.plateNum" });
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {
				"policy._id", "policy.state", "car._id", "car.model",
				"car.mileage", "car.plateNum" }));
		comboBox_2.setBounds(12, 23, 189, 24);
		panel_8.add(comboBox_2);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(233, 26, 269, 19);
		panel_8.add(textField_2);

		JButton button = new JButton("Add query element");
		button.setBounds(334, 67, 168, 25);
		panel_8.add(button);

		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Update ass.", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_10.setBounds(10, 88, 586, 425);
		panel_10.setPreferredSize(new Dimension(586, 470));

		txtUpdate = new JTextField();
		txtUpdate
				.setToolTipText("Insert a reference name of the assessment herre");
		txtUpdate.setText("Update1");
		txtUpdate.setColumns(10);
		txtUpdate.setBounds(66, 29, 166, 20);
		panel_10.add(txtUpdate);

		JLabel label_2 = new JLabel("Name:");
		label_2.setBounds(21, 32, 46, 14);
		panel_10.add(label_2);

		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBorder(new TitledBorder(null, "Options",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12.setBounds(21, 60, 525, 60);
		panel_10.add(panel_12);

		JCheckBox chckbxUpsert = new JCheckBox("Upsert");
		chckbxUpsert.setBounds(10, 22, 78, 23);
		panel_12.add(chckbxUpsert);

		JCheckBox chckbxMulti = new JCheckBox("Multi");
		chckbxMulti.setBounds(104, 22, 129, 23);
		panel_12.add(chckbxMulti);

		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Query", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		panel_13.setBounds(21, 132, 525, 123);
		panel_10.add(panel_13);

		@SuppressWarnings("unchecked")
		JComboBox<?> comboBox_3 = new JComboBox(new Object[] {});
		comboBox_3.setModel(new DefaultComboBoxModel(
				new String[] { "policy._id" }));
		comboBox_3.setBounds(12, 23, 189, 24);
		panel_13.add(comboBox_3);

		txtpush = new JTextField();
		txtpush.setColumns(10);
		txtpush.setBounds(233, 26, 269, 19);
		panel_13.add(txtpush);

		JButton button_1 = new JButton("Add query element");
		button_1.setBounds(334, 67, 168, 25);
		panel_13.add(button_1);

		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Update", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		panel_11.setBounds(21, 267, 525, 123);
		panel_10.add(panel_11);

		txtunderwriting = new JTextField();
		txtunderwriting.setText("#underwriting");
		txtunderwriting.setColumns(10);
		txtunderwriting.setBounds(233, 26, 269, 19);
		panel_11.add(txtunderwriting);

		JButton btnAddUpdateElement = new JButton("Add update element");
		btnAddUpdateElement.setBounds(306, 67, 196, 25);
		panel_11.add(btnAddUpdateElement);

		txtpush_1 = new JTextField();
		txtpush_1.setText("$push");
		txtpush_1.setColumns(10);
		txtpush_1.setBounds(12, 26, 196, 19);
		panel_11.add(txtpush_1);

		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Aggregate ass.", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_14.setBounds(10, 88, 586, 425);
		panel_14.setPreferredSize(new Dimension(586, 550));

		txtAggregate = new JTextField();
		txtAggregate
				.setToolTipText("Insert a reference name of the assessment herre");
		txtAggregate.setText("Aggregate1");
		txtAggregate.setColumns(10);
		txtAggregate.setBounds(66, 29, 166, 20);
		panel_14.add(txtAggregate);

		JLabel label_3 = new JLabel("Name:");
		label_3.setBounds(21, 32, 46, 14);
		panel_14.add(label_3);

		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "$match", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		panel_16.setBounds(21, 132, 525, 123);
		panel_14.add(panel_16);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(233, 26, 269, 19);
		panel_16.add(textField_3);

		JButton button_2 = new JButton("Add query element");
		button_2.setBounds(334, 67, 168, 25);
		panel_16.add(button_2);

		txtUnderwritingstaffid = new JTextField();
		txtUnderwritingstaffid.setText("underwriting.staff_id");
		txtUnderwritingstaffid.setColumns(10);
		txtUnderwritingstaffid.setBounds(12, 26, 196, 19);
		panel_16.add(txtUnderwritingstaffid);

		JPanel panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "$group", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		panel_17.setBounds(21, 267, 525, 150);
		panel_14.add(panel_17);

		txtsum = new JTextField();
		txtsum.setText("{ $sum : 1 }");
		txtsum.setColumns(10);
		txtsum.setBounds(231, 57, 269, 19);
		panel_17.add(txtsum);

		JButton button_3 = new JButton("Add update element");
		button_3.setBounds(304, 98, 196, 25);
		panel_17.add(button_3);

		txtcount = new JTextField();
		txtcount.setText("$count");
		txtcount.setColumns(10);
		txtcount.setBounds(10, 57, 196, 19);
		panel_17.add(txtcount);
		
		txtUnderwritingstaffid_1 = new JTextField();
		txtUnderwritingstaffid_1.setText("underwriting.staff_id");
		txtUnderwritingstaffid_1.setColumns(10);
		txtUnderwritingstaffid_1.setBounds(231, 27, 269, 19);
		panel_17.add(txtUnderwritingstaffid_1);
		
		JLabel lblid = new JLabel("_id:");
		lblid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblid.setBounds(10, 30, 196, 14);
		panel_17.add(lblid);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Global settings",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		panel_1.setPreferredSize(panel_1.getSize());
		panel.setLayout(new GridLayout(0, 1, 0, 10));

		JPanel panel_19 = new JPanel();
		panel_19.setBorder(new TitledBorder(null, "Assessment components",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_19.setBounds(10, 23, 764, 59);
		panel_1.add(panel_19);
		panel_19.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"insert", "find", "remove", "update", "aggregate"}));
		comboBox.setBounds(10, 22, 99, 20);
		panel_19.add(comboBox);
		comboBox.addItem("insert");
		comboBox.addItem("find");
		comboBox.addItem("remove");
		comboBox.addItem("update");
		comboBox.addItem("aggregate");

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(134, 21, 70, 23);
		panel_19.add(btnAdd);

		JPanel panel_18 = new JPanel();
		panel_18.setBounds(10, 95, 774, 186);
		panel_1.add(panel_18);
		panel_18.setLayout(null);
		panel_18.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Weights", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		panel_18.setPreferredSize(panel_18.getSize());

		textField_6 = new JTextField();
		textField_6.setText("5");
		textField_6
				.setToolTipText("Insert a reference name of the assessment herre");
		textField_6.setColumns(10);
		textField_6.setBounds(139, 45, 86, 20);
		panel_18.add(textField_6);

		JLabel lblInsert = new JLabel("Insert:");
		lblInsert.setBounds(10, 47, 111, 14);
		panel_18.add(lblInsert);

		JLabel lblFind = new JLabel("Find:");
		lblFind.setBounds(10, 73, 76, 14);
		panel_18.add(lblFind);

		textField_7 = new JTextField();
		textField_7.setText("2");
		textField_7
				.setToolTipText("Insert a reference name of the assessment herre");
		textField_7.setColumns(10);
		textField_7.setBounds(139, 71, 86, 20);
		panel_18.add(textField_7);

		JLabel lblRemove = new JLabel("Remove:");
		lblRemove.setBounds(10, 100, 76, 14);
		panel_18.add(lblRemove);

		textField_8 = new JTextField();
		textField_8.setText("3");
		textField_8
				.setToolTipText("Insert a reference name of the assessment herre");
		textField_8.setColumns(10);
		textField_8.setBounds(139, 98, 86, 20);
		panel_18.add(textField_8);

		JLabel lblUpdate = new JLabel("Update:");
		lblUpdate.setBounds(10, 127, 76, 14);
		panel_18.add(lblUpdate);

		textField_9 = new JTextField();
		textField_9.setText("4");
		textField_9
				.setToolTipText("Insert a reference name of the assessment herre");
		textField_9.setColumns(10);
		textField_9.setBounds(139, 125, 86, 20);
		panel_18.add(textField_9);

		JLabel lblAggregate = new JLabel("Aggregate:");
		lblAggregate.setBounds(10, 154, 76, 14);
		panel_18.add(lblAggregate);

		textField_10 = new JTextField();
		textField_10.setText("1");
		textField_10
				.setToolTipText("Insert a reference name of the assessment herre");
		textField_10.setColumns(10);
		textField_10.setBounds(139, 152, 86, 20);
		panel_18.add(textField_10);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Qualitive assessment");
		chckbxNewCheckBox_1.setSelected(true);
		chckbxNewCheckBox_1.setBounds(10, 17, 132, 23);
		panel_18.add(chckbxNewCheckBox_1);

		JPanel panel_20 = new JPanel();
		panel_20.setLayout(null);
		panel_20.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Fields to retrieve", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_20.setBounds(10, 60, 525, 157);
		panel_2.add(panel_20);

		JScrollPane scrollPane_4 = new JScrollPane((Component) null);
		scrollPane_4.setViewportBorder(new BevelBorder(BevelBorder.LOWERED,

		null, null, null, null));
		scrollPane_4.setBounds(12, 29, 489, 116);
		panel_20.add(scrollPane_4);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"policy._id", Boolean.TRUE},
				{"policy.state", Boolean.TRUE},
				{"car._id", Boolean.FALSE},
				{"car.model", Boolean.FALSE},
				{"car.mileage", Boolean.FALSE},
				{"car.plateNum", Boolean.FALSE},
				{"car.company", Boolean.FALSE},
				{"car.buildYear", Boolean.FALSE},
				{"#insuredParty", Boolean.TRUE},
				{"#item", Boolean.TRUE},
				{null, null},
			},
			new String[] {
				"Filed", "Select"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_4.setViewportView(table_1);

		JPanel panel_22 = new JPanel();
		panel_22.setLayout(null);
		panel_22.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Stop criterion", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_22.setBounds(10, 228, 525, 87);
		panel_2.add(panel_22);

		JRadioButton rdbtnSampleCardinality = new JRadioButton(
				"Sample cardinality");
		JRadioButton rdbtnConfidenceValue = new JRadioButton("Confidence value");
		rdbtnConfidenceValue.setSelected(true);
		rdbtnSampleCardinality.setBounds(6, 19, 111, 23);
		rdbtnConfidenceValue.setBounds(131, 19, 109, 23);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnConfidenceValue);
		bg.add(rdbtnSampleCardinality);
		panel_22.add(rdbtnConfidenceValue);
		panel_22.add(rdbtnSampleCardinality);

		textField_12 = new JTextField();
		textField_12.setText("95");
		textField_12.setBounds(10, 49, 230, 20);
		panel_22.add(textField_12);
		textField_12.setColumns(10);

		panel.add(panel_2);
//		panel.add(panel_3);
		panel.add(panel_6);
		panel.add(panel_10);
		panel.add(panel_14);
		panel.add(panel_1);
		
		JPanel panel_26 = new JPanel();
		panel_26.setLayout(null);
		panel_26.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Initial volume", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_26.setBounds(10, 292, 774, 118);
		panel_1.add(panel_26);
		
		JComboBox comboBox_4 = new JComboBox(new Object[]{});
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"policy"}));
		comboBox_4.setBounds(12, 23, 189, 24);
		panel_26.add(comboBox_4);
		
		textField_11 = new JTextField();
		textField_11.setText("60000");
		textField_11.setColumns(10);
		textField_11.setBounds(233, 26, 269, 19);
		panel_26.add(textField_11);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.setBounds(512, 23, 59, 25);
		panel_26.add(btnAdd_1);

		JPanel panel_15 = new JPanel();
		panel_15.setLayout(null);
		panel_15.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_15.setBounds(21, 76, 525, 44);
		panel_14.add(panel_15);

		JLabel lblNewLabel = new JLabel("pipelineEl");
		lblNewLabel.setBounds(12, 12, 70, 15);
		panel_15.add(lblNewLabel);

		JComboBox comboBox_5 = new JComboBox(new Object[] {});
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] { "$project",
				"$match", "$redact", "$limit", "$skip", "$unwind", "$group",
				"$sort", "$geoNear", "$out" }));
		comboBox_5.setBounds(100, 7, 189, 24);
		panel_15.add(comboBox_5);

		JButton button_4 = new JButton("Add");
		button_4.setBounds(301, 8, 70, 23);
		panel_15.add(button_4);

		JPanel panel_25 = new JPanel();
		panel_25.setLayout(null);
		panel_25.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Stop criterion", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_25.setBounds(21, 428, 525, 87);
		panel_14.add(panel_25);

		JRadioButton radioButton_4 = new JRadioButton("Confidence value");
		radioButton_4.setSelected(true);
		radioButton_4.setBounds(131, 19, 109, 23);
		panel_25.add(radioButton_4);

		JRadioButton radioButton_5 = new JRadioButton("Sample cardinality");
		radioButton_5.setBounds(6, 19, 111, 23);
		panel_25.add(radioButton_5);

		textField_15 = new JTextField();
		textField_15.setText("90");
		textField_15.setColumns(10);
		textField_15.setBounds(10, 49, 230, 20);
		panel_25.add(textField_15);

		JPanel panel_24 = new JPanel();
		panel_24.setLayout(null);
		panel_24.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Stop criterion", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_24.setBounds(21, 401, 525, 87);
		panel_10.add(panel_24);

		JRadioButton radioButton_2 = new JRadioButton("Confidence value");
		radioButton_2.setBounds(131, 19, 109, 23);
		panel_24.add(radioButton_2);

		JRadioButton radioButton_3 = new JRadioButton("Sample cardinality");
		radioButton_3.setSelected(true);
		radioButton_3.setBounds(6, 19, 111, 23);
		panel_24.add(radioButton_3);

		textField_14 = new JTextField();
		textField_14.setText("75000");
		textField_14.setColumns(10);
		textField_14.setBounds(10, 49, 230, 20);
		panel_24.add(textField_14);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenClassDiagram = new JMenuItem("Open Class Diagram\r\n");
		mnFile.add(mntmOpenClassDiagram);

		JMenuItem mntmSaveAssessment = new JMenuItem("Save Assessment");
		mnFile.add(mntmSaveAssessment);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);

		JMenu mnRun = new JMenu("Run");
		menuBar.add(mnRun);

		JMenuItem mntmInterpretAndLaunch = new JMenuItem(
				"Interpret and launch JMeter");
		mnRun.add(mntmInterpretAndLaunch);
		
		JMenuItem mntmRunAssessment = new JMenuItem("Run assessment");
		mnRun.add(mntmRunAssessment);

		frame.setDefaultLookAndFeelDecorated(false);
	}
}
