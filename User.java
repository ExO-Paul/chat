package sokolchik.pavel.chat;

/**
 * Created by sokolchik_p on 08.09.2014.
 */
public class User {
    String nickname;
    String login;
    String password;

    public User() {
    }

    ;

    public User(String login, String password, String nickname) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }

}

