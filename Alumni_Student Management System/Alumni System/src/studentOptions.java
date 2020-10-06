import java.util.Scanner;

public class studentOptions {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char ch;
		segregate seg = new segregate();
		Scanner s = new Scanner(System.in);
		System.out.println("1.View the list of all alumni\n2.View alumni domain wise\n3.View list of internships");
		int choice = Integer.parseInt(s.nextLine());
		switch(choice)
		{
		case 1:
			User.alum_display();
			break;
		case 2:
			System.out.println("Enter domain");
			String domain = s.nextLine();
			if(domain.equalsIgnoreCase("machine learning"))
				seg.machinel();
			else if(domain.equalsIgnoreCase("blockchain"))
				seg.blockchain();
			else if(domain.equalsIgnoreCase("cloud computing"))							
				seg.cloud();								
			else if(domain.equalsIgnoreCase("data science"))
				seg.datascience();
			else
				seg.iot();	
			break;
		case 3:
			User.internships_display();
			break;
		}
		System.out.print("Press c to Continue and x to Sign out");
		ch = s.next().charAt(0);
		if (ch == 'c') {
			studentOptions.main(null);
		}
		if (ch == 'x') {
			stud_login.main(null);
		}
	}

}
