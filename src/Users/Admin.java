package src.Users;

public class Admin extends User<Admin> {

    @Override
    public String getFilePath() {
        return Resource().adminLoginFile;
    }

    @Override
    protected Admin createUser() {
        return new Admin();
    }
    
}
