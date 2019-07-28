package KohonenNN;

import java.util.ArrayList;

public class OutputLayer {

    int size;
    double initialRadio;
    Neuron[][] neurons;
    private Neuron bmuNeuron;

    public OutputLayer(){}

    public int getSize(){
        return  size;
    }

    public void init(int size,ArrayList<Double> inputs){

        this.size = size;
        initialRadio = this.size / 2;
        neurons = new Neuron[size][size];
        for(int i = 0; i < size; i ++){
            for(int j=0; j < size; j++){
                neurons[i][j] = new Neuron();
                neurons[i][j].init(inputs.size(),i,j);
            }
        }

    }

    public void setInputs(ArrayList<Double> inputs){
        for(int i = 0; i < size; i ++){
            for(int j=0; j < size; j++){
                neurons[i][j].setInputs(inputs);
            }
        }
    }

    public void calculate(){

        neurons[0][0].calculate();
        double distance = neurons[0][0].getDistance();

        for(int i = 1; i < neurons.length; i ++){

            for(int j=1; j < neurons.length; j++){
                neurons[i][j].calculate();

                if(neurons[i][j].getDistance()  < distance){
                    bmuNeuron = neurons[i][j];
                    distance = neurons[i][j].getDistance();
                }
            }
        }
    }

    public Neuron getBmuNeuron()
    {
        return bmuNeuron;
    }

    public void updateNeurons(double learningRate,double sigma){

        for(int i = 0; i < neurons.length; i ++){

            for(int j=0; j < neurons.length; j++){
                Neuron neuron = neurons[i][j];
                double colums =  Math.pow(bmuNeuron.getColumn() - neuron.getColumn(),2);
                double rows =  Math.pow(bmuNeuron.getRow() - neuron.getRow(),2);
                double distanceToBmu = Math.sqrt(colums+rows);

                if(distanceToBmu <= sigma){ // sigman es el radio
                    neuron.updateWeights(learningRate,sigma,bmuNeuron);
                }
            }
        }
    }
}
