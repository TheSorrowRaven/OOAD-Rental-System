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
    
    public boolean isOwner;

    private UUID supportingOwnerAgentID;    //This exists to cache the id while loading halfway (the other may not be loaded yet)
    public OwnerAgentUser supportingOwnerAgent; //Load this via id

    @Override
    public String getFilePath() {
        return Resource().ownerAgentLoginFile;
    }

    @Override
    public String getSaveableText() {
        String supportingOwnerAgentID = supportingOwnerAgent != null ? supportingOwnerAgent.getID().toString() : Character.toString(Resource().nullPlacementCharacter);
        return Input.combineData(super.getSaveableText(), Boolean.toString(isOwner), supportingOwnerAgentID);
    }

    @Override
    public int loadSaveableSplitTextIntoUser(String[] split, OwnerAgentUser user) {
        int c = super.loadSaveableSplitTextIntoUser(split, user);
        OwnerAgentUser ownerAgent = (OwnerAgentUser)user;
        ownerAgent.isOwner = Boolean.parseBoolean(split[c++]);
        if (split[c] != null){
            ownerAgent.supportingOwnerAgentID = Resource().getUUIDFrom(split[c++]);
        }
        return c;
    }

    @Override
    public void onFinishLoading(){
        //TODO assign supportingOwnerAgent from id
    }

}