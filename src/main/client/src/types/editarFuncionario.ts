import { z } from 'zod'

export const Editar = z.object({
    nome: z.string().min(2).trim(),
    cpf: z.string().length(11, { message: 'CPF deve ter 11 dígitos' }),
    email: z.string().email({ message: 'E-mail inválido' }).trim(),	
    telefone: z.string().length(16, { message: 'Telefone deve ter 11 dígitos' }),
})

export type EditarFunc = z.infer<typeof Editar>
