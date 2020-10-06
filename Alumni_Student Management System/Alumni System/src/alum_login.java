import java.util.Scanner;
public class alum_login extends User {
	public static void main(String args[])
	{
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		char ch = 'y';
		int n;
		System.out.println("Welcome to Admin login");
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
				String mail = s.nextLine();
				mail = mail.replaceAll("\\r", "");
				System.out.println("Enter your password:");
				int pass = Integer.parseInt(s.nextLine());
				
				
				System.out.println("..Verifying..");
				for(alumni al: User.get_aluminfo())
				{
					if(al.get_mailId().equalsIgnoreCase(mail)&&al.password()==pass)
					{
						try
						{
							System.out.println("Verified!\n");
							adminOptions.main(null);
							
							break;
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(!al.get_mailId().equalsIgnoreCase(mail) || al.password()!=pass)
					{
						System.out.println("Unsuccessful Sign-in! Try Again!");
						break;
					}
				}
			 	break;
		case 2: User.alumni_insert();
				break;
		case 3:
			return;
		}
	}
}
