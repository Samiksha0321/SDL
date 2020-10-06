import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner; 
class server extends User { 
	private static ServerSocket serverSocket;
	private static Socket socket;
	private static PrintStream  toClient;
	private static BufferedReader fromClient; 
	private static ObjectInputStream objFromClient;
	private static ObjectOutputStream objToClient;
	private static BufferedReader input;
	
    @SuppressWarnings("unused")
	public static void main(String args[]) throws Exception { 
    	serverSocket = new ServerSocket(8234); 
    	socket = serverSocket.accept();
		System.out.println("Connected to:" + socket);        // connect it to client socket 
        System.out.println("Connection established"); 
        
        objFromClient = new ObjectInputStream(socket.getInputStream());
        
        objToClient = new ObjectOutputStream(socket.getOutputStream());
        
        toClient = new PrintStream (socket.getOutputStream());
        fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		input = new BufferedReader(new InputStreamReader(System.in));
  
		//direcct obj
		data.add(new student("Sam", "J", "sam", "cs", 123));
		data1.add(new alumni("Arnav", "L", "arnav", "machine learning", 2010, 5, 123));
		data2.add(new internships("SDE", "SD","arnav@gmail.com","Full time", "Amazon", 10000,  2));
        // server executes continuously 
        while (true) { 
        	String str="", str_send="", str_recived=""; 
        	str_send = input.readLine();
        	if(str_send.equalsIgnoreCase("Exit"))
				break;
        	toClient.println(str_send); 
        	str_recived = fromClient.readLine(); 
            System.out.println(str_recived);     
            
            String str1 = str_recived.toLowerCase();
        	switch(str1) {
        	case "student sign-in": 
        		System.out.println("*Client wants to Sign-in as a Student*");
        		str_send = input.readLine();		//ask to enter email
        		toClient.println(str_send);
        		String name = fromClient.readLine();	//client entered email
        		str_recived = fromClient.readLine(); 
        		System.out.println(str_recived);  
        		
        		str_send = input.readLine();		//ask to enter password
        		toClient.println(str_send);
        		int roll = Integer.parseInt(fromClient.readLine());	//client entered password

				System.out.println("..Verifying..");    // verify the password
				for(student s: User.get_info())
				{
					if(s.get_MailId().equalsIgnoreCase(name)&&s.password()==roll)
					{
						try
						{
							System.out.printf("Account verified!\n");		
		        			toClient.println("Verified");
							str_send = input.readLine();		//tell the client that it is verified
		        			toClient.println(str_send);
							
			        		while(true) {
			        			str_send = input.readLine();		
			        			toClient.println(str_send);
				        		str_recived = fromClient.readLine();
				        		if(str_recived.equalsIgnoreCase("Log out")) {
				        			
				        			System.out.println("Student logged out!");
				        			break;
				        		}	
				        		System.out.println(str_recived);
				        		String str3 = str_recived.toLowerCase();
		        				switch(str3) {
		        				case "all alumni":
		        					
		        					PriorityQueue<alumni> al = new PriorityQueue<alumni>(new myComparator());
		        					for(ArrayList<alumni> l :segregate.Segregate(User.data1))
		        					{
		        						for(alumni a: l)
		        						{
		        							al.add(a);
		        						}
		        					}
		        					for(alumni o:al)
		        					{
		        						//objToClient.writeObject(o);
		        					}
		        					System.out.println("List of all alumni is being displayed.");
		        					break;
		        				case "domain wise":
		        					toClient.println("Select a domain:\n 1. Machine Learning\n 2. Blockchain\n 3. Cloud Computing\n 4. Data Science");
		        					str_recived = fromClient.readLine();
		        	        		System.out.println(str_recived);
		        	        		String domain = str_recived.toLowerCase();
		        	        		switch(domain) {
		        	        		case("machine learning"):
		        	        			PriorityQueue<alumni> machine_learning = new PriorityQueue<alumni>(new myComparator());
		        	        			for(alumni ml: data1)
		        	        			{	
		        	        				  if(ml.get_domain().equalsIgnoreCase("machine learning"))
		        	        				  machine_learning.add(ml);
		        	        			}
		        	        			for(alumni ml1:machine_learning)
		        	        			{
		        	        				//objToClient.writeObject(ml1);
		        	        			}
		        	        			System.out.println("Machine Learning is being displayed.");
		        	        			break;
		        	        		case("blockchain"):
		        	        			PriorityQueue<alumni> blockchain = new PriorityQueue<alumni>(new myComparator());
		        	        			for(alumni bc: data1)
		        	        			{	
		        	        				  if(bc.get_domain().equalsIgnoreCase("blockchain"))
		        	        				  blockchain.add(bc);
		        	        			}
		        	        			for(alumni bc1:blockchain)
		        	        			{
		        	        				//objToClient.writeObject(bc1);
		        	        			}
		        	        			System.out.println("Blockchain is being displayed.");
		        	        			break;
		        	        		case("cloud computing"):
		        	        			PriorityQueue<alumni> cloud_computing = new PriorityQueue<alumni>(new myComparator());
		        	        			for(alumni cc: data1)
		        	        			{	
		        	        				  if(cc.get_domain().equalsIgnoreCase("cloud computing"))
		        	        				  cloud_computing.add(cc);
		        	        			}
		        	        			for(alumni cc1:cloud_computing)
		        	        			{
		        	        				//objToClient.writeObject(cc1);
		        	        			}
		        	        			System.out.println("Cloud computing is being displayed.");
		        	        			break;
		        	        		case("data science"):
		        	        			PriorityQueue<alumni> data_science = new PriorityQueue<alumni>(new myComparator());
		        	        			  
		        	        			for(alumni ds: data1)
		        	        			{	
		        	        				  if(ds.get_domain().equalsIgnoreCase("data science"))
		        	        				  data_science.add(ds);
		        	        			}
		        	        			for(alumni ds1:data_science)
		        	        			{
		        	        				//objToClient.writeObject(ds1);
		        	        			}
		        	        			System.out.println("Data science is being displayed.");
		        	        			break;
		        	        		}
		        					break;
		        				case "list of internships":
		        					PriorityQueue<internships> internship = new PriorityQueue<internships>(new comparison());
		        					
		        					for(ArrayList<internships> l :segregate.order(User.data2))
		        					{
		        						for(internships in: l)
		        						{
		        							internship.add(in);
		        						}
		        					}
		        					for(internships oin:internship)
		        					{
		        						
		        						//objToClient.writeObject(oin);
		        					}
		        					//toClient.println("That's all");
		        					System.out.println("All internships are being displayed.");
		        					break;
		        				}
			        		}
			        		break;
						}
						catch(Exception e) { e.printStackTrace(); }
					}
					else if(!s.get_MailId().equalsIgnoreCase(name) || s.password()!=roll)
					{
						System.out.println("Invalid Credentials!!!! Try Again!!");
						toClient.println("Invalid credentials!");
						break;
					}
					else {
						System.out.println("No such account found!");
					}
				}
        		break;
        		
        	case "alumni sign-in": 	
        		System.out.println("*Client wants to Sign-in as an Alumni*");
        		str_send = input.readLine();		//ask to enter email
        		toClient.println(str_send);
        		String al_name = fromClient.readLine();	//client entered email
        		str_recived = fromClient.readLine(); 
        		System.out.println(str_recived);  
        		
        		str_send = input.readLine();		//ask to enter password
        		toClient.println(str_send);
        		int pass = Integer.parseInt(fromClient.readLine());
        		
        		System.out.println("..Verifying..");
        		for(alumni al: User.get_aluminfo())
				{
					if(al.get_mailId().equalsIgnoreCase(al_name)&&al.password()==pass)
					{
							System.out.printf("Account verified!\n");		
		        			toClient.println("Verified");
							
		        			
		        			while(true) {
		        				str_send = input.readLine();		
			        			toClient.println(str_send);
				        		str_recived = fromClient.readLine();
				        		String in_al = str_recived;
				        		if(in_al.equalsIgnoreCase("Log out")) {
				        			System.out.println("Student logged out!");
				        			break;
				        		}	
				        		System.out.println(str_recived);
				        		String str4 = in_al.toLowerCase();
				        		switch(str4) {
				        		case "look for all alumni":
				        			
				        		
				        			
				        			System.out.println("List of all alumni is being displayed.");
				        			break;
				        		case "post an internship":
				        			str_send = input.readLine();		//ask to enter job title
				            		toClient.println(str_send);
				            		str_recived = fromClient.readLine();
				            		System.out.println(str_recived);
				            		str_send = input.readLine();		//ask to enter domain
				            		toClient.println(str_send);
				            		str_recived = fromClient.readLine();
				            		System.out.println(str_recived);
				            		str_send = input.readLine();		//ask to enter company
				            		toClient.println(str_send);
				            		str_recived = fromClient.readLine();
				            		System.out.println(str_recived);
				            		str_send = input.readLine();		//ask to description
				            		toClient.println(str_send);
				            		str_recived = fromClient.readLine();
				            		System.out.println(str_recived);
				            		str_send = input.readLine();		//ask to exp
				            		toClient.println(str_send);
				            		str_recived = fromClient.readLine();
				            		System.out.println(str_recived);
				            		str_send = input.readLine();		//ask to add stipend
				            		toClient.println(str_send);
				            		str_recived = fromClient.readLine();
				            		System.out.println(str_recived);
				            		str_send = input.readLine();		//ask email
				            		toClient.println(str_send);
				            		str_recived = fromClient.readLine();
				            		System.out.println(str_recived);
				            		
				            		internships obj = (internships)objFromClient.readObject();
				            		data2.add(obj);
				            		
				            		str_recived = fromClient.readLine();
				            		System.out.println(str_recived);
				            		str_send = input.readLine();		//internship created successfully 
				            		toClient.println(str_send);
				        			break;
				        		}
		        			}
							
							break;
					}
					else if(!al.get_mailId().equalsIgnoreCase(al_name) || al.password()!=pass)
					{
						System.out.println("Unsuccessful Sign-in! Try Again!");
						break;
					}
				}
        		break;
        		
        		
        		
        	case "create student account":
        		System.out.println("*Client wants to Create new Student account*");
        		str_send = input.readLine();		//ask to enter fname
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//ask to enter lname
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//ask to enter email
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//ask to enter branch
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//ask to enter password
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		
        		student obj = (student)objFromClient.readObject();
        		data.add(obj);
        		
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//acc created successfully 
        		toClient.println(str_send);
        		break;
        		
        	case "create alumni account": 
        		System.out.println("*Client wants to Create new Alumni account*");
        		str_send = input.readLine();		//ask to enter fname
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//ask to enter lname
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//ask to enter email
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//ask to enter domain
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//ask to enter grad yr
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//ask to enter exp yr
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//ask to enter password
        		toClient.println(str_send);
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		
        		alumni obj1 = (alumni)objFromClient.readObject();
        		data1.add(obj1);
        		
        		str_recived = fromClient.readLine();
        		System.out.println(str_recived);
        		str_send = input.readLine();		//acc created successfully 
        		toClient.println(str_send);
        		break;
        	}
        } 
  
        // close connection 
        objToClient.close();
        objFromClient.close();
        toClient.close(); 
        fromClient.close(); 
        input.close(); 
        serverSocket.close(); 
        socket.close(); 
  
        // terminate application 
        System.exit(0); 
  
    } // end of while 
}
