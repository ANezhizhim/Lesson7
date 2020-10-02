package Task1;

public class AccauntManager implements AccauntService{
    private Accaunt accaunt;
    AccauntManager(Accaunt currentAccaunt){
        this.accaunt=currentAccaunt;

    }

    @Override
    public void withdraw(int accountId, int amount) {

    }

    @Override
    public void balance(int accountId) {
        System.out.println("accountId = " + accountId);
    }

    @Override
    public void deposit(int accountId, int amount) {

    }

    @Override
    public void transfer(int from, int to, int amount) {

    }
}
