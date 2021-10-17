package src.Tenant;

import src.Users.*;

public class TenantController {
    
    public TenantUser loggedInTenant;

    public TenantController(TenantUser loggedInTenant){
        this.loggedInTenant = loggedInTenant;
    }

}
