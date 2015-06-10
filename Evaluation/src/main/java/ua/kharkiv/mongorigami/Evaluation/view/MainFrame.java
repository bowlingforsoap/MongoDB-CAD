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

public class MainFrame {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

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
		frame.setBounds(100, 100, 749, 551);
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
		scrollPane.setSize(525, 116);
		scrollPane.setLocation(21, 74);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.add(scrollPane);
		
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
		panel_3.setBorder(new TitledBorder("Insert ass."));
		panel_3.setBounds(10, 97, 586, 336);
		panel.add(panel_3);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Insert a reference name of the assessment herre");
		textField_1.setColumns(10);
		textField_1.setBounds(66, 29, 166, 20);
		panel_3.add(textField_1);
		
		JLabel label = new JLabel("Name:");
		label.setBounds(21, 32, 46, 14);
		panel_3.add(label);
		
		JPanel panel_4 = new JPanel();
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
		scrollPane_2.setBounds(10, 22, 525, 116);
		panel_4.add(scrollPane_2);
		scrollPane_2.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		frame.setDefaultLookAndFeelDecorated(false);
	}
}
