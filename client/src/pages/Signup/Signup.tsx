import { useForm } from 'react-hook-form'
import { UserSignup } from '../../validations/user'
import { zodResolver } from '@hookform/resolvers/zod'
import { useHookFormMask } from 'use-mask-input'
import { useQuery } from '@tanstack/react-query'
import axios from 'axios'
import { isCepValid, limparMascaraCep } from '../../utils/cep/cep'
import { useEffect, useRef } from 'react'
import { estadosBrasil } from '../../utils/uf/uf'
import { useNavigate } from 'react-router-dom'

export default function Signup() {
	const {
		register,
		handleSubmit,
		watch,
		setValue,
		formState: { errors },
	} = useForm<UserSignup>({
		resolver: zodResolver(UserSignup),
		mode: 'onChange',
	})
	const registerWithMask = useHookFormMask(register)
	const navigate = useNavigate();

	const onSubmit = async (data: UserSignup) => {
		const payload = {
			cpf: data.cpf,
			email: data.email,
			nome: data.nome,
			saldo_milhas: 0,
			endereco: {
				cep: data.cep,
				rua: data.logradouro,
				numero: data.numero,
				complemento: data.complemento,
				bairro: data.bairro,
				cidade: data.cidade,
				uf: data.estado
			}
		}
		const response = await axios.post("http://localhost:3000/clientes", payload);
		alert(`Usuário ${response?.data?.nome} criado!`)
		navigate("/login");
	}

	const cepSujo = watch('cep')
	const inputNumeroRef = useRef<HTMLInputElement>(null)

	const enderecoCorreios = useQuery({
		queryKey: ['dadosCep'],
		queryFn: async () => {
			const cepRequest = limparMascaraCep(watch('cep'))
			const response = await axios.get(`https://viacep.com.br/ws/${cepRequest}/json/`)
			if (response.data.error) throw new Error('CEP não encontrado')
			return response.data
		},
		enabled: false,
		retry: false,
	})

	useEffect(() => {
		const cepLimpo = limparMascaraCep(cepSujo)
		if (cepLimpo && isCepValid(cepLimpo)) {
			enderecoCorreios.refetch()
		}
	}, [cepSujo])

	//popular inputs se CEP ok!
	useEffect(() => {
		if (enderecoCorreios.data) {
			const { logradouro, bairro, localidade, uf } = enderecoCorreios.data
			setValue('logradouro', logradouro || '')
			setValue('bairro', bairro || '')
			setValue('cidade', localidade || '')
			setValue('estado', uf || '')

			setTimeout(() => {
				inputNumeroRef.current?.focus()
			}, 100)
		}
	}, [enderecoCorreios.data])

	if (cepSujo) console.log(watch('cep').length)

	return (
		<div className="flex flex-col items-center justify-center gap-8">
			<h1 className="font-medium">Cadastro</h1>
			<form onSubmit={handleSubmit(onSubmit)} className="flex flex-col w-lg h-100vh gap-4">
				<div className="input-container w-64">
					<label htmlFor="cpf" className="input-label">
						CPF
					</label>
					<input
						id="cpf"
						type="tel"
						{...registerWithMask('cpf', 'cpf', {
							required: true,
						})}
						className="input-base"
					/>
					{errors.cpf && <p className="input-error-msg">{errors.cpf?.message}</p>}
				</div>
				<div className="input-container w-64">
					<label htmlFor="cpf" className="input-label">
						E-mail
					</label>
					<input
						id="email"
						type="email"
						{...registerWithMask('email', 'email', {
							required: true,
						})}
						className="input-base"
					/>
					{errors.email && <p className="input-error-msg">{errors.email?.message}</p>}
				</div>
				<div className="input-container w-84">
					<label htmlFor="nome" className="input-label">
						Nome completo
					</label>
					<input id="nome" type="text" {...register('nome')} className="input-base" />
					{errors.nome && <p className="input-error-msg">{errors.nome?.message}</p>}
				</div>
				<div className="input-container w-40">
					<label htmlFor="cep" className="input-label">
						CEP
					</label>
					<input
						id="cep"
						type="tel"
						{...registerWithMask('cep', ['99999-999'], {
							required: true,
						})}
						className="input-base"
					/>
					{errors.cep && <p className="input-error-msg">{errors.cep?.message}</p>}
				</div>
				<div className="flex flex-row gap-4">
					<div className="input-container w-60">
						<label htmlFor="logradouro" className="input-label">
							Logradouro
						</label>
						<input
							id="logradouro"
							type="text"
							{...register('logradouro')}
							disabled
							className="input-base cursor-not-allowed"
						/>
						{errors.logradouro && <p className="input-error-msg">{errors.logradouro?.message}</p>}
					</div>
					<div className="input-container w-20">
						<label htmlFor="numero" className="input-label">
							Número
						</label>
						<input
							id="numero"
							type="tel"
							{...register('numero')}
							ref={(e) => {
								register('numero').ref(e)
								inputNumeroRef.current = e
							}}
							className="input-base"
						/>
						{errors.numero && <p className="input-error-msg">{errors.numero?.message}</p>}
					</div>
				</div>
				<div className="input-container">
					<label htmlFor="complemento" className="input-label">
						Complemento
					</label>
					<input id="complemento" type="text" {...register('complemento')} className="input-base" />
					{errors.complemento && <p className="input-error-msg">{errors.complemento?.message}</p>}
				</div>
				<div className="input-container w-84">
					<label htmlFor="bairro" className="input-label">
						Bairro
					</label>
					<input
						id="bairro"
						type="text"
						{...register('bairro')}
						disabled
						className="input-base cursor-not-allowed"
					/>
					{errors.bairro && <p className="input-error-msg">{errors.bairro?.message}</p>}
				</div>
				<div className="flex flex-row gap-4">
					<div className="input-container w-84">
						<label htmlFor="cidade" className="input-label">
							Cidade
						</label>
						<input
							id="cidade"
							type="text"
							{...register('cidade')}
							disabled
							className="input-base cursor-not-allowed"
						/>
						{errors.cidade && <p className="input-error-msg">{errors.cidade?.message}</p>}
					</div>
					<div className="input-container w-20 relative">
						<label htmlFor="estado" className="input-label">
							Estado
						</label>
						<select id="estado" {...register('estado')} disabled className="input-base cursor-not-allowed">
							{estadosBrasil &&
								estadosBrasil.map((estado) => <option value={estado.value}>{estado.value}</option>)}
							<div className="pointer-events-none absolute right-2 top-9">▼</div>
						</select>
					</div>
				</div>
				<button
					type="submit"
					className="cursor-pointer w-84 text-white bg-green-600 p-4 rounded-full hover:bg-green-500"
				>
					Cadastrar
				</button>
			</form>
		</div>
	)
}
