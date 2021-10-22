package src.Users;

import src.*;

import java.util.*;

/**
 * Owner or Agent
 */
public class OwnerAgentUser extends User<OwnerAgentUser>{

    @Override
    public OwnerAgentUser createUser() {
        return new OwnerAgentUser();
    }
    
    public boolean isOwner;

    // private UUID supportingOwnerAgentID;

    // public UUID getSupportingOwnerAgentID(){
    //     return supportingOwnerAgentID;
    // }

    @Override
    public String getFilePath() {
        return Resource().ownerAgentLoginFile;
    }

    @Override
    public String getSaveableText() {
        // String ownerAgentID = supportingOwnerAgentID != null ? supportingOwnerAgentID.toString() : Character.toString(Resource().nullPlacementCharacter);
        return Input.combineData(super.getSaveableText(), Boolean.toString(isOwner)/*, ownerAgentID*/);
    }

    @Override
    public int loadSaveableSplitTextIntoUser(String[] split, OwnerAgentUser user) {
        int c = super.loadSaveableSplitTextIntoUser(split, user);
        OwnerAgentUser ownerAgent = (OwnerAgentUser)user;
        ownerAgent.isOwner = Boolean.parseBoolean(split[c++]);
        /*if (split[c] != null){
            ownerAgent.supportingOwnerAgentID = Resource().getUUIDFrom(split[c++]);
        }*/
        return c;
    }

    @Override
    public void onFinishLoading(){
        
    }

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

    /**
     * For when displaying tables of this data, total columns required, override if require more
     * @return
     */
    // @Override
    // public int getTableDisplayColumns(){
    //     return 4;   //Username, password, name, supporting owner agent name
    // }
    /**
     * Creates the object data required to display on a table
     * @return
     */
    // @Override
    // public ArrayList<Object> getTableDisplayColumnsData(){
    //     var column = getBaseTableDisplayColumnsData();
    //     var command = new Command<OwnerAgentUser>(){
    //         public String name = Resource().str_unassigned_ownerAgent;
    //         @Override
    //         public boolean execute(OwnerAgentUser ownerAgent, Object discard){
    //             if (ownerAgent.getID().equals(supportingOwnerAgentID)){
    //                 name = ownerAgent.name;
    //                 return true;
    //             }
    //             return false;
    //         }
    //     };
    //     Main.instance().serializer.readForEach(this, command);
    //     column.add(command.name);
    //     return column;
    // }
    // @Override
    // public ArrayList<String> getColumnHeaders(boolean isOwnerAgent){
    //     ArrayList<String> headers = getBaseColumnHeaders();
    //     headers.add(!isOwnerAgent ? Resource().str_owner : Resource().str_agent);
    //     return headers;
    // }

}