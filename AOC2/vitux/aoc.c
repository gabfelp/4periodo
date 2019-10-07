#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char** argv) {
    char frase[1000], decod[1000];
    printf("Digite a frase na língua do P:\n");
    gets(frase);
    int i = 0;
    int d = 0;
    int p = 0;
    strcat(frase, "p");
    do {
        if ( frase[i] == 'p') {
		p++;
		if(p>1){
			decod[d] = frase[i];
	    		d++;
	    		p=0;
		}
	}else{
	    	decod[d] = frase[i];
	    	d++;
	    	p=0;
	} 	
        i++;
    } while (i < 1000);
    puts(decod);
    return (EXIT_SUCCESS);
}