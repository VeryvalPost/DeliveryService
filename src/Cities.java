import java.util.Scanner;

public enum Cities {
    MSK,
    SPB,
    SOCH,
    KRD,
    VRN;

    public static Cities detectCity(String input) {
        switch (input){
            case "SPB":
                return SPB;
            case "MSK":
                return MSK;
            case "SOCH":
                return SOCH;
            case "KRD":
                return KRD;
            case "VRN":
                return VRN;
        }
        return null;
    }

    public int Distance(Cities cities){
        int dist;
        switch (cities){
            case SPB:
                dist = 0;
                return dist;
            case MSK:
                dist = 700;
                return dist;
            case VRN:
                dist = 1300;
                return dist;
            case KRD:
                dist = 2200;
                return dist;
            case SOCH:
                dist = 2600;
                return dist;
        } return 0;
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
        //Cities city = Cities.detectCity(input);
        return Cities.detectCity(input);
    }

    //метод для расчета расстояния между локациями
    public static int DistanceBetween(Cities city1, Cities city2){

        int pointA = city1.Distance(city1);
        int pointB = city1.Distance(city2);
        //int between = Math.abs(pointA - pointB);
        return Math.abs(pointA - pointB);
    }
}
