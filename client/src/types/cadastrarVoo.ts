import { z } from 'zod'

export const Cadastrar = z.object({
    dataHora: z.preprocess(
		(val) => {
			if (typeof val === 'string') {
				const match = val.match(/^(\d{2})\/(\d{2})\/(\d{4})$/)
				if (match) {
					const [, dd, mm, yyyy] = match
					return `${yyyy}-${mm}-${dd}`
				}
			}
			return val
		},
		z.string().regex(/^\d{4}-\d{2}-\d{2}$/, 'Data inválida')
	),
    aeroportoOrigem: z.string().length(3, { message: 'Aeroporto Origem deve ter 3 dígitos' }).trim(),
    aeroportoDestino: z.string().length(3, { message: 'CPF deve ter 11 dígitos' }).trim(),
    valorPassagem: z.string().regex(/^\d+$/, { message: 'Telefone deve conter apenas números' }).trim(),
    quantidadeMilhas: z.string().trim(),
    quantidadePoltronas: z.string().regex(/^\d+$/, { message: 'Quantidade de Poltronas deve conter apenas números' }).trim(),
})

export type CadastroVoo = z.infer<typeof Cadastrar>
