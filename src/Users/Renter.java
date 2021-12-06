package Users;



public class Renter {
    protected String searchCriteria;


    public Renter() {
        searchCriteria = "";
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
