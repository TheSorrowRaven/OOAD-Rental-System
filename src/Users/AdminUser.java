package src.Users;

/**
 * Serializable Object to contiain the admin user
 */
public class AdminUser extends User<AdminUser> {

    /**
     * Creates a dummy admin to parse the default admin (located in resource) into an admin user object and return it
     * @param defaultSerializedAdmin
     * @return
     */
    public static AdminUser getDefaultAdminUser(String defaultSerializedAdmin){
        AdminUser dummy = new AdminUser();
        AdminUser defaultAdmin = dummy.loadSaveableText(defaultSerializedAdmin);
        return defaultAdmin;
    }

    /**
     * Returns the admin save file path
     */
    @Override
    public String getFilePath() {
        return Resource().adminLoginFile;
    }

    /**
     * Returns a new AdminUser
     */
    @Override
    public AdminUser createUser() {
        return new AdminUser();
    }
    
}
