import axios from 'axios'

export async function getEnderecoFromCep(cep: string) {
	try {
		const { data } = await axios.get(`https://viacep.com.br/ws/${cep}/json/`, {
			params: {
				cep: '',
			},
		})
		return data
	} catch (e) {
		console.log(e)
		return e
	}
}
