package src.Profile;

import src.*;
import src.Users.*;

public class Profile {
    
    public boolean changeNamePasswordOf(User<?> user, String name, String password){
        user.name = name;
        user.password = password;
        if (user instanceof TenantUser tenant){
            return changeNamePassword(TenantUser.class, tenant);
        }
        else if (user instanceof OwnerAgentUser ownerAgent){
            return changeNamePassword(OwnerAgentUser.class, ownerAgent);
        }
        else if (user instanceof AdminUser admin){
            return changeNamePassword(AdminUser.class, admin);
        }
        return false;
    }

    private <T extends User<T>> boolean changeNamePassword(Class<T> userClass, T user){
        var command = new Command<T>(){
            public int lineNumber = -1;
            @Override
            public boolean execute(T t, Object data){
                if (user.getID().equals(t.getID())){
                    lineNumber = (int)data;
                    return true;
                }
                return false;
            }
        };
        Main.instance().serializer.removeForEach(user, command);
        if (command.lineNumber == -1){
            return false;
        }
        Main.instance().serializer.remove(user, command.lineNumber);
        Main.instance().serializer.write(user);
        return true;
    }

}
