package model;

// ================= CLASS =================
public class Admin extends Person {

    // ================= VARIABLES =================
    private String username;
    private String password;

    // ================= CONSTRUCTOR =================
    public Admin(String name, int age, String gender, String contact,
            String username, String password) {
        super(name, age, gender, contact);
        this.username = username;
        this.password = password;
    }

    // ================= GETTER AND SETTER METHODS =================
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ================= METHOD =================
    public boolean checkPassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    // ================= DISPLAY METHOD (OVERRIDDEN) =================
    public void displayDetails() {
        System.out.println("Admin Name : " + getName());
        System.out.println("Username   : " + username);
    }
}