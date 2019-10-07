.text
.globl tstring

tstring:

	pushl %ebp
	movl %esp, %ebp
	subl $0, %esp
	
	
	movl 8(%ebp), %ebx 				#ebx guarda a base do vetor CODIFICADA
	movl $0, %esi 					#esi ser� o contador i do vetor CODIFICADA
	movl $0, %edi 					#edi ser� o contador j do vetor CODIFICADA
	movl $0, %ecx					#ecx ser� o nosso flag


	LWhile:						#Inicio do while
		cmpl %esi, 12(%ebp)			#Se i == tamanho, entao sai do while	
		je	LFimWhile			#Pula para o fim do while
		
		cmpl $0, %ecx				#Verifica se o flag � zero
		jne	LElseIf1				#Quando flag for diferente de zero, salta para o else do if1	
			cmpb $' ', 0(%ebx, %esi, 1)		#Verifica se a posi��o i do vetor � um espa�o
			jne	LNaoEEspaco			#Pula se n�o for espa�o

			movb 0(%ebx, %esi, 1), %edx     #edx � um resgitrador de trabalho que recebe v[i]
			movb %edx, 0(%ebx, %edi, 1)	#v[j] = v[i] 
			movl $0, %ecx			#zera o flag
			addl $1, %esi			#i++
			addl $1, %edi			#j++
			jmp LWhile

			LNaoEEspaco:
			movl $1, %ecx			#flag = 1
			addl $1, %esi			#i++
			jmp LWhile			#Volta para o inicio do while

		LElseIf1:
			movb 0(%ebx, %esi, 1), %edx     #edx � um resgitrador de trabalho que recebe v[i]
			movb %edx, 0(%ebx, %edi, 1)	#v[j] = v[i] 
			movl $0, %ecx			#zera o flag
			addl $1, %esi			#i++
			addl $1, %edi			#j++
			jmp LWhile
	LFimWhile:		
		
	movb $'\0', 0(%ebx, %edi, 1)
	
	movl %ebp, %esp
	popl %ebp
ret
	
	