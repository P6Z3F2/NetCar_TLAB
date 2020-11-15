package hu.bme.aut.netcar;

public class LoginResponse {

    private String message;
    private Integer id;


    public LoginResponse(String message, Integer id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
