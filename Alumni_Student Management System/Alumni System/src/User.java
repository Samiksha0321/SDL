import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
class myComparator implements Comparator<alumni>
{
	@Override
	public int compare(alumni ob1, alumni  ob2)
	{
		if(ob1.get_year() < ob2.get_year())
			return 1;
		else if(ob1.get_year() > ob2.get_year())
			return -1;
		return 0;
	}
}
class mycompare implements Comparator<student>
{	
	@Override
	public int compare(student ob1, student ob2)
	{
		if(ob1.password() < ob2.password())
			return 1;
		else if(ob1.password() > ob2.password())
			return -1;
		return 0;
	}
}
class comparison implements Comparator<internships>
{	
	@Override
	public int compare(internships ob1, internships ob2)
	{
		if(ob1.get_stipend() < ob2.get_stipend())
			return 1;
		else if(ob1.get_stipend() > ob2.get_stipend())
			return -1;
		return 0;
	}
}
class StringLengthException extends Exception
{
	private static final long serialVersionUID = 1L;
	public StringLengthException() {}
	public StringLengthException(String str)
	{
		super(str);
	}
}
class IncorrectChoiceException extends Exception
{
	private static final long serialVersionUID = 1L;
	public IncorrectChoiceException() {}
	public IncorrectChoiceException(String str)
	{
		super(str);
	}
}
public class User {
	private static String fname,lname, mailId, domain,Engg_field;
	private static int year,experience;
	private static int password, password1;
	private static String job_title,description,Company;
	private static  int stipend;
	protected static HashSet<student> data = new HashSet<student>();
	protected static HashSet<alumni> data1 = new HashSet<alumni>();
	protected static HashSet<internships> data2 = new HashSet<internships>();
	protected static Set<student> get_info()
	{
		return data;
	}
	protected static Set<alumni> get_aluminfo()
	{
		return data1;
	}
	protected static Set<internships> get_internshipinfo()
	{
		return data2;
	}
	public static void student_insert()
	{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("First name:");
		fname = scan.nextLine();
		
		System.out.println("Last Name:");
		lname = scan.nextLine();
		
		System.out.println("Email id:");
		mailId = scan.nextLine();
		
		System.out.println("Engineering field:");
		Engg_field = scan.nextLine();
		
		System.out.println("Password:");
		boolean isnumeric;
		do
		{
			try
			{
				password = Integer.parseInt(scan.nextLine());
				isnumeric = true;
			}catch(NumberFormatException e)
			{
				isnumeric = false;
				System.out.println("Invalid Input!");
			}
		}while(!isnumeric);
		
		data.add(new student(fname,lname,mailId,Engg_field,password));
	}
	public static void display()
	{
	PriorityQueue<student> st = new PriorityQueue<student>(new mycompare());
	System.out.println("\tName|\t\t\tEngineering Field|\t\t\tEmail id|\t\tRollNo|");
	for(student std: data)
	{
		st.add(std);
	}
	for(student o:st)
	{
		System.out.printf("%-24s %-24s %-24s %-24s %-24d  \n",o.get_fname(),o.get_lname(),
			o.get_Engg_field(),o.get_MailId(),o.password());
	}
	}
	public static void delete_student(int n)
	{
		Iterator<student> itr = data.iterator();
		student str ;
		while(itr.hasNext())
		{
			str = (student)itr.next();
			if(str.password()==n)
			{
				System.out.println("User: " + str.password() + " removed!!");
				itr.remove();
				break;
			}
		}
	}
	public static void alumni_insert()
	{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("First name:");
		fname = scan.nextLine();
		
		System.out.println("Last Name:");
		lname = scan.nextLine();
		
		System.out.println("Email id:");
		mailId = scan.nextLine();
		
		System.out.println("Domain:");
		domain = scan.nextLine();
		
		System.out.println("Graduation year:");
		year = Integer.parseInt(scan.nextLine());
		
		System.out.println("No. of years of work experience:");
		experience = Integer.parseInt(scan.nextLine());
		
		System.out.println("Password:");
		password1 = Integer.parseInt(scan.nextLine());
		
		data1.add(new alumni(fname,lname,mailId,domain,year,experience, password1));
	}
	public static void alum_display() {
		PriorityQueue<alumni> al = new PriorityQueue<alumni>(new myComparator());
		System.out.println("\tName|\t\t\tDomain|\t\t\tEmail id|\t\tGraduation year|\t\tExperience|");
		for(ArrayList<alumni> l :segregate.Segregate(User.data1))
		{
			for(alumni a: l)
			{
				al.add(a);
			}
		}
		for(alumni o:al)
		{
			System.out.printf("%-23s %-23s %-23s %-23s %-23d %-23d \n",o.get_fname(),o.get_lname(),
							o.get_domain(),o.get_mailId(),o.get_experience(),o.get_year());
			System.out.println();
		}
	}
	public static void delete_alumni(String n,String m,int year)
	{
		Iterator<alumni> itr = data1.iterator();
		alumni str ;
		while(itr.hasNext())
		{
			str = (alumni)itr.next();
			if(str.get_fname().equalsIgnoreCase(n) && str.get_lname().equalsIgnoreCase(m) && str.get_year()==year)
			{
				System.out.println("User: " + str.get_fname() + str.get_lname() + " removed!!");
				itr.remove();
				break;
			}
		}
	}
	public static void internship_insert()
	{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Job title:");
		job_title = scan.nextLine();
		
		System.out.println("Domain:");
		domain = scan.nextLine();
		
		System.out.println("Email id for contacting:");
		mailId = scan.nextLine();
		
		System.out.println("Company name:");
		Company = scan.nextLine();
		
		System.out.println("Job description:");
		description = scan.nextLine();
		
		System.out.println("Enter Stipend:");
		stipend = Integer.parseInt(scan.nextLine());
		
		System.out.println("Enter no. of years of work experience:");
		experience = Integer.parseInt(scan.nextLine());

		data2.add(new internships(job_title,domain,mailId,description,Company,stipend,experience));
	}
	 public static void internships_display() {
		 //System.out.println("Inside display internship");
		PriorityQueue<internships> internship = new PriorityQueue<internships>(new comparison());
		System.out.println("Job title\t\tDomain\t\t\tEmail id\t\t\tDescription\t\t\tCompany\t\tStipend\t\tExperience");
		/*
		 * for(ArrayList<internships> l :segregate.order(User.data2)) { for(internships
		 * in: l) { internship.add(in); } }
		 */
		for(ArrayList<internships> l :segregate.order(User.data2))
		{
			//System.out.println("Inside display internship1");
			for(internships a: l)
			{
				internship.add(a);
				//System.out.println("Inside display internship2");
			}
			
		}
		for(internships o:internship)
		{
			
			System.out.printf("%-23s %-23s %-23s %-25s %-25s %-23d %-23d \n",o.get_job_title(),o.get_domain(),
							o.get_mailId(),o.get_description(),o.get_Company(),o.get_stipend(),o.get_experience());
			System.out.println();
		}
	}
	/*public static void main(String args[])
	{
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("******Welcome Student and Alumni******");
		do
		{
		System.out.println("1.Student\n2.Alumni");
		System.out.println("Enter your choice");
		int ch = s.nextInt();
		switch(ch)
		{
		case 1:
			do
			{
				System.out.println("1. Insert\n2. Show\n3. Delete\n4.Exit");
				System.out.println("Enter your choice:");
				int choice = s.nextInt();
				switch(choice)
				{
				case 1:
						try
						{
							student_insert();
						}catch(Exception e)
						{
							e.printStackTrace();
						}
						break;
				case 2:
					try
					{
						display();
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					break;
				case 3:
					System.out.println("Enter rollno to be deleted:");
					s.nextLine();
					int n = Integer.parseInt(s.nextLine());
					delete_student(n);
					break;
				case 4:
					return;
				default:
					System.out.println("Invalid Choice!!");
				}
			}while(true);
		case 2:
			do
			{
				System.out.println("1. Insert\n2. Show\n3. Delete\n4.Exit");
				System.out.println("Enter your choice:");
				int choice = s.nextInt();
				switch(choice)
				{
				case 1:
						try
						{
							alumni_insert();
						}catch(Exception e)
						{
							e.printStackTrace();
						}
						break;
				case 2:
					try
					{
						alum_display();
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					break;
				case 3:
					System.out.println("Enter first,last name and graduation year of alumni to be deleted:");
					s.nextLine();
					String n = s.nextLine();
					s.nextLine();
					String m = s.nextLine();
					s.nextLine();
					int y = s.nextInt();
					delete_alumni(n,m,y);
					break;
				case 4:
					return;
				default:
					System.out.println("Invalid Choice!!");
				}
			}while(true);
		case 3:
			return;
		default:
			System.out.println("Invalid Choice!!");
		}
		}while(true);
	}*/
}
