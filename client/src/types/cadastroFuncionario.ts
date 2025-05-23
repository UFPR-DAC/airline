import { z } from 'zod'

export const Cadastro = z.object({
    nome: z.string().min(2).trim(),
    cpf: z.string().length(11, { message: 'CPF deve ter 11 dígitos' }).regex(/^\d+$/, { message: 'CPF deve conter apenas números' }),
    email: z.string().email({ message: 'E-mail inválido' }).trim(),	
    telefone: z.string().length(11, { message: 'Telefone deve ter 11 dígitos' }).regex(/^\d+$/, { message: 'Telefone deve conter apenas números' }),
})

export type CadastroFuncionario = z.infer<typeof Cadastro>
