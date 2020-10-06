import java.io.Serializable;
import java.util.*;

public class Login {
	public static void main(String args[])
	{
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("\t\t##Student Alumni System##");
		do
		{
			System.out.println("Login as: \n1. Admin \n2. Student \n3. Alumni \n4. Exit");
			System.out.println("Select:");
			int choice = s.nextInt();
			s.nextLine();
			switch(choice)
			{
			case 1:
				admin.main(null);
				break;
			case 2:
				stud_login.main(null);
				break;
			case 3:
				alum_login.main(null);
				break;
			case 4:
				System.out.println("\t\tThank You!");
				System.exit(0);
			}
		}while(true);
	}
}
