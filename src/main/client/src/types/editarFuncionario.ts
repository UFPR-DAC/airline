import { z } from 'zod'

export const Editar = z.object({
    email: z.string().email({ message: 'E-mail inválido' }).trim(),	
    nome: z.string().min(2).trim()
})

export type EditarFunc = z.infer<typeof Editar>
