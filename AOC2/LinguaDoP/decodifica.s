.text
.globl decodifica

decodifica:

	pushl %ebp
	movl %esp,%ebp
	subl $0, %esp
	
	movl 8(%ebp), %ebx #base do vet codigo
	movl $0, %ecx #flag
	movl $0, %esi #contador j
	movl $0, %edi #contador i
	
	aWhile:
		cmpl %edi, 12(%ebp) #i=size, sai while
		je aFimW
		
		cmpl $0, %ecx #flag=0
		jne aElse #flag!=0, pula else 
		cmpb $' ', 0(%ebx, %edi, 1) # i é um espaço ?
		jne NaoEspaco
		
		movb 0(%ebx, %edi,1), %edx #edx = v[i]
		movb %edx,0(%ebx, %esi, 1) #v[i]=v[j]
		movl $0, %ecx #flag 0
		addl $1, %edi #i++
		addl $1, %esi #j++
		jmp aWhile
	
	NaoEspaco:
		movl $1, %ecx #fl 1
		addl $1, %edi #i++
		jmp aWhile
	
	aElse:
		movb 0(%ebx, %edi, 1), %edx #v[i]
		movb %edx, 0(%ebx, %esi, 1) #v[i]=v[j]
		movl $0, %ecx #flag 0
		addl $1, %edi #i++
		addl $1, %esi #j++
	aFimW:
	
	movb $'\0', 0(%ebx, %esi, 1)
	
	movl %ebp, %esp
	popl %ebp
ret