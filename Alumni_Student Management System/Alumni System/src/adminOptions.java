import java.util.Scanner;

public class adminOptions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char ch;
		Scanner s = new Scanner(System.in);
		System.out.println("1.Look for fellow alumni \n2.Post internship oppurtunities");
		int choice= Integer.parseInt(s.nextLine());
		switch(choice)
		{
		case 1:
		User.alum_display();
		break;
		case 2:
		User.internship_insert();
		break;
		}
		System.out.print("Press c to Continue and x to Sign out");
		ch = s.next().charAt(0);
		if (ch == 'c') {
			adminOptions.main(null);
		}
		if (ch == 'x') {
			alum_login.main(null);
		}
	}

}
