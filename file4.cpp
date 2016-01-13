void __fastcall TForm1::SolveBtnClick(TObject *Sender)
{
double **mas = new double*[n];
for (int i = 0; i < n; i++)
    mas[i] = new double[m];
for (int i = 0; i < n; i++)
    for (int j = 0; j < m; j++)
        mas[i][j] = StrToFloat(Matrix->Cells[j][i]);
if (Task1RB->Checked) {
    const double MAX = 1e+27;
    double min = MAX;
    for (int i = 0; i < n; i++) {
        int b;
        if (i < n / 2) {
            b = n / 2 + i;
        } else {
            b = n / 2 + n - 1 - i;
        }
        for (int j = b; j < n; j++) {
            //mas[i][j] = 0;
            // double to int type cast
            //*
            int t = mas[i][j];
            bool b = (t > 9 || t < -9) && abs(t/10%10-t%10) == 2;
            if (mas[i][j] < min && b)
                min = mas[i][j];
            //*/
        }
    }
    if (min == MAX) {
        ShowMessage("Такого элемента нет");
    } else {
        ShowMessage(FloatToStr(min));
    }
} else if (Task2RB->Checked) {
    // TODO 2nd task
    const double MIN = -1e+27;
    double max = MIN;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++) {
            // mas[i][j]
            bool lmin = true;
            if (i > 0)
                lmin = lmin && (mas[i][j] < mas[i - 1][j]);
            if (i < n - 1)
                lmin = lmin && (mas[i][j] < mas[i + 1][j]);
            if (j > 0)
                lmin = lmin && (mas[i][j] < mas[i][j - 1]);
            if (j < m - 1)
                lmin = lmin && (mas[i][j] < mas[i][j + 1]);
            if (lmin && (mas[i][j] > max))
                max = mas[i][j];
        }
    if (max == MIN) {
        ShowMessage("Такого элемента нет");
    } else {
        ShowMessage(FloatToStr(max));
    }
}
/*
for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
        Matrix->Cells[i][j] = FloatToStr(mas[j][i]);
//*/
for (int i = 0; i < n; i++)
    delete [] mas[i];
delete [] mas;
}
