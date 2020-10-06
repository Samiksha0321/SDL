import java.util.Scanner;
public class admin extends User{
	private static student search_student(int roll)
	{
		for(student st:User.data)
		{
			if(st.password()==roll)
				return st;
		}
		return null;
	}
	private static alumni search_alumni(String mail)
	{
		for(alumni a:User.data1)
		{
			if(a.get_mailId().equalsIgnoreCase(mail))
				return a;
		}
		return null;
	}
	public static void main(String args[])
	{
		Scanner s = new Scanner(System.in);
		do
		{
		System.out.println("*Welcome to Admin Login*");
		System.out.println("1.View alumni list\n2.View student list\n3.Create new Alumnus\n4.Create new Student\n5.Return");
		System.out.println("Select:");
		int ch = s.nextInt();
		s.nextLine();
		switch(ch)
		{
		case 1:
			User.alum_display();
			break;
		case 2:
			User.display();
			break;
		
		case 3:
			System.out.println("1.Enter alumnus details\n2.Edit alumnus detail");
			System.out.println("Select:");
			int opt=s.nextInt();
			s.nextLine();
			switch(opt)
			{
			case 1:
				User.alumni_insert();
				break;
			case 2:
				System.out.println("Enter mail id");
				String mail = s.nextLine();
				alumni o = search_alumni(mail);
				if(o!=null)
				{
					System.out.println("Alumnus found");
					System.out.println("Edit: \n1. Name\n2. Domain\n3. Experience");
					System.out.println("Select:");
					int op = s.nextInt();
					s.nextLine();
					switch(op)
					{
					case 1:
						System.out.println("Enter new first name:");
						String fname= s.nextLine();
						o.new_fname(fname);
						System.out.println("Enter new last name:");
						String lname= s.nextLine();
						o.new_lname(lname);
						break;
					case 2:
						System.out.println("Enter new domain");
						String domain= s.nextLine();
						o.new_domain(domain);
						break;
					case 3:
						System.out.println("Enter new roll:");
						int exp= Integer.parseInt(s.nextLine());
						o.new_experience(exp);
						break;
					}
				}
			}
			break;
		case 4:
			System.out.println("1. Enter Student details\n2. Edit Student Detail");
			System.out.println("Enter choice");
			int choice = s.nextInt();
			s.nextLine();
			switch(choice)
			{
			case 1:
				User.student_insert();
				break;
			case 2:
				System.out.println("Enter roll no.");
				int stud_roll = Integer.parseInt(s.nextLine());
				student obj = search_student(stud_roll);
				if(obj!=null)
				{
					System.out.println("Student found");
					System.out.println("Edit: \n1. Name\n2. Engg_field\n3. Rollno.");
					System.out.println("Select:");
					int choice1 = s.nextInt();
					s.nextLine();
					switch(choice1)
					{
					case 1:
						System.out.println("Enter new first name:");
						String fname= s.nextLine();
						obj.new_fname(fname);
						System.out.println("Enter new last name:");
						String lname= s.nextLine();
						obj.new_lname(lname);
						break;
					case 2:
						System.out.println("Enter new field:");
						String field= s.nextLine();
						obj.new_Engg_Field(field);
						break;
					case 3:
						System.out.println("Enter new roll:");
						int RollNo= Integer.parseInt(s.nextLine());
						obj.password(RollNo);
						break;
					}
				}
			}
			break;
		case 5:
			return;
		}
		}while(true);
	}
}
