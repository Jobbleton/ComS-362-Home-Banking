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
import javax.swing.ListSelectionModel;

public class HomeBankingView extends JFrame
{
	private static final long serialVersionUID = 1L;
	Image storedImage;
	private JPanel mainContentPane;
	private JTextField txtAddPerson;
	private JTextField txtRemovePerson;
	private JTextField txtAddChild;
	private JTextField txtRemoveChild;
	private JTextField txtAddBank;
	private JTextField txtRemoveBank;

	private static HomeBankingController mainController;
	//Launch the application.
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					mainController = new HomeBankingController();
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
		
		JButton btnAddPerson = new JButton("Add Person");
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddPerson.setBounds(201, 10, 89, 23);
		largeInternalPanel.add(btnAddPerson);
		
		txtRemovePerson = new JTextField();
		txtRemovePerson.setText("person to remove by id");
		txtRemovePerson.setColumns(10);
		txtRemovePerson.setBounds(10, 42, 181, 20);
		largeInternalPanel.add(txtRemovePerson);
		
		JButton btnRemovePerson = new JButton("Delete Person");
		btnRemovePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemovePerson.setBounds(201, 41, 89, 23);
		largeInternalPanel.add(btnRemovePerson);
		
		txtAddChild = new JTextField();
		txtAddChild.setText("child to add (first, second, id)");
		txtAddChild.setColumns(10);
		txtAddChild.setBounds(10, 73, 181, 20);
		largeInternalPanel.add(txtAddChild);
		
		txtRemoveChild = new JTextField();
		txtRemoveChild.setText("child to remove by id");
		txtRemoveChild.setColumns(10);
		txtRemoveChild.setBounds(10, 104, 181, 20);
		largeInternalPanel.add(txtRemoveChild);
		
		JButton btnAddChild = new JButton("Add Child");
		btnAddChild.setBounds(201, 72, 89, 23);
		largeInternalPanel.add(btnAddChild);
		
		JButton btnDeleteChild = new JButton("Delete Child");
		btnDeleteChild.setBounds(201, 103, 89, 23);
		largeInternalPanel.add(btnDeleteChild);
		
		txtAddBank = new JTextField();
		txtAddBank.setText("bank to add (name)");
		txtAddBank.setColumns(10);
		txtAddBank.setBounds(10, 135, 181, 20);
		largeInternalPanel.add(txtAddBank);
		
		txtRemoveBank = new JTextField();
		txtRemoveBank.setText("bank to remove by name");
		txtRemoveBank.setColumns(10);
		txtRemoveBank.setBounds(10, 166, 181, 20);
		largeInternalPanel.add(txtRemoveBank);
		
		JButton btnAddBank = new JButton("Add Bank");
		btnAddBank.setBounds(201, 134, 89, 23);
		largeInternalPanel.add(btnAddBank);
		
		JButton btnRemoveBank = new JButton("Delete Bank");
		btnRemoveBank.setBounds(201, 165, 89, 23);
		largeInternalPanel.add(btnRemoveBank);
		
		ArrayList<String> peopleNames = new ArrayList<String>();
		int counter = mainController.getPeopleList().size();
		for(int j = 0; j < counter; j++)
		{
			peopleNames.add(mainController.getPeopleList().get(j).getFullName());
		}
		JList tempList = new JList(peopleNames.toArray());
        tempList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane peopleList = new JScrollPane(tempList);
		peopleList.setBounds(300, 11, 227, 175);
		largeInternalPanel.add(peopleList);
	}
}

