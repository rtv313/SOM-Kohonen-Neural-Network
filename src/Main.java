import KohonenNN.*;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]){

        KohonenNN kohonenNN = new KohonenNN();
        ArrayList<Double> inputs = new ArrayList<Double>();
        inputs.add(0.0);
        inputs.add(0.0);
        inputs.add(0.0);
        inputs.add(0.0);
        kohonenNN.init(10,inputs,0.1,4000);

        Training training = new Training();
        training.init(kohonenNN);
        training.trainKNN();

        ArrayList<Iris> trainingSet = training.getTrainingSet();
        String[][] outputMap =  new String [kohonenNN.getSize()][kohonenNN.getSize()];

        for(int x = 0 ;  x < kohonenNN.getSize() ; x++){

            for(int y = 0 ;  y < kohonenNN.getSize() ; y++){

                outputMap[x][y] = "-";
            }
        }

        for(Iris iris : trainingSet){

            ArrayList<Double> input = new ArrayList<Double>();
            input.add(iris.getPetalLength());
            input.add(iris.getPetalWidth());
            input.add(iris.getSepalLength());
            input.add(iris.getSepalWidth());

            kohonenNN.setInput(input);
            kohonenNN.calculate();
            Neuron bmu = kohonenNN.getBmu();

            if(iris.getKind().equals("Iris-setosa")){
                outputMap[bmu.getRow()][bmu.getColumn()] = "x";
            }

            if(iris.getKind().equals("Iris-versicolor")){
                outputMap[bmu.getRow()][bmu.getColumn()] = "y";
            }

            if(iris.getKind().equals("Iris-virginica")){
                outputMap[bmu.getRow()][bmu.getColumn()] = "z";
            }
        }

        System.out.println("Setosa = X");
        System.out.println("Versicolor = Y");
        System.out.println("Virginica = Z");

        for(int x = 0 ;  x < kohonenNN.getSize() ; x++){

            String row ="";
            for(int y = 0 ;  y < kohonenNN.getSize() ; y++){

                row += outputMap[x][y]+" ";
            }

            System.out.println(row);
        }

    }
}
