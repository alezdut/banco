package Bank.Usuarios;

public class Admin extends User{

    public Admin(String name, String lastName, String userName, String password) {
        super(name, lastName, userName, password);
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

}
