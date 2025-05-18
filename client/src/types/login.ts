import { z } from 'zod'

export const Login = z.object({
	email: z.string().email().trim(),
	senha: z
		.string()
		.min(4, { message: 'A senha deve ter 4 caracteres' })
		.max(4, { message: 'A senha deve ter 4 caracteres' }),
})

export type LoginForm = z.infer<typeof Login>
