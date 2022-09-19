package Usuarios;

public class Admin extends User{
    private boolean admin = true;

    public Admin(String name, String lastName, String userName, String password) {
        super(name, lastName, userName, password);
    }

    public boolean isAdmin() {
        return admin;
    }

}
