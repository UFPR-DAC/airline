import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router'
import { UserSignup } from '../../validations/user'
import { fetchUserByCpf } from '../../services/user'
import { FlightResults } from '../../types/flight'
import Reservation from '../../components/Reservation/Reservation'

export default function Reservations() {
	const navigate = useNavigate()
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
		return (
			<div className="flex flex-col items-center relative min-h-screen w-full gap-8 bg-amber-200 justify-center">
				<p className="text-center text-black">Faça login para visualizar suas reservas</p>
				<button
					className="cursor-pointer w-40 text-white bg-green-600 p-4 rounded-full hover:bg-green-500"
					onClick={() => navigate('/login')}
				>
					Ir para login
				</button>
			</div>
		)
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
