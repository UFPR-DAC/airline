import { z } from 'zod'

export const Cadastro = z.object({
    email: z.string().email({ message: 'E-mail inválido' }).trim(),	
    nome: z.string().min(2).trim()
})

export type CadastroFuncionario = z.infer<typeof Cadastro>
