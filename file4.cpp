//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop
#include <math.h>
#include "Unit1.h"
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma link "CSPIN"
#pragma resource "*.dfm"
TForm1 *Form1;
//---------------------------------------------------------------------------
__fastcall TForm1::TForm1(TComponent* Owner)
    : TForm(Owner)
{
}
//---------------------------------------------------------------------------

double calculate(double x, double y)
{
    return exp((sin(y) - x) * (cos(y) + x)) +
           sqrt(fabs(
                     ((sin(y) - x) * (cos(y) + x)) /
                     (pow(10, 5) * log(3 * x))
           ));
}

void __fastcall TForm1::Button1Click(TObject *Sender)
{
double x = StrToFloat(Edit1->Text);
double y = StrToFloat(Edit2->Text);
if (x != 0) {
    double ans = exp((sin(y) - x) * (cos(y) + x)) +
           sqrt(fabs(
                     ((sin(y) - x) * (cos(y) + x)) /
                     (pow(10, 5) * log(3 * x))
           ));
    ShowMessage(FloatToStr(ans));
}
z = pow(sin(x)-pow(y,(3+20)/9), 2);
z = ((1+2)/(3+4));
z = calculate(1/2, 1) + 1/2.;
}
//---------------------------------------------------------------------------

double arifm(double a, double b, double c)
{
    return (a + b + c) / 3;
}

void __fastcall TForm1::Button2Click(TObject *Sender)
{
double a = StrToFloat(Edit3->Text);
double b = StrToFloat(Edit4->Text);
double c = StrToFloat(Edit5->Text);
z = calculate(1, 2)+1/2.;
z = f() + fabs(pow(3/2.,3/2.));
double ans = arifm((a + b) / (a - c * b), b * b - 2 * a * c, 3 * pow(c, a - b)) -
             arifm((c + a) / (a * b), sqrt(b) / pow(a, 1. / 3), pow(a, b + 1. / c));
ShowMessage(FloatToStr(ans));
}
//---------------------------------------------------------------------------
int n = 1;
void __fastcall TForm1::CSpinEdit1Change(TObject *Sender)
{
if (CSpinEdit1->Text != "") {
    n = CSpinEdit1->Value;
    StringGrid1->ColCount = n;
}
}
//---------------------------------------------------------------------------
double *mas;

void shift(int k)
{
for (int i = 0; i < k; i++) {
    double tmp = mas[0];
    for (int j = 1; j < n; j++) {
        mas[j - 1] = mas[j];
    }
    mas[n - 1] = tmp;
}
}

void __fastcall TForm1::Button3Click(TObject *Sender)
{
int k = CSpinEdit2->Value;
mas = new double[n];
for (int i = 0; i < n; i++) {
    mas[i] = StrToFloat(StringGrid1->Cells[i][0]);
}
shift(k);
for (int i = 0; i < n; i++) {
    StringGrid1->Cells[i][0] = FloatToStr(mas[i]);
}
delete [] mas;
}
//---------------------------------------------------------------------------
double perimeter(double w1, double w2, double h)
{
double c = sqrt(pow(fabs(w1 - w2) / 2, 2) + pow(h, 2));
return w1 + w2 + 2 * c;
}

void __fastcall TForm1::Button4Click(TObject *Sender)
{
const int N = 2;
double w[N][2];
double h[N];
for (int i = 0; i < 2; i++) {
    for (int j = 0; j < 2; j++) {
        w[i][j] = StrToFloat(StringGrid2->Cells[j + 1][i + 1]);
    }
    h[i] = StrToFloat(StringGrid2->Cells[3][i + 1]);
}
double s = 0;
for (int i = 0; i < 2; i++) {
    s += perimeter(w[i][0], w[i][1], h[i]);
}
ShowMessage(FloatToStr(s));
}
//---------------------------------------------------------------------------
void __fastcall TForm1::FormCreate(TObject *Sender)
{
StringGrid2->Cells[1][0] = "Основание 1";
StringGrid2->Cells[2][0] = "Основание 2";
StringGrid2->Cells[3][0] = "Высота";
StringGrid2->Cells[0][1] = "1";
StringGrid2->Cells[0][2] = "2";
}
//---------------------------------------------------------------------------
