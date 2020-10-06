package Task1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class AccauntManagerApp {
    public static void main(String[] args) throws IOException {

        Path pathFile = Paths.get("Clients.txt");
        System.out.println("Путь: " + pathFile.toString());

        AccauntManager clientService = new AccauntManager();
        clientService.setPathFile(pathFile);
        clientService.openFile();

        Scanner scan = new Scanner(System.in);
        String commandLine = "";
        while (!commandLine.equals("ex")) {
            System.out.println("Введите команду: ");
            commandLine = scan.nextLine();
            if (commandLine.equals("ex")) {
                break;
            }
            int startParamPos = commandLine.indexOf("[");
            int endParamPos = commandLine.indexOf("]");

            int idClient = Integer.parseInt(commandLine.substring(startParamPos + 1, endParamPos));

            switch (commandLine.substring(0, 9)) {
                case "balance [":
                    try {
                        clientService.balance(idClient);
                    } catch (UnknownAccountException ex) {
                        System.out.println("UnknownAccountException = " + ex.getMessage());
                    }
                    break;
                case "withdraw ":
                    double clientAmount = Double.parseDouble(commandLine.substring(commandLine.indexOf("] [") + 3, commandLine.length() - 1));
                    try {
                        clientService.withdraw(idClient, clientAmount);
                    } catch (Exception ex) {
                        System.out.println("Ошибка: " + ex.getMessage());
                    }

                    break;
                case "deposite ":
                    clientAmount = Double.parseDouble(commandLine.substring(commandLine.indexOf("] [") + 3, commandLine.length() - 1));
                    try {
                        clientService.deposit(idClient, clientAmount);
                    } catch (Exception ex) {
                        System.out.println("Ошибка: " + ex.getMessage());
                    }

                    break;
                case "transfer ":
                    String subStr = commandLine.substring(commandLine.indexOf("] [") + 3, commandLine.length() - 1);
                    int idTo = Integer.parseInt(subStr.substring(0, subStr.indexOf("]")));
                    clientAmount = Double.parseDouble(subStr.substring(subStr.indexOf("] [") + 3, subStr.length()));

                    try {
                        clientService.transfer(idClient, idTo, clientAmount);
                    } catch (Exception ex) {
                        System.out.println("Ошибка: " + ex.getMessage());
                    }
                    break;
            }
        }
        System.out.println("До свидания!");
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

transfer [4] [5] [200]
 */