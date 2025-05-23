import { z } from 'zod'

export const UserSignup = z.object({
	cpf: z.string().min(14, { message: 'CPF inválido' }).max(14, { message: 'CPF inválido' }).trim(),
	email: z.string().email({ message: 'E-mail inválido' }).trim(),
	nome: z.string().min(2, { message: 'Digite um nome completo' }).trim(),
	saldoMilhas: z.number(),
	cep: z.string().length(9, { message: 'CEP inválido' }).trim(),
	logradouro: z.string().trim(),
	numero: z.string().regex(/^\d+$/, { message: 'Digite apenas números' }).trim(),
	complemento: z.string().trim().optional(),
	bairro: z.string().trim(),
	cidade: z.string().trim(),
	estado: z.string().trim(),
})

export type UserSignup = z.infer<typeof UserSignup>

export const UserLogin = z.object({
	email: z.string().email({ message: 'E-mail inválido' }).trim(),
	senha: z.string().min(6, { message: 'Senha deve ter no mínimo 6 caracteres' }).trim(),
})

export type UserLogin = z.infer<typeof UserLogin>
