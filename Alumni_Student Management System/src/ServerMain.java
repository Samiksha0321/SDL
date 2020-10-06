import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

	int port;
    ServerSocket server = null;
    Socket client = null;
    ExecutorService executorService = null;
    int clientcount = 0;
    static Statement st = null;
    
    
    public static void main(String[] args) throws Exception {
    	ServerMain serverobj=new ServerMain(5000);
        serverobj.startServer();
    }
    
    ServerMain(int port){
        this.port=port;
        executorService = Executors.newFixedThreadPool(5);
    }

    public void startServer() throws IOException, Exception {
        
        server=new ServerSocket(5000);
        System.out.println("Server ready for connections");
        while(true)
        {
            client=server.accept();
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student_alumni_database","root","123654");
      		//st = con.createStatement();
            clientcount++;
            ServerThread runnable= new ServerThread(client,clientcount,this);            
            executorService.execute(runnable);
        }
        
    }

    private static class ServerThread implements Runnable {
        
    	Socket client=null;
        int id;
        
        ServerThread(Socket client, int count ,ServerMain server ) throws IOException, Exception {
        	
            this.client=client;
            this.id=count;
            System.out.println("Connection "+id+"established with client "+client);
            
           //JDBC connectivity
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student_alumni_database","root","123654");
            st = con.createStatement();
        
        }

        @SuppressWarnings("unchecked")
		@Override
        public void run() {
        	int x=1;
        	
        	try{
        		while(true){
        			BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        			PrintStream toClient = new PrintStream (client.getOutputStream());
        	        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        	        ObjectOutputStream objToClient = new ObjectOutputStream(client.getOutputStream());
        	        ObjectInputStream objFromClient = new ObjectInputStream(client.getInputStream());
        			
        	        String str_send, str_recived;
        			
        	        while(true) {
        	        	System.out.println("Start of the server");
            	        str_recived = fromClient.readLine(); 
                        if (str_recived.equalsIgnoreCase("Exit"))
                        {
                            toClient.println("BYE"); 
                            x=0;
                            System.out.println("Connection ended by client.");
                            objToClient.close();
                            objFromClient.close();
                            toClient.close(); 
                            fromClient.close(); 
                            input.close(); 
                            break;
                        }
                        

                        String str1 = str_recived.toLowerCase();
                    	switch(str1) {
                    	case "student sign-in": 
                    		System.out.println("*Client wants to Sign-in as a Student*");
                    		Hashtable<String,String> student_creds = (Hashtable<String,String>) objFromClient.readObject();
                    		String user = student_creds.get("email");
            				String pass = student_creds.get("password"); 
            				System.out.println("..Verifying..");    // verify the password
            				
            				if (user != null && pass != null) {
            		            String sql = "Select * from student where EMAIL='" + user + "' and PASSWORD='" + pass + "'";
            		            ResultSet rs = st.executeQuery(sql);
            		            if (rs.next()) {
            		            	System.out.println("Studnt login successful!");
            		                str_send = "Verified";
            		                toClient.println(str_send); 		//Verified
            		                
            		                while(true) {
            		                	str_recived = fromClient.readLine();
    					        		if(str_recived.equalsIgnoreCase("Log out")) {
    					        			
    					        			System.out.println("Student logged out!");
    					        			break;
    					        		}	
    					        		System.out.println(str_recived);
    					        		String str3 = str_recived.toLowerCase();
    			        				switch(str3) {
    			        				case "list of all alumni":
    			        					
    			        					LinkedList<String> alum = new LinkedList<String>();
    			        					ResultSet alumni=st.executeQuery("select FNAME, LNAME,  EMAIL, DOMAIN, GRAD_YR, EXP from alumni");
    			        					int count1=0;
    			        					while(alumni.next()) {
    			        						alum.add(alumni.getString(1));
    			        						alum.add(alumni.getString(2));
    			        						alum.add(alumni.getString(3));
    			        						alum.add(alumni.getString(4));
    			        						alum.add(alumni.getString(5));
    			        						alum.add(alumni.getString(6));
    			        						count1++;
    			        					}
    			        					str_send = Integer.toString(count1);
    		        		                toClient.println(str_send);
    		        		                objToClient.writeObject(alum);
    		        		                System.out.println("All alumni are being displayed.");
    			        					
    			        					break;
    			        				
    			        				case "list of internships":
    			        					LinkedList<String> inter = new LinkedList<String>();
    			        					ResultSet internship=st.executeQuery("select * from internship");
    			        					int count2=0;
    			        					while(internship.next()) {
    			        						inter.add(internship.getString(1));
    			        						inter.add(internship.getString(2));
    			        						inter.add(internship.getString(3));
    			        						inter.add(internship.getString(4));
    			        						inter.add(internship.getString(5));
    			        						inter.add(internship.getString(6));
    			        						inter.add(internship.getString(7));
    			        						count2++;
    			        					}
    			        					str_send = Integer.toString(count2);
    		        		                toClient.println(str_send);
    		        		                objToClient.writeObject(inter);
    		        		                System.out.println("All internships are being displayed.");
    		        		                
    			        					break;
    			        				}
            		                }
            		              
            		            } else {
            		                System.out.println("Wrong Credentials");
            		            }
            				}

        			        // You can also validate user by result size if its comes zero user is invalid else user is valid
            				
                    		break;
                    		
                    	case "alumni sign-in": 	
                    		System.out.println("*Client wants to Sign-in as an Alumni*");
                    		
                    		Hashtable<String,String> alumni_creds = (Hashtable<String,String>) objFromClient.readObject();
                    		String user1 = alumni_creds.get("email");
            				String pass1 = alumni_creds.get("password"); 
            				System.out.println("..Verifying..");    // verify the password
            				
            				if (user1 != null && pass1 != null) {
            		            String sql = "Select * from alumni where EMAIL='" + user1 + "' and PASSWORD='" + pass1 + "'";
            		            ResultSet rs = st.executeQuery(sql);
            		            if (rs.next()) {
            		            	System.out.println("Alumni login successful!");
            		                str_send = "Verified";
            		                toClient.println(str_send); 		//Verified
            		                
            		                while(true) {
            		                	str_recived = fromClient.readLine();
    					        		if(str_recived.equalsIgnoreCase("Log out")) {
    					        			
    					        			System.out.println("Student logged out!");
    					        			break;
    					        		}	
    					        		System.out.println(str_recived);
    					        		String str3 = str_recived.toLowerCase();
    			        				switch(str3) {
    			        				case "list of all alumni":
    			        					
    			        					LinkedList<String> alum = new LinkedList<String>();
    			        					ResultSet alumni=st.executeQuery("select FNAME, LNAME,  EMAIL, DOMAIN, GRAD_YR, EXP from alumni");
    			        					int count1=0;
    			        					while(alumni.next()) {
    			        						alum.add(alumni.getString(1));
    			        						alum.add(alumni.getString(2));
    			        						alum.add(alumni.getString(3));
    			        						alum.add(alumni.getString(4));
    			        						alum.add(alumni.getString(5));
    			        						alum.add(alumni.getString(6));
    			        						count1++;
    			        					}
    			        					str_send = Integer.toString(count1);
    		        		                toClient.println(str_send);
    		        		                objToClient.writeObject(alum);
    		        		                System.out.println("All alumni are being displayed.");
    			        					
    			        					break;
    			        				
    			        				case "post an internship":
    			        					
    			        					LinkedList<String> intern = (LinkedList<String>) objFromClient.readObject();
    			        					String job_title = intern.poll();
    			        					String Domain = intern.poll();
    			        					String email = intern.poll();
    			        					String description = intern.poll();
    			        					String Company = intern.poll();
    			        					String stipend = intern.poll();
    			        					String exp_req = intern.poll();
    			        					String stri = "insert into internship value ('"+ job_title +"','"+ Domain +"','"+ email +"','"+ description +"','"+ Company +"','"+ stipend +"','"+ exp_req +"')";
    			        					int i=st.executeUpdate(stri);
    			        					System.out.println("Internship added successfully!");
    			        					break;
    			        				}
            		                }
            		              
            		            } else {
            		                System.out.println("Wrong Credentials");
            		            }
            				}
            				break;
                    		
                    	case "create student account":
                    		System.out.println("*Client wants to Create new Student account*");
                    		
                    		LinkedList<String> stud_create = (LinkedList<String>) objFromClient.readObject();
        					String fname = stud_create.poll();
        					String lname = stud_create.poll();
        					String MailId = stud_create.poll();
        					String Engg_field = stud_create.poll();
        					String password11 = stud_create.poll();
        					String stri = "insert into student value ('"+ fname +"','"+ lname +"','"+ MailId +"','"+ Engg_field +"','"+ password11 +"')";
        					int i = st.executeUpdate(stri);
        					System.out.println("Student account created successfully!");
                    		break;
                    		
                    	case "create alumni account": 
                    		System.out.println("*Client wants to Create new Alumni account*");
                    		
                    		LinkedList<String> intern = (LinkedList<String>) objFromClient.readObject();
        					String fname1 = intern.poll();
        					String lname1 = intern.poll();
        					String mailId = intern.poll();
        					String domain = intern.poll();
        					String year = intern.poll();
        					String experience = intern.poll();
        					String password111 = intern.poll();
        					String stri1 = "insert into alumni value ('"+ fname1 +"','"+ lname1 +"','"+ mailId +"','"+ domain +"','"+ year +"','"+ experience +"','"+ password111 +"')";
        					int j = st.executeUpdate(stri1);
        					System.out.println("Alumni account created successfully!");
        					break;
                    	}
        	        }
                    
                    break;
        		}
        		
        		client.close();
        		if(x==0) {
        			System.out.println( "Server cleaning up." );
        			System.exit(0);
        		}
        	} 
        	catch(IOException ex){
            System.out.println("Error : "+ex);
        	} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
}
