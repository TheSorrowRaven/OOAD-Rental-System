package src.Tenant;

import src.Property.PropertyController;
import src.Users.*;

public class TenantController extends PropertyController {
    
    public TenantUser loggedInTenant;

    public TenantController(TenantUser loggedInTenant){
        this.loggedInTenant = loggedInTenant;
    }

}
