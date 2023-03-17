package Entity;

public class User {
    private String idUser;
    private String pwd;

    public User(String idUser, String pwd) {
        this.idUser = idUser;
        this.pwd = pwd;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser='" + idUser + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
