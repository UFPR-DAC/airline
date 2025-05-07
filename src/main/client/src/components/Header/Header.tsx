import { useNavigate } from 'react-router'

export default function Header() {
	const navigate = useNavigate()

	return (
		<nav className="flex w-full">
			<div className="flex grow">
				<button
					key="home"
					onClick={() => navigate('/')}
					className={`flex w-60 py-4 px-6 text-center justify-center font-medium rounded-t-2xl cursor-pointer text-black`}
				>
					Solis
				</button>
				<button
					key="reservations"
					onClick={() => console.log('reservas!')}
					className={`flex box-border w-60 py-4 px-6 text-center justify-center font-medium rounded-t-2xl cursor-pointer text-black border-b-4 border-white hover:border-green-400`}
				>
					Minhas reservas
				</button>
			</div>
			<button
				key="login"
				onClick={() => navigate('/login')}
				className={`flex w-60 py-4 px-6 text-center justify-center font-medium rounded-t-2xl cursor-pointer text-black hover:underline`}
			>
				Minha conta
			</button>
		</nav>
	)
}
