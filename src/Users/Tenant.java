package src.Users;


/**
 * Potential Tenant
 */
public class Tenant extends User<Tenant> {


    @Override
    public String getFilePath(){
        return Resource().tenantLoginFile;
    }

    @Override
    protected Tenant createUser() {
        return new Tenant();
    }
    
}
