import Storage.Pallet;
import Storage.Trailer;

public class Application {
    public static void main(String[] args) {
        System.out.println("*** Gin-Shop ***");
        final Trailer pallet = new Trailer();
        for (int i = 0; i<32; i++) {
            pallet.push(new Pallet("abcdef"));
        }
    }
}
