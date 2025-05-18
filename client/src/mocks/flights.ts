export const mockFlights = [
	{
		data: '2024-10-13T11:00:00Z-03:00',
		origem: 'CWB',
		destino: 'GRU',
		voos: [
			{
				codigo: 'VOO0001',
				data: '2025-08-10T09:30:00Z-03:00',
				valor_passagem: 250.0,
				quantidade_poltronas_total: 100,
				quantidade_poltronas_ocupadas: 70,
				estado: 'CONFIRMADO',
				aeroporto_origem: {
					codigo: 'CWB',
					nome: 'Aeroporto Internacional de CWB',
					cidade: 'Curitiba',
					uf: 'PR',
				},
				aeroporto_destino: {
					codigo: 'GRU',
					nome: 'Aeroporto Internacional de Guarulhos',
					cidade: 'São Paulo',
					uf: 'SP',
				},
			},
		],
	},
	{
		data: '2024-10-13T16:00:00Z-03:00',
		origem: 'CWB',
		destino: 'GRU',
		voos: [
			{
				codigo: 'VOO0001',
				data: '2025-08-10T10:30:00Z-03:00',
				valor_passagem: 100.0,
				quantidade_poltronas_total: 100,
				quantidade_poltronas_ocupadas: 10,
				estado: 'CONFIRMADO',
				aeroporto_origem: {
					codigo: 'CWB',
					nome: 'Aeroporto Internacional de CWB',
					cidade: 'Curitiba',
					uf: 'PR',
				},
				aeroporto_destino: {
					codigo: 'GRU',
					nome: 'Aeroporto Internacional de Guarulhos',
					cidade: 'São Paulo',
					uf: 'SP',
				},
			},
		],
	},
]
