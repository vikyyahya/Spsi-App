package ai.lenna.spsiapp.login;

public class LoginResponse {
	private String accessToken;
	private int code;
	private boolean success;
	private User user;

	public String getAccessToken(){
		return accessToken;
	}

	public int getCode(){
		return code;
	}

	public boolean isSuccess(){
		return success;
	}

	public User getUser(){
		return user;
	}
}
