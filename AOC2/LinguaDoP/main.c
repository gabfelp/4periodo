#include <stdlib.h>
#include <stdio.h>
void main(){
	
	char codificada[1000];
	printf("Insira a mensagem codificada: ");
	scanf("%[^\n]", codificada);
	int tamanho = getTamanho(codificada);
	
	printf("%d\n", tamanho);

	printf("Frase digitada: ");
	imprime(codificada, tamanho);

	tstring(codificada, tamanho);

	printf("Nova frase: ");
	tamanho = getTamanho(codificada);
	imprime2(codificada);
}

int getTamanho(char *codificada){
	int i;
	for(i = 0; ;i++){
		if(codificada[i] == '\0'){
			return i;	
		}
	}
	
}

void imprime2(char *frase){
	int i;
	i = 0;
	while(frase[i]!='0'){
		printf("%c", frase[i]);
		i++;
	}
	printf("\n");
}

void imprime(char *frase, int tamanho){
	int i;
	for(i = 0; i<tamanho; i++){
		printf("%c", frase[i]);
	}
	printf("\n");
}
