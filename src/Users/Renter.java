package Users;


public class Renter implements User{
    protected String searchCriteria;
    protected String usertype = "Renter";


    public Renter() {
        searchCriteria = "";
    }

    public String getUsertype(){
        return usertype;
    }

    public Renter(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getSearchCriteria() {
        return this.searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
    
    
}
