// ParentUser.java

public class ParentUser extends User {
    private String a;
    private String b;
    private String n;

    public ParentUser(String username, String password, String a, String b, String n) {
        super(username, password, "", "");
        this.a = a;
        this.b = b;
        this.n = n;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }
}
