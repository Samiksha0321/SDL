import java.util.Scanner;
public class stud_login extends User{
	public static void main(String args[])
	{
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		char ch = 'y';
		int n;
		System.out.println("Welcome to Student Login");
		System.out.println("1. Sign In\n2. Create new account\n3. Return\n");
		System.out.println("Select:");
		do
		{
			try
			{
				n=Integer.parseInt(s.nextLine());
				if(n>3&&n!=0)
					throw new IncorrectChoiceException("Wrong Input! Try Again!");
				break;
				
			}
			catch(NumberFormatException e)
			{
				System.out.println("Numeric input required!!!");
			}
			catch(IncorrectChoiceException e)
			{
				System.out.println(e.getMessage());
			}
		}while(true);
		switch(n)
		{
		case 1:
				System.out.println("Enter your Email id:");
				String name = s.nextLine();
				System.out.println("Enter Rollno:");
				int roll = Integer.parseInt(s.nextLine());
				System.out.println("..Verifying..");
				for(student st: User.get_info())
				{
					if(st.get_MailId().equalsIgnoreCase(name)&&st.password()==roll)
					{
						try
						{
							System.out.printf("%s %s",st.password(),"Verified!\n");
							studentOptions.main(null);
							break;
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(!st.get_MailId().equalsIgnoreCase(name) || st.password()!=roll)
					{
						System.out.println("Invalid Credentials!!!! Try Again!!");
						break;
					}
				}
				break;
		case 2:
			User.student_insert();
			break;
		case 3:
			return;
		}
	}
}
