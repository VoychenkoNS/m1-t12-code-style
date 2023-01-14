import java.util.Scanner;

public class DepositCalculator {
    public static final String PATTER_DOUBLE = "[1-9]\\d*(\\,\\d{1,9})?";
    public static final String PATTER_INTEGER = "[1-9]\\d*";

    private double calculateComplexPercent(double amount, double yearRate, int period) {
        double sum = amount * Math.pow((1 + (yearRate / 12)), 12 * period);

        return round(sum);
    }

    private double calculateSimplePercent(double amount, double yearRate, int period) {
        return round(amount + (amount * yearRate * period));
    }

    private double round(double value) {
        double scale = Math.pow(10, 2);

        return Math.round(value * scale) / scale;
    }

    public Double calculateFinishSum(int action, double amount, double yearRate, int period) {
        Double outFinishSum;

        switch (action) {
            case 1 -> outFinishSum = calculateSimplePercent(amount, yearRate, period);
            case 2 -> outFinishSum = calculateComplexPercent(amount, yearRate, period);
            default -> {
                System.out.format("Команды %d не существует", action);
                outFinishSum = null;
            }
        }

        return outFinishSum;
    }

    public static void main(String[] args) {
        DepositCalculator depositCalculator = new DepositCalculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите сумму вклада в рублях:");

        checkInputUser(scanner, DepositCalculator. PATTER_DOUBLE, "Введите число больше 0!");
        double amount = scanner.nextDouble();

        System.out.println("Введите срок вклада в годах:");

        checkInputUser(scanner,DepositCalculator.PATTER_INTEGER, "Введите целое число больше 0!");
        int period = scanner.nextInt();

        System.out.println("Выберите тип вклада, 1 - вклад с обычным процентом, 2 - вклад с капитализацией:");

        checkInputUser(scanner,DepositCalculator.PATTER_INTEGER, "Введите целое число больше 0!");
        int action = scanner.nextInt();

        Double finishSum = depositCalculator.calculateFinishSum(action, amount, 0.06, period);

        if (finishSum != null) {
            System.out.println("Результат вклада: " + amount + " за " + period + " лет превратятся в " + finishSum);
        }
    }

    public static void checkInputUser(Scanner scanner, String pattern, String textError) {
        while (!scanner.hasNext(pattern)) {
            System.out.println(textError);

            scanner.nextLine();
        }
    }
}