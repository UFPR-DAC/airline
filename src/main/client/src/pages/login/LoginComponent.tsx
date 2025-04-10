function LoginComponent() {
    return (
        <div className="min-h-screen flex items-center justify-center">
            <div className="grid-main">
                <form action="">
                    <div>
                        <label htmlFor="login" className="block mb-4">Login:
                            <input type="text"
                                id="login"
                                className="input-text mt-2"
                                placeholder="Digite seu login" />
                        </label>
                    </div>

                    <div>
                        <label htmlFor="senha" className="block mb-4">Senha:
                            <input type="password"
                                id="senha"
                                className="input-text mt-2"
                                placeholder="Digite sua senha" />
                        </label>

                    </div>

                    <div className="flex justify-between mt-4">
                        <button className="button-secondary">Cadastrar</button>
                        <button className="button-primary">Entrar</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default LoginComponent;
