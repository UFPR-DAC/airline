import { useNavigate } from 'react-router'
import { FlightResults } from '../../types/flight'
import { formatFlightTime } from '../../utils/date/calendar'

export default function FlightOption(flight: FlightResults) {
	const navigate = useNavigate()
	const handleSubmit = () => {
		localStorage.setItem('reservation', JSON.stringify(flight))
		alert(`Reserva feita com sucesso!\n${flight.origem} - ${flight.destino}\nVoo ${flight.voos[0].codigo}`)
		navigate('/')
	}

	return (
		<div className="flex w-full gap-8 items-center justify-center bg-white border-2 border-amber-100 p-8 rounded-2xl rounded-tl-none">
			<div className="flex flex-col">
				<p className="text-lg font-semibold">{formatFlightTime(flight.data, 'America/Sao_Paulo')}</p>
				<p className="text-md font-light">{flight.voos[0].aeroporto_origem.codigo}</p>
			</div>
			<div className="flex flex-col">
				<p className="text-lg font-semibold">{formatFlightTime(flight.voos[0].data, 'America/Sao_Paulo')}</p>
				<p className="text-md font-light">{flight.voos[0].aeroporto_destino.codigo}</p>
			</div>
			<div className="flex flex-row">
				<p className="text-lg font-semibold">R$ {flight.voos[0].valor_passagem}</p>
				<button onClick={handleSubmit} className="cursor-pointer w-40 text-white bg-green-600 p-4 rounded-full">
					Reservar assento
				</button>
			</div>
		</div>
	)
}
