package Users;

/**
 *
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 */


import java.util.List;

/*
To use: 

Observer ob = new Observer();

RegisteredRenter r1 = new RegisteredRenter(...);

ob.subscribe(r1);
r1.subscribeCriteria(ob);

ob.newPost(...);


*/

public class Observer {
	private List<RegisteredRenter> subs = new ArrayList<>();
	private String searchCriteria;
	
	public void subscribe(RegisteredRenter sub){
		subs.add(sub);
    }

    public void unsubscribe(RegisteredRenter renter){
        subs.remove(sub);
    }
	
	public void notifySubscribers(Property searchCriteria) {
		for(RegisteredRenter sub : subs) {
			sub.update(searchCriteria);
		}
	}
	
	public void newPost(String searchCriteria) {
		this.searchCriteria = searchCriteria;
		notifySubscribers(searchCriteria);
	}
		
}
