import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import java.util.concurrent.TimeUnit;

public class alumni_sign_in_page extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	static String name, password;
	 ArrayList<String> credentials = new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	static alumni_sign_in_page frame;
	int flag=0;
	
	 public  ArrayList<String> main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				  //frame = new alumni_sign_in_page();
					setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//credentials.add(frame.name);
		//credentials.add(frame.password);
		int i=0;
		while(true)
		{
			try {
				TimeUnit.SECONDS.sleep(5);
				//System.out.println(i);
				//i++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag==1)
				break;
		}
			System.out.println("flag"+ flag);
			System.out.println(credentials);
			//System.out.println("frame " + frame.credentials);
			return(credentials);
	
		/*else
		{
			System.out.println("flag"+ flag);
			System.out.println(credentials);
			return (credentials);
		}*/
		
	}
	
	/**
	 * Create the frame.
	 */
	public alumni_sign_in_page() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Alumni Sign-in");
		lblNewLabel.setBounds(141, 30, 151, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Email :");
		lblNewLabel_1.setBounds(72, 95, 99, 17);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField_2 = new JTextField();
		textField_2.setBounds(167, 96, 203, 19);
		textField_2.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(72, 144, 69, 17);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField_1 = new JTextField();
		textField_1.setBounds(167, 145, 203, 19);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(216, 199, 99, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name=textField_2.getText();
				password=textField_1.getText();
				System.out.println("inner"+name);
				System.out.println("inner"+password);
				flag=1;
				credentials.add(name);
				credentials.add(password);
				System.out.println(credentials);
				flag=1;
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);
		contentPane.add(textField_2);
		contentPane.add(lblPassword);
		contentPane.add(textField_1);
		contentPane.add(btnNewButton);
	}

}
