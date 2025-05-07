import { z } from 'zod'

export const Login = z.object({
	cpf: z
		.string()
		.min(11, { message: 'CPF inválido' })
		.max(11, { message: 'CPF inválido' })
		.regex(/^\d+$/, { message: 'Digite apenas números' })
		.trim(),
	senha: z
		.string()
		.min(4, { message: 'A senha deve ter 4 caracteres' })
		.max(4, { message: 'A senha deve ter 4 caracteres' }),
})

export type LoginForm = z.infer<typeof Login>
