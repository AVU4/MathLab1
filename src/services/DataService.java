package services;

import javax.xml.crypto.Data;

public class DataService {

    private static DataService instance;


    private double[][] matrixOfCoefficients;
    private int row;
    private int size;
    private double[] columnOfFreeMember;
    private double accuracy;
    private double[] valuesOfX;
    private int numOfIteration;


    private DataService(){};

    public static DataService getInstance(){
        if (instance == null){
            instance = new DataService();
        }
        return instance;
    }

    public double[][] getMatrixOfCoefficients() {
        return matrixOfCoefficients;
    }

    public void setMatrixOfCoefficients(double[][] matrixOfCoefficients) {
        this.matrixOfCoefficients = matrixOfCoefficients;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double[] getColumnOfFreeMember() {
        return columnOfFreeMember;
    }

    public void setColumnOfFreeMember(double[] columnOfFreeMember) {
        this.columnOfFreeMember = columnOfFreeMember;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double[] getValuesOfX() {
        return valuesOfX;
    }

    public void setValuesOfX(double[] valuesOfX) {
        this.valuesOfX = valuesOfX;
    }

    public int getNumOfIteration() {
        return numOfIteration;
    }

    public void setNumOfIteration(int numOfIteration) {
        this.numOfIteration = numOfIteration;
    }
}
