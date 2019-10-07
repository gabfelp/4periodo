#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
	
	char codigo[1000];
	printf("insira mensagem ");
	scanf("%[^\n]",codigo);
	int size = getSize(codigo);
	
	decodifica(codigo,size);
	
	printf("mensagem decodificada: ");
	size =getSize(codigo);
	imprime(codigo);
}

int getSize(char *codigo){
	int j;
	for(j=0; ;j++){
		if(codigo[j]=='\0'){
			return j;
		}
	}
}
void imprime(char *dec){
	int i;
	i=0;
	while(dec[i]!='0'){
		printf("%c",dec[i]);
		i++;
	}
	printf("\n");
}