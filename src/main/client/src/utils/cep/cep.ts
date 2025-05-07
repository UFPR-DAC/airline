export function limparMascaraCep(cep: string) {
	if (cep) {
		return cep.replace(/\D/g, '')
	}
}

export function isCepValid(cep: string) {
	return /^\d{8}$/.test(cep)
}
