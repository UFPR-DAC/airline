import { UserSignup } from '../validations/user'

export const mockUsers: UserSignup[] = [
	{
		cpf: '123.456.789-10',
		email: 'vivianeendo@msn.com',
		nome: 'Viviane Mori',
		saldoMilhas: 12500,
		cep: '80240-021',
		logradouro: 'Av Silva Jardim',
		numero: '123',
		complemento: '',
		bairro: 'Seminário',
		cidade: 'Curitiba',
		estado: 'PR',
	},
	{
		cpf: '111.111.111-11',
		email: 'rafael@gmail.com',
		nome: 'Rafael Rodrigues Estefanes',
		saldoMilhas: 15000,
		cep: '82840290',
		logradouro: 'Av Da integração',
		numero: '2284',
		complemento: 'Casa',
		bairro: 'Bairro Alto',
		cidade: 'Curitiba',
		estado: 'PR',
	},
]
