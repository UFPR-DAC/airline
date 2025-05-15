import { useEffect, useState } from 'react'
import Tabela from '../../Tabela/tabela'
import { useNavigate } from 'react-router'
import { Flight, FlightResults } from '../../../types/flight'

export default function FuncionarioHome() {
	const navigate = useNavigate()
	const [flights, setFlights] = useState(null)

	useEffect(() => {
		const storedFlights = localStorage.getItem('mockFlights')
		if (storedFlights) {
			setFlights(JSON.parse(storedFlights))
		}
	}, [])

	const colunas = [
		{ label: 'Embarque', value: 'data' },
		{ label: 'Origem', value: 'origem' },
		{ label: 'Destino', value: 'destino' },
		{ label: 'Situação', value: (item: FlightResults) => item.voos[0]?.estado },
	]

	const buttons = [
		{
			body: 'Embarcar',
			class: 'bg-green-500 text-white px-4 py-2 rounded',
			action: (dado: Flight) => ConfirmacaoDeEmbarque(dado),
		},
		{
			body: 'Cancelar voo',
			class: 'bg-red-500 text-white px-4 py-2 rounded',
			action: CancelamentoDoVoo,
		},
		{
			body: 'Realizar voo ',
			class: 'bg-blue-500 text-white px-4 py-2 rounded',
			action: RealizacaoDoVoo,
		},
	]

	function ConfirmacaoDeEmbarque(dado: Flight) {
		navigate('/funcionario/embarque', { state: dado })
	}

	function CancelamentoDoVoo() {
		// Mudar o Status do Voo, mudar somente o status caso o status atual seja "CONFIRMADO"
		// Caso contrário, não fazer nada
	}

	function RealizacaoDoVoo() {
		// Mudar o Status do Voo, mudar somente o status caso o status atual seja "CONFIRMADO"
		// Caso contrário, não fazer nada
	}

	return (
		<>
			<div className="">
				<div className="flex justify-between items-center">
					<h1 className="text-2xl font-bold mb-6 pr-100">Tela inicial funcionário</h1>
				</div>
			</div>
			<div className="w-full max-w-6xl bg-white rounded-lg shadow-md">
				{flights && <Tabela colunas={colunas} dados={flights} buttons={buttons} />}
			</div>
		</>
	)
}
