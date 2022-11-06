import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    /*  ��������� ��������.
    ������ ����������������� � �������������::
    1. �������� ����� ��������
    ��������� ������ � ����.

    2. ���������� ��������� ��������
    3. ����� ����������� ������ ������� �� ����-������.
    �� ����� �� ���� ������
     */
    final static String DELIVER = "���������";
    final static String SENDER = "��������";
    final static float COST = 1200; // ��������� �������� �� 1000��.  Magic �����, ���� �� ��������. ��� ��������� ��������� �������� ���� ����� ��������������� ������ �����. ���� ��������� � ������ ������ � �� ���������� ��� ������.

    public static void main(String[] args) {
        HashMap<Integer, Order> ordersMap = new HashMap<Integer, Order>();
        String fileName = "orderdata.csv";
        Scanner scanner = new Scanner(System.in);


        // ����� ��� ������ ������������� ��� ���������� ������� ����������. ���� ���� ��� ��������, �� ���� ���� ���������� �������, ���� �� ������� ������ ��� ��� �������.
        Order order = new Order(1, "Vasya", "Vasin", "+7900000000", "Ivan", "Ivanov", "+7900000001", Cities.MSK, Cities.SOCH, Status.IN_WORK);
        addOrderToCSV(order, fileName);
        Order order2 = new Order(2, "Petr", "Petrov", "+7900000003", "Tolya", "Tokin", "+7900000005", Cities.SPB, Cities.KRD, Status.ACCEPTED);
        addOrderToCSV(order2, fileName);

        while (true) {

            System.out.println("�������� ����������� �������� ��� ������� 'end' ��� ����������:" + "\n" +
                    "1. �������� ����� ��������" + "\n" +
                    "2. ���������� ��������� ��������" + "\n" +
                    "3. ����� ������ �����������" + "\n");
            String input = scanner.nextLine();
            if ("end".equals(input)) {            // ����� ������ ������� � ������ ���������� ���������
                System.out.println("�� ��������!");
                break;
            }
            switch (input) {
                case "1":
                    System.out.println("���������� ����� ��������.");
                    newOrder(ordersMap,fileName);
                    break;
                case "2":
                    System.out.println("������ ��������� �������� ����� ����������� ��������");
                    System.out.println("�������� ��������� � " +(COST / 1000 * Order.DistanceBetween(inputCity(SENDER),inputCity(DELIVER))) + " ������");
                    break;
                case "3":
                    System.out.println("������ �����������");
                    System.out.println("������� ����-�����");
                    ordersMap = readOrderFromCSV(fileName, ordersMap);
                    input = scanner.nextLine();
                    Status status = ordersMap.get(Integer.parseInt(input)).getStatus();
                    System.out.println("������ ������:"+ status);
                    break;

            }
        }

        scanner.close(); // ��, ��� �������� � ��������� ���������� ���������.


        ordersMap = readOrderFromCSV(fileName, ordersMap);
        for (Map.Entry<Integer, Order> entry : ordersMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());
        }





    }

    // ����� ��� ���������� ���������� � �������� � ���� CSV orderdata.csv
    public static void addOrderToCSV(Order order, String filename) {
        String[] ordersArray = order.toString().split(",");
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename, true))) { // ��, ��� �������� � ��������� ���������� ���������.
            writer.writeNext(ordersArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ����� ��� ������ ���������� � �������� �� ���� CSV orderdata.csv
    public static HashMap<Integer, Order> readOrderFromCSV(String filename, HashMap<Integer, Order> ordersMap) {

        try (CSVReader reader = new CSVReader(new FileReader(filename))) { // ��, ��� �������� � ��������� ���������� ���������.
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

        System.out.println("������� ��� �����������:");
        String input = scanner.nextLine();
        nameOfSender = input;

        System.out.println("������� ������� �����������:");
        input = scanner.nextLine();
        surnameOfSender = input;

        System.out.println("������� ������� �����������:");
        input = scanner.nextLine();
        telSender = input;

        System.out.println("������� ��� ����������:");
        input = scanner.nextLine();
        nameOfRecipient = input;

        System.out.println("������� ������� ����������:");
        input = scanner.nextLine();
        surnameOfRecipient = input;

        System.out.println("������� ������� ����������:");
        input = scanner.nextLine();
        telRecipient = input;

        citySend = inputCity(SENDER);

        cityDeliver = inputCity(DELIVER);

        Order forRead = new Order((ordersMap.size()+1), nameOfSender, surnameOfSender, telSender, nameOfRecipient, surnameOfRecipient, telRecipient, citySend, cityDeliver, Status.randomStatus());
        addOrderToCSV(forRead,fileName);
    }
    public static Cities inputCity(String cityOf){
        Scanner scanner = new Scanner(System.in);
        System.out.println("������� ����� "+cityOf+": \n" +
                "    MSK - ������,\n" +
                "    SPB - �����-���������,\n" +
                "    SOCH - ����,\n" +
                "    KRD - ���������,\n" +
                "    VRN - �������");
        String input = scanner.nextLine();
        Cities city = Cities.detectCity(input);
        return city;
    }
}

