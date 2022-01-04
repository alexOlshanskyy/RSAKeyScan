package model;

public class DataEntry {
    private String rawData;
    private String username;
    private RSAKeyData rsaKeyData;


    public DataEntry(String rawData, String username, RSAKeyData rsaKeyData) {
        this.rawData = rawData;
        this.username = username;
        this.rsaKeyData = rsaKeyData;
    }

    private void extractRSAKeyData() {

    }

    @Override
    public String toString() {
        return username + "," + rsaKeyData;
    }
}
