function CadastroComponent() {
	return (
		<div className="min-h-screen flex items-center justify-center">
			<div className="grid-main">
				<form action="">
					<div>
						<label htmlFor="nome" className="block mb-4">
							Nome Completo:
							<input type="text" id="nome" className="input-text mt-2" placeholder="Nome Completo:" />
						</label>
					</div>

					<div>
						<label htmlFor="cpf" className="block mb-4">
							CPF:
							<input type="text" id="cpf" className="input-text mt-2" placeholder="CPF" />
						</label>
					</div>

					<div>
						<label htmlFor="email" className="block mb-4">
							E-mail:
							<input type="text" id="email" className="input-text mt-2" placeholder="E-mail" />
						</label>
					</div>

					<div>
						<label htmlFor="cep" className="block mb-4">
							CEP:
							<input type="text" id="cep" className="input-text mt-2" placeholder="CEP" />
						</label>
					</div>

					<div>
						<label htmlFor="rua" className="block mb-4">
							Rua:
							<input type="text" id="login" className="input-text mt-2" placeholder="Rua" />
						</label>
					</div>

					<div>
						<label htmlFor="login" className="block mb-4">
							Número:
							<input type="text" id="login" className="input-text mt-2" placeholder="Número" />
						</label>
					</div>

					<div>
						<label htmlFor="complemento" className="block mb-4">
							Complemento:
							<input type="text" id="complemento" className="input-text mt-2" placeholder="Complemento" />
						</label>
					</div>

					<div>
						<label htmlFor="cidade" className="block mb-4">
							Cidade:
							<input type="text" id="cidade" className="input-text mt-2" placeholder="Cidade" />
						</label>
					</div>

					<div>
						<label htmlFor="estado" className="block mb-4">
							Estado:
							<input type="text" id="estado" className="input-text mt-2" placeholder="Estado" />
						</label>
					</div>

					<div className="flex justify-between mt-4">
						<button className="button-secondary">Entrar</button>
						<button className="button-primary">Cadastra-se</button>
					</div>
				</form>
			</div>
		</div>
	)
}

export default CadastroComponent
