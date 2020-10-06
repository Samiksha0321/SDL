import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.*;

public class ClientMain {
    
    @SuppressWarnings("unchecked")
	public static void main(String args[]) throws Exception {
		
    	Socket socket=new Socket("127.0.0.1",5000);
		BufferedReader fromServer=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		ObjectInputStream objFromServer = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objToServer = new ObjectOutputStream(socket.getOutputStream());
		
		
		
		
		String str, str_send, str_recived;
		
		while (true) {
			System.out.println("\t1. Create student account\n\t2. Create alumni account\n\t3. Student sign-in\n\t4. Alumni sign-in\n\t5. Exit");
			System.out.print("Client : ");
			
			str_send = input.readLine();
        	str = str_send;
        	if(str_send.equalsIgnoreCase("Exit")) {
        		System.out.println("Connection ended by client");
        		break;
        	}
        	toServer.writeBytes(str_send + "\n"); 
        	
        	String str1 = str.toLowerCase();
        	switch(str1) {
        	
        	case "student sign-in": 
        		System.out.println("Enter email: ");
        		String email = input.readLine();
        		System.out.println("Enter password: ");
        		String password = input.readLine();	
        		
    			Hashtable<String,String> student_creds = new Hashtable<String,String>();
    			student_creds.put("email", email);
    			student_creds.put("password", password);
    			objToServer.writeObject(student_creds);
    			
        		System.out.println("..Waiting for the server to verify your account..");			// validation from server 
        		str_recived = fromServer.readLine();
        		if (str_recived.equalsIgnoreCase("Verified") ) {
        			System.out.println("Successfully signed in!");
        			
        			while(true) {
        				
        				System.out.println("\tView:\n\t- List of all alumni\n\t- List of internships\n\t- Log out");
        				
            			str_send = input.readLine();
        				if(str_send.equalsIgnoreCase("Log out")) {
        					toServer.writeBytes(str_send + "\n");
        					break;
        				}
        				toServer.writeBytes(str_send + "\n");
        				String str3 = str_send.toLowerCase();
        				switch(str3) {
        				
        				case "list of all alumni":
        					System.out.println("**List of all alumni**");
        					System.out.print("\n");
        					System.out.println("\tF_Name|\t\tL_Name|\t\tEmail_Id|\t\tDomain|\t\tGraduation year|\t\tExperience|");
        					int count1 = Integer.parseInt(fromServer.readLine());
        					LinkedList<String> alum = (LinkedList<String>) objFromServer.readObject();
        					while(count1>0) {
        						System.out.print("\t");
        						System.out.print(alum.poll());
        						System.out.print("\t\t");
        						System.out.print(alum.poll());
        						System.out.print("\t\t");
        						System.out.print(alum.poll());
        						System.out.print("\t\t");
        						System.out.print(alum.poll());
        						System.out.print("\t\t");
        						System.out.print(alum.poll());
        						System.out.print("\t\t");
        						System.out.print(alum.poll());
        						System.out.print("\n");
        						count1--;
        					}
        					
        					System.out.println("\n");
        					break;
        					
        				case "list of internships":
        					System.out.println("**List of all available internships**");
        					System.out.print("\n");
        					int count2 = Integer.parseInt(fromServer.readLine());
        					LinkedList<String> inter = (LinkedList<String>) objFromServer.readObject();
        					while(count2>0) {
        						System.out.print("Job title: ");
        						System.out.print(inter.poll());
        						System.out.print("\nDomain: ");
        						System.out.print(inter.poll());
        						System.out.print("\nContact email: ");
        						System.out.print(inter.poll());
        						System.out.print("\nDescription: ");
        						System.out.print(inter.poll());
        						System.out.print("\nCompany: ");
        						System.out.print(inter.poll());
        						System.out.print("\nStipend: ");
        						System.out.print(inter.poll());
        						System.out.print("\nMin exp required(in years): ");
        						System.out.print(inter.poll());
        						System.out.print("\n---------------------");
        						System.out.print("\n");
        						count2--;
        					}
        					
        					System.out.println("\n");
        					break;
        				}
        			}
        		}
        		else {
        			str_recived = fromServer.readLine(); // access denied
        			System.out.println(str_recived);
        		}
        		break;
        			
        	case "alumni sign-in": 
        		System.out.println("Enter email: ");
        		String email1 = input.readLine();
        		System.out.println("Enter password: ");
        		String password1 = input.readLine();	
        		
        		Hashtable<String,String> alumni_creds = new Hashtable<String,String>();
        		alumni_creds.put("email", email1);
        		alumni_creds.put("password", password1);
    			objToServer.writeObject(alumni_creds);
    			
        		System.out.println("..Waiting for the server to verify your account..");			// validation from server 
        		str_recived = fromServer.readLine();
        		if (str_recived.equalsIgnoreCase("Verified") ) {
        			System.out.println("Successfully signed in!");
        			
        			while(true) {
        				
        				System.out.println("\t- List all alumni\n\t- Post an internship\n\t- Log out");
        				
            			str_send = input.readLine();
        				if(str_send.equalsIgnoreCase("Log out")) {
        					toServer.writeBytes(str_send + "\n");
        					break;
        				}
        				toServer.writeBytes(str_send + "\n");
        				String str3 = str_send.toLowerCase();
        				switch(str3) {
        				
        				case "list of all alumni":
        					System.out.println("**List of all available internships**");
        					System.out.print("\n");
        					System.out.println("\tF_Name|\t\tL_Name|\t\tEmail_Id|\t\tDomain|\t\tGraduation year|\t\tExperience|");
        					int count1 = Integer.parseInt(fromServer.readLine());
        					LinkedList<String> alum = (LinkedList<String>) objFromServer.readObject();
        					while(count1>0) {
        						System.out.print("\t");
        						System.out.print(alum.poll());
        						System.out.print("\t\t");
        						System.out.print(alum.poll());
        						System.out.print("\t\t");
        						System.out.print(alum.poll());
        						System.out.print("\t\t");
        						System.out.print(alum.poll());
        						System.out.print("\t\t");
        						System.out.print(alum.poll());
        						System.out.print("\t\t");
        						System.out.print(alum.poll());
        						System.out.print("\n");
        						count1--;
        					}
        					
        					System.out.println("\n");
        					break;
        					
        				case "post an internship":
        					
        					LinkedList<String> intern = new LinkedList<String>();
        					
        					System.out.println("Job title: ");
	    	    			String job_title = input.readLine();
	    	    			intern.add(job_title);
	    	    			System.out.println("Domain: ");
	    	    			String domain = input.readLine();
	    	    			intern.add(domain);
	    	    			System.out.println("Contact email: ");
	    	    			String mailId = input.readLine();
	    	    			intern.add(mailId);
	    	    			System.out.println("Description: ");
	    	    			String description = input.readLine();
	    	    			intern.add(description);
	    	    			System.out.println("Company: ");
	    	    			String Company = input.readLine();
	    	    			intern.add(Company);
	    	    			System.out.println("Stipend: ");
	    	    			String stipend = input.readLine();
	    	    			intern.add(stipend);
	    	    			System.out.println("Min exp required(in years): ");
	    	    			String experience = input.readLine();
	    	    			intern.add(experience);
	    	    			
	    	    			objToServer.writeObject(intern);
	    	    			
        					break;
        				}
        			}
        		}
        		else {
        			str_recived = fromServer.readLine(); // access denied
        			System.out.println(str_recived);
        		}
        		break;
	    		
    			
        	case "create student account": 

        		LinkedList<String> stud_create = new LinkedList<String>();
        		
        		System.out.println("First name: ");
    			String fname = input.readLine();
    			stud_create.add(fname);
        		System.out.println("Last name: ");
    			String lname = input.readLine();
    			stud_create.add(lname);
    			System.out.println("Email: ");
    			String MailId = input.readLine();
    			stud_create.add(MailId);
    			System.out.println("Branch: ");
    			String Engg_field = input.readLine();
    			stud_create.add(Engg_field);
    			System.out.println("Password: ");
    			String password11 = input.readLine();
    			stud_create.add(password11);
        		
    			objToServer.writeObject(stud_create);
    			System.out.println("Account details saved!\n");
    			break;
    			
        	case "create alumni account": 	

        		LinkedList<String> alumni_create = new LinkedList<String>();
        		
        		System.out.println("First name: ");
    			String fname1 = input.readLine();
    			alumni_create.add(fname1);
        		System.out.println("Last name: ");
    			String lname1 = input.readLine();
    			alumni_create.add(lname1);
    			System.out.println("Email: ");
    			String mailId = input.readLine();
    			alumni_create.add(mailId);
    			System.out.println("Current Domain: ");
    			String domain = input.readLine();
    			alumni_create.add(domain);
    			System.out.println("Graduation year: ");
    			String year = input.readLine();
    			alumni_create.add(year);
    			System.out.println("Experience in the current domain(in years): ");
    			String experience = input.readLine();
    			alumni_create.add(experience);
    			System.out.println("Password: ");
    			String password111 = input.readLine();
    			alumni_create.add(password111);
        		
    			objToServer.writeObject(alumni_create);
    			System.out.println("Account details saved!\n");
    			break;
    		
        	}
        	
        	
  			
		}
		objFromServer.close();
        objToServer.close();
        toServer.close(); 
        fromServer.close(); 
        input.close(); 
        socket.close(); 
	}
    
}
