import { useEffect, useState } from 'react'
import { useParams } from 'react-router'
import { UserSignup } from '../../validations/user'
import { fetchUserByCpf } from '../../services/user'
import { FlightResults } from '../../types/flight'
import Reservation from '../../components/Reservation/reservation'

export default function Reservations() {
	const { clientId } = useParams()
	const [user, setUser] = useState<UserSignup | null>(null)
	const [reservation, setReservation] = useState<FlightResults | null>(null)

	useEffect(() => {
		const storedReservation = localStorage.getItem('reservation')
		if (storedReservation) {
			try {
				const parsed: FlightResults = JSON.parse(storedReservation)
				setReservation(parsed)
			} catch (error) {
				console.error('Failed to parse reservation:', error)
			}
		}
	}, [])

	useEffect(() => {
		console.log('Reservation ', reservation)
	}, [reservation])

	useEffect(() => {
		if (clientId) {
			fetchUserByCpf(clientId).then((data) => {
				if (data) setUser(data)
			})
		}
	}, [clientId])

	if (!user) {
		return <p className="text-center text-black">Carregando...</p>
	}

	if (!reservation) return <p>Nenhuma reserva encontrada</p>

	return (
		<>
			<div className="flex flex-col relative min-h-screen w-full bg-amber-200 justify-center">
				<div className="flex flex-col gap-4 items-center">
					<h2>Minhas reservas</h2>
					<Reservation flight={reservation} />
				</div>
			</div>
		</>
	)
}
