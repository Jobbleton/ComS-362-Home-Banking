import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HomeBankingView extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JPanel mainContentPane;
	
	private JTextField txtAddPerson;
	private JTextField txtRemovePerson;
	private JTextField txtAddChild;
	private JTextField txtRemoveChild;
	private JTextField txtAddBank;
	private JTextField txtRemoveBank;
	private JTextField txtAddAccount;
	private JTextField txtRemoveAccount;
	
	private JScrollPane peopleList;
	private JScrollPane childrenList;
	private JScrollPane bankList;
	private JScrollPane accountList;
	
	private ArrayList<String> peopleNames;
	private ArrayList<String> childrenNames;
	private ArrayList<String> bankNames;
	private ArrayList<String> accountNames;

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
		
		
		//DEBUG
		//addPerson(new Person("James", "Test", "1234"));
		//Initialization of database
		try
		{	
			File inputFile = new File("src/initialization.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			//DEBUG
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			//temporary
			NodeList nList = doc.getElementsByTagName("person");
			System.out.println("----------------------------");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					System.out.println("Person ID : " + eElement.getAttribute("ID"));
					
					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
					
					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		//external panel
		JPanel largeExternalPanel = new JPanel();
		largeExternalPanel.setBorder(new CompoundBorder());
		largeExternalPanel.setBackground(SystemColor.controlHighlight);
		mainContentPane.add(largeExternalPanel);
		largeExternalPanel.setLayout(null);
		
		//internal scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1258, 676);
		largeExternalPanel.add(scrollPane);
		
		//main internal panel
		JPanel largeInternalPanel = new JPanel();
		scrollPane.setViewportView(largeInternalPanel);
		largeInternalPanel.setLayout(null);
		
		//entry for person to add
		txtAddPerson = new JTextField();
		txtAddPerson.setText("person to add \"first last id\"");
		txtAddPerson.setBounds(10, 11, 181, 20);
		largeInternalPanel.add(txtAddPerson);
		txtAddPerson.setColumns(10);
		
		//button to add person
		JButton btnAddPerson = new JButton("Add Person");
		//action handling
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//DEBUG
				//System.out.println("ACTION PERFORMED");
				//peopleNames.add("TEST");
				
				//add new person
				String toAdd = txtAddPerson.getText();
				String[] tokenizedAdd = toAdd.split("\\s+");
				if(tokenizedAdd.length == 3)
				{
					mainController.addPerson(tokenizedAdd[0],tokenizedAdd[1],tokenizedAdd[2]);
				}
				
				//update list
				peopleNames = new ArrayList<String>();
				int counter = mainController.getPeopleList().size();
				for(int j = 0; j < counter; j++)
				{
					peopleNames.add(mainController.getPeopleList().get(j).getFullName());
				}
				JList tempList = new JList(peopleNames.toArray());
				peopleList.setViewportView(tempList);
			}
		});
		btnAddPerson.setBounds(201, 10, 138, 23);
		largeInternalPanel.add(btnAddPerson);
		
		//remove person label
		txtRemovePerson = new JTextField();
		txtRemovePerson.setText("person to remove by id");
		txtRemovePerson.setColumns(10);
		txtRemovePerson.setBounds(10, 42, 181, 20);
		largeInternalPanel.add(txtRemovePerson);
		
		//button to remove person
		JButton btnRemovePerson = new JButton("Delete Person");
		btnRemovePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove person
				String toDel = txtRemovePerson.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 1)
				{
					if(!mainController.removePerson(tokenizedDel[0]))
						txtRemovePerson.setText("Person not found by id given");
					else
						txtRemovePerson.setText("Success!");
				}
				else
				{
					txtRemovePerson.setText("Format: \"id\"");
				}
				
				//update people list
				peopleNames = new ArrayList<String>();
				int counter = mainController.getPeopleList().size();
				for(int j = 0; j < counter; j++)
				{
					peopleNames.add(mainController.getPeopleList().get(j).getFullName());
				}
				
				JList tempList = new JList(peopleNames.toArray());
				peopleList.setViewportView(tempList);
			}
		});
		btnRemovePerson.setBounds(201, 41, 138, 23);
		largeInternalPanel.add(btnRemovePerson);
		
		//add child label
		txtAddChild = new JTextField();
		txtAddChild.setText("child to add (first, second, id, parentID)");
		txtAddChild.setColumns(10);
		txtAddChild.setBounds(10, 73, 181, 20);
		largeInternalPanel.add(txtAddChild);
		
		//remove child label
		txtRemoveChild = new JTextField();
		txtRemoveChild.setText("child to remove by id");
		txtRemoveChild.setColumns(10);
		txtRemoveChild.setBounds(10, 104, 181, 20);
		largeInternalPanel.add(txtRemoveChild);
		
		//add child button
		JButton btnAddChild = new JButton("Add Child");
		btnAddChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//DEBUG
				//System.out.println("ACTION PERFORMED");
				//peopleNames.add("TEST");
				
				//add child
				String toAdd = txtAddChild.getText();
				String[] tokenizedAdd = toAdd.split("\\s+");
				if(tokenizedAdd.length == 4)
				{
					mainController.addChild(tokenizedAdd[0],tokenizedAdd[1],tokenizedAdd[2],tokenizedAdd[3]);
				}
				else
				{
					txtAddChild.setText("Format: \"first, second, id, parentID\"");
				}
				//update child list
				childrenNames = new ArrayList<String>();
				int counter = mainController.getChildrenList().size();
				for(int j = 0; j < counter; j++)
				{
					childrenNames.add(mainController.getChildrenList().get(j).getFullName());
				}
				JList tempList = new JList(childrenNames.toArray());
				childrenList.setViewportView(tempList);
			}
		});
		btnAddChild.setBounds(201, 72, 138, 23);
		largeInternalPanel.add(btnAddChild);
		
		//remove child button
		JButton btnDeleteChild = new JButton("Delete Child");
		btnDeleteChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//remove child
				String toDel = txtRemoveChild.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 1)
				{
					if(!mainController.removeChild(tokenizedDel[0]))
					{
						txtRemoveChild.setText("Child not found by id given");
					}
					else
						txtRemoveChild.setText("Success!");
				}
				else
				{
					txtRemoveChild.setText("Format: \"id\"");
				}
				
				//update child list
				childrenNames = new ArrayList<String>();
				int counter = mainController.getChildrenList().size();
				for(int j = 0; j < counter; j++)
				{
					childrenNames.add(mainController.getChildrenList().get(j).getFullName());
				}
				JList tempList = new JList(childrenNames.toArray());
				childrenList.setViewportView(tempList);
			}
		});
		btnDeleteChild.setBounds(201, 103, 138, 23);
		largeInternalPanel.add(btnDeleteChild);
		
		//add bank label
		txtAddBank = new JTextField();
		txtAddBank.setText("bank to add (name)");
		txtAddBank.setColumns(10);
		txtAddBank.setBounds(10, 135, 181, 20);
		largeInternalPanel.add(txtAddBank);
		
		//remove bank label
		txtRemoveBank = new JTextField();
		txtRemoveBank.setText("bank to remove by name");
		txtRemoveBank.setColumns(10);
		txtRemoveBank.setBounds(10, 166, 181, 20);
		largeInternalPanel.add(txtRemoveBank);
		
		//add bank button
		JButton btnAddBank = new JButton("Add Bank");
		btnAddBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//DEBUG
				//System.out.println("ACTION PERFORMED");
				//peopleNames.add("TEST");
				
				//add bank
				String toAdd = txtAddBank.getText();
				String[] tokenizedAdd = toAdd.split("\\s+");
				if(tokenizedAdd.length == 1)
				{
					mainController.addBank(tokenizedAdd[0]);
				}
				
				//DEBUG
				//System.out.println(tokenizedAdd.length + " " + tokenizedAdd[0]);
				
				//update bank list
				bankNames = new ArrayList<String>();
				int counter = mainController.getBankList().size();
				for(int j = 0; j < counter; j++)
				{
					bankNames.add(mainController.getBankList().get(j).getName());
				}
				JList tempList = new JList(bankNames.toArray());
				bankList.setViewportView(tempList);
			}
		});
		btnAddBank.setBounds(201, 134, 138, 23);
		largeInternalPanel.add(btnAddBank);
		
		//remove bank button
		JButton btnRemoveBank = new JButton("Delete Bank");
		btnRemoveBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//remove bank
				String toDel = txtRemoveBank.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 1)
				{
					if(!mainController.removeBank(tokenizedDel[0]))
					{
						txtRemoveBank.setText("Bank not found by name given");
					}
					else
						txtRemoveBank.setText("Success!");
				}
				else
				{
					txtRemoveBank.setText("Format: \"name\"");
				}
				
				//update bank list
				bankNames = new ArrayList<String>();
				int counter = mainController.getBankList().size();
				for(int j = 0; j < counter; j++)
				{
					bankNames.add(mainController.getBankList().get(j).getName());
				}				
				JList tempList = new JList(bankNames.toArray());
				bankList.setViewportView(tempList);
			}
		});
		btnRemoveBank.setBounds(201, 165, 138, 23);
		largeInternalPanel.add(btnRemoveBank);
		
		//complex initialization for people list
		peopleNames = new ArrayList<String>();
		int counter1 = mainController.getPeopleList().size();
		for(int j = 0; j < counter1; j++)
		{
			peopleNames.add(mainController.getPeopleList().get(j).getFullName());
		}
		JList tempPeopleList = new JList(peopleNames.toArray());
        tempPeopleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		peopleList = new JScrollPane(tempPeopleList);
		peopleList.setBounds(349, 12, 227, 175);
		largeInternalPanel.add(peopleList);
		
		//complex initialization for children list
		childrenNames = new ArrayList<String>();
		int counter2 = mainController.getChildrenList().size();
		for(int j = 0; j < counter2; j++)
		{
			peopleNames.add(mainController.getChildrenList().get(j).getFullName());
		}
		JList tempChildrenList = new JList(childrenNames.toArray());
        tempChildrenList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		childrenList = new JScrollPane(tempChildrenList);
		childrenList.setBounds(349, 197, 227, 175);
		largeInternalPanel.add(childrenList);
		
		//complex initialization for bank list
		bankNames = new ArrayList<String>();
		int counter3 = mainController.getBankList().size();
		for(int j = 0; j < counter3; j++)
		{
			bankNames.add(mainController.getBankList().get(j).getName());
		}
		JList tempBankList = new JList(bankNames.toArray());
        tempBankList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bankList = new JScrollPane(tempBankList);
		bankList.setBounds(586, 12, 227, 175);
		largeInternalPanel.add(bankList);
		
		//add account label
		txtAddAccount = new JTextField();
		txtAddAccount.setText("account to add (bank ownerID accountID)");
		txtAddAccount.setColumns(10);
		txtAddAccount.setBounds(10, 197, 181, 20);
		largeInternalPanel.add(txtAddAccount);
		
		//remove account label
		txtRemoveAccount = new JTextField();
		txtRemoveAccount.setText("account to remove (bank accountID)");
		txtRemoveAccount.setColumns(10);
		txtRemoveAccount.setBounds(10, 228, 181, 20);
		largeInternalPanel.add(txtRemoveAccount);
		
		//add account button
		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//add account
				String toAdd = txtAddAccount.getText();
				String[] tokenizedAdd = toAdd.split("\\s+");
				if(tokenizedAdd.length == 3)
				{
					ArrayList<Bank> allBanks = mainController.getBankList();
					//check through all banks
					for(int i = 0; i < allBanks.size(); i++)
					{
						if(allBanks.get(i).getName().equals(tokenizedAdd[0]))
						{
							//check through the various people
							ArrayList<Person> allPeople = mainController.getPeopleList();
							for(Person p : allPeople)
							{
								if(p.getIdentifier().equals(tokenizedAdd[1]))
								{
									//for a valid bank and person
									//attach accountid and create account
									allBanks.get(i).
									addAccount(new Account(p, tokenizedAdd[2]));
									txtAddAccount.setText("SUCCESS");
									//DEBUG
									//System.out.println("Person matched!");
									break;
								}
								else
								{
									txtAddAccount.setText("Not a valid person");
								}
							}
							//DEBUG
							//System.out.println("testing for person");
							break;
						}
						else
						{
							txtAddAccount.setText("Not a valid bank");
						}
					}
				}
				else
				{
					txtAddAccount.setText("Format: \"bank ownerID accountID\"");
				}
				
				//DEBUG
				//System.out.println(tokenizedAdd.length + " " + tokenizedAdd[0]);
				
				//update account list
				accountNames = new ArrayList<String>();
				int counter = mainController.getBankList().size();
				for(int j = 0; j < counter; j++)
				{
					ArrayList<Bank> allBanks = mainController.getBankList();
					for(Bank b : allBanks)
					{
						ArrayList<Account> allAccounts = b.getAccounts();
						for(int i = 0; i < allAccounts.size(); i++)
						{
							accountNames.add(allAccounts.get(i).getOwner().getFullName() + " " + allAccounts.get(i).getID());
						}
					}
				}
				JList tempList = new JList(accountNames.toArray());
				accountList.setViewportView(tempList);
			}
		});
		btnAddAccount.setBounds(201, 195, 138, 23);
		largeInternalPanel.add(btnAddAccount);
		
		//remove account button
		JButton btnRemoveAccount = new JButton("Delete Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//DEBUG
				//System.out.println("ACTION PERFORMED");
				//peopleNames.add("TEST");
				
				//remove account
				String toDel = txtRemoveAccount.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 2)
				{
					//for all banks
					ArrayList<Bank> allBanks = mainController.getBankList();
					for(Bank b : allBanks)
					{
						if(b.getName().equals(tokenizedDel[0]))
						{
							//for a valid account
							ArrayList<Account> bankAccounts = b.getAccounts();
							for(Account a : bankAccounts)
							{
								if(a.getID().equals(tokenizedDel[1]))
								{
									b.removeAccount(a);
									txtRemoveAccount.setText("SUCCESS");
									break;
								}
								else
								{
									txtRemoveAccount.setText("Not a valid account");
								}
							}
							break;
						}
						else
						{
							txtRemoveAccount.setText("Not a valid bank");
						}
					}
				}
				else
				{
					txtRemoveAccount.setText("Format: \"bank accountID\"");
				}
				
				//DEBUG
				//System.out.println(tokenizedAdd.length + " " + tokenizedAdd[0]);
				
				//update account list
				accountNames = new ArrayList<String>();
				int counter = mainController.getBankList().size();
				for(int j = 0; j < counter; j++)
				{
					ArrayList<Bank> allBanks = mainController.getBankList();
					for(Bank b : allBanks)
					{
						ArrayList<Account> allAccounts = b.getAccounts();
						for(Account a : allAccounts)
						{
							accountNames.add(a.getOwner().getFullName() + " " + a.getID());
						}
					}
				}
				JList tempList = new JList(accountNames.toArray());
				accountList.setViewportView(tempList);
			}
		});
		btnRemoveAccount.setBounds(201, 227, 138, 23);
		largeInternalPanel.add(btnRemoveAccount);
		
		//initialize account names
		accountNames = new ArrayList<String>();
		int counter4 = mainController.getBankList().size();
		for(int j = 0; j < counter4; j++)
		{
			ArrayList<Bank> allBanks = mainController.getBankList();
			for(Bank b : allBanks)
			{
				ArrayList<Account> allAccounts = b.getAccounts();
				for(Account a : allAccounts)
				{
					accountNames.add(a.getOwner().getFullName());
				}
			}
		}
		JList tempAccountList = new JList(accountNames.toArray());
        tempAccountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		accountList = new JScrollPane(tempAccountList);
		accountList.setBounds(586, 197, 227, 175);
		largeInternalPanel.add(accountList);
	}
}

