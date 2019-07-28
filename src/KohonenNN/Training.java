package KohonenNN;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Training {
    int epochs;
    double radioStart;
    double sigma;
    double learningRateStart;
    double learningRate;
    KohonenNN kohonenNN;
    ArrayList<Iris> trainingSet;

    public void init(KohonenNN kohonenNN){
        trainingSet = new ArrayList<Iris>();
        loadTrainingSet();
        this.kohonenNN = kohonenNN;
        this.epochs = kohonenNN.getEpochs();
        learningRateStart = kohonenNN.getLearningRate();
        learningRate = 0.0;
        radioStart = kohonenNN.getSize() / 2;
    }

    public  void loadTrainingSet(){

        String csvFile = System.getProperty("user.dir")+"\\src"+"\\KohonenNN"+"\\iris.csv";System.out.println(csvFile);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] plant = line.split(cvsSplitBy);
                double sepalLength = Double.parseDouble(plant[0]);
                double sepalWidth = Double.parseDouble(plant[1]);
                double petalLength = Double.parseDouble(plant[2]);
                double petalWidth = Double.parseDouble(plant[3]);
                String kind = plant[4];
                Iris iris = new Iris(sepalLength,sepalWidth,petalLength,petalWidth,kind);
                trainingSet.add(iris);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Data set Size " + trainingSet.size() );
    }

    public void trainKNN(){

        for(int i = 0; i < epochs;i++){


            for(int j=0; j < trainingSet.size();j++){
                Iris iris = trainingSet.get(j);
                ArrayList<Double> input = new ArrayList<Double>();
                input.add(iris.getPetalLength());
                input.add(iris.getPetalWidth());
                input.add(iris.getSepalLength());
                input.add(iris.getSepalWidth());

                kohonenNN.setInput(input);
                kohonenNN.calculate();
                kohonenNN.updateWeights(learningRate,sigma);
            }

            updateLearningRate(i);
            updateSigma(i);
        }
    }

    private void updateLearningRate(int epoch){
        learningRate = learningRateStart * Math.exp(-(double)epoch/epochs);
    }


    private void updateSigma(int epoch){
        if(epoch > 0 ) {
            double lambda = epochs / Math.log(radioStart);
            sigma = radioStart * Math.exp(-(double) epoch / lambda);
        }else{
            sigma = radioStart;
        }
    }

    public ArrayList<Iris> getTrainingSet(){
        return  trainingSet;
    }
}
