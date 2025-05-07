import { z } from 'zod'

export const User = z.object({
	cpf: z
		.string()
		.min(11, { message: 'CPF inválido' })
		.max(11, { message: 'CPF inválido' })
		.regex(/^\d+$/, { message: 'Digite apenas números' })
		.trim(),
	email: z.string().email({ message: 'E-mail inválido' }).trim(),
	nome: z.string().min(2).trim(),
	saldoMilhas: z.number(),
	cep: z.string().length(8).trim(),
	logradouro: z.string().trim(),
	numero: z.string().trim(),
	complemento: z.string().trim().optional(),
	bairro: z.string().trim(),
	cidade: z.string().trim(),
	estado: z.string().trim(),
})

export type User = z.infer<typeof User>
