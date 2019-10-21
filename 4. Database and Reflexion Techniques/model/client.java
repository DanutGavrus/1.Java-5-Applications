package model;

public class client {
    private int id_client;
    private String client_name;
    private int client_age;

    public client(int id_client, String client_name, int client_age) {
        super();
        this.id_client = id_client;
        this.client_name = client_name;
        this.client_age = client_age;
    }

    public client(String client_name, int client_age) {
        super();
        this.client_name = client_name;
        this.client_age = client_age;
    }

    public String getClient_name() {
        return client_name;
    }

    public int getClient_age() {
        return client_age;
    }
}
