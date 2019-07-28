package  KohonenNN;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Float.NaN;

public class Neuron {

    ArrayList<Double> inputs;
    ArrayList<Double> weights;
    int row, column;
    double distance;

    public Neuron(){}

    public void init(int weigthsSize,int row,int column){

        this.row = row;
        this.column = column;
        distance = 0.0;
        weights = new ArrayList<Double>();
        Random r = new Random();

        for (int i  = 0 ; i < weigthsSize ; i++){
            weights.add(r.nextDouble());
        }
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public void setInputs(ArrayList<Double> inputs){
        this.inputs = inputs;
    }

    public double getWeight(int index){
        return  weights.get(index);
    }

    public void calculate(){

        distance = 0;

        for(int i = 0; i < inputs.size(); i++){

          distance += Math.pow(inputs.get(i) - weights.get(i),2);
        }

        distance = Math.sqrt(distance);

       // System.out.println("Distance " + Double.toString(distance));
    }

    public double getDistance(){
        return  distance;
    }

    public void updateWeights(double learningRate,double sigma,Neuron bmu){

        for(int i = 0; i < weights.size(); i++){
            double newWeight = weights.get(i) + learningRate*neighbourhoodFunction(bmu,sigma)*(inputs.get(i) - weights.get(i));
            weights.set(i,newWeight);
        }
    }

    private double neighbourhoodFunction(Neuron bmu,double sigma){
        // Calculate distance
        double colums =  Math.pow(bmu.getColumn() -  getColumn(),2);
        double rows =  Math.pow(bmu.getRow() - getRow(),2);
        double bmuDistance = Math.sqrt(colums+rows);
        double result = Math.exp(-Math.pow(bmuDistance,2) / (2*Math.pow(sigma,2)));

        return  result;
    }
}
