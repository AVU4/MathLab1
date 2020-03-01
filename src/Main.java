import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

import services.*;

public class Main {
    public static void main(String[] args) {
        try {
            InputService inputService = new InputService();
            OutputService outputService = new OutputService();
            LogicService logicService = new LogicService();
            int code;
            boolean flagProblem = false;
            System.out.println("Добро пожаловать!");
            while (true) {
                code = logicService.entenrce();
                if (code == -1) break;
                else if (code == 1) {
                    try {
                        outputService.printExample();
                        System.out.println("Вы выбрали режим чтения из файла. Заполните Ваш файл согласно примеру выше, затем введите путь к файлу или просто нажмите Enter");
                        inputService.inputFromFile(InputService.scanner.nextLine());
                    } catch (FileNotFoundException | NoSuchFileException e) {
                        System.out.println("Данный файл найти не удалось");
                        flagProblem = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка в оформленни файла или в данных");
                        flagProblem = true;
                    }
                } else if (code == 2) {
                    System.out.println("Выбран режим чтения с клавиатуры");
                    inputService.inputFromKeyboards();
                }else if (code == 3){
                    System.out.println("Выберете размерность матрицы");
                    logicService.generateRandomMatrix();
                }else {
                    System.out.println("Вы ввели что-то странное, попробуйте ещё раз");
                    flagProblem = true;
                }

                if (!flagProblem) {
                    logicService.MathPreparation();
                    if (logicService.MathTesting()) {
                       logicService.MathStarts();
                    } else {
                        System.out.println("Не удалось достичь диагонального преобладания. Следует поменять коэффициенты вручную");
                    }
                }

            }
            System.out.println("Осуществлён выход из программы");
            InputService.scanner.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}