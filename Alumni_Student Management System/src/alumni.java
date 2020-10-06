import java.io.Serializable;

public class alumni implements Serializable{
	private String fname,lname, mailId, domain;
	private int year,experience;
	int password;
	alumni(String fname,String lname,String mailId,String domain,int experience,int year, int password)
	{
		this.fname = fname;
		this.lname = lname;
		this.mailId = mailId;
		this.domain = domain;
		this.experience = experience;
		this.year = year;
		this.password = password;
	}
	public String get_mailId()
	{
		return this.mailId;
	}
	public String get_fname()
	{
		return this.fname;
	}
	public String get_lname()
	{
		return this.lname;
	}
	public String get_domain()
	{
		return this.domain;
	}
	public int get_experience()
	{
		return this.experience;
	}
	public int get_year()
	{
		return this.year;
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
	protected void new_domain(String domain)
	{
		this.domain = domain;
	}
	protected void new_experience(int experience)
	{
		this.experience = experience;
	}
	protected void password(int password)
	{
		this.password = password;
	}
	protected void new_mailId(String mailId)
	{
		this.mailId = mailId;
	}
	protected void new_year(int year)
	{
		this.year = year;
	}
}

