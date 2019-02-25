package data;

/**
 * Created by crisantru on 17/04/18.
 */

public final class UserContract {
    private UserContract(){}

    public static class UserEntry{
        public static final String TABLE_NAME = "user_info";
        public static final String USER_ID = "user_id";
        public static final String NAME = "name";
        public static final String LASTNAME = "lastname";
        public static final String GENDER = "gender";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";

    }
}
