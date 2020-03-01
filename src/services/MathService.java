package services;

public class MathService {

    DataService dataService = DataService.getInstance();
    OutputService output = new OutputService();

    public void generateRandom(){
        int num = dataService.getRow();
        double[] tmp = new double[num];
        int sum = 0;
        double[][] matrix = new double[num][num];

        for (int i = 0; i < num; i ++){
            for (int j = 0; j < num; j ++){
                if (j != num - 1){
                    tmp[j] = Math.random()*5 + 1;
                    sum += tmp[j];
                }else{
                    tmp[j] = sum + Math.random()*3;
                }
            }
            for (int j = 0; j < num; j ++){
                if (j == num - 1){
                    matrix[i][i] = tmp[num - 1];
                }else if (j != i){
                    matrix[i][j] = tmp[j];
                }
            }
            sum = 0;
        }
       dataService.setMatrixOfCoefficients(matrix);

        double[] column = new double[num];

        for (int i = 0; i < num; i ++){
            column[i] = Math.random()*10 + 1;
        }

        dataService.setColumnOfFreeMember(column);

        double[] valuesOfX = new double[num];
        for (int i = 0; i < num; i ++){
            valuesOfX[i] = 0;
        }

        dataService.setValuesOfX(valuesOfX);
        dataService.setAccuracy(0.0001);
        dataService.setNumOfIteration(100);
        output.RandomMatrix();
    }

    public void startMethod(double[][] matrix, double[] freeMembers, double accuracy, int numOfIteration, double[] valuesOfX, int size, int row){


        boolean flagFound = false;
        int currentIteration = 0;
        double[] maxAbsoluteDifs = new double[numOfIteration + 1];
        for (int k = 0; k < maxAbsoluteDifs.length; k ++){
            maxAbsoluteDifs[k] = -1;
        }

        while (currentIteration < numOfIteration){
            double absoluteDif = 0;
            currentIteration ++;

            for (int i = 0; i < row; i ++){
                double x = 0;
                for (int j = 0; j < size; j ++ ){

                    if (j != i){
                        x += matrix[i][j] * valuesOfX[j];
                    }
                }
                x = (freeMembers[i] - x)/matrix[i][i];

                double d = Math.abs(x - valuesOfX[i]);
                if (d > absoluteDif){
                    absoluteDif = d;
                }
                valuesOfX[i] = x;
                if (i == row - 1){
                    maxAbsoluteDifs[currentIteration] = absoluteDif;
                    if (absoluteDif < accuracy){
                        output.printAnswer(currentIteration, maxAbsoluteDifs, valuesOfX);
                        flagFound = true;
                        currentIteration = numOfIteration;
                    }
                }
            }
        }
        if (!flagFound){
            output.printAnswerNotFound();
        }
    }

    public double[][] transformationMatrix(double[][] matrix, double[] columnOfFreeMembers, int rows, int size){
        double[][] resultMatrix = new double[rows][size + 1];
        boolean flagColumnTransform = false;
        int indexOfColumn = 0;
        boolean flagRowTransform = false;
        int indexOfRow = 0;

        for (int i = 0; i < size - 1; i ++){
            double maxNum = matrix[i][i];
            for (int j = i + 1; j < size; j ++){
                if (matrix[i][j] > maxNum){
                    maxNum = matrix[i][j];
                    flagColumnTransform = true;
                    indexOfColumn = j;
                }
            }
            int j = i + 1;
            while (j < rows){
                if (maxNum < matrix[j][i]){
                    maxNum = matrix[j][i];
                    flagColumnTransform = false;
                    flagRowTransform = true;
                    indexOfRow = j;
                }
                j ++;
            }



            if (flagColumnTransform){
                for (int k = 0; k < rows; k ++){
                    double tmp = matrix[k][i];
                    matrix[k][i] = matrix[k][indexOfColumn];
                    matrix[k][indexOfColumn] = tmp;
                }
            }else if (flagRowTransform){
                double[] tmp = matrix[i];
                double tmp2 = columnOfFreeMembers[i];
                columnOfFreeMembers[i] = columnOfFreeMembers[indexOfRow];
                columnOfFreeMembers[indexOfRow] = tmp2;
                matrix[i] = matrix[indexOfRow];
                matrix[indexOfRow] = tmp;
            }
            flagColumnTransform = false;
            flagRowTransform = false;

        }
        for (int k = 0; k < rows; k ++){
            for (int p = 0; p < size + 1; p ++){
                if (p != size){
                    resultMatrix[k][p] = matrix[k][p];
                }else{
                    resultMatrix[k][p] = columnOfFreeMembers[k];
                }
            }
        }
        return resultMatrix;
    }


    public boolean isCorrectMatrix(double[][] matrix){
        for (int i = 0; i < matrix.length; i ++){
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j ++){
                if (j != i){
                    sum += Math.abs(matrix[i][j]);
                }
            }
            if (sum > matrix[i][i]){
                return false;
            }
        }
        return true;
    }
}
