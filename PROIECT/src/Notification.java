public class Notification {
    private String mesaj;
    public Notification(String mesaj) {
        this.mesaj = mesaj;
    }

    public void afisare() {
        System.out.println(mesaj);
    }
}
