package classes;

public class User {
    private String username;
    private String password;
    private int id;
    private String role;
    private String name;
    private String surname;

    public User(String username, String password, String name, String surname, int id, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String pass){
        if (pass.equals(password)){
            return true;
        } else {
            return false;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public String getRole(){
        return role;
    }

}