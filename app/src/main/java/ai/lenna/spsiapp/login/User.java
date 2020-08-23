package ai.lenna.spsiapp.login;

public class User{
	private String updatedAt;
	private String name;
	private String createdAt;
	private Object emailVerifiedAt;
	private int id;
	private String email;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}
}
