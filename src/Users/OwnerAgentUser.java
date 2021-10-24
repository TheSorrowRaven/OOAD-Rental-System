package src.Users;

import src.*;

import java.util.*;

/**
 * Serializable Object - Owner or Agent User (Since both have exactly the same capabilities/functionalities, they're combined)
 */
public class OwnerAgentUser extends User<OwnerAgentUser>{

    /**
     * Creates a new Owner/Agent user object
     */
    @Override
    public OwnerAgentUser createUser() {
        return new OwnerAgentUser();
    }
    
    /**
     * This boolean is to identify whether it is an owner or agent
     */
    public boolean isOwner;

    /**
     * Returns the owner/agent file path in resource
     */
    @Override
    public String getFilePath() {
        return Resource().ownerAgentLoginFile;
    }

    /**
     * Returns the converted saveable text of the owner agent (adds the isOwner value only)
     */
    @Override
    public String getSaveableText() {
        return Input.combineData(super.getSaveableText(), Boolean.toString(isOwner));
    }

    /**
     * Loads the owner agent from a split string (Loads isOwner only)
     */
    @Override
    public int loadSaveableSplitTextIntoUser(String[] split, OwnerAgentUser user) {
        int c = super.loadSaveableSplitTextIntoUser(split, user);
        OwnerAgentUser ownerAgent = (OwnerAgentUser)user;
        ownerAgent.isOwner = Boolean.parseBoolean(split[c++]);
        return c;
    }

    @Override
    public void onFinishLoading(){
        
    }

    /**
     * Override equals to compare via id
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof OwnerAgentUser ownerAgentUser){
            return ownerAgentUser.id.equals(id);
        }
        else if (obj instanceof UUID ownerAgentID){
            return getID().equals(ownerAgentID);
        }
        return false;
    }

}