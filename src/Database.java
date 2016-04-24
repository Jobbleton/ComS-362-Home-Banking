import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Database
{
	public static ArrayList<Person> getPeople()
	{
		ArrayList<Person> toReturn = new ArrayList<Person>();
	
		try
		{	
			File inputFile = new File("src/database.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("person");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					String pID = eElement.getAttribute("ID");					
					String fName = eElement.getAttribute("fName");
					String lName = eElement.getAttribute("lName");
					
					toReturn.add(new Person(fName, lName, pID));
				}
			}
			try
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/database.txt"));
				transformer.transform(source, result);
				
				//DEBUG
				StreamResult consoleResult = new StreamResult(System.out);
				
				transformer.transform(source, consoleResult);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		return toReturn;
	}
	
	public static ArrayList<Bank> getBanks()
	{
		ArrayList<Bank> toReturn = new ArrayList<Bank>();
		
		try
		{	
			File inputFile = new File("src/database.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList nBankList = doc.getElementsByTagName("bank");
			
			for(int temp1 = 0; temp1 < nBankList.getLength(); temp1++)
			{
				Node nBankNode = nBankList.item(temp1);
						
				if (nBankNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element bankEle = (Element) nBankNode;
					String bID = bankEle.getAttribute("name");			
					Bank bankToAdd = new Bank(bID);
					
					NodeList nAccountList = bankEle.getElementsByTagName("account");
							
					for(int temp2 = 0; temp2 < nAccountList.getLength(); temp2++)
					{
						Node nAccountNode = nAccountList.item(temp2);
						
						if (nAccountNode.getNodeType() == Node.ELEMENT_NODE)
						{
							Element accEle = (Element) nAccountNode;
							String ownerID = accEle.getAttribute("owner");
							String aID = accEle.getAttribute("ID");
							Account accToAdd = new Account(getPerson(ownerID), aID);
							double value = Double.parseDouble(accEle.getAttribute("value"));
							accToAdd.setValue(value);
							
							NodeList nSubList = accEle.getElementsByTagName("dependent");
							
							for(int temp3 = 0; temp3 < nSubList.getLength(); temp3++)
							{
								Node nSubNode = nSubList.item(temp3);
								
								if (nSubNode.getNodeType() == Node.ELEMENT_NODE)
								{
									Element subEle = (Element) nSubNode;
									String dID = subEle.getAttribute("ID");
									Child depToAdd = getChild(dID);			
									accToAdd.addDependent(depToAdd);
								}
							}
							
							nSubList = accEle.getElementsByTagName("billing");
							
							for(int temp3 = 0; temp3 < nSubList.getLength(); temp3++)
							{
								Node nSubNode = nSubList.item(temp3);
								
								if (nSubNode.getNodeType() == Node.ELEMENT_NODE)
								{
									Element subEle = (Element) nSubNode;
									String bName = subEle.getAttribute("name");
									double bValue = Double.parseDouble(subEle.getAttribute("value"));		
									accToAdd.addBill(new Billing(bName, bValue));
								}
							}
							
							nSubList = accEle.getElementsByTagName("autobilling");
							
							for(int temp3 = 0; temp3 < nSubList.getLength(); temp3++)
							{
								Node nSubNode = nSubList.item(temp3);
								
								if (nSubNode.getNodeType() == Node.ELEMENT_NODE)
								{
									Element subEle = (Element) nSubNode;
									String bName = subEle.getAttribute("name");
									double bValue = Double.parseDouble(subEle.getAttribute("value"));
									double bAmtToBill = Double.parseDouble(subEle.getAttribute("amountToBill"));
									double bDailyFreq = Double.parseDouble(subEle.getAttribute("dailyFreq"));
									int bTBilled = Integer.parseInt(subEle.getAttribute("timesbilled"));
									
									AutoBilling aBToAdd = new AutoBilling(bName, bAmtToBill, bDailyFreq);
									aBToAdd.setValue(bValue);
									aBToAdd.setTimesBilled(bTBilled);

									accToAdd.addAutoBill(aBToAdd);
								}
							}
							
							nSubList = accEle.getElementsByTagName("manualbilling");
							
							for(int temp3 = 0; temp3 < nSubList.getLength(); temp3++)
							{
								Node nSubNode = nSubList.item(temp3);
								
								if (nSubNode.getNodeType() == Node.ELEMENT_NODE)
								{
									Element subEle = (Element) nSubNode;
									String bName = subEle.getAttribute("name");
									double bValue = Double.parseDouble(subEle.getAttribute("value"));
									double bAmtToBill = Double.parseDouble(subEle.getAttribute("amountToBill"));
									int bTBilled = Integer.parseInt(subEle.getAttribute("timesbilled"));
									
									ManualBilling mBToAdd = new ManualBilling(bName, bAmtToBill);
									mBToAdd.setValue(bValue);
									mBToAdd.setTimesBilled(bTBilled);
									
									accToAdd.addManBill(mBToAdd);
								}
							}
							
							nSubList = accEle.getElementsByTagName("asset");
							
							for(int temp3 = 0; temp3 < nSubList.getLength(); temp3++)
							{
								Node nSubNode = nSubList.item(temp3);
								
								if (nSubNode.getNodeType() == Node.ELEMENT_NODE)
								{
									Element subEle = (Element) nSubNode;
									String bName = subEle.getAttribute("name");
									double bValue = Double.parseDouble(subEle.getAttribute("value"));
									
									accToAdd.addAsset(new Asset(bName, bValue));
								}
							}
							
							nSubList = accEle.getElementsByTagName("loan");
							
							for(int temp3 = 0; temp3 < nSubList.getLength(); temp3++)
							{
								Node nSubNode = nSubList.item(temp3);
								
								if (nSubNode.getNodeType() == Node.ELEMENT_NODE)
								{
									Element subEle = (Element) nSubNode;
									String bName = subEle.getAttribute("name");
									double bValue = Double.parseDouble(subEle.getAttribute("value"));
									
									accToAdd.addLoan(new Loan(bName, bValue));
								}
							}
							
							nSubList = accEle.getElementsByTagName("fund");
							
							for(int temp3 = 0; temp3 < nSubList.getLength(); temp3++)
							{
								Node nSubNode = nSubList.item(temp3);
								
								if (nSubNode.getNodeType() == Node.ELEMENT_NODE)
								{
									Element subEle = (Element) nSubNode;
									String bName = subEle.getAttribute("name");
									double bValue = Double.parseDouble(subEle.getAttribute("value"));
									accToAdd.addFund(new Fund(bName, bValue));
								}
							}
							
							bankToAdd.addAccount(accToAdd);
						}
					}
					toReturn.add(bankToAdd);
				}
			}
			try
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/database.txt"));
				transformer.transform(source, result);
				
				//DEBUG
				StreamResult consoleResult = new StreamResult(System.out);
				
				transformer.transform(source, consoleResult);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		return toReturn;
	}
	
	public static ArrayList<Child> getChildren()
	{
		ArrayList<Child> toReturn = new ArrayList<Child>();
		
		try
		{	
			File inputFile = new File("src/database.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("child");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					String cID = eElement.getAttribute("ID");					
					String fName = eElement.getAttribute("fName");
					String lName = eElement.getAttribute("lName");
					String parentID = eElement.getAttribute("parent");
					
					toReturn.add(new Child(fName, lName, cID, parentID));
				}
			}
			try
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/database.txt"));
				transformer.transform(source, result);
				
				//DEBUG
				StreamResult consoleResult = new StreamResult(System.out);
				
				transformer.transform(source, consoleResult);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		return toReturn;
	}
	
	public static boolean addPerson(Person p)
	{	
		try
		{	
			File inputFile = new File("src/database.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			
			
			NodeList nList = doc.getElementsByTagName("person");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					String pID = eElement.getAttribute("ID");					
					if(pID.equals(p.getIdentifier()))
					{
						return false;
					}
				}
			}
			
			Element e = doc.createElement("person");
			e.setAttribute("ID", p.getIdentifier());
			e.setAttribute("fName", p.getFirstName());
			e.setAttribute("lName", p.getLastName());
			doc.appendChild(e);			
					
			try
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/database.txt"));
				transformer.transform(source, result);
				
				//DEBUG
				StreamResult consoleResult = new StreamResult(System.out);
				
				transformer.transform(source, consoleResult);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
			
			return true;
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		return false;
	}
	
	public static Person getPerson(String ID)
	{
		Person toReturn = null;
		
		try
		{	
			File inputFile = new File("src/database.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("person");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					String pID = eElement.getAttribute("ID");					
					if(pID.equals(ID))
					{
						String fName = eElement.getAttribute("fName");
						String lName = eElement.getAttribute("lName");
						return new Person(fName, lName, pID);
					}
				}
			}
			try
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/database.txt"));
				transformer.transform(source, result);
				
				//DEBUG
				StreamResult consoleResult = new StreamResult(System.out);
				
				transformer.transform(source, consoleResult);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		return toReturn;
	}
	
	public static boolean removePerson(String ID)
	{		
		try
		{	
			File inputFile = new File("src/database.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("person");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					String pID = eElement.getAttribute("ID");					
					if(pID.equals(ID))
					{
						doc.removeChild(nList.item(temp));
					}
				}
			}
			try
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/database.txt"));
				transformer.transform(source, result);
				
				//DEBUG
				StreamResult consoleResult = new StreamResult(System.out);
				
				transformer.transform(source, consoleResult);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
				//return false;
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
			//return false;
		}
		
		return false;
	}
	
	public static boolean updatePerson(Person p)
	{
		removePerson(p.getIdentifier());
		return addPerson(p);
	}
	
	public static boolean addBank(Bank b)
	{
		try
		{	
			File inputFile = new File("src/database.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList nBankList = doc.getElementsByTagName("bank");
			
			for(int temp1 = 0; temp1 < nBankList.getLength(); temp1++)
			{
				Node nBankNode = nBankList.item(temp1);
						
				if (nBankNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element bankEle = (Element) nBankNode;
					String bID = bankEle.getAttribute("name");			
					if(bID.equals(b.getName()))
						return false;
					
					
				}
			}
			
			Element eBank = doc.createElement("bank");
			eBank.setAttribute("name", b.getName());
			ArrayList<Account> bankAccounts = b.getAccounts();
			for(Account a : bankAccounts)
			{
				Element eAcc = doc.createElement("account");
				eAcc.setAttribute("owner", a.getOwner().getIdentifier());
				eAcc.setAttribute("ID", a.getID());
				eAcc.setAttribute("value", "" + a.getValue());
				eAcc.setAttribute("transfers", ""+a.getTransferAmount());
				ArrayList<Child> childrenOfAcc = a.getDependents();
				for(Child c : childrenOfAcc)
				{
					Element eSub = doc.createElement("dependent");
					eSub.setAttribute("ID", c.getIdentifier());
					eAcc.appendChild(eSub);
				}
				ArrayList<Billing> billOA = a.getBills();
				for(Billing c : billOA)
				{
					Element eSub = doc.createElement("billing");
					eSub.setAttribute("name", c.getName());
					eSub.setAttribute("value", "" + c.getValue());
					eAcc.appendChild(eSub);
				}
				ArrayList<AutoBilling> autoBillOA = a.getAutoBills();
				for(AutoBilling c : autoBillOA)
				{
					Element eSub = doc.createElement("autobilling");
					eSub.setAttribute("name", c.getName());
					eSub.setAttribute("value", "" + c.getValue());
					eSub.setAttribute("timesbilled", "" + c.getTimesBilled());
					eSub.setAttribute("dailyFreq", "" + c.getFrequency());
					eAcc.appendChild(eSub);
				}
				ArrayList<ManualBilling> manbillOA = a.getManBills();
				for(ManualBilling c : manbillOA)
				{
					Element eSub = doc.createElement("manualbilling");
					eSub.setAttribute("name", c.getName());
					eSub.setAttribute("value", "" + c.getValue());
					eSub.setAttribute("timesbilled", "" + c.getTimesBilled());
					eAcc.appendChild(eSub);
				}
				ArrayList<Asset> assetOA = a.getAssets();
				for(Asset c : assetOA)
				{
					Element eSub = doc.createElement("asset");
					eSub.setAttribute("name", c.getName());
					eSub.setAttribute("value", "" + c.getValue());
					eAcc.appendChild(eSub);
				}
				ArrayList<Loan> loanOA = a.getLoans();
				for(Loan c : loanOA)
				{
					Element eSub = doc.createElement("loan");
					eSub.setAttribute("name", c.getName());
					eSub.setAttribute("value", "" + c.getValue());
					eAcc.appendChild(eSub);
				}
				ArrayList<Fund> fundOA = a.getFunds();
				for(Fund c : fundOA)
				{
					Element eSub = doc.createElement("fund");
					eSub.setAttribute("name", c.getName());
					eSub.setAttribute("value", "" + c.getValue());
					eAcc.appendChild(eSub);
				}
				eBank.appendChild(eAcc);
			}

			doc.appendChild(eBank);	
			
			try
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/database.txt"));
				transformer.transform(source, result);
				
				//DEBUG
				StreamResult consoleResult = new StreamResult(System.out);
				
				transformer.transform(source, consoleResult);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
			return true;
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		return false;
	}
	
	public static Bank getBank(String name)
	{
		ArrayList<Bank> banks = getBanks();
		for(Bank b : banks)
		{
			if(b.getName().equals(name))
				return b;
		}
		return null;
	}
	
	public static boolean removeBank(String name)
	{
		try
		{	
			File inputFile = new File("src/database.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("bank");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					String pID = eElement.getAttribute("name");					
					if(pID.equals(name))
					{
						doc.removeChild(nList.item(temp));
					}
				}
			}
			try
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/database.txt"));
				transformer.transform(source, result);
				
				//DEBUG
				StreamResult consoleResult = new StreamResult(System.out);
				
				transformer.transform(source, consoleResult);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
				//return false;
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
			//return false;
		}
		
		return false;
	}
	
	public static boolean updateBank(Bank b)
	{
		removeBank(b.getName());
		return addBank(b);
	}
	
	public static boolean addChild(Child c)
	{
		try
		{	
			File inputFile = new File("src/database.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			
			
			NodeList nList = doc.getElementsByTagName("person");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					String pID = eElement.getAttribute("ID");					
					if(pID.equals(c.getIdentifier()))
					{
						return false;
					}
				}
			}
			
			Element e = doc.createElement("child");
			e.setAttribute("ID", c.getIdentifier());
			e.setAttribute("fName", c.getFirstName());
			e.setAttribute("lName", c.getLastName());
			e.setAttribute("parent", c.getParent());
			doc.appendChild(e);			
					
			try
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/database.txt"));
				transformer.transform(source, result);
				
				//DEBUG
				StreamResult consoleResult = new StreamResult(System.out);
				
				transformer.transform(source, consoleResult);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
			
			return true;
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		return false;
	}
	
	public static Child getChild(String ID)
	{
		ArrayList<Child> children = getChildren();
		for(Child c : children)
		{
			if(c.getIdentifier().equals(ID))
				return c;
		}
		return null;
	}
	
	public static boolean removeChild(String ID)
	{
		try
		{	
			File inputFile = new File("src/database.txt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("child");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					String pID = eElement.getAttribute("ID");					
					if(pID.equals(ID))
					{
						doc.removeChild(nList.item(temp));
					}
				}
			}
			try
			{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("src/database.txt"));
				transformer.transform(source, result);
				
				//DEBUG
				StreamResult consoleResult = new StreamResult(System.out);
				
				transformer.transform(source, consoleResult);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
				//return false;
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
			//return false;
		}
		
		return false;
	}
	
	public static boolean updateChild(Child c)
	{
		removeChild(c.getIdentifier());
		return addChild(c);
	}
}
