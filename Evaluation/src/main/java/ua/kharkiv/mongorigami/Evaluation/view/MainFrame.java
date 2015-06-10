package ua.kharkiv.mongorigami.Evaluation.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
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

public class MainFrame {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
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
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_1;
	private JTextField textField_6;
	private JTextField textField_7;

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
				}
			}
		));
		frame.getContentPane().add(new JScrollPane(tree), BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		JScrollPane scrollPane_1 = new JScrollPane(panel);
		frame.getContentPane().add(scrollPane_1, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder("Assessment components"));
		panel_1.setBounds(10, 11, 586, 65);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(26, 24, 99, 20);
		panel_1.add(comboBox);
		comboBox.addItem("insert");
		comboBox.addItem("find");
		comboBox.addItem("remove");
		comboBox.addItem("update");
		comboBox.addItem("aggregate");
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(150, 23, 70, 23);
		panel_1.add(btnAdd);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder("Insert ass."));
		panel_2.setBounds(10, 97, 586, 217);
		
		textField = new JTextField();
		textField.setToolTipText("Insert a reference name of the assessment herre");
		textField.setBounds(66, 29, 166, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(21, 32, 46, 14);
		panel_2.add(lblName);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Find ass.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 97, 586, 389);
		//panel.add(panel_3);
		
		txtInsert = new JTextField();
		txtInsert.setText("Insert1");
		txtInsert.setToolTipText("Insert a reference name of the assessment herre");
		txtInsert.setColumns(10);
		txtInsert.setBounds(66, 29, 166, 20);
		panel_3.add(txtInsert);
		
		JLabel label = new JLabel("Name:");
		label.setBounds(21, 32, 46, 14);
		panel_3.add(label);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Fields", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(21, 76, 525, 157);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		//panel.add(panel_2);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"policy._id", Boolean.FALSE},
				{"policy.state", Boolean.FALSE},
				{"car._id", Boolean.FALSE},
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
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(table);
		
		JScrollPane scrollPane_2 = new JScrollPane(table);
		scrollPane_2.setBounds(12, 29, 489, 116);
		panel_4.add(scrollPane_2);
		scrollPane_2.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Query", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(21, 245, 525, 97);
		panel_3.add(panel_5);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"car.model", "policy._id", "policy.state", "car._id", "car.model", "car.mileage", "car.plateNum"}));
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
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Remove ass.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(10, 88, 586, 490);
		//panel.add(panel_6);
		
		txtRemove = new JTextField();
		txtRemove.setToolTipText("Insert a reference name of the assessment herre");
		txtRemove.setText("Remove1");
		txtRemove.setColumns(10);
		txtRemove.setBounds(66, 29, 166, 20);
		panel_6.add(txtRemove);
		
		JLabel label_1 = new JLabel("Name:");
		label_1.setBounds(21, 32, 46, 14);
		panel_6.add(label_1);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Fields to remove", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(21, 267, 525, 157);
		panel_6.add(panel_9);
		
		JScrollPane scrollPane_3 = new JScrollPane(table);
		scrollPane_3.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_3.setBounds(12, 29, 489, 116);
		panel_9.add(scrollPane_3);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_7.setLayout(null);
		panel_7.setBounds(21, 76, 525, 44);
		panel_6.add(panel_7);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Just one");
		chckbxNewCheckBox.setBounds(25, 8, 129, 23);
		panel_7.add(chckbxNewCheckBox);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Query", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(21, 132, 525, 123);
		panel_6.add(panel_8);
		
		JComboBox comboBox_2 = new JComboBox(new String[] {"car.model", "policy._id", "policy.state", "car._id", "car.model", "car.mileage", "car.plateNum"});
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"policy._id", "policy.state", "car._id", "car.model", "car.mileage", "car.plateNum"}));
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
		panel_10.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Update ass.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(10, 88, 586, 425);
		//panel.add(panel_10);
		
		txtUpdate = new JTextField();
		txtUpdate.setToolTipText("Insert a reference name of the assessment herre");
		txtUpdate.setText("Update1");
		txtUpdate.setColumns(10);
		txtUpdate.setBounds(66, 29, 166, 20);
		panel_10.add(txtUpdate);
		
		JLabel label_2 = new JLabel("Name:");
		label_2.setBounds(21, 32, 46, 14);
		panel_10.add(label_2);
		
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_12.setBounds(21, 76, 525, 44);
		panel_10.add(panel_12);
		
		JCheckBox chckbxUpsert = new JCheckBox("Upsert");
		chckbxUpsert.setBounds(25, 8, 78, 23);
		panel_12.add(chckbxUpsert);
		
		JCheckBox chckbxMulti = new JCheckBox("Multi");
		chckbxMulti.setBounds(119, 8, 129, 23);
		panel_12.add(chckbxMulti);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Query", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.setBounds(21, 132, 525, 123);
		panel_10.add(panel_13);
		
		JComboBox comboBox_3 = new JComboBox(new Object[]{});
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"policy._id"}));
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
		panel_11.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Update", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		panel_14.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Aggregate ass.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_14.setBounds(10, 88, 586, 425);
		//panel.add(panel_14);
		
		txtAggregate = new JTextField();
		txtAggregate.setToolTipText("Insert a reference name of the assessment herre");
		txtAggregate.setText("Aggregate1");
		txtAggregate.setColumns(10);
		txtAggregate.setBounds(66, 29, 166, 20);
		panel_14.add(txtAggregate);
		
		JLabel label_3 = new JLabel("Name:");
		label_3.setBounds(21, 32, 46, 14);
		panel_14.add(label_3);
		
		JPanel panel_15 = new JPanel();
		panel_15.setLayout(null);
		panel_15.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_15.setBounds(21, 76, 525, 44);
		panel_14.add(panel_15);
		
		JLabel lblNewLabel = new JLabel("pipelineEl");
		lblNewLabel.setBounds(12, 12, 70, 15);
		panel_15.add(lblNewLabel);
		
		JComboBox comboBox_5 = new JComboBox(new Object[]{});
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"$project", "$match", "$redact", "$limit", "$skip", "$unwind", "$group", "$sort", "$geoNear", "$out"}));
		comboBox_5.setBounds(100, 7, 189, 24);
		panel_15.add(comboBox_5);
		
		JButton button_4 = new JButton("Add");
		button_4.setBounds(301, 8, 70, 23);
		panel_15.add(button_4);
		
		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "$match", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_16.setBounds(21, 132, 525, 123);
		panel_14.add(panel_16);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(233, 26, 269, 19);
		panel_16.add(textField_3);
		
		JButton button_2 = new JButton("Add query element");
		button_2.setBounds(334, 67, 168, 25);
		panel_16.add(button_2);
		
		textField_1 = new JTextField();
		textField_1.setText("$push");
		textField_1.setColumns(10);
		textField_1.setBounds(12, 26, 196, 19);
		panel_16.add(textField_1);
		
		JPanel panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "$group", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_17.setBounds(21, 267, 525, 123);
		panel_14.add(panel_17);
		
		textField_4 = new JTextField();
		textField_4.setText("#underwriting");
		textField_4.setColumns(10);
		textField_4.setBounds(233, 26, 269, 19);
		panel_17.add(textField_4);
		
		JButton button_3 = new JButton("Add update element");
		button_3.setBounds(306, 67, 196, 25);
		panel_17.add(button_3);
		
		textField_5 = new JTextField();
		textField_5.setText("$push");
		textField_5.setColumns(10);
		textField_5.setBounds(12, 26, 196, 19);
		panel_17.add(textField_5);
		
		JPanel panel_18 = new JPanel();
		panel_18.setLayout(null);
		panel_18.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Weights", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_18.setBounds(10, 88, 586, 425);
		panel.add(panel_18);
		
		textField_6 = new JTextField();
		textField_6.setToolTipText("Insert a reference name of the assessment herre");
		textField_6.setText("Aggregate1");
		textField_6.setColumns(10);
		textField_6.setBounds(150, 30, 166, 20);
		panel_18.add(textField_6);
		
		JLabel lblInsert = new JLabel("Insert:");
		lblInsert.setBounds(21, 32, 111, 14);
		panel_18.add(lblInsert);
		
		JLabel lblFind = new JLabel("Find:");
		lblFind.setBounds(21, 58, 76, 14);
		panel_18.add(lblFind);
		
		textField_7 = new JTextField();
		textField_7.setToolTipText("Insert a reference name of the assessment herre");
		textField_7.setText("Aggregate1");
		textField_7.setColumns(10);
		textField_7.setBounds(150, 56, 166, 20);
		panel_18.add(textField_7);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		frame.setDefaultLookAndFeelDecorated(false);
	}
}
