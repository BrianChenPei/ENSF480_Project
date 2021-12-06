package Users;

public class RegisteredRenter extends Renter{
    private String name;


    public RegisteredRenter() {
        super();
        name = "";
    }

    public RegisteredRenter(String name) {
        super();
        this.name = name;
    }

    public RegisteredRenter(String name, String sc) {
        super(sc);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchCriteria() {
        return super.searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        super.searchCriteria = searchCriteria;
    }

}
