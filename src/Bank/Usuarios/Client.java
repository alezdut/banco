package Bank.Usuarios;

public class Client extends User{
    public Client(String name, String lastName, String userName, String password){
        super(name, lastName, userName, password);
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    public String toString(){
        return "Nombre: " + this.getName() + " " + this.getLastName();
    }
}
