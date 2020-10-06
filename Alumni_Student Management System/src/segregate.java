import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
public class segregate extends User{
	public static ArrayList<ArrayList<alumni> > Segregate(HashSet<alumni> data1)
	{
		ArrayList<ArrayList<alumni> > mainlist = new ArrayList<ArrayList<alumni> >();
		ArrayList<alumni> machine_learning = new ArrayList<alumni>();
		ArrayList<alumni> blockchain = new ArrayList<alumni>();
		ArrayList<alumni> cloud_computing = new ArrayList<alumni>();
		ArrayList<alumni> data_science = new ArrayList<alumni>();
		ArrayList<alumni> iot = new ArrayList<alumni>();
		for(alumni a:data1)
		{
			if(a.get_domain().equalsIgnoreCase("Machine Learning"))
				machine_learning.add(a);
			else if(a.get_domain().equalsIgnoreCase("Cloud Computing"))
				cloud_computing.add(a);
			else if(a.get_domain().equalsIgnoreCase("Blockchain"))
				blockchain.add(a);
			else if(a.get_domain().equalsIgnoreCase("Data Science"))
				data_science.add(a);
			else
				iot.add(a);
		}
		mainlist.add(machine_learning);
		mainlist.add(blockchain);
		mainlist.add(cloud_computing);
		mainlist.add(data_science);
		mainlist.add(iot);
		return mainlist;
	}
	public static ArrayList<ArrayList<internships> > order(HashSet<internships> data2)
	{
		ArrayList<ArrayList<internships> > mainlist = new ArrayList<ArrayList<internships> >();
		ArrayList<internships> machine_learning = new ArrayList<internships>();
		ArrayList<internships> blockchain = new ArrayList<internships>();
		ArrayList<internships> cloud_computing = new ArrayList<internships>();
		ArrayList<internships> data_science = new ArrayList<internships>();
		ArrayList<internships> iot = new ArrayList<internships>();
		for(internships in:data2)
		{
			if(in.get_domain().equalsIgnoreCase("Machine Learning"))
				machine_learning.add(in);
			else if(in.get_domain().equalsIgnoreCase("Cloud Computing"))
				cloud_computing.add(in);
			else if(in.get_domain().equalsIgnoreCase("Blockchain"))
				blockchain.add(in);
			else if(in.get_domain().equalsIgnoreCase("Data Science"))
				data_science.add(in);
			else
				iot.add(in);
		}
		mainlist.add(machine_learning);
		mainlist.add(blockchain);
		mainlist.add(cloud_computing);
		mainlist.add(data_science);
		mainlist.add(iot);
		return mainlist;
	}
	/*public static ArrayList<alumni> ml()
	{
		ArrayList<alumni> machine_learning = new ArrayList<alumni>();
		for(alumni a:data1)
		{
			if(a.get_domain().equalsIgnoreCase("machine_learning"))
				machine_learning.add(a);
		}
		return machine_learning;
	}*/
	public static void machinel()
	{
	  PriorityQueue<alumni> machine_learning = new PriorityQueue<alumni>(new myComparator());
	  System.out.println("FName\t\t\tLName\t\t\tDomain\t\t\tEmail id\t\tGraduation year\t\tExperience");
	  for(alumni al: data1)
	{	
		  if(al.get_domain().equalsIgnoreCase("machine learning"))
		  machine_learning.add(al);
	}
	for(alumni o:machine_learning)
	{
			System.out.printf("%-23s %-23s %-23s %-23s %-23d %-23d \n",o.get_fname(),o.get_lname(),
			o.get_domain(),o.get_mailId(),o.get_year(),o.get_experience());
	}
	}
	public static void blockchain()
	{
	  PriorityQueue<alumni> blockchain = new PriorityQueue<alumni>(new myComparator());
	  System.out.println("FName\t\t\tLName\t\t\tDomain\t\t\tEmail id\t\tGraduation year\t\tExperience");
	  for(alumni al: data1)
	{	
		  if(al.get_domain().equalsIgnoreCase("blockchain"))
		  blockchain.add(al);
	}
	for(alumni o:blockchain)
	{
			System.out.printf("%-23s %-23s %-23s %-23s %-23d %-23d \n",o.get_fname(),o.get_lname(),
			o.get_domain(),o.get_mailId(),o.get_year(),o.get_experience());
	}
	}
	public static void datascience()
	{
	  PriorityQueue<alumni> data_science = new PriorityQueue<alumni>(new myComparator());
	  System.out.println("FName\t\t\tLName\t\t\tDomain\t\t\tEmail id\t\tGraduation year\t\tExperience");
	  for(alumni al: data1)
	{	
		  if(al.get_domain().equalsIgnoreCase("data science"))
		  data_science.add(al);
	}
	for(alumni o:data_science)
	{
			System.out.printf("%-23s %-23s %-23s %-23s %-23d %-23d \n",o.get_fname(),o.get_lname(),
			o.get_domain(),o.get_mailId(),o.get_year(),o.get_experience());
	}
	}
	public static void cloud()
	{
	  PriorityQueue<alumni> cloud_computing = new PriorityQueue<alumni>(new myComparator());
	  System.out.println("FName\t\t\tLName\t\t\tDomain\t\t\tEmail id\t\tGraduation year\t\tExperience");
	  for(alumni al: data1)
	{	
		  if(al.get_domain().equalsIgnoreCase("cloud computing"))
		  cloud_computing.add(al);
	}
	for(alumni o:cloud_computing)
	{
			System.out.printf("%-23s %-23s %-23s %-23s %-23d %-23d \n",o.get_fname(),o.get_lname(),
			o.get_domain(),o.get_mailId(),o.get_year(),o.get_experience());
	}
	}
	public static void iot()
	{
	  PriorityQueue<alumni> iot = new PriorityQueue<alumni>(new myComparator());
	  System.out.println("FName\t\t\tLName\t\t\tDomain\t\t\tEmail id\t\tGraduation year\t\tExperience");
	  for(alumni al: data1)
	{	
		  if(al.get_domain().equalsIgnoreCase("iot"))
		  iot.add(al);
	}
	for(alumni o:iot)
	{
			System.out.printf("%-23s %-23s %-23s %-23s %-23d %-23d \n",o.get_fname(),o.get_lname(),
			o.get_domain(),o.get_mailId(),o.get_year(),o.get_experience());
	}
	}
	
}
