package src.Users;

import src.*;
import java.util.UUID;

/**
 * Owner or Agent
 */
public class OwnerAgent extends User<OwnerAgent>{

    @Override
    protected OwnerAgent createUser() {
        return new OwnerAgent();
    }
    
    public boolean isAgent;

    private UUID supportingOwnerAgentID;    //This exists to cache the id while loading halfway (the other may not be loaded yet)
    public OwnerAgent supportingOwnerAgent; //Load this via id

    @Override
    public String getFilePath() {
        return Resource().ownerAgentLoginFile;
    }

    @Override
    public String getSaveableText() {
        return Input.combineData(super.getSaveableText(), Boolean.toString(isAgent), supportingOwnerAgent.getID().toString());
    }

    @Override
    public int loadSaveableSplitTextIntoUser(String[] split, OwnerAgent user) {
        int c = super.loadSaveableSplitTextIntoUser(split, user);
        OwnerAgent ownerAgent = (OwnerAgent)user;
        ownerAgent.isAgent = Boolean.parseBoolean(split[c++]);
        supportingOwnerAgentID = Resource().getUUIDFrom(split[c++]);
        return c;
    }

    @Override
    public void onFinishLoading(){
        //TODO assign supportingOwnerAgent from id
    }

}