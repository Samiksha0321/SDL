import java.io.*; 
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.PriorityQueue; 

class taking_student implements Serializable {
	String fname, MailId, Engg_field;
	String lname;
	int password;
}

class taking_alumni implements Serializable {
	String fname,lname, mailId, domain;
	int year,experience;
	int password;
}

class taking_internship implements Serializable{
	String job_title,domain,mailId,description,Company;
	int stipend,experience;
}

class client extends User{ 
  
	private static Socket socket;
	private static ObjectOutputStream objToServer;
	private static ObjectInputStream objFromServer;
	private static DataOutputStream toServer;
	private static BufferedReader fromServer; 
	private static BufferedReader input;
	
	
    @SuppressWarnings("static-access")
	public static void main(String args[]) throws Exception { 
    	socket = new Socket("localhost",8234);
		objToServer = new ObjectOutputStream(socket.getOutputStream());
		objFromServer = new ObjectInputStream(socket.getInputStream());
		toServer = new DataOutputStream(socket.getOutputStream());
		fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		input = new BufferedReader(new InputStreamReader(System.in));
        
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/student_alumni_database","root","123654");
		Statement st=con.createStatement();
		
		
		//direcct obj
				data.add(new student("Sam", "J", "sam", "cs", 123));
				data1.add(new alumni("Arnav", "L", "arnav", "machine learning", 2010, 5, 123));
				data2.add(new internships("SDE", "SD","arnav@gmail.com","Full time", "Amazon", 10000,  2));
  
        // repeat as long as exit 
        //System.out.println("\t\t*** WELCOME TO STUDENT ALUMNI SYSTEM ***\n");
        
        System.out.println("\t- Create student account\n\t- Create alumni account\n\t- Student sign-in\n\t- Alumni sign-in\n\t- Exit");
        while (true) {
        	String str, str_send, str_recived;
        	
        	str_recived = fromServer.readLine(); 
            System.out.println(str_recived);
        	str_send = input.readLine();
        	str = str_send;
        	if(str_send.equalsIgnoreCase("Exit"))
				break;
        	toServer.writeBytes(str_send + "\n"); 
     
            String str1 = str.toLowerCase();
        	switch(str1) {
        	case "options":
        		//System.out.println("\t- Create student account\n\t- Create alumni account\n\t- Student sign-in\n\t- Alumni sign-in\n\t- Exit");
        		break;
        	case "student sign-in": 
        		str_recived = fromServer.readLine(); 		//enter email
    			System.out.println(str_recived);
        		str_send = input.readLine();
        		toServer.writeBytes(str_send + "\n");	//to server
        		toServer.writeBytes("Email has been entered.\n");	//to server
        		
        		str_recived = fromServer.readLine(); 		//enter password
    			System.out.println(str_recived);
        		str_send = input.readLine();	
        		toServer.writeBytes(str_send + "\n");	//to server
        		
        		System.out.println("..Waiting for the server to verify your account..");			// validation from server 
        		str_recived = fromServer.readLine();
        		if (str_recived.equalsIgnoreCase("Verified") ) {
        			str_recived = fromServer.readLine(); 
        			System.out.println(str_recived);		//server will say _ You have successfully signed in!
        			
        			while(true) {
        				
        				System.out.println("\tView:\n\t- List of all alumni\n\t- List of internships\n\t- Profile\n\t- Delete profile\n\t- Log out");
        				
        				str_recived = fromServer.readLine(); 
            			System.out.println(str_recived);
            			str_send = input.readLine();
        				if(str_send.equalsIgnoreCase("Log out")) {
        					toServer.writeBytes(str_send + "\n");
        					break;
        				}
        				toServer.writeBytes(str_send + "\n");
        				String str3 = str_send.toLowerCase();
        				switch(str3) {
        				case "list of all alumni":
        					//User.alum_display();
        					System.out.println("\tName|\t\t\tDomain|\t\t\tEmail id|\t\tGraduation year|\t\tExperience|");
        					ResultSet alumni=st.executeQuery("select * from student");
        					while(alumni.next())
        					{
        					System.out.print(alumni.getString(1)+" ");
        					System.out.print(alumni.getString(2)+" ");
        					System.out.print(alumni.getString(3)+" ");
        					System.out.print(alumni.getString(4)+" ");
        					System.out.print(alumni.getString(5)+" ");
        					System.out.print(alumni.getString(6)+" ");

        					System.out.println("\n");
        					}
        					break;
        					
        				case "list of internships":
        					//User.internships_display();
        					System.out.println("Job title\t\tDomain\t\t\tEmail id\t\t\tDescription\t\t\tCompany\t\tStipend\t\tExperience");
        					ResultSet internship =st.executeQuery("select * from student");
        					while(internship.next())
        					{
        					System.out.print(internship.getString(1)+" ");
        					System.out.print(internship.getString(2)+" ");
        					System.out.print(internship.getString(3)+" ");
        					System.out.print(internship.getString(4)+" ");
        					System.out.print(internship.getString(5)+" ");
        					System.out.print(internship.getString(6)+" ");
        					System.out.print(internship.getString(7)+" ");
        					System.out.println("\n");
        					}
        					break;
        				}
        			}
        			}
        		
        		else {
        			str_recived = fromServer.readLine(); // access denied
        			System.out.println(str_recived);
        			break;
        		}
        		break;
        			
        	case "alumni sign-in": 
        		
        		
        	
        		alumni_sign_in_page alumni_signin = new alumni_sign_in_page();
        		ArrayList<String> arr = alumni_signin.main(new String[]{});
	    		
        		for(int i = 0; i < arr.size(); i++) {   
        		    System.out.print(arr.get(i));
        		}  
	    		
	    		System.out.println("..Waiting for the server to verify your account..");			// validation from server 
        		
	    		str_recived=fromServer.readLine();
	    		System.out.println(str_recived+" \n");
	    		if (str_recived.equalsIgnoreCase("Verified") ) {
	    			
	    					//server will say _ You have successfully signed in!
	    			
	    			while(true) {
	    				System.out.println("\t- Look for all alumni\n\t- Post an internship\n\t- Log out");
	    				str_recived = fromServer.readLine(); 
            			System.out.println(str_recived);
            			str_send = input.readLine();
            			String in_al = str_send;
        				if(in_al.equalsIgnoreCase("Log out")) {
        					toServer.writeBytes(str_send + "\n");
        					System.out.println("before break");
        					break;
        				}
        				toServer.writeBytes(str_send + "\n");
        				String str5 = in_al.toLowerCase();
	    	        	switch(str5) {
	    	        	case "look for all alumni":
	    	        		System.out.println("\tName|\t\t\tDomain|\t\t\tEmail id|\t\tGraduation year|\t\tExperience|");
	    	        		ResultSet rs=st.executeQuery("select * from alumni");
	    	        		while(rs.next())
	    	        		{
	    	        		System.out.print(rs.getString(1)+" ");
	    	        		System.out.print(rs.getString(2)+" ");
	    	        		System.out.print(rs.getString(3)+" ");
	    	        		System.out.print(rs.getString(4)+" ");
	    	        		System.out.print(rs.getString(5)+" ");
	    	        		System.out.print(rs.getString(6)+" ");

	    	        		System.out.println("\n");
	    	        		}
	    	        		//User.alum_display(); 
	    	        		/*
							 * while(true) { str_recived = fromServer.readLine(); if
							 * (str_recived.equalsIgnoreCase("That's all")) break; taking_alumni inte =
							 * (taking_alumni)objFromServer.readObject();
							 * System.out.printf("%-23s %-23s %-23s %-23s %-23d %-23d \n",inte.fname,inte.
							 * lname,inte.domain,inte.mailId,inte.experience,inte.year);
							 * System.out.println();
							 * 
							 * }
							 */
	    	        		break;
	    	        	case "post an internship":
	    	        		String job_title,domain,mailId,description,Company;
	    	        		int stipend=0,experience=0; 
	    	        		
	    	        		str_recived = fromServer.readLine(); 		//job title
	    	    			System.out.println(str_recived);
	    	    			job_title = input.readLine();
	    	    			toServer.writeBytes("Title has been entered.\n");
	    	    			str_recived = fromServer.readLine(); 		//domain
	    	    			System.out.println(str_recived);
	    	    			domain = input.readLine();
	    	    			toServer.writeBytes("Domain has been entered.\n");
	    	    			str_recived = fromServer.readLine(); 		//company name 
	    	    			System.out.println(str_recived);
	    	    			Company = input.readLine();
	    	    			toServer.writeBytes("Company name has been entered.\n");
	    	    			str_recived = fromServer.readLine(); 		//description
	    	    			System.out.println(str_recived);
	    	    			description = input.readLine();
	    	    			toServer.writeBytes("Description has been entered.\n");
	    	    			str_recived = fromServer.readLine(); 		//exp required
	    	    			System.out.println(str_recived);
	    	    			experience = Integer.parseInt(input.readLine());
	    	    			toServer.writeBytes("Exp. number has been entered.\n");
	    	    			str_recived = fromServer.readLine(); 		//stipend 
	    	    			System.out.println(str_recived);
	    	    			stipend = Integer.parseInt(input.readLine());
	    	    			toServer.writeBytes("Stipend has been entered.\n");
	    	    			str_recived = fromServer.readLine(); 		//email id for contacting
	    	    			System.out.println(str_recived);
	    	    			mailId = input.readLine();
	    	    			toServer.writeBytes("Email has been entered.\n");
	    	        		
	    	    			int internship = st.executeUpdate("insert into internship values (job_title,domain,mailId,description,Company,stipend,experience)");
	    	    			//data2.add(new internships(job_title,domain,mailId,description,Company,stipend,experience));
	    	        		internships obj = new internships(job_title,domain,mailId,description,Company,stipend,experience);
	    	        		objToServer.writeObject(obj);
	    	        		
	    	        		toServer.writeBytes("New internhsip added!\n");
	    	    			str_recived = fromServer.readLine(); 		//internship created successfully
	    	    			System.out.println(str_recived);
	    	        		break;
	    	        	}
	    			}
	    			//System.out.println("before break 2");
	    			break;
	    		}
	    		else {
	    			str_recived = fromServer.readLine(); // access denied
	    			System.out.println(str_recived);
	    			break;
	    		}
	    	
	    		
    			
        	case "create student account": 
        		String fname, MailId, Engg_field;
        		String lname;
        		int password=0;
        		str_recived = fromServer.readLine(); 		//first
    			System.out.println(str_recived);
    			fname = input.readLine();
    			toServer.writeBytes("First name has been entered.\n");
    			str_recived = fromServer.readLine(); 		//last
    			System.out.println(str_recived);
    			lname = input.readLine();
    			toServer.writeBytes("Last name has been entered.\n");
    			str_recived = fromServer.readLine(); 		//email id
    			System.out.println(str_recived);
    			MailId = input.readLine();
    			toServer.writeBytes("Email has been entered.\n");
    			str_recived = fromServer.readLine(); 		//branch
    			System.out.println(str_recived);
    			Engg_field = input.readLine();
    			toServer.writeBytes("Branch has been entered.\n");
    			str_recived = fromServer.readLine(); 		//password
    			System.out.println(str_recived);
    			boolean isnumeric;
    			do
    			{
    				try
    				{
    					password = Integer.parseInt(input.readLine());
    					isnumeric = true;
    				}catch(NumberFormatException e)
    				{
    					isnumeric = false;
    					System.out.println("Invalid Input!");
    				}
    			}while(!isnumeric);
    			toServer.writeBytes("Password has been entered.\n");
    			
    			int student = st.executeUpdate("insert into student values (fname,lname,MailId,Engg_field,password)");
    			//data.add(new student(fname,lname,MailId,Engg_field,password));
    			student obj = new student(fname, lname, MailId,Engg_field, password);
    			objToServer.writeObject(obj);					//pass the obj
    			
    			toServer.writeBytes("Account details saved!\n");
    			str_recived = fromServer.readLine(); 		//account successfully created
    			System.out.println(str_recived);
    			break;
    			
        	case "create alumni account": 	
        		String fname1, lname1, mailId, domain;
        		int year=0, experience=0;
        		int password1 = 0;
        		str_recived = fromServer.readLine(); 		//first
    			System.out.println(str_recived);
    			fname1 = input.readLine();
    			toServer.writeBytes("First name has been entered.\n");
    			str_recived = fromServer.readLine(); 		//last
    			System.out.println(str_recived);
    			lname1 = input.readLine();
    			toServer.writeBytes("Last name has been entered.\n");
    			str_recived = fromServer.readLine(); 		//email id
    			System.out.println(str_recived);
    			mailId = input.readLine();
    			toServer.writeBytes("Email has been entered.\n");
    			str_recived = fromServer.readLine(); 		//domain
    			System.out.println(str_recived);
    			domain = input.readLine();
    			toServer.writeBytes("Domain has been entered.\n");
    			str_recived = fromServer.readLine(); 		//graduation year
    			System.out.println(str_recived);
    			year = Integer.parseInt(input.readLine());
    			toServer.writeBytes("Graduation year has been entered.\n");
    			str_recived = fromServer.readLine(); 		//experience year
    			System.out.println(str_recived);
    			experience = Integer.parseInt(input.readLine());
    			toServer.writeBytes("Number of experience years has been entered.\n");
    			str_recived = fromServer.readLine(); 		//Password
    			System.out.println(str_recived);
    			password1 = Integer.parseInt(input.readLine());
    			toServer.writeBytes("Password has been entered.\n");
    			
    			int alumni = st.executeUpdate("insert into alumni values (fname1,lname1,mailId,domain,year,experience, password1)");
    			//data1.add(new alumni(fname1,lname1,mailId,domain,year,experience, password1));
    			alumni obj1 = new alumni(fname1, lname1, mailId, domain, year, experience, password1);
    			objToServer.writeObject(obj1);
    			
    			toServer.writeBytes("Account details saved!\n");
    			str_recived = fromServer.readLine(); 		//account successfully created
    			System.out.println(str_recived);
    			break;
        	}
        } 
  
        //rs.close();
		st.close();
		con.close();
        // close connection. 
  
        objFromServer.close();
        objToServer.close();
        toServer.close(); 
        fromServer.close(); 
        input.close(); 
        socket.close(); 
    } 
} 



