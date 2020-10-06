import java.io.Serializable;

public class internships implements Serializable{
	String job_title,domain,mailId,description,Company;
	int stipend,experience;
	internships(String job_title,String domain,String mailId,String description,String Company,int stipend,int experience)
	{
		this.job_title = job_title;
		this.domain = domain;
		this.mailId = mailId;
		this.description = description;
		this.Company = Company;
		this.stipend = stipend;
		this.experience = experience;	
	}
	public String get_mailId()
	{
		return this.mailId;
	}
	public String get_job_title()
	{
		return this.job_title;
	}
	public String get_description()
	{
		return this.description;
	}
	public String get_domain()
	{
		return this.domain;
	}
	public String get_Company()
	{
		return this.Company;
	}
	public int get_experience()
	{
		return this.experience;
	}
	public int get_stipend()
	{
		return this.stipend;
	}
}

