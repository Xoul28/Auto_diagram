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

double calculate()
{
    return (sqrt(5) + sqrt(7)) / (7 + 5) +
           (sqrt(2) + sqrt(8)) / (8 + 2) +
           (sqrt(3) + sqrt(10)) / (10 + 3);
}

void __fastcall TForm1::Button1Click(TObject *Sender)
{
double x = calculate();
ShowMessage(FloatToStr(x));
}
//---------------------------------------------------------------------------

double arifm(double a, double b)
{
    return (a + b) / 2;
}

void __fastcall TForm1::Button2Click(TObject *Sender)
{
double a = StrToFloat(Edit1->Text);
double b = StrToFloat(Edit2->Text);
double ans = arifm(4 * b - a, 2 * a + a) - arifm(a - b, pow(b, a - 2));
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

void shift()
{
for (int i = 1; i < n; i++) {
    mas[i - 1] = mas[i];
}
mas[n - 1] = 0;
}

void __fastcall TForm1::Button3Click(TObject *Sender)
{
mas = new double[n];
for (int i = 0; i < n; i++) {
    mas[i] = StrToFloat(StringGrid1->Cells[i][0]);
}
shift();
for (int i = 0; i < n; i++) {
    StringGrid1->Cells[i][0] = FloatToStr(mas[i]);
}
delete [] mas;
}
//---------------------------------------------------------------------------
int reverse(int n)
{
int ans = 0;
while (n > 0) {
    ans = ans * 10 + n % 10;
    n /= 10;
}
return ans;
}

void __fastcall TForm1::Button4Click(TObject *Sender)
{
const int N = 5;
int numbers[N];
for (int i = 0; i < N; i++) {
    numbers[i] = StrToInt(Memo1->Lines->Strings[i]);
}
for (int i = 0; i < N; i++) {
    numbers[i] = reverse(numbers[i]);
}
Memo1->Clear();
for (int i = 0; i < N; i++) {
    Memo1->Lines->Add(IntToStr(numbers[i]));
}
}
//---------------------------------------------------------------------------
