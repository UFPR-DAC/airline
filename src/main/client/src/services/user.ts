import { mockUsers } from '../mocks/users'
import { UserSignup } from '../validations/user'

export async function fetchUserByEmail(email: string): Promise<UserSignup | null> {
	await new Promise((res) => setTimeout(res, 500))
	const user = mockUsers.find((u) => u.email === email)
	return user ?? null
}

export async function fetchUserByCpf(cpf: string): Promise<UserSignup | null> {
	await new Promise((res) => setTimeout(res, 500))
	const user = mockUsers.find((u) => u.cpf === cpf)
	return user ?? null
}
