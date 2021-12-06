package Users;



public class Manager implements User{
    private String name;
    private String usertype = "Manager";


    public Manager() {
        name="";
    }

    public String getUsertype(){
        return usertype;
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
