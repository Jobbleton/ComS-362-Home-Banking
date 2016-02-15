import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HomeBankingController
{
	ArrayList<Person> peopleList;
	ArrayList<Child> childrenList;
	ArrayList<Bank> bankList;
	
	HomeBankingController()
	{
		peopleList = new ArrayList<Person>();
		childrenList = new ArrayList<Child>();
		bankList = new ArrayList<Bank>();
		
		//DEBUG
		addPerson(new Person("James", "Test", "1234"));
		
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
			NodeList nList = doc.getElementsByTagName("student");
			System.out.println("----------------------------");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					System.out.println("Student roll no : " 
							+ eElement.getAttribute("rollno"));
					
					System.out.println("First Name : " 
							+ eElement
							.getElementsByTagName("firstname")
							.item(0)
							.getTextContent());
					
					System.out.println("Last Name : " 
							+ eElement
							.getElementsByTagName("lastname")
							.item(0)
							.getTextContent());
					
					System.out.println("Nick Name : " 
							+ eElement
							.getElementsByTagName("nickname")
							.item(0)
							.getTextContent());
					
					System.out.println("Marks : " 
							+ eElement
							.getElementsByTagName("marks")
							.item(0)
							.getTextContent());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Person> getPeopleList()
	{
		return peopleList;
	}
	
	public ArrayList<Child> getChildrenList()
	{
		return childrenList;
	}
	
	public ArrayList<Bank> getBankList()
	{
		return bankList;
	}
	
	public boolean addPerson(Person p)
	{
		peopleList.add(p);
		return true;
	}
	
	public boolean removePerson(String id)
	{
		for(int i = 0; i < peopleList.size(); i++)
		{
			if(peopleList.get(i).getIdentifier().equals(id))
			{
				peopleList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean addChild(Child p)
	{
		childrenList.add(p);
		return true;
	}
	
	public boolean removeChildren(String id)
	{
		for(int i = 0; i < childrenList.size(); i++)
		{
			if(childrenList.get(i).getIdentifier().equals(id))
			{
				childrenList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean addBank(Bank p)
	{
		bankList.add(p);
		return true;
	}
	
	public boolean removeBank(String id)
	{
		for(int i = 0; i < bankList.size(); i++)
		{
			if(bankList.get(i).getName().equals(id))
			{
				bankList.remove(i);
				return true;
			}
		}
		return false;
	}
}
