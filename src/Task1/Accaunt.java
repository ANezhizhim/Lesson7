package Task1;

public class Accaunt {
    private int id;
    private String holder;
    private double amount;

    public Accaunt(int id, String holder, double amount) {
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public String getHolder() {
        return this.holder;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setId(int accountId) {
        this.id = accountId;
    }
    public void setHolder(String accountHolder) {
        this.holder = accountHolder;
    }
    public void setAmount(double accountAmount) {
        this.amount = accountAmount;
    }
}


/*
∎Реализовать класс Account c полями: id –уникальный идентификатор счета, holder –владелец счета, amount –сумма на счете
∎Реализовать интерфейс AccountService, который производит манипуляцию со счетами пользователей. Информация о счете должна хранится в файловой системе.
 Доступ к файловому хранилищу осуществлять с помощью символьных потоков ввода/вывода.
p\\ublic class AccountService {void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;
void balance(int accountId) throws UnknownAccountException;void deposit(int accountId, int amount) throws NotEnoughMoneyException,
 UnknownAccountException;void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException;}
∎Реализовать пользовательский класс для управления аккаунтами:
-при вводе в консоле команды balance [id] –вывеси информацию о счёте
-при вводе в консоле команды withdraw [id] [amount] –снять указанную сумму
-при вводе в консоле команды deposite [id] [amount] –внести на счет указанную сумму
-при вводе в консоле команды transfer [from] [to] [amount] –перевести сумму с одного счета на другой
∎При старте приложения, если не существует файлового хранилища, создать его и заполнить 10 счетами
 */