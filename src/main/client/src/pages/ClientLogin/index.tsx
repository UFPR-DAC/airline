import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { useAuth } from '../../contexts/AuthContext'
import { UserLogin } from '../../validations/user'
import { useNavigate } from 'react-router'
import { fetchUserByEmail } from '../../services/user'

export default function ClientLogin() {
	const { login } = useAuth()
	const navigate = useNavigate()
	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm<UserLogin>({
		resolver: zodResolver(UserLogin),
		mode: 'onChange',
	})
	const onSubmit = async (data: UserLogin) => {
		const userData = await fetchUserByEmail(data.email)
		if (!userData) {
			alert('Usuário não encontrado!')
			return
		} else {
			console.log(userData)
		}
		login(userData)
		navigate(`/cliente/${userData.cpf}`)
	}

	return (
		<div className="flex flex-col items-center justify-center gap-8">
			<h1 className="font-medium">Login</h1>
			<form onSubmit={handleSubmit(onSubmit)} className="flex flex-col w-lg h-fit items-center gap-4">
				<div className="input-container w-84">
					<label htmlFor="email" className="input-label">
						E-mail
					</label>
					<input id="email" type="text" {...register('email')} className="input-base" />
					{errors.email && <p className="input-error-msg">{errors.email?.message}</p>}
				</div>
				<div className="input-container w-84">
					<label htmlFor="senha" className="input-label">
						Senha
					</label>
					<input id="senha" type="password" {...register('senha')} className="input-base" />
					{errors.senha && <p className="input-error-msg">{errors.senha?.message}</p>}
				</div>
				<button className="cursor-pointer w-80 text-white bg-green-600 p-4 rounded-full hover:bg-green-500">
					Entrar
				</button>
			</form>
		</div>
	)
}
