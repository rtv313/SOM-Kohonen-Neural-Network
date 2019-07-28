package KohonenNN;

import java.util.ArrayList;

public class KohonenNN {
    private ArrayList<Double> inputs;
    private OutputLayer outputLayer;
    private double learningRate;
    private int epochs;
    private int size;

    public KohonenNN(){}

    public void init(int size,ArrayList<Double> inputs,double learningRate,int epochs){
        outputLayer = new OutputLayer();
        outputLayer.init(size,inputs);
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.size = size;
    }

    public int getEpochs(){
        return  epochs;
    }

    public  int getSize(){
        return  size;
    }

    public double getLearningRate(){
        return  learningRate;
    }

    public void setInput(ArrayList<Double> inputs){
        this.inputs = inputs;
    }

    public void calculate(){
        outputLayer.setInputs(inputs);
        outputLayer.calculate();
    }

    public void updateWeights(double learningRate,double sigma){
        outputLayer.updateNeurons(learningRate,sigma);
    }

    public Neuron getBmu(){
        return  outputLayer.getBmuNeuron();
    }
}
