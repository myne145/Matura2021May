package Main;

public class ClientCode {
    private final int clientNumber;
    private final int howManyPeople;
    private final String cityDistrict;
    private final String fullCode;
    private final String clientNumString;
    public ClientCode(String clientCode) {
        clientNumber = Integer.parseInt(clientCode.substring(0, 5));
        clientNumString = clientCode.substring(0, 5);
        howManyPeople = Integer.parseInt(clientCode.substring(5, 7));
        cityDistrict = clientCode.substring(7,10);
        fullCode = clientCode;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public String getClientNumString() {
        return clientNumString;
    }

    public int getHowManyPeople() {
        return howManyPeople;
    }

    public String getCityDistrict() {
        return cityDistrict;
    }

    public String toString() {
        return fullCode;
    }
}
