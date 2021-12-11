package Users;

/**
 * @author Brian Chen <a
 * href="mailto: brian.chen@ucalgary.ca">brian.chen@ucalgary.ca</a>
 * 
 * @version 1.2
 * 
 * @since 1.0
 */

import java.util.ArrayList;

import java.util.List;

import Property.Property;

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
	private Property searchCriteria;

	/**
	 * adds a registered renter to subs list
	 * @param sub
	 */
	public void subscribe(RegisteredRenter sub){
		subs.add(sub);
    }

	public boolean checkSubscribed(RegisteredRenter candidate) {
		if(subs.contains(candidate)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * unsubscribes registered renter from subs list
	 * @param renter
	 */
    public void unsubscribe(RegisteredRenter renter){
        subs.remove(renter);
    }
	
	/**
	 * sends all subs that searched the given criteria all listing
	 * @param searchCriteria
	 */
	public void notifySubscribers(Property searchCriteria) {
		for(RegisteredRenter sub : subs) {
			sub.update(searchCriteria);
		}
	}
	
	/**
	 * Notifys all subs when new post is added
	 * @param searchCriteria
	 */
	public void newPost(Property searchCriteria) {
		this.searchCriteria = searchCriteria;
		notifySubscribers(searchCriteria);
	}
		
}
