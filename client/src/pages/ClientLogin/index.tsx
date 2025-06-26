import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { UserLogin } from '../../validations/user'
import { useNavigate } from 'react-router'
import axios from 'axios'

export default function ClientLogin() {
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
		const payload = {
			login: data.email,
			senha: data.senha
		}
		const response = await axios.post("http://localhost:3000/login", payload);
		localStorage.setItem("token", response?.data?.access_token);
		localStorage.setItem("nome", response?.data?.usuario?.nome);
		if (response?.data?.tipo === "CLIENTE") {
			navigate(`/cliente/${response?.data?.usuario?.codigo}`)
		} else if (response?.data?.tipo === "FUNCIONARIO") {
			navigate(`/funcionario/${response?.data?.usuario?.codigo}`)
		}
	}

	function handleNovoCliente() {
		navigate("/cadastro");
	}

	function handleNovoFuncionario() {
		navigate("/funcionario/cadastro")
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
				<button onClick={handleNovoCliente} className='underline cursor-pointer'>Cadastro de novo cliente</button>
				<button onClick={handleNovoFuncionario} className='underline cursor-pointer'>Cadastro de novo funcionário</button>
				<button className="cursor-pointer w-80 text-white bg-green-600 p-4 rounded-full hover:bg-green-500">
					Entrar
				</button>
			</form>
		</div>
	)
}
