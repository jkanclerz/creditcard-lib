package pl.jkan.banking.clientdetails;

class ClientDetails {
    private String id;
    private String firstname;

    public ClientDetails(String id, String firstname) {
        this.firstname = firstname;
        this.id = id;
    }

    private ClientDetails() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
