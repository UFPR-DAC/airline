import { useEffect, useState } from 'react'
import { useParams } from 'react-router'
import { UserSignup } from '../../validations/user'
import { fetchUserByCpf } from '../../services/user'

export default function ClientDashboard() {
	const { clientId } = useParams()
	const [user, setUser] = useState<UserSignup | null>(null)

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

	return (
		<>
			<div className="flex flex-col relative min-h-screen w-full bg-amber-200 justify-center">
				<div className="flex flex-col gap-4 items-center">
					<h2>Seus pontos</h2>
					<p className="font-medium text-4xl">{user.saldoMilhas || 0}</p>
				</div>
				<div className="px-80">
					<button className="cursor-pointer text-white bg-green-600 p-4 rounded-full hover:bg-green-500">
						Usar pontos
					</button>
				</div>
			</div>
		</>
	)
}
