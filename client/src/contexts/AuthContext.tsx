import { createContext, ReactNode, useContext, useEffect, useState } from 'react'
import { UserLogin, UserSignup } from '../validations/user'
import { z } from 'zod'

export const AuthUserSchema = UserLogin.pick({ email: true })
export type AuthUser = z.infer<typeof AuthUserSchema>

type AuthContextType = {
	user: UserSignup | null
	login: (user: UserSignup) => void
	logout: () => void
	isAuthenticated: boolean
}

const AuthContext = createContext<AuthContextType | undefined>(undefined)

type AuthProviderProps = {
	children: ReactNode
}

export const AuthProvider = ({ children }: AuthProviderProps) => {
	const [user, setUser] = useState<UserSignup | null>(null)

	useEffect(() => {
		const storedUser = localStorage.getItem('user')
		if (storedUser) {
			setUser(JSON.parse(storedUser))
		}
	}, [])

	const login = (user: UserSignup) => {
		localStorage.setItem('user', JSON.stringify(user))
		setUser(user)
	}

	const logout = () => {
		localStorage.removeItem('user')
		setUser(null)
	}

	const isAuthenticated = !!user

	return <AuthContext.Provider value={{ user, login, logout, isAuthenticated }}>{children}</AuthContext.Provider>
}

export const useAuth = (): AuthContextType => {
	const context = useContext(AuthContext)
	if (!context) {
		throw new Error('useAuth must be used within an AuthProvider')
	}
	return context
}
