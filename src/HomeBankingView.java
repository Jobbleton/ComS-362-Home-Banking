import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.awt.Component;
import javax.swing.JTextArea;

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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtAccountToCheck;
	private JTextField txtAccountToCheck_1;
	private JTextField txtBillToAdd;
	private JTextField txtBillToDel;
	private JTextField txtGetLoanAmt;
	private JTextField txtAddLoanaccid;
	private JTextField txtRemoveLoanaccid;
	private JTextField txtAddAutobillaccid;
	private JTextField txtRemoveAutobillaccid;
	private JTextField txtAddManbillaccid;
	private JTextField txtRemoveManbillaccid;
	private JTextField txtGetTransferAmt;
	private JTextField txtUpdateaccidAmt;
	private JTextField txtAddAssetaccid;
	private JTextField txtRemoveAssetaccid;
	private JTextField txtGetActivityaccid;
	
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
		setBounds(0, 0, 1280, 900);
		mainContentPane = new JPanel();
		mainContentPane.setBorder(new EmptyBorder(3,3,3,3));
		setContentPane(mainContentPane);
		mainContentPane.setLayout(new BorderLayout(0, 0));
		
		//external panel
		JPanel largeExternalPanel = new JPanel();
		largeExternalPanel.setBorder(new CompoundBorder());
		largeExternalPanel.setBackground(SystemColor.controlHighlight);
		mainContentPane.add(largeExternalPanel);
		largeExternalPanel.setLayout(null);
		
		//internal scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1258, 856);
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
		txtRemovePerson.setText("person to remove \"id\"");
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
		txtAddChild.setText("child to add \"first, second, id, parentID\"");
		txtAddChild.setColumns(10);
		txtAddChild.setBounds(10, 73, 181, 20);
		largeInternalPanel.add(txtAddChild);
		
		//remove child label
		txtRemoveChild = new JTextField();
		txtRemoveChild.setText("child to remove \"id\"");
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
		txtAddBank.setText("bank to add \"name\" (no spaces)");
		txtAddBank.setColumns(10);
		txtAddBank.setBounds(10, 135, 181, 20);
		largeInternalPanel.add(txtAddBank);
		
		//remove bank label
		txtRemoveBank = new JTextField();
		txtRemoveBank.setText("bank to remove \"name\"");
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
					txtAddBank.setText("SUCCESS!");
				}
				else
					txtAddBank.setText("Format (no spaces): \"name\"");
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
					if(mainController.removeBank(tokenizedDel[0]))
						txtRemoveBank.setText("Success!");
					else
						txtRemoveBank.setText("Bank not found by name given");
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
			childrenNames.add(mainController.getChildrenList().get(j).getFullName());
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
		txtAddAccount.setText("account to add \"bank ownerID accountID\"");
		txtAddAccount.setColumns(10);
		txtAddAccount.setBounds(10, 197, 181, 20);
		largeInternalPanel.add(txtAddAccount);
		
		//remove account label
		txtRemoveAccount = new JTextField();
		txtRemoveAccount.setText("account to remove \"bank, accountID\"");
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
					if(mainController.addAccount(tokenizedAdd[0], tokenizedAdd[1], tokenizedAdd[2]))
						txtAddAccount.setText("SUCCESS");
				}
				else
				{
					txtAddAccount.setText("Format: \"bank ownerID accountID\"");
				}
				
				//DEBUG
				//System.out.println(tokenizedAdd.length + " " + tokenizedAdd[0]);
				
				//update account list
				accountNames = null;
				accountNames = new ArrayList<String>();
				
					ArrayList<Bank> allBanks = mainController.getBankList();
					for(Bank b : allBanks)
					{
						ArrayList<Account> allAccounts = b.getAccounts();
						for(int i = 0; i < allAccounts.size(); i++)
						{
							try
							{
								accountNames.add(allAccounts.get(i).getOwner().getFullName() + " " + allAccounts.get(i).getID());
							}
							catch(NullPointerException e)
							{
								//No owner
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
		ArrayList<Bank> allBanks = mainController.getBankList();
		for(Bank b : allBanks)
		{
			ArrayList<Account> allAccounts = b.getAccounts();
			for(Account a : allAccounts)
			{
				accountNames.add(a.getOwner().getFullName() + " " + a.getID());
				//DEBUG
				//System.out.println(a.getOwner().getFullName());
			}
		}
		JList tempAccountList = new JList(accountNames.toArray());
		//DEBUG
		//System.out.println(accountNames.toArray().length);
        tempAccountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		accountList = new JScrollPane(tempAccountList);
		accountList.setBounds(586, 197, 227, 175);
		largeInternalPanel.add(accountList);
		
		textField = new JTextField();
		textField.setText("dep to add \"accID childID\"");
		textField.setColumns(10);
		textField.setBounds(10, 259, 181, 20);
		largeInternalPanel.add(textField);
		
		JButton button = new JButton("Add Dependent");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = textField.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 2)
				{
					if(mainController.addDependent(tokenizedDel[0], tokenizedDel[1]))
						textField.setText("SUCCESS");
					else
						textField.setText("Formatting error.");
				}
				else
				{
					textField.setText("format: \"accid childid\"");
				}
			}
		});
		button.setBounds(201, 258, 138, 23);
		largeInternalPanel.add(button);
		
		textField_1 = new JTextField();
		textField_1.setText("dep to del \"accID childID\"");
		textField_1.setColumns(10);
		textField_1.setBounds(10, 290, 181, 20);
		largeInternalPanel.add(textField_1);
		
		JButton button_1 = new JButton("Remove Dependent");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = textField_1.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 2)
				{
					if(mainController.removeDependent(tokenizedDel[0],tokenizedDel[1]))
						textField_1.setText("SUCCESS");
					else
						textField_1.setText("Improper formatting.");
				}
				else
				{
					textField_1.setText("format: \"accid childid\"");
				}
			}
		});
		button_1.setBounds(201, 289, 138, 23);
		largeInternalPanel.add(button_1);
		
		txtAccountToCheck = new JTextField();
		txtAccountToCheck.setText("account to check \"accID\"");
		txtAccountToCheck.setColumns(10);
		txtAccountToCheck.setBounds(10, 321, 181, 20);
		largeInternalPanel.add(txtAccountToCheck);
		
		JButton btnGetAccountValue = new JButton("Get Account Value");
		btnGetAccountValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String toDel = txtAccountToCheck.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 1)
				{
					txtAccountToCheck.setText("Account value: "+mainController.getAccountBalance(tokenizedDel[0]));
				}
				else
				{
					txtAccountToCheck.setText("format: \"accid childid\"");
				}
			}
		});
		btnGetAccountValue.setBounds(201, 320, 138, 23);
		largeInternalPanel.add(btnGetAccountValue);
		
		txtAccountToCheck_1 = new JTextField();
		txtAccountToCheck_1.setText("account to check \"accID\"");
		txtAccountToCheck_1.setColumns(10);
		txtAccountToCheck_1.setBounds(10, 352, 181, 20);
		largeInternalPanel.add(txtAccountToCheck_1);
		
		JButton btnGetAccountFund = new JButton("Get Account Fund Value");
		btnGetAccountFund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtAccountToCheck_1.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 1)
				{
					txtAccountToCheck_1.setText("Account value: "+ mainController.getTotalFunds(tokenizedDel[0]));
				}
				else
				{
					txtAccountToCheck_1.setText("format: \"accid childid\"");
				}
			}
		});
		btnGetAccountFund.setBounds(201, 351, 138, 23);
		largeInternalPanel.add(btnGetAccountFund);
		
		txtBillToAdd = new JTextField();
		txtBillToAdd.setText("bill to add \"accID, name, value\"");
		txtBillToAdd.setColumns(10);
		txtBillToAdd.setBounds(10, 383, 181, 20);
		largeInternalPanel.add(txtBillToAdd);
		
		JButton btnAddBilling = new JButton("Add Billing");
		btnAddBilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtBillToAdd.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 3)
				{
					if(mainController.addBilling(tokenizedDel[0],tokenizedDel[1],Double.parseDouble(tokenizedDel[2])))
						txtBillToAdd.setText("SUCCESS");
					else
						txtBillToAdd.setText("Improper insert");
				}
				else
				{
					txtBillToAdd.setText("format: \"accid name value\"");
				}
			}
		});
		btnAddBilling.setBounds(201, 382, 138, 23);
		largeInternalPanel.add(btnAddBilling);
		
		txtBillToDel = new JTextField();
		txtBillToDel.setText("bill to del \"accID, name\"");
		txtBillToDel.setColumns(10);
		txtBillToDel.setBounds(10, 414, 181, 20);
		largeInternalPanel.add(txtBillToDel);
		
		JButton btnRemoveBilling = new JButton("Remove Billing");
		btnRemoveBilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtBillToDel.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 2)
				{
					if(mainController.removeBilling(tokenizedDel[0],tokenizedDel[1]))
						txtBillToDel.setText("SUCCESS");
					else
						txtBillToDel.setText("Improper insert");
				}
				else
				{
					txtBillToDel.setText("format: \"accid name\"");
				}
			}
		});
		btnRemoveBilling.setBounds(201, 413, 138, 23);
		largeInternalPanel.add(btnRemoveBilling);
		
		txtGetLoanAmt = new JTextField();
		txtGetLoanAmt.setText("get loan amt \"accID\"");
		txtGetLoanAmt.setColumns(10);
		txtGetLoanAmt.setBounds(10, 445, 181, 20);
		largeInternalPanel.add(txtGetLoanAmt);
		
		JButton btnLoanAmount = new JButton("Loan Amount");
		btnLoanAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtGetLoanAmt.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 1)
				{
					txtGetLoanAmt.setText("" + mainController.getLoanAmount(tokenizedDel[0]));
				}
				else
				{
					txtGetLoanAmt.setText("format: \"accid\"");
				}
			}
		});
		btnLoanAmount.setBounds(201, 444, 138, 23);
		largeInternalPanel.add(btnLoanAmount);
		
		txtAddLoanaccid = new JTextField();
		txtAddLoanaccid.setText("add loan \"accID, name, amt\"");
		txtAddLoanaccid.setColumns(10);
		txtAddLoanaccid.setBounds(10, 476, 181, 20);
		largeInternalPanel.add(txtAddLoanaccid);
		
		JButton btnAddLoan = new JButton("Add Loan");
		btnAddLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtAddLoanaccid.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 3)
				{
					if(mainController.addLoan(tokenizedDel[0],tokenizedDel[1],Double.parseDouble(tokenizedDel[2])))
						txtAddLoanaccid.setText("SUCCESS");
					else
						txtAddLoanaccid.setText("Improper insert");
				}
				else
				{
					txtAddLoanaccid.setText("format: \"accid name amt\"");
				}
			}
		});
		btnAddLoan.setBounds(201, 475, 138, 23);
		largeInternalPanel.add(btnAddLoan);
		
		txtRemoveLoanaccid = new JTextField();
		txtRemoveLoanaccid.setText("remove loan \"accID, name\"");
		txtRemoveLoanaccid.setColumns(10);
		txtRemoveLoanaccid.setBounds(10, 507, 181, 20);
		largeInternalPanel.add(txtRemoveLoanaccid);
		
		JButton btnDeleteLoan = new JButton("Delete Loan");
		btnDeleteLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtRemoveLoanaccid.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 2)
				{
					if(mainController.removeLoan(tokenizedDel[0],tokenizedDel[1]))
						txtRemoveLoanaccid.setText("SUCCESS");
					else
						txtRemoveLoanaccid.setText("Improper insert");
				}
				else
				{
					txtRemoveLoanaccid.setText("format: \"accid name\"");
				}
			}
		});
		btnDeleteLoan.setBounds(201, 506, 138, 23);
		largeInternalPanel.add(btnDeleteLoan);
		
		txtAddAutobillaccid = new JTextField();
		txtAddAutobillaccid.setText("add autobill \"accID, name, amountToBill, frequency\"");
		txtAddAutobillaccid.setColumns(10);
		txtAddAutobillaccid.setBounds(10, 538, 181, 20);
		largeInternalPanel.add(txtAddAutobillaccid);
		
		JButton btnAddAutobill = new JButton("Add AutoBill");
		btnAddAutobill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtAddAutobillaccid.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 4)
				{
					if(mainController.addAutoBilling(tokenizedDel[0],tokenizedDel[1],Double.parseDouble(tokenizedDel[2]),Double.parseDouble(tokenizedDel[3])))
						txtAddAutobillaccid.setText("SUCCESS");
					else
						txtAddAutobillaccid.setText("Improper insert");
				}
				else
				{
					txtAddAutobillaccid.setText("format: \"accid name\"");
				}
			}
		});
		btnAddAutobill.setBounds(201, 537, 138, 23);
		largeInternalPanel.add(btnAddAutobill);
		
		txtRemoveAutobillaccid = new JTextField();
		txtRemoveAutobillaccid.setText("remove autobill \"accID, name\"");
		txtRemoveAutobillaccid.setColumns(10);
		txtRemoveAutobillaccid.setBounds(10, 569, 181, 20);
		largeInternalPanel.add(txtRemoveAutobillaccid);
		
		JButton btnRemoveAutobill = new JButton("Remove AutoBill");
		btnRemoveAutobill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtRemoveAutobillaccid.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 2)
				{
					if(mainController.removeAutoBilling(tokenizedDel[0],tokenizedDel[1]))
						txtRemoveAutobillaccid.setText("SUCCESS");
					else
						txtRemoveAutobillaccid.setText("Improper insert");
				}
				else
				{
					txtRemoveAutobillaccid.setText("format: \"accid name\"");
				}
			}
		});
		btnRemoveAutobill.setBounds(201, 568, 138, 23);
		largeInternalPanel.add(btnRemoveAutobill);
		
		txtAddManbillaccid = new JTextField();
		txtAddManbillaccid.setText("add manbill \"accID, name, amountToBill\"");
		txtAddManbillaccid.setColumns(10);
		txtAddManbillaccid.setBounds(10, 600, 181, 20);
		largeInternalPanel.add(txtAddManbillaccid);
		
		JButton btnAddManualbill = new JButton("Add ManualBill");
		btnAddManualbill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtAddManbillaccid.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 3)
				{
					if(mainController.addManualBilling(tokenizedDel[0],tokenizedDel[1],Double.parseDouble(tokenizedDel[2])))
						txtAddManbillaccid.setText("SUCCESS");
					else
						txtAddManbillaccid.setText("Improper insert");
				}
				else
				{
					txtAddManbillaccid.setText("format: \"accid name\"");
				}
			}
		});
		btnAddManualbill.setBounds(201, 599, 138, 23);
		largeInternalPanel.add(btnAddManualbill);
		
		txtRemoveManbillaccid = new JTextField();
		txtRemoveManbillaccid.setText("remove manbill \"accID, name\"");
		txtRemoveManbillaccid.setColumns(10);
		txtRemoveManbillaccid.setBounds(10, 631, 181, 20);
		largeInternalPanel.add(txtRemoveManbillaccid);
		
		JButton btnRemoveManualbill = new JButton("Remove ManualBill");
		btnRemoveManualbill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtRemoveManbillaccid.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 2)
				{
					if(mainController.removeManualBilling(tokenizedDel[0],tokenizedDel[1]))
						txtRemoveManbillaccid.setText("SUCCESS");
					else
						txtRemoveManbillaccid.setText("Improper insert");
				}
				else
				{
					txtRemoveManbillaccid.setText("format: \"accid name\"");
				}
			}
		});
		btnRemoveManualbill.setBounds(201, 630, 138, 23);
		largeInternalPanel.add(btnRemoveManualbill);
		
		txtGetTransferAmt = new JTextField();
		txtGetTransferAmt.setText("get transfer amt \"accID\"");
		txtGetTransferAmt.setColumns(10);
		txtGetTransferAmt.setBounds(10, 662, 181, 20);
		largeInternalPanel.add(txtGetTransferAmt);
		
		JButton btnGetTransferAmount = new JButton("Get Transfer Amount");
		btnGetTransferAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtGetTransferAmt.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 1)
				{
					txtGetTransferAmt.setText("Transfer amount: " + mainController.getTransferAmount(tokenizedDel[0]));
				}
				else
				{
					txtGetTransferAmt.setText("format: \"accid name\"");
				}
			}
		});
		btnGetTransferAmount.setBounds(201, 661, 138, 23);
		largeInternalPanel.add(btnGetTransferAmount);
		
		txtUpdateaccidAmt = new JTextField();
		txtUpdateaccidAmt.setText("update transfer \"accID, amt\"");
		txtUpdateaccidAmt.setColumns(10);
		txtUpdateaccidAmt.setBounds(10, 693, 181, 20);
		largeInternalPanel.add(txtUpdateaccidAmt);
		
		JButton btnUpdateTransferAmount = new JButton("Update Transfer Amount");
		btnUpdateTransferAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtUpdateaccidAmt.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 2)
				{
					if(mainController.updateTransferAmount(tokenizedDel[0],Double.parseDouble(tokenizedDel[1])))
						txtUpdateaccidAmt.setText("SUCCESS");
					else
						txtUpdateaccidAmt.setText("Improper insert");
				}
				else
				{
					txtUpdateaccidAmt.setText("format: \"accid name\"");
				}
			}
		});
		btnUpdateTransferAmount.setBounds(201, 692, 138, 23);
		largeInternalPanel.add(btnUpdateTransferAmount);
		
		txtAddAssetaccid = new JTextField();
		txtAddAssetaccid.setText("add asset \"accID, name, value\"");
		txtAddAssetaccid.setColumns(10);
		txtAddAssetaccid.setBounds(10, 724, 181, 20);
		largeInternalPanel.add(txtAddAssetaccid);
		
		JButton btnAddAsset = new JButton("Add Asset");
		btnAddAsset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtAddAssetaccid.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 3)
				{
					if(mainController.addAsset(tokenizedDel[0],tokenizedDel[1],Double.parseDouble(tokenizedDel[2])))
						txtAddAssetaccid.setText("SUCCESS");
					else
						txtAddAssetaccid.setText("Improper insert");
				}
				else
				{
					txtAddAssetaccid.setText("format: \"accid name\"");
				}
			}
		});
		btnAddAsset.setBounds(201, 723, 138, 23);
		largeInternalPanel.add(btnAddAsset);
		
		txtRemoveAssetaccid = new JTextField();
		txtRemoveAssetaccid.setText("remove asset \"accID, name\"");
		txtRemoveAssetaccid.setColumns(10);
		txtRemoveAssetaccid.setBounds(10, 753, 181, 20);
		largeInternalPanel.add(txtRemoveAssetaccid);
		
		JButton btnDeleteAsset = new JButton("Delete Asset");
		btnDeleteAsset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String toDel = txtRemoveAssetaccid.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 2)
				{
					if(mainController.removeAsset(tokenizedDel[0],tokenizedDel[1]))
						txtRemoveAssetaccid.setText("SUCCESS");
					else
						txtRemoveAssetaccid.setText("Improper insert");
				}
				else
				{
					txtRemoveAssetaccid.setText("format: \"accid name\"");
				}
			}
		});
		btnDeleteAsset.setBounds(201, 752, 138, 23);
		largeInternalPanel.add(btnDeleteAsset);
		
		txtGetActivityaccid = new JTextField();
		txtGetActivityaccid.setText("get activity \"accID\"");
		txtGetActivityaccid.setColumns(10);
		txtGetActivityaccid.setBounds(10, 784, 181, 20);
		largeInternalPanel.add(txtGetActivityaccid);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(349, 383, 464, 430);
		largeInternalPanel.add(scrollPane_1);
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JButton btnGetActivity = new JButton("Get Activity");
		btnGetActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DEBUG
				//textArea.setText("DEBUG\nDEBUGGGG");
				String toDel = txtGetActivityaccid.getText();
				String[] tokenizedDel = toDel.split("\\s+");
				if(tokenizedDel.length == 1)
				{
					textArea.setText(mainController.getActivity(tokenizedDel[0]));
					txtGetActivityaccid.setText("SUCCESS");
				}
				else
				{
					txtGetActivityaccid.setText("format: \"id\"");
				}
			}
		});
		btnGetActivity.setBounds(201, 783, 138, 23);
		largeInternalPanel.add(btnGetActivity);
		
		JScrollPane scrollPane_2 = new JScrollPane((Component) null);
		scrollPane_2.setBounds(823, 383, 423, 430);
		largeInternalPanel.add(scrollPane_2);
		JTextArea textArea_1 = new JTextArea();
		scrollPane_2.setViewportView(textArea_1);
		
		JButton btnCheckOwners = new JButton("Check Owners");
		btnCheckOwners.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText(mainController.checkOwners());
			}
		});
		btnCheckOwners.setBounds(201, 814, 138, 23);
		largeInternalPanel.add(btnCheckOwners);
		
		
		
		
	}
}

