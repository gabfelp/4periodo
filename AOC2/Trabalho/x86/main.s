	.text
	.globl	main
main:
	pushl	%ebp
	movl	%esp, %ebp
	movl	8(%ebp), %eax
	cmpl	12(%ebp), %eax
	jle	.J2
	movl	$1, %eax
	jmp	.J1
.J2:
	movl	$0, %eax
.J1:
	popl	%ebp
ret