#include <stdio.h>
//gcc main.c arquivo.s -o output.out
extern int soma(int, int) __asm("soma");

void main(){
	int a, b;
    int  res;
    a = 5;
    b = 6;
    // Call the assembly function to add the numbers
    res = soma(a,b);
	printf("The sum as computed in assembly is : %d \n", res);
	system("pause");
}