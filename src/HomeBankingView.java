import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;

public class HomeBankingView extends JFrame
{
	private static final long serialVersionUID = 1L;
	Image storedImage;
	private JPanel mainContentPane;
	private JTextField txtAddPerson;
	private JTextField txtPersonToRemove;
	private JTextField txtChildToAdd;
	private JTextField txtChildToRemove;
	private JTextField txtBankToAdd;
	private JTextField txtBankToRemove;

	//Launch the application.
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					HomeBankingView frame = new HomeBankingView();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	//Create the frame.
	public HomeBankingView()
	{
		HomeBankingController mainController = new HomeBankingController();
		
		//block for main window
		setTitle("ComS 362 Home Banking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		mainContentPane = new JPanel();
		mainContentPane.setBorder(new EmptyBorder(3,3,3,3));
		setContentPane(mainContentPane);
		mainContentPane.setLayout(new BorderLayout(0, 0));
		
		//main internal panel
		JPanel largeExternalPanel = new JPanel();
		largeExternalPanel.setBorder(new CompoundBorder());
		largeExternalPanel.setBackground(SystemColor.controlHighlight);
		mainContentPane.add(largeExternalPanel);
		largeExternalPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1258, 676);
		largeExternalPanel.add(scrollPane);
		
		JPanel largeInternalPanel = new JPanel();
		scrollPane.setViewportView(largeInternalPanel);
		largeInternalPanel.setLayout(null);
		
		txtAddPerson = new JTextField();
		txtAddPerson.setText("person to add (first, second, id)");
		txtAddPerson.setBounds(10, 11, 181, 20);
		largeInternalPanel.add(txtAddPerson);
		txtAddPerson.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Person");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(201, 10, 89, 23);
		largeInternalPanel.add(btnNewButton);
		
		txtPersonToRemove = new JTextField();
		txtPersonToRemove.setText("person to remove by id");
		txtPersonToRemove.setColumns(10);
		txtPersonToRemove.setBounds(10, 42, 181, 20);
		largeInternalPanel.add(txtPersonToRemove);
		
		JButton button = new JButton("Delete Person");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(201, 41, 89, 23);
		largeInternalPanel.add(button);
		
		txtChildToAdd = new JTextField();
		txtChildToAdd.setText("child to add (first, second, id)");
		txtChildToAdd.setColumns(10);
		txtChildToAdd.setBounds(10, 73, 181, 20);
		largeInternalPanel.add(txtChildToAdd);
		
		txtChildToRemove = new JTextField();
		txtChildToRemove.setText("child to remove by id");
		txtChildToRemove.setColumns(10);
		txtChildToRemove.setBounds(10, 104, 181, 20);
		largeInternalPanel.add(txtChildToRemove);
		
		JButton button_1 = new JButton("Add Person");
		button_1.setBounds(201, 72, 89, 23);
		largeInternalPanel.add(button_1);
		
		JButton button_2 = new JButton("Delete Person");
		button_2.setBounds(201, 103, 89, 23);
		largeInternalPanel.add(button_2);
		
		txtBankToAdd = new JTextField();
		txtBankToAdd.setText("bank to add (name)");
		txtBankToAdd.setColumns(10);
		txtBankToAdd.setBounds(10, 135, 181, 20);
		largeInternalPanel.add(txtBankToAdd);
		
		txtBankToRemove = new JTextField();
		txtBankToRemove.setText("bank to remove by name");
		txtBankToRemove.setColumns(10);
		txtBankToRemove.setBounds(10, 166, 181, 20);
		largeInternalPanel.add(txtBankToRemove);
		
		JButton button_3 = new JButton("Add Person");
		button_3.setBounds(201, 134, 89, 23);
		largeInternalPanel.add(button_3);
		
		JButton button_4 = new JButton("Delete Person");
		button_4.setBounds(201, 165, 89, 23);
		largeInternalPanel.add(button_4);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(300, 11, 227, 175);
		largeInternalPanel.add(scrollPane_1);
	}
}

