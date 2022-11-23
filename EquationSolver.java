class EquationSolver implements Observer{
  /**
   * THE FOLLOWING CODE IS THE ALGORITHM IN ORDER TO GET THE ANSWERS THAT IS FORMED FROM THE 2D ARRAY MATRIX_VAR
   */
  //@param matrix_var IS THE 2-DIMENSIONAL ARRAY OF CONSTANTS AND COEFFICIENTS
  //@return A 1-DIMENSIONAL ARRAY 
  public static double[] solve_quest(double[][] matrix_var) {
    int Widths = matrix_var.length;
    double[] ans = new double[Widths]; //THE VARIABLES ANSWERS ARE IN THIS CODE 
    //USES FORWARD ELIMINATION
    for(int i = 0; i < Widths-1; i++) {
      for(int j = i+1; j < Widths; j++) {
        double Ratio = matrix_var[j][i]/matrix_var[i][i];
        for(int x = i; x < Widths+1; x++) {
          matrix_var[j][x] = matrix_var[j][x] - Ratio*matrix_var[i][x];
        }
      }
      //THE FOLLOWING CODE USES THE GAUSSIAN ELIMINATION ALGORITHM IN ORDER TO SOLVE THE LINEAR EQUATIONS 
    }
    //FOLLOWING CODE USES BACKWARD SUBSTRACTING
    ans[Widths-1] = matrix_var[Widths-1][Widths] / matrix_var[Widths-1][Widths-1];
    for(int i = Widths-2; i > -1; i--) {
        double sums = matrix_var[i][Widths];
        for(int j = i+1; j < Widths; j++) {
            sums = sums - matrix_var[i][j]*ans[j];
        }
        ans[i] = sums/matrix_var[i][i];
    }
    //THE ANS IS THE VALUE FOR EACH VARIABLE
    return ans;
  }
  
  // @Override
  public void update(String file) {
    System.out.println("updated QuestionData");
  }
}
