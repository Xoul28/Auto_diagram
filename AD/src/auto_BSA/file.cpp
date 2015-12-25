double **mas = new double*[n];
for (int i = 0; i < n; i++)
    mas[i] = new double[m];
for (int i = 0; i < n; i++)
    for (int j = 0; j < m; j++)
        mas[i][j] = StrToFloat(Matrix->Cells[j][i]);
if (Task1RB->Checked) {
    float *arr=new float[(n/2)*(n/2)];
    int k = n;
    for (int i =n/2;i<n;i++){
    k--;
    int ind=0;
      for(int j = 0;j<n;j++){
          if(j<(n-k)||j>(k-1)){
            arr[ind] = mas[i][j];
            ind++;
          }
      }
      }
      float sum = 0;
      for(int i =0;i<((n/2)*(n/2)-1);i++){
         float pov = arr[i];
         for(int j=i+1;j<(n/2)*(n/2);j++){
           if(abs(arr[j]-pov)<1.0e-5){
            arr[i]=0;
            arr[j]=0;
           }
         }
        sum+=arr[i];
      }
     if(sum)
    ShowMessage(FloatToStr(sum));
    else
    ShowMessage("таких нет");
} else if (Task2RB->Checked) {
    // TODO 2nd task
   float max = -99;
   int indi =0,indj=0;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++) {
            // mas[i][j]
            if(mas[i][j]>max){
             max=mas[i][j];
                 indi=i;
                 indj=j;
            }
            }
           // ShowMessage(FloatToStr(max));
    float buf = 0;
    for(int i = indi;i>0;i--){
      for(int j = 0;j<m;j++){
       buf=mas[i-1][j];
       mas[i-1][j]=mas[i][j];
       mas[i][j]=buf;
      }
    }

      for(int j = indj;j>0;j--){
          for(int i = 0;i<n;i++){
                buf=mas[i][j-1];
                mas[i][j-1]=mas[i][j];
                mas[i][j]=buf;
          }
      }
      for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
        Matrix->Cells[i][j] = FloatToStr(mas[j][i]);
}

/*for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
        Matrix->Cells[i][j] = FloatToStr(mas[j][i]);
//
 */
for (int i = 0; i < n; i++)
    delete [] mas[i];
delete [] mas;