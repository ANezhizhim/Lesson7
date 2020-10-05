package Task1;

public interface AccauntService {
    void withdraw(int accountId, double amount) throws NotEnoughMoneyException, UnknownAccountException;
    void balance(int accountId)throws UnknownAccountException;
    void deposit(int accountId, double amount)throws NotEnoughMoneyException, UnknownAccountException;
    void transfer(int from, int to, double amount) throws NotEnoughMoneyException, UnknownAccountException;
}

/*
∎Реализовать интерфейс AccountService, который производит манипуляцию со счетами пользователей. Информация о счете должна хранится в файловой системе.
 Доступ к файловому хранилищу осуществлять с помощью символьных потоков ввода/вывода.
p\\ublic class AccountService {void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;

void balance(int accountId) throws UnknownAccountException;
void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;
void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException;}
 */