package Main;

public class ClientCode {
    private final int clientNumber;
    private final int howManyPeople;
    private final String cityDistrict;
    public ClientCode(String clientCode) {
        clientNumber = Integer.parseInt(clientCode.substring(0, 5));
        howManyPeople = Integer.parseInt(clientCode.substring(5, 7));
        cityDistrict = clientCode.substring(7,10);
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public int getHowManyPeople() {
        return howManyPeople;
    }

    public String getCityDistrict() {
        return cityDistrict;
    }
}
