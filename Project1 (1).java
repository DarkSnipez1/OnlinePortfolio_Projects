public class Project1 {
    public static final int Num_Lines = 4;

    public static void main(String[] args) {
        double[] w = {2, 2, 2, 2, -6, -16, -22, -32, -1, 1, 1, -1.9, 10.9};
        double[] x_train = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        double[] y_train = {10, 10, 10, 10, 8, 6, 4, 2, 0, 0, 0, 0, 2, 4, 6, 8, 10, 10, 10, 10, 10}; 
        double[][] result = GradDescend(w, x_train, y_train);
        double b_out = result[0][3 * Num_Lines]; 
        double cost = result[0][1];


        //initial weights
        for (int i = 0; i < 4; i++) {
            System.out.println("w" + (i + 1) + "= " + String.format("%.4f", result[0][i]));
        }
      
        //initial biases
        for (int i = 4; i < 8; i++) {
            System.out.println("b" + (i - 3) + "= " + String.format("%.4f", result[0][i]));
        }
        //output weights
        for (int i = 8; i < 12; i++) {
            System.out.println("w_out" + (i - 7) +  "= " + String.format("%.4f", result[0][i]));
        }

        // output bias/cost
        System.out.println("b_out= " + String.format("%.4f", b_out) + ", cost= " + String.format("%.4f", cost));
    }

  
    public static double Relu(double y) {
        double y_relu;
        if(y>0) {
            y_relu = y;
        }else {
            y_relu = 0.001 * y;
        }
        return y_relu;


        //return Math.max(0, y); 
    }

    public static double Cost(double[] w, double[] x_train, double[] y_train) {
        double cost = 0;
        double[] y_relu = new double[y_train.length];
        double[] w_in = new double[Num_Lines];
        double[] b_in = new double[Num_Lines];
        double[] w_out = new double[Num_Lines];
        double b_out = w[3 * Num_Lines];

        for (int i = 0; i < Num_Lines; i++) {
            w_in[i] = w[i];
            b_in[i] = w[i + Num_Lines];
            w_out[i] = w[i + 2 * Num_Lines];
        }

        for (int i = 0; i < x_train.length; i++) {
            y_relu[i] = 0.0;
            for (int j = 0; j < Num_Lines; j++) {
                y_relu[i] += w_out[j] * Relu(w_in[j] * x_train[i] + b_in[j]);
            }
            y_relu[i] += b_out;
            cost += Math.pow(y_relu[i] - y_train[i], 2);
        }

        return cost / y_train.length; // return average cost (recommendation)
    }

    public static double[][] GradDescend(double[] w, double[] x_train, double[] y_train) {
        double[][] result = new double[1][w.length];
        double slope_w;
        double dw = 0.001;
        double[] w_estimate = w.clone(); 
        double dCost_w = 10;
        double tolerance = 0.000000001;
        int iteration = 0;
        int Max_Iteration = 1000000; // reduced for testing purposes, increase later

        while (Math.abs(dCost_w) > tolerance && iteration < Max_Iteration) {
            for (int i = 0; i < w.length; i++) {
                double original_value = w_estimate[i];

                w_estimate[i] = original_value + dw;
                double cost_up = Cost(w_estimate, x_train, y_train);

                w_estimate[i] = original_value - dw;
                double cost_down = Cost(w_estimate, x_train, y_train);

                dCost_w = cost_up - cost_down;
                slope_w = dCost_w / (2 * dw);
                w_estimate[i] = original_value - slope_w * dw; 

                if (iteration % 100000 == 0) {
                    double cost = Cost(w_estimate, x_train, y_train);
                    System.out.println("Iteration= " + iteration + ", cost= " + cost);
                }
            }
            iteration++;
        }
        
        result[0] = w_estimate; 
       
        return result;
    }
}