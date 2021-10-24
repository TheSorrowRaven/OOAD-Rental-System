package src.Tenant;

import src.Property.PropertyController;
import src.Users.*;

/**
 * Controller for Tenant
 * Most of the functionality are already handled by property controller
 */
public class TenantController extends PropertyController {
    
    public TenantUser loggedInTenant;

    /**
     * Constructor accepting a logged in tenant user
     * @param loggedInTenant
     */
    public TenantController(TenantUser loggedInTenant){
        this.loggedInTenant = loggedInTenant;
    }

}
