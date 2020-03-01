package services;

import java.io.*;
import java.util.Scanner;


public class InputService {

    DataService dataService = DataService.getInstance();
    public static Scanner scanner;
    {
        scanner = new Scanner(System.in);
    }

    public void inputFromKeyboards(){
        double[][] matrixOfCoefficients = new double[20][20];
        int row = 0;
        int size = 0;
        int currentSize = 0;
        System.out.println("Введите коэффициенты системы линейных уравнений (Ввод осуществляется через пробел), " +
                "после введённых коэффициентов введите команду end," +
                " вместо числа можете написать x, которой в результате будет заменён на случайный коэффициент");

        String input;
        while (true){
            try {
                input = scanner.nextLine();
                if (input.equals("end")) {
                    break;
                }
                String[] elems = input.split(" ");
                if (size == 0) {
                    size = elems.length;
                }
                currentSize = elems.length;
                if (currentSize == size){
                    for (int j = 0; j < elems.length; j++) {
                        if (elems[j].equals("x")){
                            matrixOfCoefficients[row][j] = Math.random()*15 + 1;
                        }else{
                            matrixOfCoefficients[row][j] = Double.parseDouble(elems[j]);
                        }
                    }
                    row++;
                }else{
                    if (currentSize == 1){
                        Double.parseDouble(elems[0]);
                    }
                    System.out.println("Число коэффициентов не совпадает, возможно Вы что-то упустили, если Вы пропустили намеренно, то вместо пропуска поставте 0");
                }
            }catch (NumberFormatException e){
                System.out.println("Вы ввели непонятную строку");
            }catch (ArrayIndexOutOfBoundsException e){
                size = 0;
                for (int i = 0; i < 20; i ++){
                    matrixOfCoefficients[row][i] = 0;
                }
                System.out.println("Введено большое элементов, ограничение до 20");
            }
        }

        dataService.setSize(size);
        dataService.setRow(row);

        double[][] resultMatrix = new double[row][size];
        for (int i = 0; i < row; i ++){
            for (int j = 0; j < size; j ++){
                resultMatrix[i][j] = matrixOfCoefficients[i][j];
            }
        }

        dataService.setMatrixOfCoefficients(resultMatrix);

        double[] columnOfFreeMember = new double[row];
        System.out.println("Введите столбец свободных членов");
        for (int i = 0; i < row; i ++){
            try {
                columnOfFreeMember[i] = Double.parseDouble(scanner.nextLine());
            }catch (NumberFormatException e){
                i -= 1;
                System.out.println("Введена не число");
            }
        }

        dataService.setColumnOfFreeMember(columnOfFreeMember);

        for (int i = 0; i < row; i ++){
            for (int j = 0; j < size + 1; j ++){
                if (j  == size){
                    System.out.print("|" + columnOfFreeMember[i]);
                }else System.out.print(matrixOfCoefficients[i][j] + " ");

            }
            System.out.println("");
        }



        System.out.println("Задайте точность вычислений");
        double accuracy;
        while (true){
            try {
                accuracy = Double.parseDouble(scanner.nextLine().replace(",", "."));
                break;
            }catch (NumberFormatException e){
                System.out.println("Введено не число");
            }
        }
        dataService.setAccuracy(accuracy);

        System.out.println("Задайте начальные приближения значений неизвестных");
        double[] valuesOfX = new double[size];
        for (int i = 0; i < size; i ++){
            try {
                valuesOfX[i] = Double.parseDouble(scanner.nextLine());
            }catch (NumberFormatException e){
                i --;
                System.out.println("Введено не число");
            }
        }
        dataService.setValuesOfX(valuesOfX);

        System.out.println("Задайте допустимое число итераций");
        int numOfIteration;
        while (true) {
            try {
                numOfIteration = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Введено не число");
            }
        }

        dataService.setNumOfIteration(numOfIteration);
    }

    public void inputFromFile(String pathFile) throws IOException {
        Scanner scanner;
        InputStream inputStream = getClass().getResourceAsStream("/example.txt");
        if (pathFile.equals("")){
            scanner = new Scanner(inputStream);
        }else{
            File file = new File(pathFile);
            scanner = new Scanner(file);
        }
        double[][] matrixOfCoefficients = new double[20][20];
        int row = 0;
        int size = 0;
        int k = 0;
        int numSection = 0;
        double[] columnOfFreeMember = new double[0];
        double[] valuesOfX = new double[0];
        int numOfIteration = 0;
        double accuracy = 0;


        while (scanner.hasNext()){
            String input = scanner.nextLine();
             if (input.startsWith("//")){
                numSection ++;
                if (numSection == 1){
                    columnOfFreeMember = new double[row];
                    valuesOfX = new double[size];
                }else  if (numSection == 2){
                    k = 0;
                }
            }else if (numSection == 0){
                String[] elems = input.split(" ");
                size = elems.length;
                for (int j = 0; j < elems.length; j++) {
                    matrixOfCoefficients[row][j] = Double.parseDouble(elems[j]);
                }
                row++;
            }else if (numSection == 1){
                 columnOfFreeMember[k] = Double.parseDouble(input);
                 k ++;
             }else if (numSection == 2){
                 accuracy = Double.parseDouble(input.replace(",","."));
             }else if (numSection == 3){
                 valuesOfX[k] = Double.parseDouble(input);
                 k ++;
             }else if (numSection == 4){
                 numOfIteration = Integer.parseInt(input);
             }
        }
        dataService.setMatrixOfCoefficients(matrixOfCoefficients);
        dataService.setColumnOfFreeMember(columnOfFreeMember);
        dataService.setAccuracy(accuracy);
        dataService.setValuesOfX(valuesOfX);
        dataService.setRow(row);
        dataService.setSize(size);
        dataService.setNumOfIteration(numOfIteration);
        inputStream.close();
        scanner.close();
    }

    public void inputForRandomMatrix(){
        int num;
        while (true) {
            try {
                num = Integer.parseInt(InputService.scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Вы ввели что-то запрещённое");
            }
        }
        dataService.setRow(num);
        dataService.setSize(num);
    }
}
