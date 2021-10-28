package src.Users;


/**
 * Raihan
 * 
 * Serializable Object - Potential Tenant User
 */
public class TenantUser extends User<TenantUser> {


    /**
     * Returns the file path of the tenants from Resource
     */
    @Override
    public String getFilePath(){
        return Resource().tenantLoginFile;
    }

    /**
     * Creates and returns a new tenant user
     */
    @Override
    public TenantUser createUser() {
        return new TenantUser();
    }
    
}
