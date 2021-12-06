package Users;



public class Manager {
    private String name;


    public Manager() {
        name="";
    }
    
    public Manager(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
