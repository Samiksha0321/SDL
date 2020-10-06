import java.io.Serializable;

public class student implements Serializable{
	String fname, MailId, Engg_field;
	String lname;
	int password;
	
	student(String fname,String lname,String MailId,String Engg_field,int password)
	{
		this.fname = fname;
		this.lname = lname;
		this.MailId = MailId;
		this.Engg_field = Engg_field;
		this.password = password;

	}

	public String get_fname()
	{
		return this.fname;
	}
	public String get_lname()
	{
		return this.lname;
	}
	public String get_MailId()
	{
		return this.MailId;
	}
	public String get_Engg_field()
	{
		return this.Engg_field;
	}
	public int password()
	{
		return this.password;
	}
	protected void new_fname(String fname)
	{
		this.fname = fname;
	}
	protected void new_lname(String lname)
	{
		this.lname = lname;
	}
	protected void new_Engg_Field(String engg)
	{
		this.Engg_field = engg;
	}
	protected void password(int password)
	{
		this.password = password;
	}
}
