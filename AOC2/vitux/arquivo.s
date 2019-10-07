.text
.global soma
soma:
	pushl %ebp
	movl %esp,%ebp
	subl $0,%esp
	
	movl 8(%ebp),%eax
	addl 12(%ebp),%eax
	
	movl %ebp,%esp
	popl %ebp
ret
