package src.Admin;

import src.*;
import src.Login.ILoginnable;
import src.Users.*;
import src.SystemComponents.*;

public class AdminController {
    
    public Resource Resource(){
        return Resource.instance();
    }


    public AdminUser admin;

    public AdminController(AdminUser loggedInAdmin){
        admin = loggedInAdmin;
    }

}
