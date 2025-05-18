import { z } from 'zod'

export const Confirmacao = z.object({

    dataHora: z.string().trim(),
    aeroportoOrigem: z.string().trim(),
    aeroportoDestino: z.string().trim(),
    codigoReserva: z.string().length(6, { message: 'Código de reserva deve ter 6 dígitos' }),
})

export type ConfirmacaoEmb = z.infer<typeof Confirmacao>
