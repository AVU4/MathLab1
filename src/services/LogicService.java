package services;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.NoSuchFileException;

public class LogicService {

    private OutputService outputService = new OutputService();
    private InputService inputService = new InputService();
    private MathService mathService = new MathService();
    private DataService dataService = DataService.getInstance();
    private String input;

    public int entenrce() throws IOException {
        System.out.println("Выберите режим, чтение из файла, введите 1, или чтение с клавиатуры, введите 2, введите 3, для того, чтобы сгенерировать случайную матрицу и решить её, для выхода введите exit");
        input = InputService.scanner.nextLine();
        if (input.equals("exit")) {
            return -1;
        }
        if (input.equals("1")) {
            outputService.printExample();
            System.out.println("Вы выбрали режим чтения из файла. Заполните Ваш файл согласно примеру выше, затем введите путь к файлу или просто нажмите Enter");
            return 1;
        }else if (input.equals("2")) {
            System.out.println("Выбран режим чтения с клавиатуры");
            return 2;
        } else if (input.equals("3")) {
            return 3;
        }else
            return 0;
    }

    public void MathPreparation(){
        double[][] resultMatrix = mathService.transformationMatrix(dataService.getMatrixOfCoefficients(), dataService.getColumnOfFreeMember(), dataService.getRow(), dataService.getSize());
        double[][] matrix = new double[dataService.getRow()][dataService.getSize()];
        double[] column = new double[dataService.getRow()];
        for (int i = 0; i < dataService.getRow(); i++) {
            for (int j = 0; j < dataService.getSize() + 1; j++) {
                if (j != dataService.getSize()) {
                    matrix[i][j] = resultMatrix[i][j];
                } else {
                    column[i] = resultMatrix[i][j];
                }
            }
        }
        dataService.setMatrixOfCoefficients(matrix);
        dataService.setColumnOfFreeMember(column);
    }

    public boolean MathTesting(){
        return mathService.isCorrectMatrix(dataService.getMatrixOfCoefficients());
    }

    public void MathStarts(){
        mathService.startMethod(dataService.getMatrixOfCoefficients(), dataService.getColumnOfFreeMember(),
                dataService.getAccuracy(), dataService.getNumOfIteration(), dataService.getValuesOfX(), dataService.getSize(),
                dataService.getRow());
    }


    public void generateRandomMatrix() {
        inputService.inputForRandomMatrix();
        mathService.generateRandom();
    }
}
