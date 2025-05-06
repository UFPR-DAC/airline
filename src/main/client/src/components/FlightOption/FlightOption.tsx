import { FlightOptionProps } from '../../types/flightOptionProps'

export default function FlightOption(flight: FlightOptionProps) {
	return (
		<div className="flex gap-4 bg-amber-200 p-8 rounded-2xl rounded-tl-none">
			<p>{flight.departureAirport}</p>
			<p>{flight.departureDateUTC.toDateString()}</p>
			<p>{flight.arrivalAirport}</p>
		</div>
	)
}
