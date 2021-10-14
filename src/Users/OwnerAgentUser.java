package src.Users;

import src.*;
import java.util.UUID;

/**
 * Owner or Agent
 */
public class OwnerAgentUser extends User<OwnerAgentUser>{

    @Override
    public OwnerAgentUser createUser() {
        return new OwnerAgentUser();
    }
    
    public boolean isAgent;

    private UUID supportingOwnerAgentID;    //This exists to cache the id while loading halfway (the other may not be loaded yet)
    public OwnerAgentUser supportingOwnerAgent; //Load this via id

    @Override
    public String getFilePath() {
        return Resource().ownerAgentLoginFile;
    }

    @Override
    public String getSaveableText() {
        return Input.combineData(super.getSaveableText(), Boolean.toString(isAgent), supportingOwnerAgent.getID().toString());
    }

    @Override
    public int loadSaveableSplitTextIntoUser(String[] split, OwnerAgentUser user) {
        int c = super.loadSaveableSplitTextIntoUser(split, user);
        OwnerAgentUser ownerAgent = (OwnerAgentUser)user;
        ownerAgent.isAgent = Boolean.parseBoolean(split[c++]);
        supportingOwnerAgentID = Resource().getUUIDFrom(split[c++]);
        return c;
    }

    @Override
    public void onFinishLoading(){
        //TODO assign supportingOwnerAgent from id
    }

}