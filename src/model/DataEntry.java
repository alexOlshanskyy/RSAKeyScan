package model;

public class DataEntry {
    private String rawData;
    private RSAKeyData rsaKeyData;


    public DataEntry(String rawData) {
        this.rawData = rawData;

    }

    private void extractRSAKeyData() {

    }

    @Override
    public String toString() {
        return "DataEntry{" +
                "rawData='" + rawData + '\'' +
                ", rsaKeyData=" + rsaKeyData +
                '}';
    }
}
