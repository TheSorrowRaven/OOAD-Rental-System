package src.Users;

public class AdminUser extends User<AdminUser> {

    public static AdminUser getDefaultAdminUser(String defaultSerializedAdmin){
        AdminUser dummy = new AdminUser();
        AdminUser defaultAdmin = dummy.loadSaveableText(defaultSerializedAdmin);
        return defaultAdmin;
    }

    @Override
    public String getFilePath() {
        return Resource().adminLoginFile;
    }

    @Override
    public AdminUser createUser() {
        return new AdminUser();
    }
    
}
