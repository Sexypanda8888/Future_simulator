package login;

public class user {
    private String user_name ="";
    private String password ="";

    public user(String user_name,String password){
        this.user_name = user_name;
        this.password = password;
    }
    public void setUsername(String user_name){
        this.user_name = user_name;
    }
    public String getUsername(){
        return user_name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}
