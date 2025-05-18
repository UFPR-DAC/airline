import { FlightResults } from '../../types/flight'
import { formatFlightTime } from '../../utils/date/calendar'

interface ReservationProps {
	flight: FlightResults
}

export default function Reservation({ flight }: ReservationProps) {
	if (!flight || !flight.voos[0].aeroporto_origem || !flight.voos[0].aeroporto_destino) return <p>Nenhum voo</p>

	return (
		<div className="flex w-full gap-8 items-center justify-center bg-white border-2 border-amber-100 p-8 rounded-2xl rounded-tl-none">
			<div className="flex flex-col">
				<p className="text-lg font-semibold">{formatFlightTime(flight.data, 'America/Sao_Paulo')}</p>
				<p className="text-md font-light">{flight.voos[0].aeroporto_origem.codigo}</p>
			</div>
			<div className="flex flex-col">
				<p className="text-lg font-semibold">{formatFlightTime(flight.data, 'America/Sao_Paulo')}</p>
				<p className="text-md font-light">{flight.voos[0].aeroporto_destino.codigo}</p>
			</div>
			<div className="flex flex-row">
				<p className="text-lg font-semibold">R$ {flight.voos[0].valor_passagem}</p>
			</div>
		</div>
	)
}
