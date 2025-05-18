import { z } from 'zod'

export const OperationTypeEnum = z.enum(['PURCHASE', 'USE'])

export const MilesTransaction = z.object({
	milesAmount: z
		.number({ message: 'O valor informado deve ser apenas em números' })
		.int({ message: 'O valor não deve possuir decimais' })
		.positive({ message: 'O valor não deve ser negativo' })
		.min(1000, { message: 'A quantidade mínima de compra é 1.000 milhas' })
		.max(1000000, { message: 'A quantidade máxima de compra é 1.000.000 milhas' }),
	currencyAmount: z.number().positive({ message: 'O valor de compra deve ser maior que zero' }),
	operationType: OperationTypeEnum,
})

export type operationType = z.infer<typeof OperationTypeEnum>
export type MilesTransaction = z.infer<typeof MilesTransaction>
