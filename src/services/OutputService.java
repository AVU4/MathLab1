package services;

import javax.xml.crypto.Data;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class OutputService {

    public void printAnswer(int currentIteration, double[] maxAbsoluteDifs, double[] valuesOfX){
        System.out.println("Число итераций, за которое было найдено решение, равняется " + currentIteration);
        System.out.println("Столбец погрешностей");
        int q = 1;
        while (q < maxAbsoluteDifs.length && maxAbsoluteDifs[q] != -1 ){
            System.out.println(maxAbsoluteDifs[q]);
            q ++;
        }
        System.out.println("");
        System.out.println("Решение");
        for (int k = 0; k < valuesOfX.length; k++){
            System.out.println(valuesOfX[k]);

        }
    }

    public void printAnswerNotFound(){
        System.out.println("Решение найти не удалось");
    }

    public void  printExample() throws UnsupportedEncodingException {

        Reader reader = new InputStreamReader(getClass().getResourceAsStream("/example.txt"), "UTF-8");
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }
    }

    public void RandomMatrix(){
        DataService dataService = DataService.getInstance();
        int num = dataService.getRow();
        for (int i = 0; i < num; i ++){
            for (int j = 0; j < num; j ++){
                System.out.print(dataService.getMatrixOfCoefficients()[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
