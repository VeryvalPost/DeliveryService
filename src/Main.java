import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    /*  Программа доставки.
    Должна взаимодействовать с пользователем::
    1. Оформить новую доставку
    Сохранять заказы в файл.

    2. Рассчитать стоимость доставки
    3. Иметь возможность поиска статуса по трек-номеру.
    Из файла по трек номеру
     */
    final static String DELIVER = "получения";
    final static String SENDER = "отправки";
    final static float COST = 1200; // Стоимость доставки за 1000км.  Magic число, чтоб не путаться. При изменении стоимости доставки цену нужно скорректировать только здесь. Хотя программа в других местах и не использует эти данные.

    public static void main(String[] args) {
        HashMap<Integer, Order> ordersMap = new HashMap<Integer, Order>();
        String fileName = "orderdata.csv";
        Scanner scanner = new Scanner(System.in);


        // Здесь два заказа исключительно для заполнения таблицы изначально. Если файл уже заполнен, то этот блок необходимо удалить, чтоб не работал каждый раз при запуске.
        Order order = new Order(1, "Vasya", "Vasin", "+7900000000", "Ivan", "Ivanov", "+7900000001", Cities.MSK, Cities.SOCH, Status.IN_WORK);
        addOrderToCSV(order, fileName);
        Order order2 = new Order(2, "Petr", "Petrov", "+7900000003", "Tolya", "Tokin", "+7900000005", Cities.SPB, Cities.KRD, Status.ACCEPTED);
        addOrderToCSV(order2, fileName);

        while (true) {

            System.out.println("Выберите необходимое действие или введите 'end' для завершения:" + "\n" +
                    "1. Оформить новую доставку" + "\n" +
                    "2. Рассчитать стоимость доставки" + "\n" +
                    "3. Найти статус отправления" + "\n");
            String input = scanner.nextLine();
            if ("end".equals(input)) {            // вывод полной корзины в случае завершения программы
                System.out.println("До свидания!");
                break;
            }
            switch (input) {
                case "1":
                    System.out.println("Оформление новой доставки.");
                    newOrder(ordersMap,fileName);
                    break;
                case "2":
                    System.out.println("Расчет стоимости доставки между населенными пунктами");
                    System.out.println("Доставка обойдется в " +(COST / 1000 * Order.DistanceBetween(inputCity(SENDER),inputCity(DELIVER))) + " рублей");
                    break;
                case "3":
                    System.out.println("Статус отправления");
                    System.out.println("Введите трек-номер");
                    ordersMap = readOrderFromCSV(fileName, ordersMap);
                    input = scanner.nextLine();
                    Status status = ordersMap.get(Integer.parseInt(input)).getStatus();
                    System.out.println("Статус заказа:"+ status);
                    break;

            }
        }

        scanner.close(); // Всё, что работает с ресурсами необходимо закрывать.


        ordersMap = readOrderFromCSV(fileName, ordersMap);
        for (Map.Entry<Integer, Order> entry : ordersMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());
        }





    }

    // метод для добавления информации о доставке в файл CSV orderdata.csv
    public static void addOrderToCSV(Order order, String filename) {
        String[] ordersArray = order.toString().split(",");
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename, true))) { // Всё, что работает с ресурсами необходимо закрывать.
            writer.writeNext(ordersArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // метод для чтения информации о доставке из файл CSV orderdata.csv
    public static HashMap<Integer, Order> readOrderFromCSV(String filename, HashMap<Integer, Order> ordersMap) {

        try (CSVReader reader = new CSVReader(new FileReader(filename))) { // Всё, что работает с ресурсами необходимо закрывать.
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String line = Arrays.toString(nextLine);
                line = line.substring(1, line.length() - 1);
                String[] order = line.split(", ");
                Order forRead = new Order(Integer.parseInt(order[0]), order[1], order[2], order[3], order[4], order[5], order[6], Cities.detectCity(order[7]), Cities.detectCity(order[8]), Status.strToEnum(order[9]));
                ordersMap.put(Integer.parseInt(order[0]), forRead);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return ordersMap;
    }

    public static void newOrder(HashMap<Integer, Order> ordersMap, String fileName) {
        Scanner scanner = new Scanner(System.in);

        String nameOfSender;
        String surnameOfSender;
        String telSender;
        String nameOfRecipient;
        String surnameOfRecipient;
        String telRecipient;
        Cities citySend;
        Cities cityDeliver;
        Status status;

        ordersMap = readOrderFromCSV(fileName,ordersMap);

        System.out.println("Введите имя отправителя:");
        String input = scanner.nextLine();
        nameOfSender = input;

        System.out.println("Введите фамилию отправителя:");
        input = scanner.nextLine();
        surnameOfSender = input;

        System.out.println("Введите телефон отправителя:");
        input = scanner.nextLine();
        telSender = input;

        System.out.println("Введите имя получателя:");
        input = scanner.nextLine();
        nameOfRecipient = input;

        System.out.println("Введите фамилию получателя:");
        input = scanner.nextLine();
        surnameOfRecipient = input;

        System.out.println("Введите телефон получателя:");
        input = scanner.nextLine();
        telRecipient = input;

        citySend = inputCity(SENDER);

        cityDeliver = inputCity(DELIVER);

        Order forRead = new Order((ordersMap.size()+1), nameOfSender, surnameOfSender, telSender, nameOfRecipient, surnameOfRecipient, telRecipient, citySend, cityDeliver, Status.randomStatus());
        addOrderToCSV(forRead,fileName);
    }
    public static Cities inputCity(String cityOf){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите город "+cityOf+": \n" +
                "    MSK - Москва,\n" +
                "    SPB - Санкт-Петербург,\n" +
                "    SOCH - Сочи,\n" +
                "    KRD - Краснодар,\n" +
                "    VRN - Воронеж");
        String input = scanner.nextLine();
        Cities city = Cities.detectCity(input);
        return city;
    }
}

