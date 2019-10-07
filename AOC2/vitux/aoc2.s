	.file	"aoc.c"
	.def	___main;	.scl	2;	.type	32;	.endef
	.section .rdata,"dr"
	.align 4
LC0:
	.ascii "Digite a frase na l\355ngua do P:\12\0"
LC1:
	.ascii "p\0"
	.text
.globl _main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$2072, %esp
	andl	$-16, %esp
	movl	$0, %eax
	addl	$15, %eax
	addl	$15, %eax
	shrl	$4, %eax
	sall	$4, %eax
	movl	%eax, -2044(%ebp)
	movl	-2044(%ebp), %eax
	call	__alloca
	call	___main
	movl	$LC0, (%esp)
	call	_printf
	leal	-1016(%ebp), %eax
	movl	%eax, (%esp)
	call	_gets
	movl	$0, -2028(%ebp)
	movl	$0, -2032(%ebp)
	movl	$0, -2036(%ebp)
	movl	$LC1, 4(%esp)
	leal	-1016(%ebp), %eax
	movl	%eax, (%esp)
	call	_strcat
L2:
	leal	-8(%ebp), %eax
	addl	-2028(%ebp), %eax
	subl	$1008, %eax
	cmpb	$112, (%eax)
	jne	L5
	leal	-2036(%ebp), %eax
	incl	(%eax)
	cmpl	$1, -2036(%ebp)
	jle	L7
	leal	-8(%ebp), %eax
	addl	-2032(%ebp), %eax
	leal	-2016(%eax), %edx
	leal	-8(%ebp), %eax
	addl	-2028(%ebp), %eax
	subl	$1008, %eax
	movzbl	(%eax), %eax
	movb	%al, (%edx)
	leal	-2032(%ebp), %eax
	incl	(%eax)
	movl	$0, -2036(%ebp)
	jmp	L7
L5:
	leal	-8(%ebp), %eax
	addl	-2032(%ebp), %eax
	leal	-2016(%eax), %edx
	leal	-8(%ebp), %eax
	addl	-2028(%ebp), %eax
	subl	$1008, %eax
	movzbl	(%eax), %eax
	movb	%al, (%edx)
	leal	-2032(%ebp), %eax
	incl	(%eax)
	movl	$0, -2036(%ebp)
L7:
	leal	-2028(%ebp), %eax
	incl	(%eax)
	cmpl	$999, -2028(%ebp)
	jg	L3
	jmp	L2
L3:
	leal	-2024(%ebp), %eax
	movl	%eax, (%esp)
	call	_puts
	movl	$0, %eax
	leave
	ret
	.def	_puts;	.scl	2;	.type	32;	.endef
	.def	_strcat;	.scl	2;	.type	32;	.endef
	.def	_gets;	.scl	2;	.type	32;	.endef
	.def	_printf;	.scl	2;	.type	32;	.endef
