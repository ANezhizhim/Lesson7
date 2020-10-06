package Task1;

import java.io.*;
import java.nio.file.Path;

public class AccauntManager implements AccauntService {
    private Accaunt accaunt;
    private String strFlow;
    private Path pathFile;
    private int distStart;
    private int distEnd;

    AccauntManager() {
        this.strFlow = "";
        this.accaunt = new Accaunt(-1, null, 0.0D);
    }

    public void setPathFile(Path path) {
        this.pathFile = path;
    }

    public String getPathFile() {
        return this.pathFile.toString();
    }

    public void addAccaunt(Accaunt newClient) {
        this.strFlow += newClient.getId() + "|" + newClient.getHolder() + "|" + newClient.getAmount() + "|";

    }

    public void writeFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.pathFile.toString()))) {
            writer.println(this.strFlow);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void openFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.pathFile.toString()))) {
            this.strFlow = reader.readLine();
        } catch (FileNotFoundException ex) {

            fillClientDataBase();
            throw ex;
        }

    }

    public String getFlowString() {
        return this.strFlow;
    }

    public void setAccaunt(Accaunt clientAccaunt) {
        this.accaunt = clientAccaunt;
    }

    public int findAccaunt(int accountId) {
        int strLen = this.strFlow.length();
        int id = -1;
        boolean clientFounded = false;
        double balance = 0.0D;
        String holder = "Unnamed";

        String buff = "";
        char ch;
        int order = -1;
        for (int i = 0; i < strLen; i++) {
            ch = this.strFlow.charAt(i);
            if (ch != '|') {
                buff += this.strFlow.charAt(i);
            } else {
                order++;
                if (order > 2) {
                    order = 0;
                }
                switch (order) {
                    case 0:
                        id = Integer.parseInt(buff);
                        distStart = i - buff.length();
                        break;
                    case 1:
                        holder = buff;
                        break;
                    case 2:
                        balance = Double.parseDouble(buff);
                        distEnd = i + 1;
                        break;
                }
                buff = "";
            }
            if ((id == accountId) && (order == 2)) {

                this.accaunt.setId(id);
                this.accaunt.setHolder(holder);
                this.accaunt.setAmount(balance);
                clientFounded = true;
                break;
            }
        }
        if (!clientFounded) {

            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void withdraw(int accountId, double amount) throws NotEnoughMoneyException, UnknownAccountException {
        if (this.findAccaunt(accountId) == 0) {
            String strBefore = this.strFlow.substring(0, this.distStart);
            String strAfter = this.strFlow.substring(this.distEnd, this.strFlow.length());
            double clientAmount = this.accaunt.getAmount() - amount;
            if (clientAmount >= 0) {
                this.accaunt.setAmount(clientAmount);
                this.strFlow = strBefore + this.accaunt.getId() + "|" + this.accaunt.getHolder() + "|" + this.accaunt.getAmount() + "|" + strAfter;
                if (amount > 0) {
                    System.out.println("Списание со счета: " + this.accaunt.getHolder() + " , сумма: " + amount + ", остаток: " + this.accaunt.getAmount());
                } else {
                    System.out.println("Внесение на счет: " + this.accaunt.getHolder() + " , сумма: " + (-1) * amount + ", остаток: " + this.accaunt.getAmount());
                }

                this.writeFile();
            } else {
                throw new NotEnoughMoneyException("Недостаточно средств на счету: " + accountId);
            }
        } else {
            throw new UnknownAccountException("Владелец с таким Id не найден: " + accountId);
        }
    }

    @Override
    public void balance(int accountId) throws UnknownAccountException {
        if (this.findAccaunt(accountId) == 0) {
            System.out.println("Владелец счета: " + this.accaunt.getHolder() + " , сумма: " + this.accaunt.getAmount());
        } else {
            throw new UnknownAccountException("Владелец с таким Id не найден: " + accountId);
        }

    }

    @Override
    public void transfer(int from, int to, double amount) throws NotEnoughMoneyException, UnknownAccountException {
        withdraw(from, amount);
        deposit(to, amount);
    }

    @Override
    public void deposit(int accountId, double amount) throws NotEnoughMoneyException, UnknownAccountException {
        withdraw(accountId, -amount);

    }

    public void fillClientDataBase() {
        Accaunt[] clients = new Accaunt[10];

        clients[0] = new Accaunt(1, "Иванов А.А.", 1000.0D);
        clients[1] = new Accaunt(2, "Петров А.А.", 1010.0D);
        clients[2] = new Accaunt(3, "Сидоров А.А.", 1020.0D);
        clients[3] = new Accaunt(4, "Мурадян А.А.", 1030.0D);
        clients[4] = new Accaunt(5, "Стоянов А.А.", 1040.0D);
        clients[5] = new Accaunt(6, "Олейников А.А.", 1050.0D);
        clients[6] = new Accaunt(7, "Петросян А.А.", 1060.0D);
        clients[7] = new Accaunt(8, "Шифрин А.А.", 1070.0D);
        clients[8] = new Accaunt(9, "Карапетян А.А.", 1080.0D);
        clients[9] = new Accaunt(10, "Кнопкин А.А.", 1090.0D);
        for (int i = 0; i < 10; i++) {
            this.addAccaunt(clients[i]);
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.pathFile.toString()))) {
            writer.println(this.strFlow);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

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