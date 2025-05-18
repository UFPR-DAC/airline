import { z } from 'zod'

export const FlightSearchParams = z.object({
	origin: z
		.string()
		.length(3, 'Código de aeroporto inválido')
		.regex(/^[A-Z]+$/, 'Apenas letras'),
	destination: z
		.string()
		.length(3, 'Código de aeroporto inválido')
		.regex(/^[A-Z]+$/, 'Apenas letras'),
	departureDate: z.preprocess(
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
	arrivalDate: z.preprocess(
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
})

export type FlightSearchParams = z.infer<typeof FlightSearchParams>
