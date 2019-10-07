	.file	"teste.c"
	.text
	.globl	laco
	.type	laco, @function
laco:
.LFB0:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$16, %esp
	movl	$0, -8(%ebp)
	movl	$0, -4(%ebp)
	jmp	.L2
.L3:
	addl	$1, -8(%ebp)
	addl	$1, -4(%ebp)
.L2:
	movl	-4(%ebp), %eax
	cmpl	8(%ebp), %eax
	jl	.L3
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE0:
	.size	laco, .-laco
	.ident	"GCC: (Ubuntu 5.4.0-6ubuntu1~16.04.2) 5.4.0 20160609"
	.section	.note.GNU-stack,"",@progbits
