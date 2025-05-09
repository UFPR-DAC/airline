import { useNavigate } from 'react-router'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { useAuth } from '../../contexts/AuthContext'
import { useEffect, useRef, useState } from 'react'

export default function Header() {
	const navigate = useNavigate()
	const [showDropdown, setShowDropdown] = useState(false)
	const { user, logout, isAuthenticated } = useAuth()
	const dropdownRef = useRef<HTMLDivElement>(null)
	const firstName = user?.nome?.split(' ')[0] || 'Minha conta'

	const handleClick = () => {
		if (isAuthenticated) {
			setShowDropdown((prev) => !prev)
		} else {
			navigate('/login')
		}
	}

	const handleLogout = () => {
		logout()
		navigate('/')
	}

	useEffect(() => {
		const handleClickOutside = (event: MouseEvent) => {
			if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
				setShowDropdown(false)
			}
		}
		document.addEventListener('mousedown', handleClickOutside)
		return () => {
			document.removeEventListener('mousedown', handleClickOutside)
		}
	}, [])

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
					onClick={() => navigate(`/cliente/${user?.cpf}/reservas`)}
					className={`flex box-border w-60 py-4 px-6 text-center justify-center font-medium rounded-t-2xl cursor-pointer text-black border-b-4 border-white hover:border-green-400`}
				>
					Minhas reservas
				</button>
				<button
					key="signup"
					onClick={() => navigate(`/cadastro`)}
					className={`flex box-border w-60 py-4 px-6 text-center justify-center font-medium rounded-t-2xl cursor-pointer text-black border-b-4 border-white hover:border-green-400`}
				>
					Cadastro
				</button>
			</div>
			<div className="flex">
				<button
					key="login"
					onClick={handleClick}
					className={`flex w-60 py-4 px-6 text-center justify-center items-center gap-4 font-medium rounded-t-2xl cursor-pointer text-black hover:underline`}
				>
					<FontAwesomeIcon icon={['fas', 'user']} />
					{firstName}
				</button>
				{showDropdown && isAuthenticated && (
					<div className="absolute w-32 top-13 right-20 mt-2 bg-white rounded-b-md p-2 z-10">
						<button
							onClick={handleLogout}
							className="w-full text-left px-4 py-2 hover:bg-gray-100 text-sm cursor-pointer"
						>
							Sair
						</button>
					</div>
				)}
			</div>
		</nav>
	)
}
