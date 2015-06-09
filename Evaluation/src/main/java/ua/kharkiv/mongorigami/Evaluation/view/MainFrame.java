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

public class MainFrame {

	private JFrame frame;
	private JTable table;

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
		frame.setBounds(100, 100, 710, 449);
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
		frame.getContentPane().add(tree, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
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
		btnAdd.setBounds(150, 23, 51, 23);
		panel_1.add(btnAdd);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder("Insert ass."));
		panel_2.setBounds(10, 97, 586, 65);
		panel.add(panel_2);
		
		table = new JTable();
		table.setBounds(82, 22, 187, 18);
		panel_2.add(table);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		frame.setDefaultLookAndFeelDecorated(false);
	}
}
