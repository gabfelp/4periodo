	.file	"aoc.c"
	.def	___main;	.scl	2;	.type	32;	.endef
	.section .rdata,"dr"
	.align 4
LC0:
	.ascii "Digite a frase na l\355ngua do P:\0"
	.text
.globl _main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%edi
	pushl	%esi
	pushl	%ebx
	subl	$2060, %esp
	andl	$-16, %esp
	movl	$16, %eax
	call	__alloca
	call	___main
	movl	$LC0, (%esp)
	call	_puts
	leal	-1032(%ebp), %eax
	movl	%eax, (%esp)
	call	_gets
	movl	$0, %edx
	movl	$0, %esi
	movl	$0, -2044(%ebp)
	leal	-1032(%ebp), %ebx
	cld
	movl	$-1, %ecx
	movb	$0, %al
	movl	%ebx, %edi
	repne
	scasb
	notl	%ecx
	movw	$112, -1033(%ecx,%ebp)
L2:
	cmpb	$112, -1032(%edx,%ebp)
	jne	L5
	incl	-2044(%ebp)
	cmpl	$1, -2044(%ebp)
	jle	L7
	movb	$112, -2040(%esi,%ebp)
	incl	%esi
	movl	$0, -2044(%ebp)
	jmp	L7
L5:
	movzbl	-1032(%edx,%ebp), %eax
	movb	%al, -2040(%esi,%ebp)
	incl	%esi
	movl	$0, -2044(%ebp)
L7:
	incl	%edx
	cmpl	$999, %edx
	jle	L2
	leal	-2040(%ebp), %eax
	movl	%eax, (%esp)
	call	_puts
	movl	$0, %eax
	leal	-12(%ebp), %esp
	popl	%ebx
	popl	%esi
	popl	%edi
	popl	%ebp
	ret
	.def	_puts;	.scl	2;	.type	32;	.endef
	.def	_puts;	.scl	2;	.type	32;	.endef
	.def	_gets;	.scl	2;	.type	32;	.endef
