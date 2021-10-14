package src.Users;


/**
 * Potential Tenant
 */
public class TenantUser extends User<TenantUser> {


    @Override
    public String getFilePath(){
        return Resource().tenantLoginFile;
    }

    @Override
    public TenantUser createUser() {
        return new TenantUser();
    }
    
}
