import { useForm } from 'react-hook-form'
import { Login, LoginForm } from '../../types/login'
import { zodResolver } from '@hookform/resolvers/zod'

export default function ClientLogin() {
	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm<LoginForm>({
		resolver: zodResolver(Login),
		mode: 'onChange',
	})
	const onSubmit = (data: LoginForm) => console.log(data)

	return (
		<div className="flex flex-col items-center justify-center gap-8">
			<h1 className="font-medium">Login</h1>
			<form onSubmit={handleSubmit(onSubmit)} className="flex flex-col w-lg h-fit items-center gap-4">
				<div className="input-container w-84">
					<label htmlFor="cpf" className="input-label">
						CPF
					</label>
					<input id="cpf" type="tel" {...register('cpf')} className="input-base" />
					{errors.cpf && <p className="input-error-msg">{errors.cpf?.message}</p>}
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
